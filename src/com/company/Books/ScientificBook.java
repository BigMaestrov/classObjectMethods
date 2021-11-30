package com.company.Books;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

public class ScientificBook extends Book implements Cloneable{
    private double citationIndex;
    public double getCitationIndex() {
        return citationIndex;
    }
    public void setCitationIndex(double citationIndex) {
        this.citationIndex = citationIndex;
    }
    public ScientificBook() {
        super();
        setCitationIndex(0.0);
    }
    public ScientificBook(String author, String name, int cost, int year, double citationIndex) {
        super(author, name, cost, year);
        setCitationIndex(citationIndex);
    }
    public ScientificBook(String author, int year) {
        this();
        setAuthor(author);
        setYear(year);
    }
    public static String toString(ScientificBook book){
        return book.getAuthor() + " " + book.getName() + " " +
                book.getCost() + " " + book.getYear() + " "+ book.getCitationIndex();
    }

    @Override
    public  String toString(){
        return this.getAuthor() + "\n" + this.getName() + "\n" +
                this.getCost() + "\n" + this.getYear() + "\n"+ this.getCitationIndex();
    }
    public void printBook(){
        System.out.println(getAuthor() + " " + getName() + " " + getCost()
                + " " + getYear() + " "+ getCitationIndex());
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
        ScientificBook that = (ScientificBook) o;
        return Double.compare(that.citationIndex, citationIndex) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp = Double.doubleToLongBits(citationIndex);
        result += (int) (temp^(temp>>>32));
        return result;
    }

    @Override
    public Object clone() {
        Object result = null;
        result = super.clone();
        return result;
    }
}
