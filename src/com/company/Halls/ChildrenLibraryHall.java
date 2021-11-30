package com.company.Halls;

import com.company.Books.ChildrenBook;
import com.company.Books.IBook;
import com.company.Exceptions.BookIndexOutOfBoundsException;
import com.company.Exceptions.InvalidBookCountException;

import java.io.IOException;
import java.io.Writer;

public class ChildrenLibraryHall implements IHall, Cloneable{
    private List childrenBooks;
    private String name;
    public List getBooks() {
        return childrenBooks;
    }

    public void setBooks(List scientificBooks) {
        this.childrenBooks = null;
        this.childrenBooks = new List();
        for(int i=0;i<scientificBooks.getLength();i++){
            this.childrenBooks.addToEnd(scientificBooks.getItemByID(i).getData());
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ChildrenLibraryHall(String name, int numBook) throws InvalidBookCountException{
        if(numBook<0){
            throw new InvalidBookCountException();
        }
        setName(name);
        this.childrenBooks = null;
        this.childrenBooks = new List();
        for(int i=0;i<numBook;i++){
            childrenBooks.addToEnd(new ChildrenBook());
        }
    }
    public ChildrenLibraryHall() {
        setName("default name");
        setBooks(new List());
    }
    public ChildrenLibraryHall(String name, List scientificBooks) {
        setName(name);
        setBooks(scientificBooks);
    }
    public void printBooks() {
        for (int i = 0; i < childrenBooks.getLength(); i++) {

            System.out.print(childrenBooks.getItemByID(i).getData().getName() + ", ");
        }
    }
    public int getCostOfAllBooks(IHall childrenLibraryHall) {
        int cost = 0;
        for (int i = 0; i < childrenLibraryHall.getBooks().getLength(); i++) {
            cost += childrenLibraryHall.getBooks().getItemByID(i).getData().getCost();
        }
        return cost;
    }
    public IBook getBookByID(int number) throws BookIndexOutOfBoundsException {
        if(number<0 || number > childrenBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        return this.childrenBooks.getItemByID(number).getData();
    }
    public void redactBook(IBook book, int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > childrenBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        this.childrenBooks.getItemByID(number).setData((ChildrenBook) book);
    }
    public void addBook(IBook book, int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > childrenBooks.getLength()+1){
            throw new BookIndexOutOfBoundsException();
        }
        childrenBooks.addByID(number, book);
    }

    public void deleteBook(int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > childrenBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        childrenBooks.removeByID(number);
    }
    public IBook getBestBook() {
        int max = 0;
        int indexMax = 0;
        for (int i = 0; i < childrenBooks.getLength(); i++) {
            if (childrenBooks.getItemByID(i).getData().getCost() >= max) {
                max = childrenBooks.getItemByID(i).getData().getCost();
                indexMax = i;
            }
        }
        return childrenBooks.getItemByID(indexMax).getData();
    }

    @Override
    public String toString(){
        StringBuilder buffer = new StringBuilder();
        buffer.append(getClass()+"\n");
        buffer.append(getName()+"\n");
        buffer.append(getBooks().getLength()+"\n");
        for(int i=0;i<getBooks().getLength();i++){
            buffer.append(getBookByID(i).toString()+"\n");
        }
        return buffer.toString();
    }

    public void writeInFile(Writer out) {
        try {
            out.write(this.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildrenLibraryHall that = (ChildrenLibraryHall) o;
        return childrenBooks.equals(that.childrenBooks) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = (name == null) ? 0 : name.hashCode();
        result = result^childrenBooks.getLength();
        for(int i=0;i<childrenBooks.getLength();i++){
            result=result^childrenBooks.getBookByID(i).hashCode();
        }
        return result;
    }

    @Override
    protected ChildrenLibraryHall clone() throws CloneNotSupportedException {
        return (ChildrenLibraryHall) super.clone();
    }
}
