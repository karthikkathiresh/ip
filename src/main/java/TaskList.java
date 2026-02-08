public class TaskList {

    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public int getTaskCount() {
        return taskCount;
    }

}