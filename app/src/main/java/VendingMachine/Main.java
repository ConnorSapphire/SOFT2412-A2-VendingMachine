package VendingMachine;

public class Main {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        while (!vm.isQuit()) {
            vm.getUser().setProducts(vm.getProducts());
            vm.getUser().createShortProducts(vm.getProducts());
            vm.getUser().setChange(vm.getChange());
            vm.getUser().setUsers(vm.getUsers());
            if (!vm.getUser().getAccessLevel().contains("anonymous")) {
                if (vm.getUser().getTransaction() != null) {
                    if (vm.getUser().getTransaction().isComplete()) {
                        vm.logout();
                    }
                }
            }
            vm.handleInput(vm.getUser().getUI().getInput().toLowerCase());
        }
    }
}
