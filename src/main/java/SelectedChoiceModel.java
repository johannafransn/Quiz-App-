/**
 * Model Structure for question, selected choice and actual answer
 */
public class SelectedChoiceModel {
    // instance variables
    private String question;
    private String answer;
    private String choice;

    /**
     * Constructor for objects of class SelectedChoiceModel
     *
     * @param question A string of question
     * @param answer A string of actual answer
     * @param choice A string of selected choice
     */
    public SelectedChoiceModel(String question, String answer, String choice)
    {
        // initialise instance variables
        this.question = question;
        this.answer = answer;
        this.choice = choice;
    }
    /**
     * get Question String from SelectedChoiceModel
     *
     * @return question string
     */
    public String Question() {
        return question;
    }

    /**
     * get Choice String from SelectedChoiceModel
     *
     * @return choice string
     */
    public String Selected() {
        return choice;
    }

    /**
     * get Actualy Answer String from SelectedChoiceModel
     *
     * @return answer string
     */
    public String Answer() {
        return answer;
    }
}
