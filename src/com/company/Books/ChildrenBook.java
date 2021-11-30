package com.company.Books;

import java.io.IOException;
import java.io.Writer;

public class ChildrenBook extends Book implements Cloneable {
    private int minimalAge;

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public ChildrenBook(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public ChildrenBook() {
        super();
        setMinimalAge(0);
    }

    public ChildrenBook(String author, String name, int cost, int year, int minimalAge) {
        super(author, name, cost, year);
        setMinimalAge(minimalAge);
    }

    public ChildrenBook(String author, int year) {
        this();
        setAuthor(author);
        setYear(year);
    }

    @Override
    public String toString() {
        return getAuthor() + " " + getName() + " " + getCost() +
                " " + getYear() + " " + getMinimalAge();
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
        if (!super.equals(o)) return false;
        ChildrenBook that = (ChildrenBook) o;
        return minimalAge == that.minimalAge;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + minimalAge;
    }

    @Override
    public Object clone() {
        Object result = null;
        result = super.clone();
        return result;
    }
}
