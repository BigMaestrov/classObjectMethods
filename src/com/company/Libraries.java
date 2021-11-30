package com.company;

import com.company.Books.ChildrenBook;
import com.company.Books.ScientificBook;
import com.company.Halls.ChildrenLibraryHall;
import com.company.Halls.ScientificLibraryHall;
import com.company.Librarys.ChildrenLibrary;
import com.company.Librarys.ILibrary;
import com.company.Librarys.ScientificLibrary;

import java.io.*;
import java.util.Scanner;

public class Libraries {

    //Запись данных о библиотеке в байтовый поток
    public static void outputLibrary(ILibrary lib, OutputStream out) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(lib);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Чтение данных о библиотеке из байтового потока
    public static ILibrary inputLibrary(InputStream in) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            return (ILibrary) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //записи библиотеки в символьный поток
    public static void writeLibrary(ILibrary lib, Writer out) {
        lib.writeInFile(out);
    }

    //чтения библиотеки из символьного потока
    public static ILibrary readLibrary(Reader in) {
        try (Scanner scanner = new Scanner(in)) {
            String buffer = scanner.nextLine();
            ILibrary library;
            if (buffer.equals("science")) {
                //Запись библиотеки
                library = new ScientificLibrary();
                library.setNumHalls(scanner.nextInt());
                scanner.nextLine();
                int intBuffer = 0;
                //Запись Зала
                for (int i = 0; i < library.getNumHalls(); i++) {
                    library.getLibraryHalls().addToEnd(new ScientificLibraryHall());
                    library.getLibraryHallsByID(i).setName(scanner.nextLine());
                    intBuffer = scanner.nextInt();
                    scanner.nextLine();
                    //Запись Книги
                    for (int j = 0; j < intBuffer; j++) {
                        ScientificBook book = new ScientificBook();

                        book.setAuthor(scanner.nextLine());

                        book.setName(scanner.nextLine());

                        book.setCost(scanner.nextInt());
                        scanner.nextLine();

                        book.setYear(scanner.nextInt());
                        scanner.nextLine();

                        book.setCitationIndex(13.0);
                        scanner.nextLine();

                        library.getLibraryHallsByID(i).addBook(book, j);
                    }
                }
                return library;
            } else if (buffer.equals("children")) {
                library = new ChildrenLibrary();
                //Запись библиотеки
                library.setNumHalls(scanner.nextInt());
                scanner.nextLine();
                int intBuffer = 0;
                //Запись Зала
                for (int i = 0; i < library.getNumHalls(); i++) {
                    library.getLibraryHalls().addToEnd(new ChildrenLibraryHall());
                    library.getLibraryHallsByID(i).setName(scanner.nextLine());
                    intBuffer = scanner.nextInt();
                    scanner.nextLine();
                    //Запись Книги
                    for (int j = 0; j < intBuffer; j++) {
                        ChildrenBook book = new ChildrenBook();

                        book.setAuthor(scanner.nextLine());

                        book.setName(scanner.nextLine());

                        book.setCost(scanner.nextInt());
                        scanner.nextLine();

                        book.setYear(scanner.nextInt());
                        scanner.nextLine();

                        book.setMinimalAge(scanner.nextInt());
                        scanner.nextLine();

                        library.getLibraryHallsByID(i).addBook(book, j);
                    }
                }
                return library;
            } else {
                System.err.println("INCORRECT TYPE OF LIBRARY");
                return null;
            }
        }
    }

    public static void outputLibrary(ILibrary lib, DataOutputStream out) throws IOException {
        out.writeInt(lib.getNumHalls());
        out.writeInt(lib.sumOfAllBooks());
        for (int i = 0; i < lib.getNumHalls(); i++) {
            ScientificLibraryHall hall = (ScientificLibraryHall) lib.getLibraryHallsByID(i);
            out.writeUTF(hall.getName());
            out.writeInt(hall.getBooks().getLength());
            for (int a = 0; a < hall.getBooks().getLength(); a++) {
                ScientificBook book = (ScientificBook) hall.getBookByID(a);
                out.writeUTF(book.getName());
                out.writeUTF(book.getAuthor());
                out.writeDouble(book.getCost());
                out.writeInt(book.getYear());
                out.writeDouble(book.getCitationIndex());
            }
        }
    }
/*
    public static ILibrary inputILibrary(DataInputStream in) throws ClassNotFoundException, IOException {
        int[] hallSizes = {};
        ScientificLibrary lib = new ScientificLibrary(hallSizes);
        lib.setNumHalls(in.readInt());
        IHall[] buffer = new ScientificLibraryHall[lib.getNumHalls()];
        for (int i = 0; i < lib.getNumHalls(); i++) {
            ScientificLibraryHall hall = new ScientificLibraryHall();
            hall.setName(in.readUTF());
            hall.setBooks(in.readInt());
            List list = new List();
            for (int a = 0; a < hall.getBooks().getLength(); a++) {
                ScientificBook book = new ScientificBook();
                book.setName(in.readUTF());
                book.setAuthor(in.readUTF());
                book.setCost(in.readDouble());
                book.setYear(in.readInt());
                book.setCitationIndex(in.readDouble());
                list.addToEnd(book);
            }
            hall.setBooks(list);
            buffer[i] = hall;
        }
        lib.setLibraryHalls(new BidirectionalList(buffer));
        return lib;
    }
*/
}
