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

        String userDirectory = System.getProperty("user.dir");
        String projectDirectory = userDirectory + "/src/main/java";
        QuestionsAdapter adapter = new QuestionsAdapter(projectDirectory + "/json151QuizQuestions.json");
        //JFrame result = new ResultGUI(adapter);
        //result.setVisible(true);
        JFrame window = new QuestionGUI(adapter);



    }
} 