public class MuleTask extends Task
{

    private final String username;
    private final String command_str;
    private final int interval;
    private long startTime;

    public MuleTask(Mule api, String username, String command_str, int interval) {
        super(api);
        this.username = username;
        this.command_str  = command_str;
        this.interval = interval;
        startTime = System.currentTimeMillis();
    }

    @Override
    public boolean canProcess() {
        return (System.currentTimeMillis()-startTime)>1000*interval;
    }

    @Override
    public void process() {
//        api.sendMessage(command_str);
        startTime = System.currentTimeMillis();
    }

}