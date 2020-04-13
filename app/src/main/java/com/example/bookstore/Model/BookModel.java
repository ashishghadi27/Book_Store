package com.example.bookstore.Model;

public class BookModel {

    private String bookId, bookTitle,bookAuthor, bookType, bookIndex, bookImg;

    public BookModel(){

    }

    public BookModel(String bookId, String bookTitle, String bookAuthor, String bookType, String bookIndex, String bookImg) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookType = bookType;
        this.bookIndex = bookIndex;
        this.bookImg = bookImg;
    }


    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookType() {
        return bookType;
    }

    public String getBookIndex() {
        return bookIndex;
    }

    public String getBookImg() {
        return bookImg;
    }
}
