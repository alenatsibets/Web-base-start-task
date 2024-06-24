package com.example.webbasestarttask.util;

import java.util.Random;

public class CodeGenerator {

    public static String generate() {
        Random random = new Random(); //make private static
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }
}