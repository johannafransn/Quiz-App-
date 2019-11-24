 

import java.util.*;
/**
 * Questions Adapter
 *  use interface to get QuestionModel
 *  
 */
public class QuestionsAdapter implements BackEnd
{
    // instance variables - replace the example below with your own
    private QuestionsGenerator questionsGenerator;
    private QuestionsCollection questions;

    /**
     * Constructor for objects of class QuestionsAdapter
     */
    public QuestionsAdapter(String n)
    {
        // initialise instance variables
        questionsGenerator = new QuestionsGenerator();
	    questions = questionsGenerator.generateQuestions(n);
    }

    /**
     * Get questions from Questions Generator Class
     * it contains Questions collection
     *
     * @param     int index
     * @return    QuestionModel
     */
    public QuestionModel getQuestion(int index)
    {
        // put your code here
        return questions.getQModel(index);
    }

    /**
     * get collection max length
     */
    public int maxKey() {
        return questions.maxKey();
    }

    /**
     * Save selected choice key to collection
     */
    public void saveChoice(int index, SelectedChoiceModel s) {
        questions.setChoice(index, s);
    }

    /**
     * Get selected choice from Collection by index key
     *
     * @param     int index number
     * @return    string
     */
    public SelectedChoiceModel getSelected(int index) {
        return questions.getChoice(index);
    }

    /**
     * Get all string questions from collection
     */
    public ArrayList getAllQuestions() {
        return questions.getAllQuestions();
    }

    /**
     * Get all string questions from collection
     */
    public ArrayList getAllSelecteChoice() {
        return questions.getAllSelecteChoice();
    }

    /**
     * Troubleshooting QuestionsCollection
     *
     * @param     Iterator from getQuestions()
     * @return    print out of all objects in QuestionsCollection
     */
    public void printAll() {
        Iterator i = questions.iterator();
        
        while(i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
