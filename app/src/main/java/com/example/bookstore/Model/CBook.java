package com.example.bookstore.Model;

public class CBook {

    private String bookId, bookTitle,bookAuthor, bookType, bookIndex, bookImg, bookSubject, bookSummary, bookPdf, rating;

    public CBook(){

    }

    public CBook(String bookId, String bookTitle, String bookAuthor, String bookType, String bookIndex, String bookImg, String bookSubject, String bookSummary, String bookPdf, String rating) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookType = bookType;
        this.bookIndex = bookIndex;
        this.bookImg = bookImg;
        this.bookSubject = bookSubject;
        this.bookSummary = bookSummary;
        this.bookPdf = bookPdf;
        this.rating = rating;
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

    public String getBookSubject() {
        return bookSubject;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public String getBookPdf() {
        return bookPdf;
    }

    public String getRating() {
        return rating;
    }

}
