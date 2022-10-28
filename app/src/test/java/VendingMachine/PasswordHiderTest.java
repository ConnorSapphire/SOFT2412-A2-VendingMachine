package VendingMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PasswordHiderTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(out, true));
    }

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void printOnInitialisation() {
        String test = "test";
        PasswordHider hider = new PasswordHider(test);
        assertTrue(out.toString().contains(test));
    }

    @Test
    public void run() {
        PasswordHider hider = new PasswordHider(" ");
        Thread mask = new Thread(hider);
        mask.start();
        try {
            Thread.currentThread().sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hider.stopMasking();
        assertTrue(out.toString().contains("*"));
    }
}
