package nimbus.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import nimbus.tasks.Task;
import nimbus.tasks.TaskList;

public class FileSaver {
    public static void write(File f, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(f);

        for (int i = 0; i < taskList.getTaskCount(); i++) {
            Task t = taskList.getTask(i);
            String textToAdd = t.toSaveFormat();
            fw.write(textToAdd + System.lineSeparator());
        }

        fw.close();
    }
}
