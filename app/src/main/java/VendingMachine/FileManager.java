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
    private String stockFileName, usersFileName, creditCardsFileName, changeFileName;

    public FileManager(String usersFileName, String stockFileName, String creditCardsFileName, String changeFileName) {
        this.users = (JSONObject) JfileReader(usersFileName);
        this.stock = (JSONObject) JfileReader(stockFileName);
        this.creditCards = (JSONArray) JfileReader(creditCardsFileName);
        this.change = (JSONObject) JfileReader(changeFileName);
        this.stockFileName = stockFileName;
        this.usersFileName = usersFileName;
        this.creditCardsFileName = creditCardsFileName;
        this.changeFileName = changeFileName;
    }

    public FileManager() {
        this.users = (JSONObject) JfileReader("users");
        this.stock = (JSONObject) JfileReader("stock");
        this.creditCards = (JSONArray) JfileReader("credit_cards");
        this.change = (JSONObject) JfileReader("change");
        this.usersFileName = "users";
        this.stockFileName = "stock";
        this.creditCardsFileName = "credit_cards";
        this.changeFileName = "change";
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
        this.users = (JSONObject) JfileReader(usersFileName);
        this.stock = (JSONObject) JfileReader(stockFileName);
        this.creditCards = (JSONArray) JfileReader(creditCardsFileName);
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
        JSONObject obj = new JSONObject();
        JSONArray drinks = (JSONArray) this.stock.get("Drinks");
        boolean found = false;
        for (Object drinkObj : drinks) {
            JSONObject drinkObject = (JSONObject) drinkObj;
            for (Object objKey : drinkObject.keySet()) {
                String key = (String) objKey;
                if (key.equals(drink.getName())) {
                    JSONObject value = (JSONObject) drinkObject.get(key);
                    value.replace("price", drink.getPrice());
                    value.replace("quantity", Double.valueOf(drink.getQuantity()));
                    value.replace("code", drink.getCode());
                    found = true;
                }
            }
        }
        if (!found) {
            JSONObject drinkObj = new JSONObject();
            JSONObject value = new JSONObject();
            value.put("price", drink.getPrice());
            value.put("quantity", Double.valueOf(drink.getQuantity()));
            value.put("code", drink.getCode());
            drinkObj.put(drink.getName(), value);
            drinks.add(drinkObj);
        }
        JSONArray chocolates = (JSONArray) this.stock.get("Chocolates");
        JSONArray chips = (JSONArray) this.stock.get("Chips");
        JSONArray candies = (JSONArray) this.stock.get("Candies");
        obj.put("Drinks", drinks);
        obj.put("Chocolates", chocolates);
        obj.put("Chips", chips);
        obj.put("Candies", candies);
        try {
            FileWriter fw = new FileWriter("src/main/java/VendingMachine/" + stockFileName + ".json");
            obj.writeJSONString(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        JSONObject obj = new JSONObject();
        JSONArray chocolates = (JSONArray) this.stock.get("Chocolates");
        boolean found = false;
        for (Object chocObj : chocolates) {
            JSONObject chocolateObject = (JSONObject) chocObj;
            for (Object objKey : chocolateObject.keySet()) {
                String key = (String) objKey;
                if (key.equals(chocolate.getName())) {
                    JSONObject value = (JSONObject) chocolateObject.get(key);
                    value.replace("price", chocolate.getPrice());
                    value.replace("quantity", Double.valueOf(chocolate.getQuantity()));
                    value.replace("code", chocolate.getCode());
                    found = true;
                }
            }
        }
        if (!found) {
            JSONObject chocObj = new JSONObject();
            JSONObject value = new JSONObject();
            value.put("price", chocolate.getPrice());
            value.put("quantity", Double.valueOf(chocolate.getQuantity()));
            value.put("code", chocolate.getCode());
            chocObj.put(chocolate.getName(), value);
            chocolates.add(chocObj);
        }
        JSONArray drinks = (JSONArray) this.stock.get("Drinks");
        JSONArray chips = (JSONArray) this.stock.get("Chips");
        JSONArray candies = (JSONArray) this.stock.get("Candies");
        obj.put("Drinks", drinks);
        obj.put("Chocolates", chocolates);
        obj.put("Chips", chips);
        obj.put("Candies", candies);
        try {
            FileWriter fw = new FileWriter("src/main/java/VendingMachine/" + stockFileName + ".json");
            obj.writeJSONString(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        JSONObject obj = new JSONObject();
        JSONArray chips = (JSONArray) this.stock.get("Chips");
        boolean found = false;
        for (Object chipObj : chips) {
            JSONObject chipObject = (JSONObject) chipObj;
            for (Object objKey : chipObject.keySet()) {
                String key = (String) objKey;
                if (key.equals(chip.getName())) {
                    JSONObject value = (JSONObject) chipObject.get(key);
                    value.replace("price", chip.getPrice());
                    value.replace("quantity", Double.valueOf(chip.getQuantity()));
                    value.replace("code", chip.getCode());
                    found = true;
                }
            }
        }
        if (!found) {
            JSONObject chipObj = new JSONObject();
            JSONObject value = new JSONObject();
            value.put("price", chip.getPrice());
            value.put("quantity", Double.valueOf(chip.getQuantity()));
            value.put("code", chip.getCode());
            chipObj.put(chip.getName(), value);
            chips.add(chipObj);
        }
        JSONArray chocolates = (JSONArray) this.stock.get("Chocolates");
        JSONArray drinks = (JSONArray) this.stock.get("Drinks");
        JSONArray candies = (JSONArray) this.stock.get("Candies");
        obj.put("Drinks", drinks);
        obj.put("Chocolates", chocolates);
        obj.put("Chips", chips);
        obj.put("Candies", candies);
        try {
            FileWriter fw = new FileWriter("src/main/java/VendingMachine/" + stockFileName + ".json");
            obj.writeJSONString(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        JSONObject obj = new JSONObject();
        JSONArray candies = (JSONArray) this.stock.get("Candies");
        boolean found = false;
        for (Object candyObj : candies) {
            JSONObject candyObject = (JSONObject) candyObj;
            for (Object objKey : candyObject.keySet()) {
                String key = (String) objKey;
                if (key.equals(candy.getName())) {
                    JSONObject value = (JSONObject) candyObject.get(key);
                    value.replace("price", candy.getPrice());
                    value.replace("quantity", Double.valueOf(candy.getQuantity()));
                    value.replace("code", candy.getCode());
                    found = true;
                }
            }
        }
        if (!found) {
            JSONObject candyObj = new JSONObject();
            JSONObject value = new JSONObject();
            value.put("price", candy.getPrice());
            value.put("quantity", Double.valueOf(candy.getQuantity()));
            value.put("code", candy.getCode());
            candyObj.put(candy.getName(), value);
            candies.add(candyObj);
        }
        JSONArray chocolates = (JSONArray) this.stock.get("Chocolates");
        JSONArray chips = (JSONArray) this.stock.get("Chips");
        JSONArray drinks = (JSONArray) this.stock.get("Drinks");
        obj.put("Drinks", drinks);
        obj.put("Chocolates", chocolates);
        obj.put("Chips", chips);
        obj.put("Candies", candies);
        try {
            FileWriter fw = new FileWriter("src/main/java/VendingMachine/" + stockFileName + ".json");
            obj.writeJSONString(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
