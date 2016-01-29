package util;

public final class StringTrimmer {

    /**
     * Public method to remove leading whitespace
     * 
     * @param source
     *            the source String object to trim
     * @return left trimmed String
     */
    public static String ltrim(String source) {
        return source.replaceAll("^\\s+", "");
    }

    /**
     * Public method to remove trailing whitespace
     * 
     * @param source
     *            the source String object to trim
     * @return right trimmed String
     */
    public static String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    /**
     * Public method to replace multiple whitespaces between words with a single
     * whitespace or blank
     * 
     * @param source
     *            the source String object to revise
     * @return space defragged String
     */
    public static String itrim(String source) {
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    /**
     * Public method to trim whitespace on ends and to fix any multiple
     * whitespace issues within the string
     */
    public static String trim(String source) {
        return itrim(lrtrim(source));
    }

    /**
     * Public method to trim whitespace from ends only
     * 
     * @param source
     * @return left and right trimmed String
     */
    public static String lrtrim(String source) {
        return ltrim(rtrim(source));
    }

}
