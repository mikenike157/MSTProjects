import java.util.ArrayList;

public class CustomerBook {
    ArrayList<Customer> customerList;
    public CustomerBook() {
         customerList = new ArrayList<Customer>();
    }
    //Creates customer object and adds it to customerList
    //Inputs: name(Name of the customer), isDisabled(String telling if the Customer is disabled)
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
    //Checks if the customer has been added to the customer list before
    //Inputs: name(Name of the desired Customer)
    //Output: true if found, if not false
    public boolean checkExists(String name) {
        for (int i = 0; i < customerList.size(); i++) {
            if (name.equals(customerList.get(i).name)) {
                return true;
            }
        }
        return false;
    }
}
