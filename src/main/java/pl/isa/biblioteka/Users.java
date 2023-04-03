package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Users {
    //Mikołaj Malinowski
    //Dodanie nowego użytkownika
    public static Person person;
    public static List<Person> users = new ArrayList<>(PersonService.readUsers());
    Scanner scanner = new Scanner(System.in);


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

    public boolean deleteUser() {
        System.out.println("Podaj nazwisko użytkownika: ");
        String lastName = scanner.nextLine();
        System.out.println("Użytkownik usunięty");
        return users.removeIf(findPersonByLastName(lastName));



    }
    private static Predicate<Person> findPersonByLastName (String secondName) {
        return user -> user.getSecondName().equalsIgnoreCase(secondName);
    }
    public void listsUsers(){
        System.out.println("Uzytkownicy w naszej aplikacji: ");
        System.out.println("---------------------------------");
        int index = 1;
        for (Person user : users) {
            System.out.println(" Imię: " + user.getFirstName() + ", Nazwisko: " + user.getSecondName());
            List<Book> personBooks = user.personBooks;
            if (!personBooks.isEmpty()){
                System.out.println("    Wypożyczone książki");
                for (Book personBook : personBooks) {
                    System.out.println("        Tytył: " + personBook.getTitle() + ", Autor: " + personBook.getAuthor());
                    index++;
                }
            } else {
                System.out.println("        - Brak wypożyczonych książek");
            }
            System.out.println("---------------------------------");
        }
    }


    public static List<Person> getUsers() {
        return users;
    }



}
