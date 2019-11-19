import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class QuestionGUI extends JFrame {

    private QuestionModel question;
    private int currentIndex = 0;

    private JLabel questionField;
    private JLabel questionStatusField;
    private ButtonGroup bg;
    private JRadioButton choice1;
    private JRadioButton choice2;
    private JRadioButton choice3;
    private JRadioButton choice4;

    private JButton nextQBtn;
    private JButton prevQBtn;

    private QuestionsAdapter adapter;

    //Constructor 
    public QuestionGUI(QuestionsAdapter a) {

        adapter = a;
        question = adapter.getQuestion(currentIndex);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(255,255,255));

        Font normalFont = new Font("sansserif",0,20);
        Font questionFont = new Font("sansserif",0,30);
        Font choicesFont = new Font("sansserif",0,16);
        
        Color mainColor = new Color(238, 108, 77);
        Color fontColor = new Color(61, 90, 128);
        Color fontStatusColor = new Color(224, 251, 252);
        Color fontQuestionColor = mainColor; //new Color(61, 90, 128);
        Color fontBtnColor = new Color(11, 37, 69);
        
        Color btnColor = new Color(152, 193, 217);
        Color topPanelColor = new Color(61, 90, 128);
        Color questionPanelColor = new Color(224, 251, 252);
        Color choicesPanelColor = new Color(152, 193, 217);
        Color bottomPanelColor = topPanelColor; //new Color(61, 90, 128);

        Dimension radioSize = new Dimension(240, 80);

        JPanel topStatusPanel = new JPanel(null);
        topStatusPanel.setBorder(BorderFactory.createEtchedBorder(1));
        topStatusPanel.setBounds(0,0,500,45);
        topStatusPanel.setBackground(topPanelColor);

        JPanel questionPanel = new JPanel(null);
        questionPanel.setBorder(BorderFactory.createEtchedBorder(1));
        questionPanel.setBounds(0,45,500,115);
        questionPanel.setBackground(questionPanelColor);

        JPanel choicesPanel = new JPanel();
        choicesPanel.setBorder(BorderFactory.createEtchedBorder(1));
        choicesPanel.setBounds(0,160,500,180);
        choicesPanel.setBackground(choicesPanelColor);

        JPanel bottomToolBar = new JPanel(null);
        bottomToolBar.setBorder(BorderFactory.createEtchedBorder(1));
        bottomToolBar.setBounds(0,340,500,60);
        bottomToolBar.setBackground(bottomPanelColor);

        nextQBtn = new JButton("Next Question >");
        nextQBtn.setBounds(295,15,190,35);
        nextQBtn.setBackground(btnColor);
        nextQBtn.setForeground(fontBtnColor);
        nextQBtn.setFont(normalFont);

        prevQBtn = new JButton("< Previous Question");
        prevQBtn.setBounds(15,15,230,35);
        prevQBtn.setBackground(btnColor);
        prevQBtn.setForeground(fontBtnColor);
        prevQBtn.setFont(normalFont);

        choice1 = new JRadioButton();
        choice1.setPreferredSize(radioSize);
        //choice1.setBounds(5,3,250,90);
        choice1.setBackground(choicesPanelColor);
        choice1.setForeground(fontColor);
        choice1.setFont(choicesFont);
        choice1.setText("<html><body style=''>Choice1 aaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaa</html>");

        choice2 = new JRadioButton();
        //choice2.setBounds(260,3,230,90);
        choice2.setBackground(choicesPanelColor);
        choice2.setForeground(fontColor);
        choice2.setFont(choicesFont);
        choice2.setText("<html><body style=''>Choice2 aaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaa</html>");
        choice2.setPreferredSize(radioSize);

        choice3 = new JRadioButton();
        //choice3.setBounds(5,88,250,90);
        choice3.setBackground(choicesPanelColor);
        choice3.setForeground(fontColor);
        choice3.setFont(choicesFont);
        choice3.setText("<html><body style=''>Choice3 aaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaa</html>");
        choice3.setPreferredSize(radioSize);

        choice4 = new JRadioButton();
        //choice4.setBounds(260,88,230,90);
        choice4.setBackground(choicesPanelColor);
        choice4.setForeground(fontColor);
        choice4.setFont(choicesFont);
        choice4.setText("Choice4");
        choice4.setPreferredSize(radioSize);

        questionField = new JLabel("<html><div style=''>Question</div></html>", SwingConstants.CENTER);
        questionField.setBounds(0,0,500,115);
        questionField.setForeground(fontQuestionColor);
        questionField.setFont(questionFont);

        questionStatusField = new JLabel("Q: 2/10", SwingConstants.RIGHT);
        questionStatusField.setBounds(400,5,80,35);
        questionStatusField.setForeground(fontStatusColor);
        questionStatusField.setFont(normalFont);
        
        // Radio button grouping
        bg = new ButtonGroup();
        bg.add(choice1);
        bg.add(choice2);
        bg.add(choice3);
        bg.add(choice4);

        //adding components to contentPane panel
        contentPane.add(bottomToolBar);
        choicesPanel.add(choice1);
        choicesPanel.add(choice2);
        choicesPanel.add(choice3);
        choicesPanel.add(choice4);
        contentPane.add(choicesPanel);
        bottomToolBar.add(nextQBtn);
        bottomToolBar.add(prevQBtn);
        questionPanel.add(questionField);
        contentPane.add(questionPanel);
        topStatusPanel.add(questionStatusField);
        contentPane.add(topStatusPanel);

        //menu generate method
        FileMenu fileMenu = new FileMenu();
        this.setJMenuBar(fileMenu.getMenu());

        //adding panel to JFrame and setting of window position and close operation
        this.add(contentPane);
        this.setTitle("Questions");
        this.setSize(500,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        final JFrame thisFrame = this;
        // Set question and radio button fields
        setFields();

        nextQBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < adapter.maxKey()) {
                    saveChoice();
                    currentIndex++;
                    question = adapter.getQuestion(currentIndex);
                    setFields();

                }else{
                    saveChoice();
                    currentIndex++;
                }

                if (currentIndex > adapter.maxKey()) {
                    JFrame result = new ResultGUI(adapter);
                    result.setVisible(true);
                    thisFrame.dispose();
                }
            }
        });

        prevQBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex > 0) {
                    saveChoice();
                    currentIndex--;
                    question = adapter.getQuestion(currentIndex);
                    setFields();
                }
            }
        });
    }

    public void saveChoice() {
        Enumeration elements = bg.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton)elements.nextElement();
            if (button.isSelected()) {
                adapter.saveChoice(currentIndex,
                        new SelectedChoiceModel(
                                question.getQuestionString(),
                                question.getAnswer(),
                                button.getActionCommand()
                        )
                );
            }
        }
    }

    public void retrieveSelectedChoice() {
        Enumeration elements = bg.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton)elements.nextElement();
            if (button.getActionCommand() == adapter.getSelected(currentIndex).Selected()) {
                button.setSelected(true);
            }
        }
    }

    public void setQField(String q) {
        questionField.setText("<html><p style='padding: 10px;'>"+q+"</p><html>");
    }

    public void setChoiceField(JRadioButton c, String q) {
        c.setText("<html>"+q+"<html>");
        c.setActionCommand(q);
    }

    public void setFields() {
        setQField(question.getQuestionString());
        setChoiceField(choice1, question.getChoice1());
        setChoiceField(choice2, question.getChoice2());
        setChoiceField(choice3, question.getChoice3());
        setChoiceField(choice4, question.getChoice4());

        questionStatusField.setText("Q: "+( currentIndex+1 )+"/"+( adapter.maxKey()+1 ));

        bg.clearSelection();
        retrieveSelectedChoice();

        // set Next Button Text based on current Index #
        if (currentIndex == adapter.maxKey())
            nextQBtn.setText("Submit");
        else
            nextQBtn.setText("Next Question >");

        // set Prev Button visibility based on current Index #
        if (currentIndex == 0)
            prevQBtn.setVisible(false);
        else
            prevQBtn.setVisible(true);
        System.out.println(currentIndex+" = "+adapter.maxKey());
    }


}


