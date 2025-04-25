package models;

import java.time.LocalDate;
import java.util.List;

public class Camel extends Pack {
    public Camel(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }
}
