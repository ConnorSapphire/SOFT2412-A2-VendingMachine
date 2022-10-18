package VendingMachine;

public class Main {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        while (!vm.isQuit()) {
            vm.handleInput();
        }
    }
}
