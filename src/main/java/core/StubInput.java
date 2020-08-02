package core;

public final class StubInput implements Input {
    /**
     * answers array for input.
     */
    private String[] answers;
    /**
     * counter for array indexes.
     */
    private int count;

    /**
     * basic constructor.
     * @param newAnswers - answers
     */
    public StubInput(final String[] newAnswers) {
        this.answers = newAnswers;
        this.count = 0;
    }

    @Override
    public String askString(final String question) {
        return answers[count++];
    }
}
