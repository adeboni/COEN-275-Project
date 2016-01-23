package util;

public final class StringTrimmer {

    private StringTrimmer() {}

    public static String ltrim(String source) {
        return source.replaceAll("^\\s+", "");
    }

    public static String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    public static String itrim(String source) {
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    public static String lrtrim(String source) {
        return ltrim(rtrim(source));
    }

    public static String trim(String source) {
        return itrim(lrtrim(source));
    }
}
