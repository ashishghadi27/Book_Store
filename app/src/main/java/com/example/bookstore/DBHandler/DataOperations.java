package com.example.bookstore.DBHandler;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bookstore.MainActivity;
import com.example.bookstore.Model.BookModel;
import com.example.bookstore.Model.CBook;
import com.example.bookstore.Model.UserReg;
import com.example.bookstore.Views.BookLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataOperations {

    private DatabaseReference mDatabase;
    private FirebaseAuth mauth;
    private String userid;
    private Context context;


    public DataOperations(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.context = context;
        mauth = FirebaseAuth.getInstance();
        userid = mauth.getUid();
    }

    public void createUser(String name, String email, String userid){
        UserReg userReg = new UserReg(name, email);
        mDatabase.child("users").child(userid).child("userdata").setValue(userReg);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();

    }

    public void setRecommended(String key, String bookId, String bookName, String bookAuthor, String bookType, String bookIndex, String bookImg, String bookSubject, String bookSummary, String bookPdf, String rating){
        CBook book = new CBook(bookId, bookName, bookAuthor, bookType, bookIndex, bookImg, bookSubject, bookSummary, bookPdf, rating);
        mDatabase.child("users").child(userid).child("recommended").child(key).setValue(book);
    }


    public void loadRecommended(final BookLoader bookLoader){

        final List<BookModel> list = new ArrayList<>();
        bookLoader.viewProgress();

        DatabaseReference passDataRef = mDatabase.child("users").child(userid).child("recommended").getRef();
        passDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    BookModel data = snapshot.getValue(BookModel.class);
                    assert data != null;
                    String bookId = snapshot.getKey();
                    String bookName = data.getBookTitle();
                    String bookAuthor = data.getBookAuthor();
                    String bookType = data.getBookType();
                    String bookIndex = data.getBookIndex();
                    String bookImg = data.getBookImg();
                    BookModel book = new BookModel(bookId, bookName, bookAuthor, bookType, bookIndex, bookImg);
                    list.add(book);
                }
                bookLoader.loadBooks(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void search(final BookLoader bookLoader, final String query){

        final List<BookModel> list = new ArrayList<>();
        bookLoader.viewProgress();

        DatabaseReference passDataRef = mDatabase.child("books").getRef();
        passDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    BookModel data = snapshot.getValue(BookModel.class);
                    assert data != null;
                    String bookId = snapshot.getKey();
                    String bookName = data.getBookTitle();
                    String bookAuthor = data.getBookAuthor();
                    String bookType = data.getBookType();
                    String bookIndex = data.getBookIndex();
                    String bookImg = data.getBookImg();
                    if(bookIndex.contains(query)||bookName.contains(query)){
                        BookModel book = new BookModel(bookId, bookName, bookAuthor, bookType, bookIndex, bookImg);
                        list.add(book);
                    }
                }
                bookLoader.loadBooks(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void completeBookData(String id, final BookLoader loader){

        id = (int)Float.parseFloat(id)+"";
        Log.v("book", id+" d");
        final List<CBook> list = new ArrayList<>();

        DatabaseReference passDataRef = mDatabase.child("books").child(id).getRef();
        passDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                    CBook data = dataSnapshot.getValue(CBook.class);
                    assert data != null;

                        String bookId = data.getBookId();
                        String bookName = data.getBookTitle();
                        String bookAuthor = data.getBookAuthor();
                        String bookType = data.getBookType();
                        String bookIndex = data.getBookIndex();
                        String bookImg = data.getBookImg();
                        String bookSubject = data.getBookSubject();
                        String bookSummary = data.getBookSummary();
                        String bookPdf = data.getBookPdf();
                        String rating = data.getRating();
                        Log.v("BOOK NAme", bookName+" dfv");
                        CBook book = new CBook(bookId, bookName, bookAuthor, bookType, bookIndex, bookImg, bookSubject, bookSummary, bookPdf, rating);
                        list.add(book);
                        loader.loadCB(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
