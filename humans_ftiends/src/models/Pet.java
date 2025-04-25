package models;

import java.time.LocalDate;

public abstract class Pet extends Animal {
    public Pet(String name, LocalDate birthDate, java.util.List<String> commands) {
        super(name, birthDate, commands);
    }
}
