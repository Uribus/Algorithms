package colors;

public enum Style {
    NORMAL("0"),
    BOLD("1"),
    ITALICS("3"),
    UNDERLINE("4");

    private final String code;

    Style(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
