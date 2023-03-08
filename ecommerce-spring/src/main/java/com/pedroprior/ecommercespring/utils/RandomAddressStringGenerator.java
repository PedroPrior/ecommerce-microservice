package com.pedroprior.ecommercespring.utils;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomAddressStringGenerator {

    private int length;
    private final String allowedChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random random = new Random();
    StringBuilder sb = new StringBuilder(length);

    public String generateRandomString() {

        // Generate 8 random digits
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(10); // Only select from digits
            char randomChar = allowedChars.charAt(index);
            sb.append(randomChar);
        }
        // Generate 3 random uppercase letters
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(26) + 10; // Select from uppercase letters
            char randomChar = allowedChars.charAt(index);
            sb.append(randomChar);
        }
        // Generate 1 random uppercase letter
        int index = random.nextInt(26) + 10; // Select from uppercase letters
        char randomChar = allowedChars.charAt(index);
        sb.append(randomChar);

        return sb.toString();
    }

}
