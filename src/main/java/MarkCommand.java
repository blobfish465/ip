public class MarkCommand implements Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        task.markDone();
        ui.showTaskMarked(task);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
