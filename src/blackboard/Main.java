package blackboard;

public final class Main {

    //private static final String CODED_STRING = "Q AZWS DSSC KAS DXZNN DASNN";
    //private static final String CODED_STRING = "J IBWF TFFO UIF TNBMM TIFMM";
    private static final String CODED_STRING = "UIF TIFFQ IBWF IBUT";

    public static void main(String[] args) {

    	Cryptographer theCryptographer = new Cryptographer();
        final String answer = theCryptographer.decipher(CODED_STRING);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("-- BLACKBOARD RESULTS: "); 
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("- SYSTEM FINAL REPLY: " + answer);
    }

}
