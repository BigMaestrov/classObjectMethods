package com.company.Halls;

import com.company.Books.Book;
import com.company.Books.IBook;

import java.io.Serializable;

public class List implements Serializable {
    class Item implements Serializable{
        private IBook data;
        private Item next;

        //конструктор
        public Item(IBook st, Item nextItem) {
            //копируем поля из параметра st в поле data
            data = st;
            //Устанавливаем указатель последнего на голову
            this.next = nextItem;
        }

        //конструктор
        public Item() {
            data = new Book();
            this.next = this;
        }

        public void setData(IBook book) {
            this.data = book;
        }

        public IBook getData() {
            return this.data;
        }

        public void setNext(Item next) {
            this.next = next;
        }

        public Item getNext() {
            return this.next;
        }
    }

    private int length;
    private Item Head;

    public List() {
        length = 0;
        Head = new Item();
    }

    public List(int length) {
        Head = new Item();
        for (int i = 0; i < length; i++) {
            addToEnd(new Book());
        }
    }

    public int getLength() {
        return length;
    }

    boolean isEmpty() {
        if (Head.next == Head) return true;
        else return false;
    }

    public Item getItemByID(int ID) {
        if (ID < 0) return null;
        Item temp = Head.next;
        for (int i = 0; i < ID; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public IBook getBookByID(int ID) {
        if (ID < 0) return null;
        Item temp = Head.next;
        for (int i = 0; i < ID; i++) {
            temp = temp.next;
        }
        return temp.getData();
    }

    public void addToEnd(IBook st) {
        length++;
        Item prev = Head;
        for (int i = 0; i < length; i++) {
            prev = prev.next;
        }
        prev.next = new Item(st, prev.next);
        return;
    }

    public void addByID(int ID, IBook elem) {
        length++;
        if (isEmpty()) {
            Head.next = new Item(elem, Head);
            return;
        }
        if (ID < 0) ID = 0;
        if (ID > length) ID = length;
        Item prev = Head;
        for (int i = 0; i < ID; i++) {
            prev = prev.next;
        }
        prev.next = new Item(elem, prev.next);
        return;
    }
/*
    public List(IBook[] array){
        this();
        length = array.length;
        Item temp;
        Item prev = new Item(array[0], head);
        head.next = prev;
        for(int i = 1; i < array.length; i++){
            temp = new Item(array[i], head);
            prev.next = temp;
            prev = prev.next;
        }
    }
*/
    public void removeByID(int ID) {
        if (ID > length || ID < 0 || isEmpty()) {
            return;
        }
        Item prev = Head;
        for (int i = 0; i < ID; i++) {
            prev = prev.next;
        }
        Item temp = prev.next.next;
        prev.next = temp;
        length--;
    }

    void deleteLast() {
        removeByID(length);
    }

    void showBookInfo(Item temp) {
        System.out.println(temp.data.toString());
    }
}
