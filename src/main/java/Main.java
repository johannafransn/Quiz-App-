import javax.swing.*;
/**
 * Quiz Application
 *
 * @use json-simlpe-1.11.jar
 * @author Tai Huynh, Khoa Tran, and Johanna Fransson
 * @version 20191107
 */
public class Main
{
    /**
     * Display the main application GUI interface.
     * @param args A string array containing
     * the command line arguments.
     * @exception Any exception
     * @return No return value.
     */
    public static void main(String[] args) throws Exception
    {
        JFrame home = new GUI_Home();
        home.pack();
        home.setVisible(true);
    }
} 