import javax.swing.*;
/**
 * Java FileMenu (currently not using)
 */
public class FileMenu {
    private JMenuBar menuBar;

    //method for generate menu
    public FileMenu() {
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu catalogs = new JMenu("Catalogs");
        JMenu help = new JMenu("Help");

        JMenuItem open = new JMenuItem("Open   ");
        JMenuItem save = new JMenuItem("Save   ");
        JMenuItem exit = new JMenuItem("Exit   ");
        JMenuItem cs151 = new JMenuItem("CS 151   ");
        JMenuItem history = new JMenuItem("World History  ");

        JMenuItem about = new JMenuItem("About   ");


        //file.add(open);
        //file.add(save);
        file.addSeparator();
        file.add(exit);
        catalogs.add(cs151);
        catalogs.add(history);

        help.add(about);

        menuBar.add(file);
        menuBar.add(catalogs);
        menuBar.add(help);
    }

    public JMenuBar getMenu() {
        return menuBar;
    }
}
