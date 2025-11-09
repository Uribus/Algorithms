package colors;

public enum Emphasis {
    
    NORMAL("3"),
    BACKGROUND_COLOR("4"),
    BRIGHT("9");

    private final String code;

    Emphasis(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
