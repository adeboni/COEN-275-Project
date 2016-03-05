package blackboard;

public final class Main {

    private static final String CODED_STRING = "Q AZWS DSSC KAS DXZNN DASNN";
    private static final String CODED_STRING_2 = "IF QFFMFE UIF TNBMM CBOBOB";

    public static void main(String[] args) {

    	Cryptographer theCryptographer = new Cryptographer();
        final String answer = theCryptographer.decipher(CODED_STRING_2);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("-- BLACKBOARD RESULTS: "); 
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("- SYSTEM FINAL REPLY: " + answer);
    }

}
