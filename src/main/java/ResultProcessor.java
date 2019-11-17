import java.util.ArrayList;

public class ResultProcessor {

    private QuestionsAdapter adapter;
    private String result = "";
    private int correct = 0;

    public ResultProcessor(QuestionsAdapter a) {
        adapter = a;

        for (int i = 0; i <= adapter.maxKey(); i++) {
            SelectedChoiceModel select = adapter.getSelected(i);

            String color = "red";
            if (select.Selected().compareTo(select.Answer()) == 0 && select.Selected().length() != 0) {
                color = "green";
                correct++;
            }

            result += "<li style='color: "+color+"'>"+select.Question()+"<br>";
            result += "Your answer: "+select.Selected()+"<br>";
            result += "Correct answer: "+select.Answer()+"<br></li>";
        }
    }

    public String getResultText() {
        return result;
    }

    public String getResultTotal() {
        double total = (double) correct / (adapter.maxKey()+1);
        return "Your score: "+ Math.round(total * 100)+"%";
    }
}
