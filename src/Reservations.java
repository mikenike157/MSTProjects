import java.util.Scanner;

/**
 * Reservations: Gathers input from user in order to reserve a room at a hotel
 */

public class Reservations {
    static int numSingle = 5, numDouble = 5, numDeluxe = 5;
    static final double EXIST_DISCOUNT = .9;
    static final double DISABLED_DISCOUNT = .8;
    public static void main(String[] args) {
        int cost; //Cost of the room
        int numDays; //Length of the stay
        int numGuests; //Total number of guests
        int numAdults; //Number of Adults
        int numChildren; //Number of Children
        CustomerBook book = new CustomerBook(); //Customer name book
        SQLServer sqlComm = new SQLServer(); //SQL Helper class
        Calender cal = new Calender(numSingle, numDouble, numDeluxe);//Calender to store dates & rooms available
        Scanner s = new Scanner(System.in);
        while (true) {

            System.out.println("How many guests? Enter a number."); //Get single, double, or deluxe
            String numGuest = s.nextLine();
            numGuests = Integer.parseInt(numGuest);
            System.out.println("What date will you arrive? Enter the date as XX-XX"); //Get dates of stay
            String date = s.nextLine();
            String dateCopy = date;
            //Split by month and day
            String[] dateSplit = date.split("-");
            Integer month = Integer.parseInt(dateSplit[0]);
            Integer day = Integer.parseInt(dateSplit[1]) - 1;
            System.out.println("How many days will you be staying?"); //Get length of stay
            String numDay = s.nextLine();
            numDays = Integer.parseInt(numDay);
            //Check if their stay goes over the end of a month
            if (numGuests == 1) { //Room is Single
                int offset = cal.getOffset(month);

                if (cal.roomsAvailable(day, 0, offset, numDays)) {
                    Single single = new Single(dateCopy);
                    System.out.println("Are you an Adult?"); //Adult check for pricing
                    String adultCheck = s.nextLine();
                    if (adultCheck.equals("Yes")) {
                        cost = single.getSingleAdultCost() * numDays;
                    } else {
                        cost = single.getSingleChildCost() * numDays;
                    }
                    System.out.println("May I have your name?"); //Customer building
                    String name = s.nextLine();
                    System.out.println("Are you disabled?"); //Customer building
                    String isDisabled = s.nextLine();
                    if (book.checkExists(name)) {
                        cost = (int) (cost*EXIST_DISCOUNT);
                        System.out.println("Welcome back " + name);
                    } else {
                        book.addCustomer(name, isDisabled);
                    }
                    if (isDisabled.equals("Yes")) {
                        cost = (int) (cost*DISABLED_DISCOUNT);
                    }
                    System.out.println("The price will be " + cost + ". Would you like to reserve a room?");
                    String confirm = s.nextLine();
                    if (confirm.equals("Yes")) {
                        //Book the room
                        cal.bookDays(day + offset, numDays, 0); //Go into the calender and mark the room as booked
                        sqlComm.insertSingle(dateCopy, numDays, cost);
                        sqlComm.insertCustomer(name, isDisabled);
                        System.out.println("Thank you!");
                    }
                } else {
                    System.out.print("Im sorry we are out of Singles for this time period. ");
                }
                //If the room being reserved is a double
            } else if (numGuests == 2) {
                int offset = cal.getOffset(month);

                if (cal.roomsAvailable(day, 0, offset, numDays)) {
                    Double dou = new Double(dateCopy);
                    System.out.println("How many Adults?");
                    String numAdult = s.nextLine();
                    numAdults = Integer.parseInt(numAdult);
                    numChildren = numGuests - numAdults;
                    cost = ((dou.getDoubleAdultCost() * numAdults) + (dou.getDoubleChildCost() * numChildren)) * numDays;
                    System.out.println("May I have your name?");
                    String name = s.nextLine();
                    System.out.println("Are you disabled?");

                    String isDisabled = s.nextLine();
                    if (book.checkExists(name)) {
                        cost = (int) (cost * EXIST_DISCOUNT);
                        System.out.println("Welcome back " + name);
                    } else {
                        book.addCustomer(name, isDisabled);
                    }
                    if (isDisabled.equals("Yes")) {
                        cost = (int) (cost * DISABLED_DISCOUNT);
                    }
                    System.out.println("The price will be " + cost + ". Would you like to reserve a room?");
                    String confirm = s.nextLine();
                    if (confirm.equals("Yes")) {
                        cal.bookDays(day + offset, numDays, 1);
                        sqlComm.insertDouble(dateCopy, numDays, cost);
                        sqlComm.insertCustomer(name, isDisabled);
                        System.out.println("Thank you!");
                    }
                } else {
                    System.out.print("Im sorry we are out of Doubles. For that time period. ");
                }

            } else if (numGuests == 3 || numGuests == 4) { //If the room being reserved is a deluxe
                int offset = cal.getOffset(month);

                if (cal.roomsAvailable(day, 0, offset, numDays)) {
                    Deluxe deluxe = new Deluxe(dateCopy);
                    System.out.println("How many Adults?");
                    String numAdult = s.nextLine();
                    numAdults = Integer.parseInt(numAdult);
                    numChildren = numGuests - numAdults;
                    cost = (deluxe.getDeluxeAdultCost() * numAdults + deluxe.getDoubleChildCost() * numChildren) * numDays;
                    System.out.println("May I have your name?");
                    String name = s.nextLine();
                    System.out.println("Are you disabled?");
                    String isDisabled = s.nextLine();
                    if (book.checkExists(name)) {

                        cost = (int) (cost*EXIST_DISCOUNT);
                    } else {
                        book.addCustomer(name, isDisabled);
                        System.out.println("Welcome back " + name);
                    }
                    if (isDisabled.equals("Yes")) {
                        cost = (int) (cost*DISABLED_DISCOUNT);
                    }
                    System.out.println("The price of will be " + cost + ". Would you like to reserve a room?");
                    String confirm = s.nextLine();
                    if (confirm.equals("Yes")) {
                        cal.bookDays(day + offset, numDays, 2);
                        sqlComm.insertDeluxe(dateCopy, numDays, cost);
                        sqlComm.insertCustomer(name, isDisabled);
                        System.out.println("Thank you!");
                    }
                } else {
                    System.out.print("Im sorry we are out of Deluxes for that time period. ");
                }
            } else {
                System.out.println("Invalid amount of guests for one room.");
            }
            System.out.println("Would you like to reserve another room?");
            String confirm = s.nextLine();

            if (!confirm.equals("Yes")) {
                return;
            }
        }
    }
}
