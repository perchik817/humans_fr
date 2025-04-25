package models;

import java.time.LocalDate;
import java.util.List;

public abstract class Animal {
    protected String name;
    protected LocalDate birthDate;
    protected List<String> commands;

    public Animal(String name, LocalDate birthDate, List<String> commands) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
    }

    public void learnCommand(String newCommand) {
        commands.add(newCommand);
    }

    public List<String> getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + name + " (" + birthDate + ") - Команды: " + commands;
    }
}
