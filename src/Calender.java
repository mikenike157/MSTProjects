public class Calender {
    int numSingle;
    int numDouble;
    int numDeluxe;
    boolean[][] singleArray;
    boolean[][] doubleArray;
    boolean[][] deluxeArray;


    public Calender(int numSingle, int numDouble, int numDeluxe) {
        this.numSingle = numSingle;
        this.numDouble = numDouble;
        this.numDeluxe = numDeluxe;
        int max = Integer.max(numSingle, numDouble);
        max = Integer.max(max, numDeluxe);
        singleArray = new boolean[365][numSingle];
        doubleArray = new boolean[365][numDouble];
        deluxeArray = new boolean[365][numDeluxe];
        for (int i = 0; i < 365; i++) {
            for(int j = 0; j < max; j++) {
                if (j < numSingle) {
                    singleArray[i][j] = true;
                }
                if (j < numDouble) {
                    doubleArray[i][j] = true;
                }
                if (j < numDeluxe) {
                    deluxeArray[i][j] = true;
                }
            }
        }
    }
    //Books the days in the correct room matrix
    //Inputs: day(Day of move in), numDays(Number of days staying), type(int to denote the type of room. 0 = single, 1 = double, 2 = deluxe)
    public void bookDays(int day, int numDays, int type) {
        if (type == 0) {
            for (int i = 0; i < numDays; i++) {
                for (int j = 0; j < numSingle; j++) {
                    if (singleArray[day+i][j] == true) {
                        singleArray[day+i][j] = false;
                        break;
                    }
                }
            }
        }
        if (type == 1) {
            for (int i = 0; i < numDays; i++) {
                for (int j = 0; j < numDouble; j++) {
                    if (doubleArray[day+i][j] == true) {
                        doubleArray[day+i][j] = false;
                        break;
                    }
                }
            }
        }
        if (type == 2) {
            for (int i = 0; i < numDays; i++) {
                for (int j = 0; j < numDeluxe; j++) {
                    if (deluxeArray[day+i][j] == true) {
                        deluxeArray[day+i][j] = false;
                        break;
                    }
                }
            }
        }
    }
    //Gets the offset of of how many days to add to the move in day to get the correct month
    //Input: month(month of move in)
    //Output: number of days to that month starting at January
    public int getOffset(Integer month) {
        if (month == 1) {
            return 0;
        }
        else if (month == 2) {
            return 31;
        }else if (month == 3) {
            return 59;
        }else if (month == 4) {
            return 90;
        }else if (month == 5) {
            return 120;
        }else if (month == 6) {
            return 151;
        }else if (month == 7) {
            return 181;
        }else if (month == 8) {
            return 212;
        }else if (month == 9) {
            return 243;
        }else if (month == 10) {
            return 273;
        }else if (month == 11) {
            return 304;
        }else {
            return 334;
        }
    }
    //Checks if there are any rooms available for the time period.
    //Inputs: day(integer to denote the day of the month moving in), type(int to denote type of room. See above), offset(number of days retrieved from getOffset), numDays(Length of stay)
    //Output: returns true if room is available, false otherwise.
    public boolean roomsAvailable(Integer day, int type, int offset, int numDays) {
        boolean flag = true;
        if (type == 0) {
            for (int i = 0; i < numDays; i++) {
                if (!flag) {
                    return flag;
                }
                for (int j = 0; j < numSingle; j++) {
                    if (!singleArray[day + offset][j]) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            }
        }
        if (type == 1) {
            for (int i = 0; i < numDays; i++) {
                if (!flag) {
                    return flag;
                }
                for (int j = 0; j < numDouble; j++) {
                    if (!doubleArray[day + offset][j]) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            }
        }
        if (type == 2) {
            for (int i = 0; i < numDays; i++) {
                if (!flag) {
                    return flag;
                }
                for (int j = 0; j < numDeluxe; j++) {
                    if (!deluxeArray[day + offset][j]) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }
}
