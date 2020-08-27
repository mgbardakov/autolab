package core.input;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * class for console input.
 * @author mbardakov
 * @since 31.07.2020
 */
public final class ConsoleInput implements Input {
    /**
     * scanner for console reading.
     */
    private Scanner scanner;
    /**
     * string consumer.
     */
    private Consumer<String> con;
    /**
     * default constructor.
     */
    public ConsoleInput(final Consumer<String> newCon) {
        scanner = new Scanner(System.in);
        this.con = newCon;
    }
    @Override
    public String askString(final String question) {
        con.accept(question);
        return scanner.nextLine();
    }
    @Override
    public Integer askInt(String question) {
        Integer rsl = null;
        try{
            rsl = Integer.parseInt(askString(question));
        } catch (NumberFormatException e) {
            con.accept("Please enter number of the menu item");
            con.accept(System.lineSeparator());
            askInt(question);
        }
        return rsl;
    }
    @Override
    public Integer askInt(String question, int maximum) {
        Integer rsl = null;
        try {
            rsl = askInt(question);
            if (rsl < 0 || rsl > maximum) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            con.accept("Your number is out of range");
            askInt(question, maximum);
        }
        return rsl;
    }
}
