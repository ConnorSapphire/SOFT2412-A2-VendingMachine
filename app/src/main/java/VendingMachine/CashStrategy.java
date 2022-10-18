package VendingMachine;

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;

import java.math.BigDecimal;

public class CashStrategy implements PaymentStrategy {
    private int twenty_y = 5;
    private int ten_y = 5;
    private int five_y = 5;
    private int ten_f = 5;
    private int five_f = 5;
    private int[] yArr = {twenty_y,ten_y,five_y,ten_f,five_f};
    public static void main(String[] args) {


        CashStrategy cashStrategy = new CashStrategy();
        PaymentContext paymentContext1 = new PaymentContext("张三", 210.5,50.0, cashStrategy);
        paymentContext1.payNow();

    }
    @Override
    public void pay(PaymentContext context) {

       double balance =  subtractionDouble(context.getmMoney(),context.getProMoney());

        if(balance<0){
            System.out.println("输入钱币不足,请再输入"+Math.abs(balance)+"doller或者取消交易");
        }else{
            System.out.println(balance);
            String balanceStr = balance+"";
            String dec =balanceStr.substring(balanceStr.indexOf("."),balanceStr.length());
            int intergerNum = (int)balance;
            int decNum = (int)(Double.parseDouble("0"+dec)*100);
            splitYuanChange(intergerNum,decNum);
        }


    }

    /**
     * double类型的减法运算
     * @param m1
     * @param m2
     * @return  不加doubleValue()则, 返回BigDecimal对象
     */
    private double subtractionDouble(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).doubleValue();
    }




    private void splitYuanChange(int money,int moneyFen) {
        if (money <= 0) {
            return;
        }
        int[] prices = {20, 10, 5};
        int[] notes = new int[prices.length];
        int change = money;
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            count = 0;
            while (change - prices[i] >= 0) {
                change = change - prices[i];
                count++;
                if(count>=yArr[i]){
                    break;
                }
            }
            notes[i] = count;
        }
        splitFenChange(moneyFen+change*100,notes,prices);
    }

    private void splitFenChange(int money,int[] yNotes,int[] yPrices) {
        if (money <= 0) {
            return;
        }
        int[] prices = {10, 5};
        int[] notes = new int[prices.length];
        int change = money;
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            count = 0;
            while (change - prices[i] >= 0) {
                change = change - prices[i];
                count++;
                if(count>=yArr[i+3] && i<1){
                    break;
                }
            }
            notes[i] = count;
        }
        if(notes[1]>five_f ){
           System.out.println("插入的钱没有可用的零钱,您可以使用不同使用不同钱币或者取消交易");
        }else {
            for (int num = 0; num < yPrices.length; num++) {
                System.out.print(yNotes[num] + "张" + yPrices[num] + "元  \n");
            }
            for (int num = 0; num < prices.length; num++) {
                System.out.print(notes[num] + "张" + prices[num] + "分  \n");
            }
        }
    }

}
