package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddUsers {
    //Mikołaj Malinowski
    //Dodanie nowego użytkownika
    public static Person person;
    public static List<Person> users = new ArrayList<>(PersonService.readUsers());

    public void addUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj imię nowego użytkownika: ");
        String firstName = input.nextLine().trim();
        System.out.println("Podaj nazwisko nowego użytkownika: ");
        String lastName = input.nextLine().trim();
        Person addPerson = new Person(firstName, lastName);
        users.add(addPerson);
        System.out.printf("Dodano nowego użytkownika: %s %s ", firstName, lastName);
    }

    public void listsUsers(){
        System.out.println("Uzytkownicy w naszej aplikacji: ");
        int index = 1;
        for (Person user : users) {
            System.out.println(index + " Imię: " + user.getFirstName() +
                    ", Nazwisko: " + user.getSecondName() +
                    ", Wypożyczone książki: " + ((user.personBooks != null) ? user.getPersonBooks() : "Brak książek"));
            index++;
        }
    }


    public static List<Person> getUsers() {
        return users;
    }
}
