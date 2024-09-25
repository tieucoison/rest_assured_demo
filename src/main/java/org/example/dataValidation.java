package org.example;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class dataValidation {
    private Faker faker = new Faker();
    private ArrayList<Object> valUsername;

    public void dataValidationUsername() {
        valUsername = new ArrayList<>();
        valUsername.add("");
        valUsername.add("     ");
        valUsername.add(null);
        valUsername.add(faker.random().nextBoolean());
        valUsername.add(faker.random().nextInt(1,9));

    }
}
