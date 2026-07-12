package org.example.extra;

public class SimpleEncryptor {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String transform(String text, String key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = ALPHABET.indexOf(c);

            if (index != -1) {
                int keyShift = ALPHABET.indexOf(key.charAt(i % key.length()));
                // If decrypting, we subtract the shift; if encrypting, we add it
                int newIndex;
                if (encrypt) {
                    newIndex = (index + keyShift) % ALPHABET.length();
                } else {
                    newIndex = (index - keyShift + ALPHABET.length()) % ALPHABET.length();
                }
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(c); // Keep special characters as-is
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String original = "U2425313chdbUcb6934tag3";
        String secretKey = "javaKey123";

        // Encrypt
        String encrypted = transform(original, secretKey, true);
        // Decrypt
        String decrypted = transform(encrypted, secretKey, false);

        System.out.println("Original:  " + original);
        System.out.println("Encrypted: " + encrypted); // Same length!
        System.out.println("Decrypted: " + decrypted);
    }
}