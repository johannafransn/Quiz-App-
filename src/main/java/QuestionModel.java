/**
 * Model Structure for questions, choices and correct answer
 */
public class QuestionModel
{
    // instance variables
    private String question;
    private String answer;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    /**
     * Constructor for objects of class database
     */
    public QuestionModel(String q, String a, String c1, String c2, String c3, String c4)
    {
        // initialise instance variables
        this.question = q;
        this.answer = a;
        this.choice1 = c1;
        this.choice2 = c2;
        this.choice3 = c3;
        this.choice4 = c4;
    }
    
    public String getQuestionString() {
        return question;
    }
    
    public String getChoice1() {
        return choice1;
    }
    
    public String getChoice2() {
        return choice2;
    }
    
    public String getChoice3() {
        return choice3;
    }
    
    public String getChoice4() {
        return choice4;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    @Override
    public String toString() {
        return this.question
            +"\t Answer is "+this.answer;
    }
}
