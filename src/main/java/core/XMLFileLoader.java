package core;

/**
 * implementation of loader using xml file input.
 * @author mbardakov
 * @since  29.07.2020
 */
public final class XMLFileLoader implements Loader {
    /**
     * current input.
     */
    private Input input;

    /**
     * main constructor.
     * @param currentInput - current input
     */
    public XMLFileLoader(final Input currentInput) {
        this.input = currentInput;
    }

    @Override
    public String[] getRecordFieldPool() {
        return null;
    }
}
