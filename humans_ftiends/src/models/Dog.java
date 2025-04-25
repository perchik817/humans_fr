package models;

import java.time.LocalDate;
import java.util.List;

public class Dog extends Pet {
    public Dog(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }
}
