package com.company;

import com.company.Books.ScientificBook;
import com.company.Halls.List;
import com.company.Halls.ScientificLibraryHall;
import com.company.Librarys.BidirectionalList;
import com.company.Librarys.ILibrary;
import com.company.Librarys.ScientificLibrary;

public class Main {

    public static void main(String[] args) {
        //Создание книг
        ScientificBook scientificBook1 = new ScientificBook("Tolkien", "Lord Of The Rings", 26, 1954, 12);
        ScientificBook scientificBook2 = new ScientificBook("Azimov", "Foundation", 25, 1942, 12);
        ScientificBook scientificBook3 = new ScientificBook("Dostoevsky", "Crime and punishment", 25, 1866, 18);
        ScientificBook scientificBook4 = new ScientificBook("Nikita", "nameOfBook", 1, 2021, 12);
        ScientificBook scientificBook5 = new ScientificBook("Daniel", "nameOfBook1", 2, 2021, 12);
        ScientificBook scientificBook6 = new ScientificBook("Max", "nameOfBook2", 3, 2021, 12);
        ScientificBook scientificBook7 = new ScientificBook("Andrey", "nameOfBook3", 4, 2021, 12);
        //Создание массива книг
        List books1 = new List();
        books1.addToEnd(scientificBook1);
        books1.addToEnd(scientificBook2);
        books1.addToEnd(scientificBook3);
        List books2 = new List();
        books2.addToEnd(scientificBook4);
        books2.addToEnd(scientificBook5);
        List books3 = new List();
        books3.addToEnd(scientificBook6);
        books3.addToEnd(scientificBook7);
        //Создание залов
        ScientificLibraryHall hall_1 = new ScientificLibraryHall("hall_1", books1);
        ScientificLibraryHall hall_2 = new ScientificLibraryHall("hall_2", books2);
        ScientificLibraryHall hall_3 = new ScientificLibraryHall("hall_3", books3);
        //Создание массива залов
        BidirectionalList halls = new BidirectionalList();
        halls.addToEnd(hall_1);
        halls.addToEnd(hall_2);
        halls.addToEnd(hall_3);
        //Создание экземпляра библиотеки
        ILibrary library = new ScientificLibrary(halls);
        System.out.print("Books in library: ");
        library.printBooks();
        //измените книгу;
        System.out.println("\n" + "1)Change book:" + "\n" + "book 1 before changing:");
        ScientificBook newBook = new ScientificBook("newAuthor", "newName", 15, 1954, 12);
        System.out.println(library.getBookByID(1).toString());
        library.changeBookByID(1, newBook);
        System.out.println("book 1 after changing:");
        System.out.println(library.getBookByID(1).toString());
        //измените зал;
        System.out.println("2)Change hall:" + "\n" + "hall 2 before changing:");
        System.out.println("name:" +
                library.getLibraryHallsByID(1).getName() + ", num of books:" +
                library.getLibraryHallsByID(1).getBooks().getLength());
        ScientificLibraryHall newHall = new
                ScientificLibraryHall("newHall", books2);
        library.changeHallByID(1, newHall);
        System.out.println("hall 2 after changing:");
        System.out.println("name:" +
                library.getLibraryHallsByID(1).getName() + ", num of books:" +
                library.getLibraryHallsByID(1).getBooks().getLength());
        // удалите книгу;
        System.out.println("3)delete book:" + "\n" + "book №1 before delete:");
        System.out.println(library.getBookByID(1).toString());
        library.deleteBookFromLibrary(1);
        System.out.println("book №1 after delete (books remove):");
        System.out.println(library.getBookByID(1).toString());
        //Выведите автора самой лучшей книги;
        System.out.println("4)Print author of the best book:");
        System.out.println("Best Author: " + library.getBestBook().getAuthor());
        // выведите список названий книг по убыванию цены.
        System.out.println("5)Print list of the names books descending price: ");
        library.printBooks(library.selectionSortBookInHallByCost());


    }
}
