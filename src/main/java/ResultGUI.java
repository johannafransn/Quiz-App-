import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ResultGUI extends JFrame {

    private Theme theme;

    private JLabel titleField;
    private JLabel textField;

    private JButton nextQBtn;
    private JButton doneBtn;

    private QuestionsAdapter adapter;

    //Constructor
    public ResultGUI(QuestionsAdapter a) {
        adapter = a;

        theme = Theme.getInstance();

        // Panels
        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setBackground(theme.getColor("topPanelColor"));

        JPanel topStatusPanel = new JPanel();
        topStatusPanel.setPreferredSize(new Dimension(500,50));
        topStatusPanel.setOpaque(false);

        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(500,290));
        middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        middlePanel.setBackground(theme.getColor("middlePanelColor"));

        JPanel bottomToolBar = new JPanel();
        bottomToolBar.setPreferredSize(new Dimension(500,60));
        bottomToolBar.setOpaque(false);

        titleField = new JLabel("Result", SwingConstants.CENTER);
        titleField.setForeground(theme.getColor("h1Color"));
        titleField.setFont(theme.getFont("h1"));

        textField = new JLabel("", SwingConstants.LEFT);
        JScrollPane scrollPane = new JScrollPane(textField);
        scrollPane.setPreferredSize(new Dimension(492,282));
        textField.setForeground(theme.getColor("normalColor"));
        textField.setFont(theme.getFont("normalFont"));

        doneBtn = new JButton("Close");
        doneBtn.setBackground(theme.getColor("btnColor"));
        doneBtn.setForeground(theme.getColor("fontBtnColor"));
        doneBtn.setFont(theme.getFont("normalFont"));

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
        this.setTitle("Result");
        this.setSize(500,400);

        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
