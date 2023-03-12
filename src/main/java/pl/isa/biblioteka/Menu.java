package pl.isa.biblioteka;

import java.util.Scanner;

public class Menu {
    public void ShowMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("Wybierz numer menu");
            System.out.println("1. Menu 1");
            System.out.println("2. Menu 2");
            System.out.println("3. Menu 3");
            System.out.println("4. Menu 4");
            System.out.println("5. Zakończ program");

         //   int userChoose = scanner.nextInt();


            if (scanner.hasNextInt()) {
                   int userChoose = scanner.nextInt();

                switch (userChoose) {
                    case 1 -> System.out.println("Menu 1");
                    case 2 -> System.out.println("Menu 2");
                    case 3 -> System.out.println("Menu 3");
                    case 4 -> System.out.println("Menu 4");
                    case 5 -> {System.out.println("Zakończ program"); isContinue = false;
                    }
                }
            } else {
                System.out.println("Wybierz poprawny numer menu");
                break;
            }
        }
    }
}

