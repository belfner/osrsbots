import org.osbot.rs07.script.MethodProvider;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MuleMenu extends JDialog
{
    private JPanel contentPane;
    private JButton buttonDone;
    private JTextField feedbackTextField;
    private JButton addButton;
    private JTextField usernameField;
    private JTextField commandField;
    private JTextField intervalField;
    private JLabel UsernameLabel;
    private JLabel commandLabel;
    private JLabel intervalLabel;
    private JLabel titleLabel;
    private ArrayList<Task> bots;
    private boolean started;


    public MuleMenu(Mule api)
    {
        bots = new ArrayList<>();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonDone);

        buttonDone.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }

        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                try
                {
                    int interval = Integer.parseInt(intervalField.getText());
                    String username = usernameField.getText();
                    String command = commandField.getText();
                    bots.add(new MuleTask(api,username,command,interval));

                    usernameField.setText("");
                    commandField.setText("");
                    intervalField.setText("");
                    feedbackTextField.setText("");
                }catch (NumberFormatException exc)
                {
                    feedbackTextField.setText("Invalid interval format");
                }

            }
        });
    }

    public boolean isStarted() {
        return started;
    }

    private void onOK()
    {
        started = true;
        dispose();
    }

    private void onCancel()
    {
        // add your code here if necessary
        dispose();
    }

    public ArrayList<Task> getTasks()
    {
        return bots;
    }

//    public static void main(String[] args)
//    {
//        JFrame frame = new JFrame("G");
//        frame.setContentPane(new GUI().contentPane);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }


}
