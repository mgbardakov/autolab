package core;

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
    public ConsoleInput() {
        scanner = new Scanner(System.in);
        con = System.out::print;
    }

    @Override
    public String askString(final String question) {
        con.accept(question);
        return scanner.nextLine();
    }
}
