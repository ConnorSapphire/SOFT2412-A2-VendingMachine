package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CreateObjectTests {
    
    @Test 
    void getCoinName(){
        ChangeCreator testCoin = new CoinCreator();
        Change coin = testCoin.create("$100", 100, 10);
        assertEquals("$100", coin.getName());
    }

    @Test 
    void setCoinName(){
        ChangeCreator testCoin = new CoinCreator();
        Change coin = testCoin.create("100", 100, 10);
        coin.setName("$100");
        assertEquals("$100", coin.getName());
    }

    @Test 
    void getCoinValue(){
        ChangeCreator testCoin = new CoinCreator();
        Change coin = testCoin.create("$100", 100, 10);
        assertEquals(100, coin.getValue());
    }

    @Test 
    void setCoinValue(){
        ChangeCreator testCoin = new CoinCreator();
        Change coin = testCoin.create("$100", 0, 10);
        coin.setValue(100);
        assertEquals(100, coin.getValue());
    }

    @Test 
    void getCoinQuantity(){
        ChangeCreator testCoin = new CoinCreator();
        Change coin = testCoin.create("$100", 100, 10);
        assertEquals(10, coin.getQuantity());
    }

    @Test 
    void setCoinQuantity(){
        ChangeCreator testCoin = new CoinCreator();
        Change coin = testCoin.create("$100", 100, 0);
        coin.setQuantity(10);
        assertEquals(10, coin.getQuantity());
    }
}
