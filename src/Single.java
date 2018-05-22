/**
 * Single: Object of a single type room
 */

public class Single {
    int singleAdultCost = 10;
    int singleChildCost = 5;
    String dateReserved;
    public Single(String dateReserved) {
        this.dateReserved = dateReserved;
    }
    public int getSingleChildCost() {
        return singleChildCost;
    }

    public int getSingleAdultCost() {
        return singleAdultCost;
    }
}
