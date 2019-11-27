import java.util.*;
/**
 * Questions Adapter, use BackEnd interface to get QuestionModel
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
     * @param index The position in the ArrayList questions
     * @return QuestionModel
     */
    public QuestionModel getQuestion(int index)
    {
        // put your code here
        return questions.getQModel(index);
    }

    /**
     * get collection max length
     *
     * @return integer Max Key Value
     */
    public int maxKey() {
        return questions.maxKey();
    }

    /**
     * Save selected radio's key to the Answers Array
     *
     * @param index The position in the ArrayList questions
     * @param s The SelectedChoiceModel
     */
    public void saveChoice(int index, SelectedChoiceModel s) {
        questions.setChoice(index, s);
    }

    /**
     * Get Selected Choice Model from Collection by index key
     *
     * @param index The position in the ArrayList questions
     * @return The SelectedChoiceModel
     */
    public SelectedChoiceModel getSelected(int index) {
        return questions.getChoice(index);
    }

    /**
     * Get all string questions from collection
     *
     * @return ArrayList of questions
     */
    public ArrayList getAllQuestions() {
        return questions.getAllQuestions();
    }

    /**
     * Used for troubleshooting questionsCollection to see if the collection is not empty
     *
     * @return print out of all objects in QuestionsCollection
     */
    public void printAll() {
        Iterator i = questions.iterator();
        
        while(i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
