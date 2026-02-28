package com.example;

import java.time.LocalDate;
import java.time.Period;

public class SimpleFunctions {
    // just say hello
    public String sayHello(String name) {
        return "Hello, " + name + "! Welcome to Jelly Page Builder";
    }

    // check if a number is even
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    // capitalize first letter of a string
    public String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return "str is null or empty";
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // calculate age from date of birth
    public int calculateAge(String dobString) {
        // parse the date of birth
        try {
            // Parse the string into a Java date object
            LocalDate dob = LocalDate.parse(dobString);
            LocalDate today = LocalDate.now();

            // Calculate the exact period between the two dates
            return Period.between(dob, today).getYears();

        } catch (Exception e) {
            // Return -1 if the user enters an invalid date string format
            return -1;
        }
    }

    // Function that throws an error if password is less than 8 characters
    public String validatePassword(String password) {
        if (password.length() < 8) {
            throw new RuntimeException("Password must be at least 8 characters long");
        }
        return "Password is valid";
    }
}