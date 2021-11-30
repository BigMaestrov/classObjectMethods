package com.company.Librarys;

import com.company.Books.IBook;
import com.company.Books.ChildrenBook;
import com.company.Exceptions.BookIndexOutOfBoundsException;
import com.company.Exceptions.HallIndexOutOfBoundsException;
import com.company.Halls.IHall;

import java.io.IOException;
import java.io.Writer;

public class ChildrenLibrary implements ILibrary, Cloneable{
    int numHalls;
    BidirectionalList childrenLibraryHalls;

    public String getType(){
        return "children";
    }

    public int getNumHalls() {
        return numHalls;
    }

    public void setNumHalls(int numHalls) {
        this.numHalls = numHalls;
    }

    public BidirectionalList getLibraryHalls() {
        return childrenLibraryHalls;
    }

    public IHall getLibraryHallsByID(int id) {
        return childrenLibraryHalls.getItemByID(id).getData();
    }

    public IBook getBookByID(int id)throws BookIndexOutOfBoundsException {
        if(id<0 || id>sumOfAllBooks()){
            throw new BookIndexOutOfBoundsException();
        }
        IBook[] booksInLibrary = new IBook[sumOfAllBooks()];
        int numBookInLibrary = 0;
        //Запись в новый массив
        for (int i = 0; i < childrenLibraryHalls.getLength(); i++) {
            for (int j = 0; j < childrenLibraryHalls.getItemByID(i).getData().getBooks().getLength(); j++) {
                booksInLibrary[numBookInLibrary] = childrenLibraryHalls.getItemByID(i).getData().getBookByID(j);
                numBookInLibrary++;
            }
        }
        return booksInLibrary[id];
    }

    public void setLibraryHalls(BidirectionalList scientificLibraryHalls) {
        this.childrenLibraryHalls = null;
        this.childrenLibraryHalls = new BidirectionalList();
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {

            this.childrenLibraryHalls.addToEnd(scientificLibraryHalls.getItemByID(i).getData());
        }
    }

    public ChildrenLibrary(BidirectionalList scientificLibraryHalls) {
        setNumHalls(scientificLibraryHalls.getLength());
        setLibraryHalls(scientificLibraryHalls);
    }

    public ChildrenLibrary() {
        setNumHalls(0);
    }

    public int sumOfAllBooks() {
        int numBook = 0;
        for (int i = 0; i < getNumHalls(); i++) {
            numBook +=
                    childrenLibraryHalls.getItemByID(i).getData().getBooks().getLength();
        }
        return numBook;
    }

    public IBook[] selectionSortBookInHallByCost() {
        IBook[] booksInLibrary = new IBook[sumOfAllBooks()];
        int numBookInLibrary = 0;
        //Запись в новый массив
        for (int i = 0; i < childrenLibraryHalls.getLength(); i++) {
            for (int j = 0; j <
                    childrenLibraryHalls.getItemByID(i).getData().getBooks().getLength(); j++) {
                booksInLibrary[numBookInLibrary] = childrenLibraryHalls.getItemByID(i).getData().getBookByID(j);
                numBookInLibrary++;
            }
        }
        //Сортировка
        for (int left = 0; left < booksInLibrary.length; left++) {
            int maxInd = left;
            for (int i = left; i < booksInLibrary.length; i++) {
                if (booksInLibrary[i].getCost() >
                        booksInLibrary[maxInd].getCost()) {
                    maxInd = i;
                }
            }
            swap(booksInLibrary, left, maxInd);
        }
        return booksInLibrary;
    }

    public void swap(IBook[] books, int left, int minId) {
        IBook book = books[left];
        books[left] = books[minId];
        books[minId] = book;
    }

    public void printNamesAndNumBooksOfHalls() {
        for (int i = 0; i < getNumHalls(); i++) {
            System.out.println("Name:" +
                    childrenLibraryHalls.getItemByID(i).getData().getName() + ", NumOfBook:" +
                    childrenLibraryHalls.getItemByID(i).getData().getBooks().getLength());
        }
    }

    public void changeHallByID(int numHall, IHall newHall) throws HallIndexOutOfBoundsException {
        if(numHall<0 || numHall>getNumHalls()){
            throw new HallIndexOutOfBoundsException();
        }
        childrenLibraryHalls.removeByID(numHall);
        childrenLibraryHalls.addByID(numHall, newHall);
    }

    public void changeBookByID(int num, IBook book) {
        int IDofBook = 0;
        //Запись в новый массив
        for (int i = 0; i < childrenLibraryHalls.getLength(); i++) {
            for (int j = 0; j <

                    childrenLibraryHalls.getItemByID(i).getData().getBooks().getLength(); j++){
                if (num == IDofBook) {

                    childrenLibraryHalls.getItemByID(i).getData().redactBook(book, j);
                }
                IDofBook++;
            }
        }
    }

    public void addBookByID(int number, IBook book) {
        if (number < 0) {
            return;
        }
        if (number > sumOfAllBooks()) {
            return;
        }
        int numBookInLibrary = 0;
        for (int i = 0; i < childrenLibraryHalls.getLength(); i++) {
            for (int j = 0; j <
                    childrenLibraryHalls.getItemByID(i).getData().getBooks().getLength(); j++, numBookInLibrary++) {
                if (numBookInLibrary == number) {

                    childrenLibraryHalls.getItemByID(i).getData().getBooks().addByID(number, book);
                }
            }
        }
    }

    public void addHall(IHall hall, int number){
        if(number<0 || number > childrenLibraryHalls.getLength()+1){
            throw new BookIndexOutOfBoundsException();
        }
        childrenLibraryHalls.addByID(number, hall);
    }

    public void deleteBookFromLibrary(int number) {
        if (number < 0) {
            return;
        }
        if (number > sumOfAllBooks()) {
            return;
        }
        int numBookInLibrary = 0;
        for (int i = 0; i < childrenLibraryHalls.getLength(); i++) {
            for (int j = 0; j <
                    childrenLibraryHalls.getItemByID(i).getData().getBooks().getLength(); j++, numBookInLibrary++) {
                if (numBookInLibrary == number) {

                    childrenLibraryHalls.getItemByID(i).getData().getBooks().removeByID(number);
                }
            }
        }
    }

    public IBook getBestBook() {
        IBook bestBook = new ChildrenBook();
        for (int i = 0; i < childrenLibraryHalls.getLength(); i++) {
            if (bestBook.getCost() <
                    childrenLibraryHalls.getItemByID(i).getData().getBestBook().getCost())
                bestBook = childrenLibraryHalls.getItemByID(i).getData().getBestBook();
        }
        return bestBook;
    }

    public void printBooks() {
        for (int i = 0; i < childrenLibraryHalls.getLength(); i++) {

            childrenLibraryHalls.getItemByID(i).getData().printBooks();
        }
    }

    public void printBooks(IBook[] books) {
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].toString());
        }
    }

    @Override
    public String toString(){
        String buffer = "";
        buffer+=getLibraryHalls().getLength()+"\n";
        for(int i=0;i<getLibraryHalls().getLength();i++){
            buffer+=getBookByID(i).toString();
        }
        return buffer;
    }

    public void writeInFile(Writer out) {
        try {
            out.write(getType()+"\n");
            out.write(this.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildrenLibrary that = (ChildrenLibrary) o;
        return numHalls == that.numHalls && childrenLibraryHalls.equals(that.childrenLibraryHalls);
    }

    @Override
    public int hashCode() {
        int result = childrenLibraryHalls.getLength();
        for(int i=0;i<childrenLibraryHalls.getLength();i++){
            result=result^childrenLibraryHalls.getItemByID(i).getData().hashCode();
        }
        return result;
    }

    @Override
    protected ChildrenLibrary clone() throws CloneNotSupportedException {
        return (ChildrenLibrary) super.clone();
    }
}
