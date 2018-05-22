import java.util.ArrayList;

public class CustomerBook {
    ArrayList<Customer> customerList;
    public CustomerBook() {
         customerList = new ArrayList<Customer>();
    }
    public void addCustomer(String name, String isDisabled) {
        if (isDisabled.equals("Yes")) {
            Customer customer = new Customer(name, true);
            customerList.add(customer);
        }
        else {
            Customer customer = new Customer(name, false);
            customerList.add(customer);
        }
    }
    public boolean customerExists(String name) {
        for (int i = 0; i < customerList.size(); i++) {
            if (name.equals(customerList.get(i).name)) {
                return true;
            }
        }
        return false;
    }
}
