package pl.isa.biblioteka;

import java.util.Scanner;

public class Menu {

    public void selectUser() {

        System.out.println("\nWitamy w naszej bibliotece");
        System.out.println("Kim jesteś ?\nWybierz odpowiednią opcję w menu");
        System.out.println("1 - Bibliotekarz");
        System.out.println("2 - Użytkownik");

        Scanner scanner = new Scanner(System.in);
        try {
            int userChoose1 = scanner.nextInt();

            if (userChoose1 == 1) {
                librarianMenu();
            } else if (userChoose1 == 2) {
                userMenu();
                System.out.println("Wybierz poprawny numer menu");
            }
        } catch (Exception e) {
            System.out.println("Wprowadź poprawny numer menu");
        }
    }

    public void librarianMenu() {

        AddUsers users = new AddUsers();
        Book book = new Book();
        BooksEdit booksEdit = new BooksEdit();
        BorrowBooks borrowBooks = new BorrowBooks();
        FolderBooks folderBooks = new FolderBooks();
        List<Book> books = readBooks();


        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("\nWybierz numer menu");
            System.out.println("1. Widok książek");
            System.out.println("2. Wypożycz książkę");
            System.out.println("3. Dodaj użytkownika");
            System.out.println("4. Lista użytkowników");
            System.out.println("5. Dodanie książki");
            System.out.println("6. Usuwanie ksiązki");
            System.out.println("7. Zakończ program");


            if (scanner.hasNextInt()) {
                int userChoose = scanner.nextInt();
                if (userChoose > 0 && userChoose < 9) {
                    switch (userChoose) {
                        case 1 -> booksEdit.showAllBooks(); // add method
                        case 2 -> borrowBooks.mainLoop(); // add method
                        case 3 -> users.addUser(); // add method
                        case 4 -> users.listsUsers(); // add method
                        case 5 -> booksEdit.addBook(); // add method
                        case 6 -> booksEdit.deleteBookByTitle(); // add method
                        //TODO dodanie opcji wypisania pojedyncczego użytkownika
                        case 7 -> {
                            PersonService.saveUsers();
                            FolderBooks.saveBooks();
                            System.out.println("Baza użytkowników i książek zapisana poprawnie");
                            isContinue = false;
                        }
                    }
                } else {
                    System.out.println("Wybierz poprawny numer menu");
                    break;
                }
            } else {
                System.out.println("Wybierz poprawny numer menu");
                break;
            }
        }
    }
}