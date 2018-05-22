import java.util.Scanner;

public class Reservations {
    public static void main(String[] args) {
        final double EXIST_DISCOUNT = .9;
        final double DISABLED_DISCOUNT = .8;
        CustomerBook book = new CustomerBook();
        SQLServer sqlComm = new SQLServer();
        //Calender to store dates & rooms available
        int numSingle = 5, numDouble = 5, numDeluxe = 5;
        Calender cal = new Calender(numSingle, numDouble, numDeluxe);
        Scanner s = new Scanner(System.in);
        //Loop until terminated by user
        while (true) {
            //Get single, double, or deluxe
            System.out.println("How many guests? Enter a number.");
            String numGuest = s.nextLine();
            int numGuests = Integer.parseInt(numGuest);
            //Get dates of stay
            System.out.println("What date will you arrive? Enter the date as XX-XX");
            String date = s.nextLine();
            String dateCopy = date;
            //Split by month and day
            String[] dateSplit = date.split("-");
            Integer month = Integer.parseInt(dateSplit[0]);
            //Get availability for month
            Integer day = Integer.parseInt(dateSplit[1]) - 1;
            System.out.println("How many days will you be staying?");
            String numDay = s.nextLine();
            int numDays = Integer.parseInt(numDay);
            //Check if their stay goes over the end of a month
            if (numGuests == 1) {
                int offset = cal.getOffset(month);
                if (cal.roomsAvailable(day, 0, offset, numDays)) {
                    Single single = new Single(dateCopy);
                    System.out.println("Are you an Adult?");
                    String adultCheck = s.nextLine();
                    int cost;
                    if (adultCheck.equals("Yes")) {
                        cost = single.getSingleAdultCost() * numDays;
                    } else {
                        cost = single.getSingleChildCost() * numDays;
                    }
                    System.out.println("May I have your name?");
                    String name = s.nextLine();
                    System.out.println("Are you disabled?");
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
                        cal.bookDays(day + offset, numDays, 0);
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
                    Doublea dou = new Doublea(dateCopy);
                    System.out.println("How many Adults?");
                    String numAdult = s.nextLine();
                    int numAdults = Integer.parseInt(numAdult);
                    int numChildren = numGuests - numAdults;
                    int cost = ((dou.getDoubleAdultCost() * numAdults) + (dou.getDoubleChildCost() * numChildren)) * numDays;
                    System.out.println("May I have your name?");
                    String name = s.nextLine();
                    System.out.println("Are you disabled?");

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
                        cal.bookDays(day + offset, numDays, 1);
                        sqlComm.insertDouble(dateCopy, numDays, cost);
                        sqlComm.insertCustomer(name, isDisabled);
                        System.out.println("Thank you!");
                    }
                } else {
                    System.out.print("Im sorry we are out of Doubles. For that time period. ");
                }
            }
            //If the room being reserved is a deluxe
            else if (numGuests == 3 || numGuests == 4) {
                int offset = cal.getOffset(month);
                if (cal.roomsAvailable(day, 0, offset, numDays)) {
                    Deluxe deluxe = new Deluxe(dateCopy);
                    System.out.println("How many Adults?");
                    String numAdult = s.nextLine();
                    int numAdults = Integer.parseInt(numAdult);
                    int numChildren = numGuests - numAdults;
                    int cost = (deluxe.getDeluxeAdultCost() * numAdults + deluxe.getDoubleChildCost() * numChildren) * numDays;
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
