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
import java.util.concurrent.*;

/**
 * Generate questions based on JSON data using multithreaded cpu processing
 * And contains the Model object
 */
public class QuestionsGenerator
{
    // This is where you set max number of questions
    // Make sure this number is less than the actual question in JSON 
    // otherwise it will throw error
    private int maxQuestions = 4;
    private QuestionsCollection question;

    /**
     * Read JSON file and convert into an ArrayList then return QuestionsCollection ArrayList
     *
     * @param jsonFilePath A string to the file's location
     * @exception FileNotFoundException, IOException, or ParseException
     * @return QuestionsCollection
     */
    public QuestionsCollection generateQuestions(String jsonFilePath)
    {
        question = new QuestionsCollection();
        
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
                int halfNumberQ = (int) (maxQuestions/2);
                Runnable r1 = new MultiThreadQGen(0, halfNumberQ, tempList, question);
                Runnable r2 = new MultiThreadQGen(halfNumberQ, maxQuestions, tempList, question);

                ExecutorService service = Executors.newCachedThreadPool();
                service.execute(r1);
                service.execute(r2);
                service.shutdown();

                /*for(int i = 0; i < maxQuestions; i++)
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
                 }*/
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
