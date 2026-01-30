public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public void setIsDone(boolean bool) {
        this.isDone = bool;
    }

    public String getStatusIcon() {
        return (this.isDone) ? "[X]" : "[]";
    }
}