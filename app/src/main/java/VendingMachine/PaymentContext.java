package VendingMachine;


public class PaymentContext {

    //付钱金额
    private double mMoney;

    private String mAccount;

    private String mUserName;

    private double proMoney;

    private PaymentStrategy mIPaymentStrategy;
    //现金支付
    PaymentContext(String userName, double mMoney, double proMoney, PaymentStrategy paymentStrategy) {
        mUserName = userName;
        this.mMoney = mMoney;
        this.proMoney = proMoney;
        mIPaymentStrategy = paymentStrategy;
    }

    void payNow(){
        mIPaymentStrategy.pay(this);
    }

    //信用卡支付
    public PaymentContext(String userName, String account,double money,
                             PaymentStrategy paymentStrategy) {
        mUserName = userName;
        mMoney = money;
        mAccount = account;
        mIPaymentStrategy = paymentStrategy;
    }

    double getmMoney() {
        return mMoney;
    }

    public void setmMoney(double mMoney) {
        this.mMoney = mMoney;
    }

    String getmAccount() {
        return mAccount;
    }

    public void setmAccount(String mAccount) {
        this.mAccount = mAccount;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public double getProMoney() {
        return proMoney;
    }

    public void setProMoney(double proMoney) {
        this.proMoney = proMoney;
    }
}
