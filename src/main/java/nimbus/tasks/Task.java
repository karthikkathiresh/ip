package nimbus.tasks;

/**
 * Represents a generic task. A <code>Task</code> object corresponds to
 * a basic task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * By default, a new task is not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone true if the task is completed, false otherwise.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public Boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon indicating whether the task is done.
     *
     * @return "[X]" if the task is done, "[ ]" if it is not done.
     */
    public String getStatusIcon() {
        return (this.isDone) ? "[X]" : "[ ]";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string containing the status icon and description of the task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Returns the string format of the task used for saving to a file.
     * This method is meant to be overridden by subclasses.
     *
     * @return An empty string for the generic Task class.
     */
    public String toSaveFormat() {
        return "";
    }
}