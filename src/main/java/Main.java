import javax.swing.*;
/**
 * Quiz Application
 *
 * @author Tai Huynh, Khoa Tran, Johanna Fransson
 * @version 20191107
 */

public class Main
{

    public static void main(String[] args) throws Exception
    {
        JFrame home = new GUI_Home();
        home.setVisible(true);

        //String userDirectory = System.getProperty("user.dir");
        //String projectDirectory = userDirectory + "/src/main/java/data";
        //QuestionsAdapter adapter = new QuestionsAdapter(projectDirectory + "/json151QuizQuestions.json");
        //JFrame window = new QuestionGUI(adapter);
        //result.setVisible(true);
        //JFrame result = new GUI_Home();
    }
} 