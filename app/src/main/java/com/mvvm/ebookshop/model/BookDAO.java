package com.mvvm.ebookshop.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDAO {

    @Insert
    void insertBook(Book book);

    @Update
    void updateBook(Book book);

    @Delete
    void deleteBook(Book book);

    @Query("SELECT * FROM books_table")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM books_table where category_id ==:categoryId")
    LiveData<List<Book>> getBooksForParticularCategory(int categoryId);

}
