package VendingMachine;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.lang.reflect.Array;
import java.time.ZoneId;

public class FileManager {

    private JSONObject stock, users, change;
    private JSONArray creditCards, transactions;
    private String stockFileName, usersFileName, creditCardsFileName, changeFileName, transactionsFileName;

    public FileManager(String usersFileName, String stockFileName, String creditCardsFileName, String changeFileName, String transactionsFileName) {
        this.users = (JSONObject) JfileReader(usersFileName);
        this.stock = (JSONObject) JfileReader(stockFileName);
        this.creditCards = (JSONArray) JfileReader(creditCardsFileName);
        this.change = (JSONObject) JfileReader(changeFileName);
        this.transactions = (JSONArray) JfileReader(transactionsFileName);
        this.stockFileName = stockFileName;
        this.usersFileName = usersFileName;
        this.creditCardsFileName = creditCardsFileName;
        this.changeFileName = changeFileName;
        this.transactionsFileName = transactionsFileName;
    }

    public FileManager() {
        this.users = (JSONObject) JfileReader("users");
        this.stock = (JSONObject) JfileReader("stock");
        this.creditCards = (JSONArray) JfileReader("credit_cards");
        this.change = (JSONObject) JfileReader("change");
        this.transactions = (JSONArray) JfileReader("transactions");
        this.usersFileName = "users";
        this.stockFileName = "stock";
        this.creditCardsFileName = "credit_cards";
        this.changeFileName = "change";
        this.transactionsFileName = "transactions";
    }

    public Object JfileReader(String filename) {
        Object obj = null;
        try {
            obj = new JSONParser()
                    .parse(new FileReader(new File("src/main/java/VendingMachine/" + filename + ".json")));
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

    public LinkedHashMap<String, Double[]> lsNotes() {
        LinkedHashMap<String, Double[]> output = new LinkedHashMap<String, Double[]>();
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
        JSONObject obj = new JSONObject();
        JSONArray notes = (JSONArray) this.change.get("Notes");
        boolean found = false;
        for (Object noteObj : notes) {
            JSONObject noteObject = (JSONObject) noteObj;
            for (Object objKey : noteObject.keySet()) {
                String key = (String) objKey;
                if (key.equals(note.getName())) {
                    JSONObject value = (JSONObject) noteObject.get(key);
                    value.replace("price", note.getValue());
                    value.replace("quantity", Double.valueOf(note.getQuantity()));
                    found = true;
                }
            }
        }
        if (!found) {
            JSONObject noteObj = new JSONObject();
            JSONObject value = new JSONObject();
            value.put("price", note.getValue());
            value.put("quantity", Double.valueOf(note.getQuantity()));
            noteObj.put(note.getName(), value);
            notes.add(noteObj);
        }
        JSONArray coins = (JSONArray) this.change.get("Coins");
        obj.put("Notes", notes);
        obj.put("Coins", coins);
        try {
            FileWriter fw = new FileWriter("src/main/java/VendingMachine/" + changeFileName + ".json");
            obj.writeJSONString(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap<String, Double[]> lsCoins() {
        LinkedHashMap<String, Double[]> output = new LinkedHashMap<String, Double[]>();
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
        JSONObject obj = new JSONObject();
        JSONArray coins = (JSONArray) this.change.get("Coins");
        boolean found = false;
        for (Object coinObj : coins) {
            JSONObject coinObject = (JSONObject) coinObj;
            for (Object objKey : coinObject.keySet()) {
                String key = (String) objKey;
                if (key.equals(coin.getName())) {
                    JSONObject value = (JSONObject) coinObject.get(key);
                    value.replace("price", coin.getValue());
                    value.replace("quantity", Double.valueOf(coin.getQuantity()));
                    found = true;
                }
            }
        }
        if (!found) {
            JSONObject coinObj = new JSONObject();
            JSONObject value = new JSONObject();
            value.put("price", coin.getValue());
            value.put("quantity", Double.valueOf(coin.getQuantity()));
            coinObj.put(coin.getName(), value);
            coins.add(coinObj);
        }
        JSONArray notes = (JSONArray) this.change.get("Notes");
        obj.put("Notes", notes);
        try {
            FileWriter fw = new FileWriter("src/main/java/VendingMachine/" + changeFileName + ".json");
            obj.writeJSONString(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String[]> lsUsers() {
        HashMap<String, String[]> output = new HashMap<String, String[]>();
        for (Object obj : this.users.keySet()) {
            String key = (String) obj;
            JSONObject user = (JSONObject) this.users.get(key);
            JSONObject card = (JSONObject) user.get("card");
            String[] value = new String[] {(String) user.get("password"), (String) card.get("name"), (String) card.get("number"), (String) user.get("access")};
            output.put(key, value);
        }
        return output;
    }

    public void updateUsers(User user) {
        if (this.users.containsKey(user.getUsername())) {
            JSONObject userObject = (JSONObject) this.users.get(user.getUsername());
            userObject.replace("password", user.getPassword());
            JSONObject cardObject = (JSONObject) userObject.get("card");
            cardObject.replace("name", user.getCardName());
            cardObject.replace("number", user.getCardNumber());
            userObject.replace("card", cardObject);
            userObject.replace("access", user.getAccessLevel());
            // update Transactions (need to save to User first)!
            this.users.replace(user.getUsername(), userObject);
        } else {
            JSONObject userObject = new JSONObject();
            userObject.put("password", user.getPassword());
            JSONObject cardObject = new JSONObject();
            cardObject.put("name", user.getCardName());
            cardObject.put("number", user.getCardNumber());
            userObject.put("card", cardObject);
            userObject.put("access", user.getAccessLevel());
            // update Transactions (need to save to user first)!
            userObject.put("transaction", new JSONArray());
            this.users.put(user.getUsername(), userObject);
        }
        try {
            FileWriter fw = new FileWriter("src/main/java/VendingMachine/" + usersFileName + ".json");
            this.users.writeJSONString(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void updateTransactionHistory(Date date, ArrayList<Product> products, Double cost, Double change, String paymentMethod) {
        JSONObject transaction = new JSONObject();
        transaction.put("date", date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
        transaction.put("time", date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime().toString());
        JSONArray productArray = new JSONArray();
        for (Product product : products) {
            productArray.add(product.getName());
        }
        transaction.put("products", productArray);
        transaction.put("amount", cost);
        transaction.put("change", change);
        transaction.put("paymentMethod", paymentMethod);
        transactions.add(transaction);
        try {
            FileWriter fw = new FileWriter("src/main/java/VendingMachine/" + transactionsFileName + ".json");
            transactions.writeJSONString(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> lsTransactionHistory() {
        ArrayList<ArrayList<String>> transactionHistory = new ArrayList<ArrayList<String>>();
        for (Object obj : transactions) {
            JSONObject transaction = (JSONObject) obj;
            ArrayList<String> currentTransaction = new ArrayList<String>();
            currentTransaction.add(transaction.get("date").toString());
            currentTransaction.add(transaction.get("time").toString());
            JSONArray products = (JSONArray) transaction.get("products");
            String productsString = "";
            for (Object productObj : products) {
                String product = (String) productObj;
                if (!productsString.equals("")) {
                    productsString += " " + product;
                } else {
                    productsString += product;
                }
            }
            currentTransaction.add(productsString);
            currentTransaction.add(transaction.get("amount").toString());
            currentTransaction.add(transaction.get("change").toString());
            currentTransaction.add(transaction.get("paymentMethod").toString());
            transactionHistory.add(currentTransaction);
        }
        return transactionHistory;
    }

    public void writeTransactionFile(String fileName, ArrayList<ArrayList<String>> transactions) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (ArrayList<String> transaction : transactions) {
                fw.write("Date: " + transaction.get(0) + "\n");
                fw.write("Time: " + transaction.get(1) + "\n");
                fw.write("Products: " + transaction.get(2) + "\n");
                fw.write("Cost: " + transaction.get(3) + "\n");
                fw.write("Change: " + transaction.get(4) + "\n");
                fw.write("Payment Method: " + transaction.get(5) + "\n");
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    public void writeProductsFile(String fileName, HashMap<String[], Double[]> drinks, HashMap<String[], Double[]> candies, HashMap<String[], Double[]> chocolates, HashMap<String[], Double[]> chips) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write("Drinks:\n");
            for (String[] drink : drinks.keySet()) {
                fw.write("\tProduct Name: " + drink[0] + "\n");
                fw.write("\tProduct Cost: " + drinks.get(drink)[0] + "\n");
                fw.write("\tProduct Quantities: " + drinks.get(drink)[1] + "\n");
                fw.write("\n");
            }
            fw.write("Candies:\n");
            for (String[] candy : candies.keySet()) {
                fw.write("\tProduct Name: " + candy[0] + "\n");
                fw.write("\tProduct Cost: " + candies.get(candy)[0] + "\n");
                fw.write("\tProduct Quantities: " + candies.get(candy)[1] + "\n");
                fw.write("\n");
            }
            fw.write("Chocolates:\n");
            for (String[] chocolate : chocolates.keySet()) {
                fw.write("\tProduct Name: " + chocolate[0] + "\n");
                fw.write("\tProduct Cost: " + chocolates.get(chocolate)[0] + "\n");
                fw.write("\tProduct Quantities: " + chocolates.get(chocolate)[1] + "\n");
                fw.write("\n");
            }
            fw.write("Chips:\n");
            for (String[] chip : chips.keySet()) {
                fw.write("\tProduct Name: " + chip[0] + "\n");
                fw.write("\tProduct Cost: " + chips.get(chip)[0] + "\n");
                fw.write("\tProduct Quantities: " + chips.get(chip)[1] + "\n");
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public List<String> readTextFile(String jsonFile, String encode) {
        List<String> dataList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(jsonFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encode);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null)
            {
                dataList.add(tempString);
               // stringBuilder.append(tempString);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
