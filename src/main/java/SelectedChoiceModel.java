public class SelectedChoiceModel {
    // instance variables
    private String question;
    private String answer;
    private String choice;

    /**
     * Constructor for objects of class database
     */
    public SelectedChoiceModel(String question, String answer, String choice)
    {
        // initialise instance variables
        this.question = question;
        this.answer = answer;
        this.choice = choice;
    }

    public String Question() {
        return question;
    }

    public String Selected() {
        return choice;
    }

    public String Answer() {
        return answer;
    }
}
