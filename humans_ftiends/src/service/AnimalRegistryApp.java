package service;

import models.Animal;
import registry.Counter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class AnimalRegistryApp {
    private static final Map<Integer, Animal> animals = new HashMap<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Counter counter = new Counter()) {
            while (true) {
                printMenu();
                String option = scanner.nextLine().trim();

                switch (option) {
                    case "1" -> addAnimal(scanner, counter);
                    case "2" -> teachCommand(scanner);
                    case "3" -> showCommands(scanner);
                    case "4" -> listAnimals();
                    case "5" -> {
                        System.out.println("Выход...");
                        return;
                    }
                    default -> System.out.println("Неверный пункт меню");
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка работы со счетчиком: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Меню реестра животных ---");
        System.out.println("1. Завести новое животное");
        System.out.println("2. Обучить команде");
        System.out.println("3. Показать команды животного");
        System.out.println("4. Показать список всех животных");
        System.out.println("5. Выход");
        System.out.print("Выберите пункт: ");
    }

    private static void addAnimal(Scanner scanner, Counter counter) {
        try {
            System.out.print("Введите имя животного: ");
            String name = scanner.nextLine().trim();

            System.out.print("Введите дату рождения (yyyy-MM-dd): ");
            String birthDateStr = scanner.nextLine().trim();
            LocalDate birthDate = LocalDate.parse(birthDateStr);

            System.out.print("Введите тип животного (dog, cat, hamster, horse, camel, donkey): ");
            String type = scanner.nextLine().trim();

            if (name.isEmpty() || type.isEmpty()) {
                System.out.println("Все поля должны быть заполнены!");
                return;
            }

            Animal animal = AnimalFactory.createAnimal(type, name, birthDate, new ArrayList<>());
            animals.put(nextId++, animal);

            counter.add();  // увеличиваем счетчик в try-with-resources

            System.out.println("Животное заведено: " + animal);

        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void teachCommand(Scanner scanner) {
        System.out.print("Введите ID животного: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID.");
            return;
        }

        Animal animal = animals.get(id);
        if (animal == null) {
            System.out.println("Животное с таким ID не найдено.");
            return;
        }

        System.out.print("Введите команду для обучения: ");
        String command = scanner.nextLine().trim();

        if (command.isEmpty()) {
            System.out.println("Команда не может быть пустой.");
            return;
        }

        animal.learnCommand(command);
        System.out.println("Животное обучено команде.");
    }

    private static void showCommands(Scanner scanner) {
        System.out.print("Введите ID животного: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID.");
            return;
        }

        Animal animal = animals.get(id);
        if (animal == null) {
            System.out.println("Животное с таким ID не найдено.");
            return;
        }

        List<String> commands = animal.getCommands();
        if (commands.isEmpty()) {
            System.out.println("Животное ещё не знает команд.");
        } else {
            System.out.println("Команды: " + String.join(", ", commands));
        }
    }

    private static void listAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Список животных пуст.");
            return;
        }
        System.out.println("Список животных:");
        animals.forEach((id, animal) -> System.out.println(id + ": " + animal));
    }
}
