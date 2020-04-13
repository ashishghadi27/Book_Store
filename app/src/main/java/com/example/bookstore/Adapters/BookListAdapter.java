package com.example.bookstore.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookstore.BookDetail;
import com.example.bookstore.Model.BookModel;
import com.example.bookstore.R;

import java.util.List;


public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {

    private List<BookModel> listItems;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView bookName, author, id, type;
        private ImageView bookImg;

        public MyViewHolder(View view) {
            super(view);
            bookName = view.findViewById(R.id.bookName);
            author = view.findViewById(R.id.bookAuthor);
            id = view.findViewById(R.id.bookId);
            type = view.findViewById(R.id.bookType);
            bookImg = view.findViewById(R.id.book_img_2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BookDetail.class);
                    intent.putExtra("bookId", id.getText()+"");
                    context.startActivity(intent);
                }
            });
        }

    }

    public BookListAdapter(List<BookModel> title, Context context) {
        this.listItems = title;
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_lay, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final BookModel list = listItems.get(position);
        holder.bookName.setText(list.getBookTitle());
        holder.author.setText(list.getBookAuthor());
        holder.id.setText(list.getBookId());
        if(list.getBookType().equals("1")) {
            holder.type.setText("FREE");
            holder.type.setTextColor(context.getResources().getColor(R.color.free));
        }
        else{
            holder.type.setText("PAID");
            holder.type.setTextColor(context.getResources().getColor(R.color.paid));
        }
        Glide.with(context)
                .load(list.getBookImg())
                .centerCrop()
                .placeholder(R.drawable.book)
                .error(R.drawable.book)
                .into(holder.bookImg);

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

}
