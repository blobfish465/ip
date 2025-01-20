public class UnmarkCommand implements Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        task.unmarkDone();
        ui.showTaskUnmarked(task);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
