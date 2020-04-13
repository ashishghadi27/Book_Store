package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookstore.Adapters.BookListAdapter;
import com.example.bookstore.DBHandler.DataOperations;
import com.example.bookstore.Model.BookModel;
import com.example.bookstore.Model.CBook;
import com.example.bookstore.Views.BookLoader;

import java.util.List;

public class Recommend extends AppCompatActivity implements BookLoader {

    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;
    private DataOperations dataOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        dataOperations = new DataOperations(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dataOperations.loadRecommended(this);
    }

    @Override
    public void loadBooks(List<BookModel> list) {
        bookListAdapter = new BookListAdapter(list,Recommend.this);
        recyclerView.setAdapter(bookListAdapter);
    }

    @Override
    public void loadCB(List<CBook> list) {

    }

    @Override
    public void viewProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
