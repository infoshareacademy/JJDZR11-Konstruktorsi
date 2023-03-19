package pl.isa.biblioteka;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;

import static pl.isa.biblioteka.FolderBooks.readBooks;

public class Main {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.ShowMenu();
    }


}