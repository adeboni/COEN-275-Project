package blackboard;

public final class Main {

    private static final String CODED_STRING = "Q AZVS DSSC KAS DXZNN DASNN";

    public static void main(String[] args) {

    	Cryptographer theCryptographer = new Cryptographer();
        final String answer = theCryptographer.decipher(CODED_STRING);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("-- BLACKBOARD RESULTS: "); 
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("- SYSTEM FINAL REPLY: " + answer);
    }

}
