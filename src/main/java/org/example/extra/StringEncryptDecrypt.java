package org.example.extra;

public class StringEncryptDecrypt {

    // XOR Encryption/Decryption Method
    public static String encryptDecrypt(String input, char key) {
        StringBuilder output = new StringBuilder();

        // XOR each character with the key
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key));
        }

        return output.toString();
    }

    public static void main(String[] args) {
        // Original String
        String originalString = "U2425313chdbUcb6934tag3";

        // Define a key for encryption/decryption
        char key = 'K'; // Example key (can be any character)

        // Encrypt the string
        String encryptedString = encryptDecrypt(originalString, key);
        System.out.println("Encrypted String: " + encryptedString);

        // Decrypt the string (apply the same XOR operation with the same key)
        String decryptedString = encryptDecrypt(encryptedString, key);
        System.out.println("Decrypted String: " + decryptedString);
    }
}