import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Result GUI JFrame
 */
public class ResultGUI extends JFrame {

    private Theme theme;
    private JLabel titleField;
    private JLabel textField;

    private QuestionsAdapter adapter;

    /**
     * Constructor for objects of class ResultGUI
     */
    public ResultGUI(QuestionsAdapter a) {
        adapter = a;

        theme = Theme.getInstance();

        //adding panel to JFrame and setting of window position and close operation
        JPanel contentPane = getPanel();
        this.add(contentPane);
        this.setTitle("Result");
        this.setSize(500,400);

        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(false);
    }

    /**
     * Get JPanel GUI and set actions listener for radios and buttons
     *
     * @return JPanel GUI
     */
    public JPanel getPanel() {
        // Panels
        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setBackground(theme.getColor("topPanelColor"));

        JPanel topStatusPanel = new JPanel();
        topStatusPanel.setPreferredSize(new Dimension(500,50));
        topStatusPanel.setOpaque(false);

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(500,350));
        middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        middlePanel.setBackground(theme.getColor("middlePanelColor"));

        titleField = new JLabel("Result", SwingConstants.CENTER);
        titleField.setForeground(theme.getColor("h1Color"));
        titleField.setFont(theme.getFont("h1"));

        textField = new JLabel("", SwingConstants.LEFT);
        JScrollPane scrollPane = new JScrollPane(textField);
        scrollPane.setPreferredSize(new Dimension(492,330));
        textField.setForeground(theme.getColor("normalColor"));
        textField.setFont(theme.getFont("normalFont"));

        //adding components to contentPane panel
        topStatusPanel.add(titleField);
        middlePanel.add(scrollPane);

        contentPane.add(topStatusPanel, "North");
        contentPane.add(middlePanel, "Center");

        final JFrame thisFrame = this;

        this.addComponentListener(new ComponentAdapter () {
            public void componentShown (ComponentEvent e) {
                fillTextField();
                //System.out.println ("Component shown");
            }

            public void componentHidden (ComponentEvent e) {
                //System.out.println ("Component hidden");
            }
        });

        return contentPane;
    }

    /**
     * Call the ResultProcessor to iterate and process the SelectedChoiceModel
     * then set result text
     */
    public void fillTextField() {
        ResultProcessor result = new ResultProcessor(adapter);
        String text = result.getResultText();
        String total = result.getResultTotal();
        textField.setText("<html><style> body { width: 360px; } li { padding-bottom: 8px }</style><body><h3><center>"+total+"</center></h3><ol>"+text+"</ol></body></html>");
    }
}
