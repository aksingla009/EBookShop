package com.mvvm.ebookshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mvvm.ebookshop.databinding.BookLlistItemBinding;
import com.mvvm.ebookshop.model.Book;

import java.util.ArrayList;

public class BooksRecyclerAdapter extends RecyclerView.Adapter<BooksRecyclerAdapter.BookViewHolder>{

    private OnRecyclerItemClickListener listener;
    private ArrayList<Book> booksList = new ArrayList<>();

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookLlistItemBinding bookLlistItemBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.book_llist_item,
                        parent,
                        false);
        return new BookViewHolder(bookLlistItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.booksListItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public void setBooksList(ArrayList<Book> newBooksList) {
        /*this.booksList = newBooksList;
        notifyDataSetChanged();*/

        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new BooksDiffUtilCallback(booksList,newBooksList),false);
        booksList = newBooksList;
        result.dispatchUpdatesTo(BooksRecyclerAdapter.this);


    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        private BookLlistItemBinding booksListItemBinding;

        public BookViewHolder(@NonNull BookLlistItemBinding bookLlistItemBinding) {
            super(bookLlistItemBinding.getRoot());
            this.booksListItemBinding = bookLlistItemBinding;

            bookLlistItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPos = getAdapterPosition();
                    if(listener != null && clickedPos != RecyclerView.NO_POSITION){
                    listener.onBookItemClick(booksList.get(clickedPos));
                    }
                }
            });
        }
    }

    public interface OnRecyclerItemClickListener{
        void onBookItemClick(Book book);
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }
}

