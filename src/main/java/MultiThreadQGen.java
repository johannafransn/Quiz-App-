import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Multithread Generate questions
 */
public class MultiThreadQGen implements Runnable {
    private int min;
    private int max;
    private ArrayList<String> answersKey;
    private ArrayList<JSONObject> object;
    private QuestionsCollection pointer;
    /**
     * Constructor for objects of class Counter
     */
    public MultiThreadQGen(int minNum, int maxNum, ArrayList<JSONObject> obj, QuestionsCollection p)
    {
        // initialise instance variables
        this.min = minNum;
        this.max = maxNum;
        this.object = obj;
        this.pointer = p;

        answersKey = new ArrayList<String>();
        answersKey.add("a");
        answersKey.add("b");
        answersKey.add("c");
        answersKey.add("d");
    }

    /**
     * Override Runnable class
     */
    @Override
    public void run()
    {
        //System.out.println("min: " + min+ " | max: "+max);
        Collections.shuffle(answersKey);

        for (int i = min; i < max; i++) {
            JSONObject obj = (JSONObject) object.get(i);
            pointer.add(
                    new QuestionModel(
                            (String) obj.get("q"),
                            (String) obj.get("ans"),
                            (String) obj.get(answersKey.get(0)),
                            (String) obj.get(answersKey.get(1)),
                            (String) obj.get(answersKey.get(2)),
                            (String) obj.get(answersKey.get(3))
                    )
            );

            // Create Empty SelectedChoiceModel
            pointer.addChoice(new SelectedChoiceModel("","",""));
            //System.out.print(i+" ");
        }
        //System.out.println("");
    }
}
