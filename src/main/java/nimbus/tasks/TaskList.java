package nimbus.tasks;

import java.util.ArrayList;

/**
 * Represents the list of tasks tracked by the application.
 * Provides methods to manipulate and access the tasks within the list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.taskCount = 0;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * Removes a task from the list.
     *
     * @param task The task to be removed.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
        taskCount--;
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index The zero-based index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Finds all tasks that contain the given keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The string to search for.
     * @return An ArrayList containing all matching Task objects.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String searchKeyword = keyword.toLowerCase();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(searchKeyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

}