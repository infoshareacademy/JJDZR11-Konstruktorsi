package pl.isa.biblioteka;

import java.util.ArrayList;
import java.util.Scanner;

public class AddUsers {
    //Mikołaj Malinowski
    //Dodanie nowego użytkownika
    private String firstName;
    private String lastName;
    private String firstNameAndLastName;
    ArrayList<String> users = new ArrayList<>();
    public void addUser (){
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj imię nowego użytkownika: ");
        firstName = input.nextLine();
        System.out.println("Podaj nazwisko nowego użytkownika: ");
        lastName = input.nextLine();
        firstNameAndLastName = firstName + " " + lastName;
        users.add(firstNameAndLastName);
        System.out.println("Dodano nowego użytkownika: "+firstNameAndLastName);
    }
    public void ListOfUsers(){
        System.out.println("Użytkownicy: ");
        for (int i=0; i<users.size(); i++){
            System.out.println(i+1+" "+users.get(i));
        }

    }
}
