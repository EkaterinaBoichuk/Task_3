package org.example.api;

import java.util.UUID;

public class User {

    private final String email;
    private final String password;
    private final String name;

    private User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User random() {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        return new User("qa." + unique + "@example.com", "Passw0rd", "QA Test " + unique);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
