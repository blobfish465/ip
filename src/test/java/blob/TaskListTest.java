package blob;

import blob.model.Task;
import blob.model.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TaskList class.
 * These tests verify the functionality of adding and managing tasks within the TaskList.
 */
public class TaskListTest {
    private TaskList taskList;

    /**
     * Sets up a new TaskList before each test to ensure test isolation.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests that a valid task can be successfully added to the TaskList.
     * This test ensures that the TaskList correctly adds a task and that
     * the task is stored correctly within the list.
     */
    @Test
    void addTask_validTask_taskAdded() {
        Task task = new ToDo("Read book");
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
        assertEquals(task, taskList.getTasks().get(0));
    }
}
