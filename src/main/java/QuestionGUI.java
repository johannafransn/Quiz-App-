import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * Question GUI JFrame
 */
public class QuestionGUI extends JFrame {

    private Theme theme;

    private QuestionModel question;
    private int currentIndex = 0;

    private JLabel questionField;
    private JLabel questionTitleField;
    private JLabel questionStatusField;
    private ButtonGroup bg;
    private JRadioButton choice1;
    private JRadioButton choice2;
    private JRadioButton choice3;
    private JRadioButton choice4;

    private JButton nextQBtn;
    private JButton prevQBtn;

    private QuestionsAdapter adapter;

    /**
     * Constructor for objects of class QuestionGUI
     */
    public QuestionGUI(QuestionsAdapter a, String title) {

        adapter = a;
        question = adapter.getQuestion(currentIndex);

        theme = Theme.getInstance();

        //adding panel to JFrame and setting of window position and close operation
        JPanel contentPane = getPanel(title);
        // Set question and radio button fields
        setFields();
        this.add(contentPane);
        this.setTitle("Questions");
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
     * @param title A String for the Quiz object title
     * @return JPanel GUI
     */
    public JPanel getPanel(String title) {
        final JFrame thisFrame = this;

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));

        contentPane.setBackground(new Color(255,255,255));
        Dimension radioSize = new Dimension(240, 80);

        JPanel topStatusPanel = new JPanel(null);
        //topStatusPanel.setBorder(BorderFactory.createEtchedBorder(1));
        topStatusPanel.setBounds(0,0,500,45);
        topStatusPanel.setBackground(theme.getColor("topPanelColor"));

        JPanel questionPanel = new JPanel(null);
        //questionPanel.setBorder(BorderFactory.createEtchedBorder(1));
        questionPanel.setBounds(0,45,500,115);
        questionPanel.setBackground(theme.getColor("questionPanelColor"));

        JPanel choicesPanel = new JPanel();
        //choicesPanel.setBorder(BorderFactory.createEtchedBorder(1));
        choicesPanel.setBounds(0,160,500,180);
        choicesPanel.setBackground(theme.getColor("choicesPanelColor"));

        JPanel bottomToolBar = new JPanel(null);
        //bottomToolBar.setBorder(BorderFactory.createEtchedBorder(1));
        bottomToolBar.setBounds(0,340,500,60);
        bottomToolBar.setBackground(theme.getColor("bottomPanelColor"));

        nextQBtn = new JButton(""); //Next Question >
        nextQBtn.setBounds(440,10,50,40);
        nextQBtn.setBackground(theme.getColor("btnColor"));
        nextQBtn.setForeground(theme.getColor("fontBtnColor"));
        nextQBtn.setFont(theme.getFont("normalFont"));
        nextQBtn.setBorder(BorderFactory.createEmptyBorder());
        nextQBtn.setContentAreaFilled(false);

        prevQBtn = new JButton("");//< Previous Question
        prevQBtn.setIcon(theme.resizeIcon("arrow_left.png", 50, 40));
        prevQBtn.setBounds(15,10,50,40);
        prevQBtn.setBackground(theme.getColor("btnColor"));
        prevQBtn.setForeground(theme.getColor("fontBtnColor"));
        prevQBtn.setFont(theme.getFont("normalFont"));
        prevQBtn.setBorder(BorderFactory.createEmptyBorder());
        prevQBtn.setContentAreaFilled(false);

        choice1 = new JRadioButton();
        choice1.setPreferredSize(radioSize);
        choice1.setBackground(theme.getColor("choicesPanelColor"));
        choice1.setForeground(theme.getColor("fontColor"));
        choice1.setFont(theme.getFont("choicesFont"));
        choice1.setText("<html><body style=''>Choice1 aaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaa</html>");

        choice2 = new JRadioButton();
        choice2.setBackground(theme.getColor("choicesPanelColor"));
        choice2.setForeground(theme.getColor("fontColor"));
        choice2.setFont(theme.getFont("choicesFont"));
        choice2.setText("<html><body style=''>Choice2 aaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaa</html>");
        choice2.setPreferredSize(radioSize);

        choice3 = new JRadioButton();
        choice3.setBackground(theme.getColor("choicesPanelColor"));
        choice3.setForeground(theme.getColor("fontColor"));
        choice3.setFont(theme.getFont("choicesFont"));
        choice3.setText("<html><body style=''>Choice3 aaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaa</html>");
        choice3.setPreferredSize(radioSize);

        choice4 = new JRadioButton();
        choice4.setBackground(theme.getColor("choicesPanelColor"));
        choice4.setForeground(theme.getColor("fontColor"));
        choice4.setFont(theme.getFont("choicesFont"));
        choice4.setText("Choice4");
        choice4.setPreferredSize(radioSize);

        questionField = new JLabel("<html><div style=''>Question</div></html>", SwingConstants.CENTER);
        questionField.setBounds(0,0,500,115);
        questionField.setForeground(theme.getColor("fontQuestionColor"));
        questionField.setFont(theme.getFont("h1"));

        questionTitleField = new JLabel(title, SwingConstants.LEFT);
        questionTitleField.setBounds(10,0,350,40);
        questionTitleField.setForeground(theme.getColor("fontStatusColor"));
        questionTitleField.setFont(theme.getFont("h1"));

        questionStatusField = new JLabel("Q: 2/10", SwingConstants.RIGHT);
        questionStatusField.setBounds(370,5,120,35);
        questionStatusField.setForeground(theme.getColor("fontStatusColor"));
        questionStatusField.setFont(theme.getFont("normalFont"));

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
        topStatusPanel.add(questionTitleField);
        topStatusPanel.add(questionStatusField);
        contentPane.add(topStatusPanel);

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

        return contentPane;
    }

    /**
     * Using iterator to set actions listener for radios
     * Save the selected radio by creating SelectedChoiceModel object and store into array
     */
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
    /**
     * Set radio selected options to the same state as the SelectedChoiceModel object
     */
    public void retrieveSelectedChoice() {
        Enumeration elements = bg.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton)elements.nextElement();
            if (button.getActionCommand() == adapter.getSelected(currentIndex).Selected()) {
                button.setSelected(true);
            }
        }
    }
    /**
     * Set JPanel JTextField with HTML for formatting purpose
     *
     * @param q A String to override the textfield
     */
    public void setQField(String q) {
        questionField.setText("<html><p style='padding: 10px;'>"+q+"</p><html>");
    }

    /**
     * Set JPanel JRadioButton text field with HTML for formatting purpose
     *
     * @param c JRadioButton object
     * @param q A String to override the textfield
     */
    public void setChoiceField(JRadioButton c, String q) {
        c.setText("<html>"+q+"<html>");
        c.setActionCommand(q);
    }

    /**
     * Clear radios button selections
     * Retrieve selected radio button data and apply the changes
     */
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
            //nextQBtn.setText("Submit");
            nextQBtn.setIcon(theme.resizeIcon("check.png", 50, 40));
        else {
            //nextQBtn.setText(""); //Next Question >
            nextQBtn.setIcon(theme.resizeIcon("arrow_right.png", 50, 40));
        }

        // set Prev Button visibility based on current Index #
        if (currentIndex == 0)
            prevQBtn.setVisible(false);
        else
            prevQBtn.setVisible(true);
        //System.out.println(currentIndex+" = "+adapter.maxKey());
    }
}


