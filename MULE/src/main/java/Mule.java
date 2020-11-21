import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Vector;

@ScriptManifest(author = "Belfner", info = "Mule script", name = "Mule", version = 0, logo = "")
public class Mule extends Script
{
    Area home = new Area(3233,3221,3233,3221);
    ArrayList<Task> tasks;
    private GUI gui;
//    ServerInterface s;
//    Vector<Message> messages;

    @Override
    public void onStart()
    {
        log("Let's get started!");
//        s = new ServerInterface("localhost",12345,"mule");
//        s = new ServerInterface("localhost",12345,"newUsers");
//        s.start();
//        messages = s.getMessages();
        try
        {
            SwingUtilities.invokeAndWait(() -> {
                gui = new GUI(this);
                gui.open();
            });
        } catch (InterruptedException | InvocationTargetException e)
        {
            log(e);
            e.printStackTrace();
            stop();
            return;
        }

        // If the user closed the dialog and didn't click the Start button
        if (!gui.isStarted())
        {
            log("Didn't start");
            stop();
            return;
        }
        tasks = gui.getTasks();
        tasks.add(new BankTask(this));
    }

//    public Vector<Message> getMessages()
//    {
//        return messages;
//    }

//    public void sendMessage(Object obj)
//    {
//        s.sendMessage(obj);
//    }

    @Override
    public int onLoop() throws InterruptedException
    {
        tasks.forEach(tasks -> tasks.run());
        return random(2000, 3000);
    }

    @Override
    public void onExit()
    {
        if (gui != null) {
            gui.close();
            log("gui closed");
        }
//        s.shutdown();
        log("Thanks for running my mule!");
    }



}