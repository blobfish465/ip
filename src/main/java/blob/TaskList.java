package blob;

import blob.model.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    public void markTask(int index) {
        tasks.get(index - 1).markDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index - 1).unmarkDone();
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public int getSize() {
        return tasks.size();
    }
}


