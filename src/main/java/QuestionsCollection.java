import java.util.*;
/**
 * Main Collection of QuestionModel and SelectedChoiceModel
 */
public class QuestionsCollection
{
    // Contains list of question model
    private ArrayList<QuestionModel> questions = new ArrayList<QuestionModel>();

    // Contains user's selected choice
    private ArrayList<SelectedChoiceModel> selectedChoice = new ArrayList<SelectedChoiceModel>();

    /**
     * add QuestionModel to ArrayList
     */
    public void add(QuestionModel s) {
        questions.add(s);
    }

    /**
     * Set selected choice to ArrayList by index key
     */
    public void addChoice(SelectedChoiceModel s) {
        selectedChoice.add(s);
    }

    /**
     * Set selected choice to ArrayList by index key
     */
    public void setChoice(int index, SelectedChoiceModel s) {
        selectedChoice.set(index, s);
    }

    /**
     * Get selected choice from ArrayList by index key
     *
     * @param     int index number
     * @return    string
     */
    public SelectedChoiceModel getChoice(int index) {
        return selectedChoice.get(index);
    }

    /**
     * Get selected choice from ArrayList by index key
     *
     * @param     int index number
     * @return    string
     */
    public ArrayList getAllSelecteChoice() {
        ArrayList<SelectedChoiceModel> temp = new ArrayList<SelectedChoiceModel>();
        for (SelectedChoiceModel q : selectedChoice) {
            temp.add(q);
        }
        return temp;
}

    /**
     * Get all question string
     *
     * @return    String of questions in ArrayList
     */
    public ArrayList getAllQuestions() {
        ArrayList<String> temp = new ArrayList<String>();
        for (QuestionModel q : questions) {
            temp.add(q.getQuestionString());
        }
        return temp;
    }

    /**
     * Get answer
     *
     * @return    Answer String
     */
    public String getAnswer(int index) {
        return questions.get(index).getAnswer();
    }

    /**
     * Get question model object
     *
     * @param     int index number
     * @return    QuestionModel
     */
    public QuestionModel getQModel(int index) {

        if (index < questions.size())
            return questions.get(index);

        return null;
    }

    public int maxKey() {
        return questions.size() - 1;
    }

    /**
     * questions Iterator to test collection existance
     */
    public Iterator iterator() {
        return questions.iterator();
    }
}
