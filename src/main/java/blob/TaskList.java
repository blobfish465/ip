package blob;

import blob.model.Task;

import java.util.ArrayList;

/**
 * Manages a list of tasks. Provides methods to manipulate tasks such as adding, deleting, and marking tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with a list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList containing all the tasks in the list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on its position.
     *
     * @param index The 1-based index of the task to be removed.
     * @return The Task that was removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The 1-based index of the task to retrieve.
     * @return The Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total number of tasks currently in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}


