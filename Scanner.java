package bashShell;
import java.util.Scanner;

public class Scanner {
    java.util.Scanner reader;
    private char currentChar;

    private byte currentKind;
    private StringBuffer currentSpelling;

    public Scanner(InputStream input) {
        reader = new java.util.Scanner(input);
        currentChar = reader.next(".").charAt(0);
    }

    public Token scan() {
        currentSpelling = new StringBuffer(" ");
        currentKind = scanToken();
        return new Token(currentKind,currentSpelling.toString());
    }

    private byte scanToken() {
        switch (currentChar) {
            //Token.VAR
            case 'a' : case 'b' : case 'c' : case 'd' : case 'e' : case 'f' : case 'g' : case 'h' : case 'i' : 
            case 'j' : case 'k' : case 'l' : case 'm' : case 'n' : case 'o' : case 'p' : case 'q' : case 'r' : 
            case 's' : case 't' : case 'u' : case 'v' : case 'w' : case 'x' : case 'y' : case 'z' : 
                takeIt();
                while (isLetter(currentChar) || isDigit(currentChar))
                    takeIt();
                return Token.VAR;
            //Token.LIT
            case '-' : 
                takeIt();
                while (isLetter(currentChar) || isDigit(currentChar))
                    takeIt();
                return Token.LIT;
            //Invalid Input
            default : 
                writeError("Lexical Error");
        }
    }

    private void takeIt() {
        currentSpelling.append(currentChar);
        currentChar = reader.next(".").charAt(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Token tok = scanner.scan();
        System.out.printf("Token: kind = %d spelling = %s /n", tok.kind, tok.spelling);
    }
}
