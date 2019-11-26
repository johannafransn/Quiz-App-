import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;

public class GUI_Home extends JFrame {

    private Theme theme;

    private JPanel middlePanel;

    private JLabel logoField;
    private JLabel textField;

    //Constructor
    public GUI_Home() {
        theme = Theme.getInstance();

        // Panels
        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setBackground(theme.getColor("topPanelColor"));

        JPanel topStatusPanel = new JPanel();
        topStatusPanel.setPreferredSize(new Dimension(500,130));
        topStatusPanel.setOpaque(false);

        middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(500,350));
        middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        middlePanel.setBackground(theme.getColor("middlePanelColor"));

        logoField = new JLabel(theme.resizeIcon("logo_transparent.png", 120, 120), SwingConstants.LEFT);
        logoField.setPreferredSize(new Dimension(100,130));
        logoField.setForeground(theme.getColor("h1Color"));
        logoField.setFont(theme.getFont("h1"));

        textField = new JLabel("<html><body style='padding: 6px'>" +
                "<h2>Grow more with knowledge!</h2>" +
                "By: Tai Huynh, Khoa Tran and Johanna Fransson<br><br>" +
                "Choose an option below to start:</body></html>", SwingConstants.LEFT);
        textField.setPreferredSize(new Dimension(390,130));
        textField.setForeground(theme.getColor("h1Color"));
        textField.setFont(theme.getFont("normalFont"));

        //adding components to contentPane panel
        topStatusPanel.add(logoField);
        topStatusPanel.add(textField);

        getFiles();

        contentPane.add(topStatusPanel, "North");
        contentPane.add(middlePanel, "Center");

        //menu generate method
        //FileMenu fileMenu = new FileMenu();
        //this.setJMenuBar(fileMenu.getMenu());

        //adding panel to JFrame and setting of window position and close operation
        this.add(contentPane);
        this.setTitle("Quizbox");
        this.setSize(500,400);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(false);

        final JFrame thisFrame = this;
    }

    public void getFiles() {
        String[] pathnames;

        File f = new File(theme.getDataDir());

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                return name.endsWith(".json");
            }
        };

        pathnames = f.list(filter);

        // Generate buttons from the files in the data directory
        for (String pathname : pathnames) {
            String name = pathname.replace(".json", "");
            JButton button = new JButton(name);
            button.setBackground(theme.getColor("btnColor"));
            button.setForeground(theme.getColor("fontBtnColor"));
            button.setFont(theme.getFont("normalFont"));
            middlePanel.add(button);

            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    QuestionsAdapter adapter = new QuestionsAdapter(theme.getDataDir() + pathname);
                    JFrame window = new QuestionGUI(adapter, name);
                    JFrame result = new GUI_Home();
                    window.setVisible(true);
                    result.setVisible(false);
                }
            });
        }
    }
}
