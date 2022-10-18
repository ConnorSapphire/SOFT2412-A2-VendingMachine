package VendingMachine;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class CardStrategy implements PaymentStrategy {

    public static void main(String[] args){
        CardStrategy cardStrategy = new CardStrategy();
        PaymentContext paymentContext1 = new PaymentContext("cky","1372947", 210.5, cardStrategy);
        paymentContext1.payNow();
    }

    @Override
    public void pay(PaymentContext context) {
       String mUsername =  context.getmUserName();
       String mAccount = context.getmAccount();

        String json = "D://Ad-admin//src//main//java//VendingMachine//credit_cards.json";

        File jsonFile = new File(json);
        String jsonData = getStr(jsonFile);
        //转json对象
        JSONArray dataJsonArr =  (JSONArray)JSONArray.parse(jsonData);
        boolean isHaveMatch = false;
        for(int i =0;i<dataJsonArr.size();i++){
            JSONObject dataJson = JSONObject.parseObject(dataJsonArr.get(i).toString());
            String accountName = dataJson.getString("account_name");
            String account = dataJson.getString("account");
            if(accountName!=null && mAccount!=null && accountName.equals(mUsername) && mAccount.equals(account)){
                isHaveMatch = true;
            }
        }
        double proMoney = context.getmMoney();
        if(isHaveMatch){
            System.out.println("持卡人: "+mUsername+" 通过信用卡: "+mAccount+" 刷取金额: "+proMoney+"元");
            writeDataToFile(mUsername+"\t"+mAccount+"\t"+proMoney);
        }else{
            System.out.println("在列表中未找到匹配的持卡人和账号");
        }

    }

    private void writeDataToFile(String content){
        File fileOrFilename = new File("account.txt");

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOrFilename, true)));
            out.write(content+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private String getStr(File jsonFile){
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
