/**
 * Iterate and process the SelectedChoiceModel and producing the result text in HTML format
 */
public class ResultProcessor {

    private QuestionsAdapter adapter;
    private String result = "";
    private int correct = 0;

    /**
     * Constructor for objects of class ResultProcessor
     */
    public ResultProcessor(QuestionsAdapter a) {
        adapter = a;

        for (int i = 0; i <= adapter.maxKey(); i++) {
            SelectedChoiceModel select = adapter.getSelected(i);
            String correctAnswer = select.Answer();


            String Qcss = "red";
            String YAcss = "text-decoration: line-through; color: red;";
            if (select.Selected().compareTo(select.Answer()) == 0 && select.Selected().length() != 0) {
                Qcss = "";
                correct++;
            }


            result += "<li><b style='color: "+Qcss+"'>"+select.Question()+"</b><br>";
            if (Qcss != "") {
                result += "Your answer: <span style='" + YAcss + "'>" + select.Selected() + "</span><br>";
            }
            correctAnswer = "<span style='color: green'>"+select.Answer()+"</span>";
            result += "Correct answer: "+correctAnswer+"<br></li>";
        }
    }
    /**
     * Get the complete result text
     *
     * @return the result conjoined text in HTML format
     */
    public String getResultText() {
        return result;
    }

    /**
     * Get the result score by totaling the correct and wrong answers
     *
     * @return the result score in HTML format
     */
    public String getResultTotal() {
        double total = (double) correct / (adapter.maxKey()+1);
        return "<h1>Your score: "+ Math.round(total * 100)+"%</h1>";
    }
}
