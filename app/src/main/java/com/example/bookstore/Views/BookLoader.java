package com.example.bookstore.Views;

import com.example.bookstore.Model.BookModel;
import com.example.bookstore.Model.CBook;

import java.util.List;

public interface BookLoader {

    void loadBooks(List<BookModel> list);
    void loadCB(List<CBook> list);
    void viewProgress();
    void hideProgress();

}
