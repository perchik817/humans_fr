package models;

import java.time.LocalDate;
import java.util.List;

public class Hamster extends Pet {
    public Hamster(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }
}
