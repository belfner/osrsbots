import java.util.ArrayList;

public class GUI
{
    private final MuleMenu mainDialog;

    private boolean started;

    public GUI(Mule api) {
        mainDialog = new MuleMenu(api);
        mainDialog.pack();
    }

    public boolean isStarted() {
        return mainDialog.isStarted();
    }


    public void open() {
        mainDialog.setVisible(true);
    }

    public void close() {
        mainDialog.setVisible(false);
        mainDialog.dispose();
    }

    public ArrayList<Task> getTasks()
    {
        return mainDialog.getTasks();
    }
}
