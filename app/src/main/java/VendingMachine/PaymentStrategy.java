package VendingMachine;

public interface PaymentStrategy {

    void pay(PaymentContext context);
    
}
