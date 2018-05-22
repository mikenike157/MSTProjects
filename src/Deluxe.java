public class Deluxe {
    int deluxeAdultCost = 50;
    int doubleChildCost = 25;
    String dateReserved;
    public Deluxe(String dateReserved) {
        this.dateReserved = dateReserved;
    }
    public int getDeluxeAdultCost() {
        return deluxeAdultCost;
    }

    public int getDoubleChildCost() {
        return doubleChildCost;
    }
}
