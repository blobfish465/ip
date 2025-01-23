package blob;

import blob.model.Task;

import java.util.ArrayList;
import java.util.List;

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

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds tasks containing the specified word in their descriptions.
     *
     * @param word The keyword to search for in task descriptions.
     * @return A list of strings representing the matching tasks with their indices.
     */
    public List<String> findTasks(String word) {
        List<String> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(word)) {
                matchingTasks.add((i + 1) + "." + task.toString());
            }
        }
        return matchingTasks;
    }
}


