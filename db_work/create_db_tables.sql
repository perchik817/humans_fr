-- eate database humans_friends;
USE humans_friends;

CREATE TABLE Animals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL
);

CREATE TABLE Pets (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Animals(id) ON DELETE CASCADE
);

CREATE TABLE PackAnimals (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Animals(id) ON DELETE CASCADE
);

CREATE TABLE Dogs (
    id INT PRIMARY KEY,
    commands TEXT,
    FOREIGN KEY (id) REFERENCES Pets(id) ON DELETE CASCADE
);

CREATE TABLE Cats (
    id INT PRIMARY KEY,
    commands TEXT,
    FOREIGN KEY (id) REFERENCES Pets(id) ON DELETE CASCADE
);

CREATE TABLE Hamsters (
    id INT PRIMARY KEY,
    commands TEXT,
    FOREIGN KEY (id) REFERENCES Pets(id) ON DELETE CASCADE
);

CREATE TABLE Horses (
    id INT PRIMARY KEY,
    commands TEXT,
    FOREIGN KEY (id) REFERENCES PackAnimals(id) ON DELETE CASCADE
);

CREATE TABLE Camels (
    id INT PRIMARY KEY,
    commands TEXT,
    FOREIGN KEY (id) REFERENCES PackAnimals(id) ON DELETE CASCADE
);

CREATE TABLE Donkeys (
    id INT PRIMARY KEY,
    commands TEXT,
    FOREIGN KEY (id) REFERENCES PackAnimals(id) ON DELETE CASCADE
);
