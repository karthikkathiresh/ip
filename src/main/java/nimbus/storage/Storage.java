package nimbus.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Deadline;
import nimbus.tasks.Event;
import nimbus.tasks.Task;
import nimbus.tasks.TaskList;
import nimbus.tasks.ToDo;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws NimbusException, FileNotFoundException {
        TaskList loadedTasks = new TaskList();
        File f = new File(filePath);

        try {
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("No existing file found. Created a new one at data/nimbus.txt!");
                return loadedTasks;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while setting up the save file: " + e.getMessage());
        }

        try {
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String line = s.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");
                String taskType = data[0].trim();
                boolean isDone = data[1].trim().equals("1");
                String description = data[2].trim();

                insertTask(loadedTasks, taskType, description, isDone, data);
            }

            s.close();
        } catch (FileNotFoundException e) {
            throw new NimbusException("File not found.");
        }

        return loadedTasks;
    }

    public void save(TaskList taskList) throws NimbusException {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < taskList.getTaskCount(); i++) {
                Task t = taskList.getTask(i);
                String textToAdd = t.toSaveFormat();
                fw.write(textToAdd + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new NimbusException("An error occurred while saving to the file: " + e.getMessage());
        }
    }

    static void insertTask(TaskList taskList, String taskType, String description, boolean isDone, String[] data) {
        switch (taskType) {
        case "T":
            insertToDo(taskList, description, isDone);
            break;

        case "D":
            if (data.length >= 4) {
                insertDeadline(taskList, data, description, isDone);
            } else {
                System.out.println("Warning: A deadline in the save file is missing its date and was skipped.");
            }
            break;

        case "E":
            if (data.length >= 4) {
                insertEvent(taskList, data, description, isDone);
            } else {
                System.out.println("Warning: An event in the save file is missing its time frame and was skipped.");
            }
            break;

        default:
            System.out.println("Warning: Unknown task type '" + taskType + "' found in save file. Skipping.");
        }
    }

    private static void insertEvent(TaskList taskList, String[] data, String description, boolean isDone) {
        String timeInfo = data[3].trim();
        int index = timeInfo.indexOf("-");
        String from = timeInfo.substring(0, index);
        String to = timeInfo.substring(index + 1);
        Event e = new Event(description, from, to);
        e.setIsDone(isDone);
        taskList.addTask(e);
    }

    private static void insertDeadline(TaskList taskList, String[] data, String description, boolean isDone) {
        String by = data[3].trim();
        Deadline d = new Deadline(description, by);
        d.setIsDone(isDone);
        taskList.addTask(d);
    }

    private static void insertToDo(TaskList taskList, String description, boolean isDone) {
        ToDo t = new ToDo(description);
        t.setIsDone(isDone);
        taskList.addTask(t);
    }
}
