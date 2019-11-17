import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


/**
 * Generate Questions Model based on JSON data
 *
 */
public class QuestionsGenerator
{
    // This is where you set max number of questions
    // Make sure this number is less than the actual question in JSON 
    //    otherwise it will throw error
    private int maxQuestions = 10;

    /**
     * Read JSON file into ArrayList and return iterator
     *
     * @return    iterator of QuestionsCollection
     */
    public QuestionsCollection generateQuestions(String jsonFilePath)
    {
        QuestionsCollection question = new QuestionsCollection();
        
        try {
            // parsing file "JSONExample.json" 
            JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader(jsonFilePath));

             
            // getting questions
            JSONArray questionsJSON = (JSONArray) obj.get("questions"); 
            
            Iterator <Object> iterator = questionsJSON.iterator();
            
            ArrayList<JSONObject> tempList = new ArrayList<JSONObject>();
            
            while (iterator.hasNext()) {
                JSONObject object = (JSONObject) iterator.next();
                tempList.add(object);
            }
            
            Collections.shuffle(tempList);
            
            // Add QuestionModel to collection, only uses maxQuestions (top 3 for now)  
           if (maxQuestions <= tempList.size())
            {
                for(int i = 0; i < maxQuestions; i++)
                {
                    JSONObject object = (JSONObject) tempList.get(i);
                    question.add(
                        new QuestionModel(
                        (String) object.get("q"),
                        (String) object.get("ans"),
                        (String) object.get("a"),
                        (String) object.get("b"),
                        (String) object.get("c"),
                        (String) object.get("d")
                        )
                    );

                    // Create Empty SelectedChoiceModel
                    question.addChoice(new SelectedChoiceModel("","",""));

                    //System.out.println("Added question #"+i);
                 }



            }else{
                    System.out.println("You messed up! You need to set QuestionsGenerator.maxQuestions to less than the JSON questions count");
            }

        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return question;
    }
}
