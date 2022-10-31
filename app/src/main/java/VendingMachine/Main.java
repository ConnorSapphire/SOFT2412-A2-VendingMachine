package VendingMachine;

public class Main {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        while (!vm.isQuit()) {
            vm.getUser().setProducts(vm.getProducts());
            vm.getUser().createShortProducts(vm.getProducts());
            vm.getUser().setChange(vm.getChange());
            vm.getUser().setUsers(vm.getUsers());
            vm.handleInput(vm.getUser().getUI().getInput().toLowerCase());
        }
    }
}
