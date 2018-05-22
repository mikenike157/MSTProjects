/**
 * Double: Object of a double type room.
 */

public class Double {
        int doubleAdultCost = 20;
        int doubleChildCost = 10;
        String dateReserved;
        public Double(String dateReserved) {
            this.dateReserved = dateReserved;
        }

    public int getDoubleAdultCost() {
        return doubleAdultCost;
    }

    public int getDoubleChildCost() {
        return doubleChildCost;
    }
}
