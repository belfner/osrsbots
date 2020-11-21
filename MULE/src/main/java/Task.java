public abstract class Task
{

    protected Mule api;

    public Task(Mule api) {
        this.api = api;
    }

    public abstract boolean canProcess();

    public abstract void process();

    public void run() {
        if (canProcess())
            process();
    }
} 