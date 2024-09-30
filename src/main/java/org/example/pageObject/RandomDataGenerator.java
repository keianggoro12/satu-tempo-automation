package org.example.pageObject;

import java.util.Random;

public class RandomDataGenerator {

    // Method to generate a random email
    public String generateRandomEmail() {
        String emailPrefix = "juli";
        String emailDomain = "@gmail.com";
        Random random = new Random();
        int randomNumber = random.nextInt(10); // Generates a random number between 0 and 999
        return emailPrefix + randomNumber + emailDomain;
    }

    // Method to generate a random password
    public String generateRandomPassword() {
        String passwordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        int passwordLength = 8; // Specify desired password length

        for (int i = 0; i < passwordLength; i++) {
            int index = random.nextInt(passwordChars.length());
            password.append(passwordChars.charAt(index));
        }

        return password.toString();
    }
}