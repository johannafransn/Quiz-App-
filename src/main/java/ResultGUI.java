import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ResultGUI extends JFrame {

    private int currentIndex = 0;

    private JLabel titleField;
    private JLabel textField;

    private JButton nextQBtn;
    private JButton doneBtn;

    private QuestionsAdapter adapter;

    //Constructor
    public ResultGUI(QuestionsAdapter a) {
        adapter = a;

        Font normalFont = new Font("sansserif",0,14);
        Font h1 = new Font("sansserif",0,30);

        Color mainColor = new Color(238, 108, 77);
        Color normalColor = new Color(61, 90, 128);
        Color fontStatusColor = new Color(224, 251, 252);
        Color h1Color = mainColor; //new Color(61, 90, 128);
        Color fontBtnColor = new Color(11, 37, 69);

        Color btnColor = new Color(152, 193, 217);
        Color topPanelColor = new Color(61, 90, 128);
        Color middlePanelColor = new Color(224, 251, 252);
        Color choicesPanelColor = new Color(152, 193, 217);
        Color bottomPanelColor = topPanelColor; //new Color(61, 90, 128);

        Dimension radioSize = new Dimension(240, 80);

        // Panels
        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setBackground(topPanelColor);

        JPanel topStatusPanel = new JPanel();
        topStatusPanel.setPreferredSize(new Dimension(500,50));
        topStatusPanel.setOpaque(false);

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(500,290));
        middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        middlePanel.setBackground(middlePanelColor);

        JPanel bottomToolBar = new JPanel();
        bottomToolBar.setPreferredSize(new Dimension(500,60));
        bottomToolBar.setOpaque(false);

        titleField = new JLabel("Result", SwingConstants.CENTER);
        titleField.setForeground(h1Color);
        titleField.setFont(h1);

        textField = new JLabel("", SwingConstants.LEFT);
        JScrollPane scrollPane = new JScrollPane(textField);
        scrollPane.setPreferredSize(new Dimension(492,282));
        textField.setForeground(normalColor);
        textField.setFont(normalFont);

        doneBtn = new JButton("Close");
        doneBtn.setBackground(btnColor);
        doneBtn.setForeground(fontBtnColor);
        doneBtn.setFont(normalFont);

        //adding components to contentPane panel
        topStatusPanel.add(titleField);
        middlePanel.add(scrollPane);
        bottomToolBar.add(doneBtn, "Center");

        contentPane.add(topStatusPanel, "North");
        contentPane.add(middlePanel, "Center");
        contentPane.add(bottomToolBar, "South");


        //menu generate method
        //FileMenu fileMenu = new FileMenu();
        //this.setJMenuBar(fileMenu.getMenu());

        //adding panel to JFrame and setting of window position and close operation
        this.add(contentPane);
        this.setTitle("GUI_project");
        this.setSize(1000,400);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(false);

        final JFrame thisFrame = this;

        this.addComponentListener ( new ComponentAdapter ()
        {
            public void componentShown ( ComponentEvent e )
            {
                fillTextField();
                System.out.println ( "Component shown" );
            }

            public void componentHidden ( ComponentEvent e )
            {
                System.out.println ( "Component hidden" );
            }
        } );

        doneBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                thisFrame.dispose();
            }
        });
    }

    public void fillTextField() {
        ResultProcessor result = new ResultProcessor(adapter);
        String text = result.getResultText();
        String total = result.getResultTotal();
        textField.setText("<html><style> body { width: 360px; } li { padding-bottom: 8px }</style><body><h3><center>"+total+"</center></h3><ol>"+text+"</ol></body></html>");
    }
}
