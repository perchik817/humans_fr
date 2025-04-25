package service;

import models.Animal;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class AnimalRegistryApp {
    private static final List<Animal> animals = new ArrayList<>();

//    public static void main(String[] args) {
//
//    }

    public void start(){
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
            animals.add(animal);

            counter.add();  // увеличиваем счетчик в try-with-resources

            System.out.println("Животное заведено: " + animal);

        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void teachCommand(Scanner scanner) {
        if (animals.isEmpty()) {
            System.out.println("Сначала заведите хотя бы одно животное.");
            return;
        }
        listAnimals();
        System.out.print("Введите номер животного, чтобы обучить командe: ");
        int num = readNumber(scanner, animals.size());
        if (num == -1) return;

        Animal animal = animals.get(num - 1);

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
        if (animals.isEmpty()) {
            System.out.println("Сначала заведите хотя бы одно животное.");
            return;
        }
        listAnimals();
        System.out.print("Введите номер животного, чтобы показать команды: ");
        int num = readNumber(scanner, animals.size());
        if (num == -1) return;

        Animal animal = animals.get(num - 1);

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
        for (int i = 0; i < animals.size(); i++) {
            System.out.println((i + 1) + ": " + animals.get(i));
        }
    }

    private static int readNumber(Scanner scanner, int max) {
        String input = scanner.nextLine().trim();
        try {
            int num = Integer.parseInt(input);
            if (num < 1 || num > max) {
                System.out.println("Номер вне диапазона.");
                return -1;
            }
            return num;
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат числа.");
            return -1;
        }
    }
}
