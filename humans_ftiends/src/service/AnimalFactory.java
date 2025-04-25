package service;

import models.*;

import java.time.LocalDate;
import java.util.List;

public class AnimalFactory {
    public static Animal createAnimal(String type, String name, LocalDate birthDate, List<String> commands) {
        return switch (type.toLowerCase()) {
            case "dog" -> new Dog(name, birthDate, commands);
            case "cat" -> new Cat(name, birthDate, commands);
            case "hamster" -> new Hamster(name, birthDate, commands);
            case "horse" -> new Horse(name, birthDate, commands);
            case "camel" -> new Camel(name, birthDate, commands);
            case "donkey" -> new Donkey(name, birthDate, commands);
            default -> throw new IllegalArgumentException("Неизвестный тип животного: " + type);
        };
    }
}
