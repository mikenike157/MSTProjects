import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * CustomerBook: Holds the list of previous customers and runs operations manipulating that list
 */
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
            Collections.sort(customerList, new CustomComparator());
        }
    }
    //Checks if the customer has been added to the customer list before
    //Inputs: name(Name of the desired Customer)
    //Output: true if found, if not false
    public boolean checkExists(String name) {
        int min = 0;
        int max = customerList.size()-1;
        while (min < max) {
            int comp = (int) Math.ceil(min + max / 2);
            if (customerList.get(comp).name.compareTo(name) == 0) {
                return true;
            } else if (customerList.get(comp).name.compareTo(name) < 0) {
                min = comp + 1;
            } else {
                max = comp - 1;
            }
        }
        return false;
    }
}

class CustomComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.name.compareTo(o2.name);
    }
}
