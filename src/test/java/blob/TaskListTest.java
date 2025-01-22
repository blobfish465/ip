package blob;

import blob.model.Task;
import blob.model.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void addTask_validTask_taskAdded() {
        Task task = new ToDo("Read book");
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
        assertEquals(task, taskList.getTasks().get(0));
    }
}
