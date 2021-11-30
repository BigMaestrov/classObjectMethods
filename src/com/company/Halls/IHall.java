package com.company.Halls;

import com.company.Books.IBook;

import java.io.Serializable;
import java.io.Writer;

public interface IHall extends Serializable {
    public List getBooks();
    public void setBooks(List scientificBooks) ;
    public String getName();
    public void setName(String name);
    public void printBooks() ;
    public  int getCostOfAllBooks(IHall childrenLibraryHall);
    public IBook getBookByID(int number);
    public void redactBook(IBook book, int number);
    public void addBook(IBook book, int number);
    public void deleteBook(int number);
    public IBook getBestBook() ;
    public void writeInFile(Writer out);
}
