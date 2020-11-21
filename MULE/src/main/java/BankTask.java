import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

public class BankTask extends Task
{

    public BankTask(Mule api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return api.getInventory().isFull();
    }

    @Override
    public void process() {
        walkToClosestBank();
        Sleep.sleepUntil(() -> inBankArea(), 60000);
        try
        {
            api.getBank().open();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Sleep.sleepUntil(() -> api.getBank().isOpen(), 10000);
        api.getBank().depositAll();
        api.getWalking().webWalk(api.home);
    }

    private final Area[] BANKS = { Banks.VARROCK_WEST, Banks.VARROCK_EAST,Banks.LUMBRIDGE_UPPER}; // add the rest

    private boolean inBankArea()
    {
        for(int x = 0; x< BANKS.length;x++)
        {
            if(BANKS[x].contains(api.myPosition()))
            {
                return true;
            }
        }
        return false;
    }

    private void walkToClosestBank(){
        api.getWalking().webWalk(BANKS);
    }

}
