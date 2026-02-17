package nimbus.tasks;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.taskCount = 0;
    }

    public void addTask(Task t) {
        tasks.add(t);
        taskCount++;
    }

    public void removeTask(Task t) {
        tasks.remove(t);
        taskCount--;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return taskCount;
    }

}