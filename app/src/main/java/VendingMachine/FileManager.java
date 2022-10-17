package VendingMachine;

import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class FileManager {

    public JSONObject json;

    public FileManager(){
        Object obj = null;
        try{
            obj = new JSONParser().parse(new FileReader(new File("app/src/main/java/VendingMachine/food.json")));  
        }catch(FileNotFoundException e){
            System.err.println("No file for storage found!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.json = (JSONObject) obj;
    }

    public void update(){
        Object obj = null;
        try{
            obj = new JSONParser().parse(new FileReader("food.json"));  
        }catch(FileNotFoundException e){
            System.err.println("No file for storage found!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.json = (JSONObject) obj;
    }

    public HashMap<String, Double[]> lsDrinks(){
        HashMap<String, Double[]> output = new HashMap<>();
        JSONObject drinks = (JSONObject) this.json.get("Drinks");
        Iterator<Map.Entry> itr = drinks.entrySet().iterator(); 
        while (itr.hasNext()) { 
            Map.Entry pair = itr.next();
            JSONObject value = (JSONObject) pair.getValue();
            Double[] num = new Double[]{(Double) value.get("price"), (Double) value.get("quantity")};
            output.put((String) pair.getKey(), num);
        } 
        return output;
    }

    public HashMap<String, Double[]> lsChocolates(){
        HashMap<String, Double[]> output = new HashMap<>();
        JSONObject chocolates = (JSONObject) this.json.get("Chocolates");
        Iterator<Map.Entry> itr = chocolates.entrySet().iterator(); 
        while (itr.hasNext()) { 
            Map.Entry pair = itr.next();
            JSONObject value = (JSONObject) pair.getValue();
            Double[] num = new Double[]{(Double) value.get("price"), (Double) value.get("quantity")};
            output.put((String) pair.getKey(), num);
        } 
        return output;
    }

    public HashMap<String, Double[]> lsChips(){
        HashMap<String, Double[]> output = new HashMap<>();
        JSONObject chips = (JSONObject) this.json.get("Chips");
        Iterator<Map.Entry> itr = chips.entrySet().iterator(); 
        while (itr.hasNext()) { 
            Map.Entry pair = itr.next();
            JSONObject value = (JSONObject) pair.getValue();
            Double[] num = new Double[]{(Double) value.get("price"), (Double) value.get("quantity")};
            output.put((String) pair.getKey(), num);
        } 
        return output;
    }

    public HashMap<String, Double[]> lsCandies(){
        HashMap<String, Double[]> output = new HashMap<>();
        JSONObject candies = (JSONObject) this.json.get("Candies");
        Iterator<Map.Entry> itr = candies.entrySet().iterator(); 
        while (itr.hasNext()) { 
            Map.Entry pair = itr.next();
            JSONObject value = (JSONObject) pair.getValue();
            Double[] num = new Double[]{(Double) value.get("price"), (Double) value.get("quantity")};
            output.put((String) pair.getKey(), num);
        } 
        return output;
    }    
}
