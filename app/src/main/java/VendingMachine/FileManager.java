package VendingMachine;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

public class FileManager {

    private JSONObject stock, users, change;
    private JSONArray creditCards;

    public FileManager(){
        this.users = (JSONObject) JfileReader("users");
        this.stock = (JSONObject) JfileReader("stock");
        this.creditCards = (JSONArray) JfileReader("credit_cards");
        this.change = (JSONObject) JfileReader("change");
    }

    public Object JfileReader(String filename) {
        Object obj = null;
        try {
            obj = new JSONParser()
                    .parse(new FileReader(new File("app/src/main/java/VendingMachine/" + filename + ".json")));
        } catch (FileNotFoundException e) {
            System.err.println(filename + ".json not found!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void update() {
        this.users = (JSONObject) JfileReader("users");
        this.stock = (JSONObject) JfileReader("stock");
        this.creditCards = (JSONArray) JfileReader("credit_cards");
    }

    public HashMap<String[], Double[]> lsDrinks() {
        HashMap<String[], Double[]> output = new HashMap<>();
        JSONArray drinks = (JSONArray) this.stock.get("Drinks");
        for (Object obj : drinks) {
            JSONObject drink = (JSONObject) obj;
            for (Object objKey : drink.keySet()) {
                String key = (String) objKey;
                JSONObject value = (JSONObject) drink.get(key);
                Double[] num = new Double[] { (Double) value.get("price"), (Double) value.get("quantity") };
                String[] str = new String[]{key, (String) value.get("code")};
                output.put(str, num);
            }
        }
        return output;
    }

    public void updateDrinks(Product drink) {
        
    }

    public HashMap<String[], Double[]> lsChocolates() {
        HashMap<String[], Double[]> output = new HashMap<>();
        JSONArray chocolates = (JSONArray) this.stock.get("Chocolates");
        for (Object obj : chocolates) {
            JSONObject chocolate = (JSONObject) obj;
            for (Object objKey : chocolate.keySet()) {
                String key = (String) objKey;
                JSONObject value = (JSONObject) chocolate.get(key);
                Double[] num = new Double[] { (Double) value.get("price"), (Double) value.get("quantity") };
                String[] str = new String[]{key, (String) value.get("code")};
                output.put(str, num);
            }
        }
        return output;
    }

    public void updateChocolates(Product chocolate) {

    }

    public HashMap<String[], Double[]> lsChips() {
        HashMap<String[], Double[]> output = new HashMap<>();
        JSONArray chips = (JSONArray) this.stock.get("Chips");
        for (Object obj : chips) {
            JSONObject chip = (JSONObject) obj;
            for (Object objKey : chip.keySet()) {
                String key = (String) objKey;
                JSONObject value = (JSONObject) chip.get(key);
                Double[] num = new Double[] { (Double) value.get("price"), (Double) value.get("quantity") };
                String[] str = new String[]{key, (String) value.get("code")};
                output.put(str, num);
            }
        }
        return output;
    }

    public void updateChips(Product chip) {

    }

    public HashMap<String[], Double[]> lsCandies() {
        HashMap<String[], Double[]> output = new HashMap<>();
        JSONArray candies = (JSONArray) this.stock.get("Candies");
        for (Object obj : candies) {
            JSONObject candy = (JSONObject) obj;
            for (Object objKey : candy.keySet()) {
                String key = (String) objKey;
                JSONObject value = (JSONObject) candy.get(key);
                Double[] num = new Double[] { (Double) value.get("price"), (Double) value.get("quantity") };
                String[] str = new String[]{key, (String) value.get("code")};
                output.put(str, num);
            }
        }
        return output;
    }

    public void updateCandies(Product candy) {
    
    }

    public HashMap<String, Double[]> lsNotes() {
        HashMap<String, Double[]> output = new HashMap<String, Double[]>();
        JSONArray notes = (JSONArray) this.change.get("Notes");
        for (Object obj : notes) {
            JSONObject note = (JSONObject) obj;
            for (Object objKey : note.keySet()) {
                String key = (String) objKey;
                JSONObject value = (JSONObject) note.get(key);
                Double[] num = new Double[] { (Double) value.get("price"), (Double) value.get("quantity") };
                output.put(key, num);
            }
        }
        return output;
    }

    
    public void updateNotes(Change note) {
    
    }

    public HashMap<String, Double[]> lsCoins() {
        HashMap<String, Double[]> output = new HashMap<String, Double[]>();
        JSONArray coins = (JSONArray) this.change.get("Coins");
        for (Object obj : coins) {
            JSONObject coin = (JSONObject) obj;
            for (Object objKey : coin.keySet()) {
                String key = (String) objKey;
                JSONObject value = (JSONObject) coin.get(key);
                Double[] num = new Double[] { (Double) value.get("price"), (Double) value.get("quantity") };
                output.put(key, num);
            }
        }
        return output;
    }

    public void updateCoins(Change coin) {

    }

    public HashMap<String, String> getCreditCards() {
        HashMap<String, String> cards = new HashMap<String, String>();
        Iterator iteratorr = creditCards.iterator();
        while (iteratorr.hasNext()) {
            JSONObject obj = (JSONObject) iteratorr.next(); 
            cards.put(obj.get("name").toString(), obj.get("number").toString()); 
        }
        return cards;
    }

    public ArrayList<String> getLastTransactions(String username){
        ArrayList<String> trans = new ArrayList<>();
        JSONObject value = (JSONObject) users.get(username);
        JSONArray transaction = (JSONArray) value.get("transaction");
        Iterator itr = transaction.iterator();
        while (itr.hasNext()) {
            trans.add((String)itr.next());
        }
        return trans;
    }

    public void modifyName(String category, String oldName, String newName){
        JSONArray oldcate = (JSONArray) stock.get(category);
        JSONArray newcate = new JSONArray();
        for (Object obj : oldcate) {
            JSONObject Jobj = (JSONObject) obj;
            for (Object objKey : Jobj.keySet()) {
                String key = (String) objKey;
                JSONObject value = (JSONObject) Jobj.get(key);
                if(key.equals(oldName)){
                    newcate.add(newName + ": " + value.toJSONString());
                }else{
                    newcate.add(Jobj.toJSONString());
                }
            }
        }
        JSONObject newstock = new JSONObject();
        for (Object objKey : stock.keySet()) {
            String key = (String) objKey;
            if(key.equals(category)){
                newstock.put(category, newcate);
            }else{
                newstock.put(key, stock.get(key));
            }
        }
        stock = newstock;
        writeJsonFile(newstock, "stock");
    }

    public void writeJsonFile(JSONObject json, String filename){
        try(FileWriter file = new FileWriter("app/src/main/java/VendingMachine/" + filename + ".json")){
            file.write(json.toJSONString());
            System.out.print(json.toJSONString());
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        FileManager fm = new FileManager();
        fm.modifyName("Chips", "Smiths", "S");
    }
}
