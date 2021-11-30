package com.company.Halls;

import com.company.Books.IBook;
import com.company.Books.ScientificBook;
import com.company.Exceptions.BookIndexOutOfBoundsException;
import com.company.Exceptions.InvalidBookCountException;

import java.io.IOException;
import java.io.Writer;

public class ScientificLibraryHall implements IHall, Cloneable{
    private List scientificBooks;
    private String name;
    public List getBooks() {
        return scientificBooks;
    }

    public void setBooks(List scientificBooks) {
        this.scientificBooks = null;
        this.scientificBooks = new List();
        for(int i=0;i<scientificBooks.getLength();i++){
            this.scientificBooks.addToEnd(scientificBooks.getItemByID(i).getData());
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ScientificLibraryHall(String name, int numBook) throws InvalidBookCountException{
        if(numBook<0){
            throw new InvalidBookCountException();
        }
        setName(name);
        this.scientificBooks = null;
        this.scientificBooks = new List();
        for(int i=0;i<numBook;i++){
            scientificBooks.addToEnd(new ScientificBook());
        }
    }
    public ScientificLibraryHall() {
        setName("default name");
        setBooks(new List());
    }
    public ScientificLibraryHall(String name, List scientificBooks) {
        setName(name);
        setBooks(scientificBooks);
    }
    public void printBooks() {
        for (int i = 0; i < scientificBooks.getLength(); i++) {

            System.out.print(scientificBooks.getItemByID(i).getData().getName() + ", ");
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
        if(number<0 || number > scientificBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        return this.scientificBooks.getItemByID(number).getData();
    }
    public void redactBook(IBook book, int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > scientificBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        this.scientificBooks.getItemByID(number).setData((ScientificBook) book);
    }
    public void addBook(IBook book, int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > scientificBooks.getLength()+1){
            throw new BookIndexOutOfBoundsException();
        }
        scientificBooks.addByID(number, book);
    }
    public void deleteBook(int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > scientificBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        scientificBooks.removeByID(number);
    }
    public IBook getBestBook() {
        int max = 0;
        int indexMax = 0;
        for (int i = 0; i < scientificBooks.getLength(); i++) {
            if (scientificBooks.getItemByID(i).getData().getCost() >= max) {
                max = scientificBooks.getItemByID(i).getData().getCost();
                indexMax = i;
            }
        }
        return scientificBooks.getItemByID(indexMax).getData();
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
        ScientificLibraryHall that = (ScientificLibraryHall) o;
        return scientificBooks.equals(that.scientificBooks) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = (name == null) ? 0 : name.hashCode();
        result = result^scientificBooks.getLength();
        for(int i=0;i<scientificBooks.getLength();i++){
            result=result^scientificBooks.getBookByID(i).hashCode();
        }
        return result;
    }

    @Override
    public ScientificLibraryHall clone() {
        ScientificLibraryHall result = null;
        try {
            result = (ScientificLibraryHall) super.clone();
            List list = (List) this.getBooks().clone();
            result.setBooks(list);
        } catch (CloneNotSupportedException ex) { }
        return result;
    }

}
