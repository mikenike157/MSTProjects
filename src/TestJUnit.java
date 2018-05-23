import org.junit.Test;

import java.io.*;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;

public class TestJUnit {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    String[] args;
    @Test
    public void testSetup() {
        args = new String[]{"1", "2", "3"};
        System.setIn(new ByteArrayInputStream("1\n01-01\n5\nYes\nMichael Hicks\nNo\nYes\nNo\n".getBytes()));
        System.setOut(new PrintStream(out));
        Reservations.main(args);
        String outString = out.toString();
        int lastIndex = outString.lastIndexOf("\r\n");
        int secondToLast = outString.substring(0, lastIndex).lastIndexOf("\r\n");
        assertEquals("Would you like to reserve another room?\r\n", out.toString().substring(secondToLast+2));
    }
    @Test
    public void testNoSingle() {
        args = new String[]{"1", "1", "1"};
        System.setIn(new ByteArrayInputStream("1\n01-01\n5\nYes\nMichael Hicks\nNo\nYes\nYes\n1\n01-03\n5\nNo\n".getBytes()));
        System.setOut(new PrintStream(out));
        Reservations.main(args);
        String outString = out.toString();
        int lastIndex = outString.lastIndexOf("\r\n");
        int secondToLast = outString.substring(0, lastIndex).lastIndexOf("\r\n");
        assertEquals("Im sorry we are out of Singles for this time period. Would you like to reserve another room?\r\n", out.toString().substring(secondToLast+2));
    }
    @Test
    public void testNoDouble() {
        args = new String[]{"1", "1", "1"};
        System.setIn(new ByteArrayInputStream("2\n01-01\n5\n2\nMichael Hicks\nNo\nYes\nYes\n2\n01-03\n5\nNo\n".getBytes()));
        System.setOut(new PrintStream(out));
        Reservations.main(args);
        String outString = out.toString();
        int lastIndex = outString.lastIndexOf("\r\n");
        int secondToLast = outString.substring(0, lastIndex).lastIndexOf("\r\n");
        assertEquals("Im sorry we are out of Doubles for that time period. Would you like to reserve another room?\r\n", out.toString().substring(secondToLast+2));
    }
    @Test
    public void testNoDeluxe() {
        args = new String[]{"1", "1", "1"};
        System.setIn(new ByteArrayInputStream("4\n01-01\n5\n2\nMichael Hicks\nNo\nYes\nYes\n3\n01-03\n5\nNo\n".getBytes()));
        System.setOut(new PrintStream(out));
        Reservations.main(args);
        String outString = out.toString();
        int lastIndex = outString.lastIndexOf("\r\n");
        int secondToLast = outString.substring(0, lastIndex).lastIndexOf("\r\n");
        assertEquals("Im sorry we are out of Deluxes for that time period. Would you like to reserve another room?\r\n", out.toString().substring(secondToLast+2));
    }
    @Test
    public void testInvalidAmountOfGuests() {
        args = new String[]{"1", "2", "3"};
        System.setIn(new ByteArrayInputStream("9\n01-01\n5\nNo\n".getBytes()));
        System.setOut(new PrintStream(out));
        Reservations.main(args);
        String outString = out.toString();
        int lastIndex = outString.lastIndexOf("\r\n");
        int secondToLast = outString.substring(0, lastIndex).lastIndexOf("\r\n");
        assertEquals("Would you like to reserve another room?\r\n", out.toString().substring(secondToLast+2));
    }
}
