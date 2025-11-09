package colors;

public class Format {

    private Style style;
    private Emphasis emphasis;
    private Color color;

    public static final String PREFIX = "\033[";
    public static final String SUFFIX = "m";
    public static final String RESET_CODE = PREFIX + "0" + SUFFIX;

    public Format(Style style, Emphasis emphasis, Color color) {
        this.style = style;
        this.emphasis = emphasis;
        this.color = color;
    }

    @Override
    public String toString() {
        return PREFIX + style + ";" + emphasis + color + SUFFIX;
    }

}
