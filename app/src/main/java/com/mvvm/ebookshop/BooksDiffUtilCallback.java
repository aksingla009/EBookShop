package com.mvvm.ebookshop;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.mvvm.ebookshop.model.Book;

import java.util.ArrayList;

public class BooksDiffUtilCallback extends DiffUtil.Callback {
    ArrayList<Book>oldBooksList;
    ArrayList<Book>newBooksList;

    public BooksDiffUtilCallback(ArrayList<Book> oldBooksList, ArrayList<Book> newBooksList) {
        this.oldBooksList = oldBooksList;
        this.newBooksList = newBooksList;
    }

    @Override
    public int getOldListSize() {
        return oldBooksList == null ? 0 : oldBooksList.size();
    }

    @Override
    public int getNewListSize() {
        return newBooksList == null ? 0 : newBooksList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldBooksList.get(oldItemPosition).getBookID() == newBooksList.get(newItemPosition).getBookID();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldBooksList.get(oldItemPosition).equals(newBooksList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
