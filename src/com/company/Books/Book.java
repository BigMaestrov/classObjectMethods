package com.company.Books;

import com.company.Exceptions.InvalidBookPriceException;

import java.io.IOException;
import java.io.Writer;

public class Book implements IBook, Cloneable {
    private String author;
    private String name;
    private int cost;
    private int year;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) throws InvalidBookPriceException {
        if (cost < 0) {
            throw new InvalidBookPriceException();
        }
        this.cost = cost;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book() {
        setAuthor("Не определено");
        setName("Не определено");
        setCost(0);
        setYear(0);
    }

    public Book(String author, String name, int cost, int year) {
        setAuthor(author);
        setName(name);
        try {
            setCost(cost);
        } catch (InvalidBookPriceException e) {
            System.err.println(e);
        }
        setYear(year);
    }

    public Book(String author, int year) {
        this();
        setAuthor(author);
        setYear(year);
    }

    @Override
    public String toString() {
        return getAuthor() + " " + getName() + " " + getCost() + " " +
                getYear();
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
        Book book = (Book) o;
        return cost == book.cost && year == book.year && author.equals(book.author) && name.equals(book.name);
    }

    @Override
    public int hashCode() {
        int hashCodeAuthor = (author == null) ? 0 : author.hashCode();
        int hashCodeName = (author == null) ? 0 : author.hashCode();
        int result = hashCodeAuthor ^ hashCodeName;
        long temp = Double.doubleToLongBits(cost);
        result += (int) (temp^(temp>>>32))+year;
        return result;
    }

    @Override
    public Object clone() {
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException ex) { }
        return result;
    }

}

