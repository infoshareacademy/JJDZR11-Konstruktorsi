package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.Scanner;

public class AddUsers {
    //Mikołaj Malinowski
    //Dodanie nowego użytkownika
    ArrayList<String> users = new ArrayList<>();

    public void addUser() {
        String firstName;
        String lastName;
        String name;

        Scanner input = new Scanner(System.in);
        System.out.println("Podaj imię nowego użytkownika: ");
        firstName = input.nextLine();
        System.out.println("Podaj nazwisko nowego użytkownika: ");
        lastName = input.nextLine();
        name = firstName + " " + lastName;
        users.add(name);
        System.out.println("Dodano nowego użytkownika: " + name);
    }

    public void listUsers() {
        System.out.println("Użytkownicy: ");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + 1 + " " + users.get(i));
        }

    }
}
