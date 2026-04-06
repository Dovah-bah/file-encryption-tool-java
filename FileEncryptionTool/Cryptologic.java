package cryptov01;

public class Cryptologic {

    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder(); // create a string box

        for (int i = 0; i < text.length(); i++) { // loop all text char by char
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i % key.length()); // key use one char at the time and loop the key when its use all his chars

            int shift = keyChar;// ASCII shift

            result.append((char) (textChar + shift)); //put the char into the string box and shifted base of the key
        }

        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i % key.length());

            int shift = keyChar;

            result.append((char) (textChar - shift)); // here i remove the shift to decrypt 	MUST BE THE SAME PASSWORD!!
        }

        return result.toString();
    }
}
