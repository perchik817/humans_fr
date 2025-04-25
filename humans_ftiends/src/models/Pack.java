package models;

import java.time.LocalDate;
import java.util.List;

public class Pack extends Animal{
    public Pack(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }
}
