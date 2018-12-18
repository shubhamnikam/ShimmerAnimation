package com.theappnerds.shubham.myshimmer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context context;
    private List<BookItem> bookItemList;

    public BookAdapter(Context context, List<BookItem> bookItemList) {
        this.context = context;
        this.bookItemList = bookItemList;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {

        BookItem bookItem = bookItemList.get(position);
        holder.name.setText(bookItem.getName());
        holder.author.setText("By " + bookItem.getAuthor());
        holder.description.setText(bookItem.getDescription());
        holder.price.setText("Price: ₹" + bookItem.getPrice());
        holder.release.setText(bookItem.getRelease());

        //Load image Using Glide library
        Glide.with(context)
                .load(bookItem.getThumbnail())
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return bookItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name, description, price, author, release;
        public ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            author = view.findViewById(R.id.author);
            description = view.findViewById(R.id.description);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
            release = view.findViewById(R.id.release);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //get onClick positon
            int position = getAdapterPosition();
            Toast.makeText(context, "Position : " + position, Toast.LENGTH_SHORT).show();

        }
    }
}
