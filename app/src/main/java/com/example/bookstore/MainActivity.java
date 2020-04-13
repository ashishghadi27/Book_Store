package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bookstore.Adapters.BookListAdapter;
import com.example.bookstore.DBHandler.DataOperations;
import com.example.bookstore.Model.BookModel;
import com.example.bookstore.Model.CBook;
import com.example.bookstore.Views.BookLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BookLoader {

    private RecyclerView recyclerView;
    private BookListAdapter bookListAdapter;
    private DataOperations dataOperations;
    private EditText search;
    private ImageView recommend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataOperations = new DataOperations(this);
        recyclerView = findViewById(R.id.recyclerView);
        search = findViewById(R.id.search);
        recommend = findViewById(R.id.recommend);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Recommend.class);
                startActivity(intent);
            }
        });

        dataOperations.search(this, "");
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dataOperations.search(MainActivity.this, search.getText()+"");
            }
        });

    }

    @Override
    public void loadBooks(List<BookModel> list) {
        bookListAdapter = new BookListAdapter(list,MainActivity.this);
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
