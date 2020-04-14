package com.example.bookstore;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookstore.DBHandler.DataOperations;
import com.example.bookstore.Model.BookModel;
import com.example.bookstore.Model.CBook;
import com.example.bookstore.Views.BookLoader;

import java.util.List;

public class BookDetail extends AppCompatActivity implements BookLoader {

    private List<CBook> list;
    private DataOperations dataOperations;
    private String id;
    private TextView title, author, type, subject, summary;
    private ImageView bookImg, pdf, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        id = intent.getStringExtra("bookId");

        dataOperations = new DataOperations(this);
        title = findViewById(R.id.book_title);
        author = findViewById(R.id.book_author);
        type = findViewById(R.id.book_status_1);
        subject = findViewById(R.id.subject);
        summary = findViewById(R.id.description_book);
        bookImg = findViewById(R.id.bookImage);
        pdf = findViewById(R.id.pdf);
        back =findViewById(R.id.back);
        dataOperations.completeBookData(id,this);


        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download(list.get(0).getBookPdf(), list.get(0).getBookTitle());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void dataSetter(){
        title.setText(list.get(0).getBookTitle());
        author.setText(list.get(0).getBookAuthor());
        if(list.get(0).getBookType().equals("1")) {
            type.setText("FREE");
            type.setTextColor(getResources().getColor(R.color.free));
        }
        else{
            type.setText("PAID");
            type.setTextColor(getResources().getColor(R.color.paid));
        }
        Glide.with(BookDetail.this)
                .load(list.get(0).getBookImg())
                .centerCrop()
                .placeholder(R.drawable.book)
                .error(R.drawable.book)
                .into(bookImg);
        String sub = list.get(0).getBookSubject();
        String summa = list.get(0).getBookSummary();
        if(sub.length() > 4){
            subject.setText(sub);
        }else subject.setText("No Subject");
        if(summa.length() > 4){
            summary.setText(summa);
        }else summary.setText("No Summary");

        if(list.get(0).getBookPdf().length() < 5)
            pdf.setVisibility(View.GONE);
    }

    private void download(String url, String name){

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        request.setTitle(name);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name+".pdf");

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void loadBooks(List<BookModel> listBook) {

    }

    @Override
    public void loadCB(List<CBook> listBook) {
        list = listBook;
        dataOperations.setRecommended(id,
                list.get(0).getBookSubject());
        dataSetter();
    }

    @Override
    public void viewProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
