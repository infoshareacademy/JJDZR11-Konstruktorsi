package pl.isa.biblioteka;

import java.io.BufferedReader;
import java.util.Scanner;

public class Menu {
    public void ShowMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("Wybierz numer menu");
            System.out.println("1. Widok książek");
            System.out.println("2. Wypożycz książkę");
            System.out.println("3. Użytkownicy");
            System.out.println("4. Edycja katalogu książek");
            System.out.println("5. Zakończ program");

            if (scanner.hasNextInt()) {
                int userChoose = scanner.nextInt();
              //  if (userChoose > 0 && userChoose < 6) {

                    switch (userChoose) {
                        case 1 -> System.out.println("Widok książek"); //Metoda
                        case 2 -> System.out.println("Wypożycz książkę");
                        case 3 -> System.out.println("Użytkownicy");
                        case 4 -> System.out.println("Edycja katalogu książek");
                        case 5 -> {System.out.println("Zakończ program");isContinue = false;}
                    }
                } else {
                    System.out.println("Wybierz poprawny numer menu");
                    break;
                }
            }
        }
    }
