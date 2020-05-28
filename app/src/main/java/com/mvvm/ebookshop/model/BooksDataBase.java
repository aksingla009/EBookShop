package com.mvvm.ebookshop.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class, Book.class},version = 1)
public abstract class BooksDataBase extends RoomDatabase {

    public abstract CategoryDAO getCategoryDAO();
    public abstract BookDAO getBookDAO();

    private static BooksDataBase instance;

    public static synchronized BooksDataBase getInstance(Context mContext){
        if(instance == null){
            instance = Room.databaseBuilder(mContext.getApplicationContext(),BooksDataBase.class,"books_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();

        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void,Void,Void>{
        private CategoryDAO categoryDAO;
        private BookDAO bookDAO;

        public InitialDataAsyncTask(BooksDataBase booksDataBase) {
            categoryDAO = booksDataBase.getCategoryDAO();
            bookDAO = booksDataBase.getBookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Category category1 = new Category();
            category1.setCategoryName("Text Books");
            category1.setCategoryDescription("Read to score good in Academics");


            Category category2 = new Category();
            category2.setCategoryName("Novels");
            category2.setCategoryDescription("Fictional stories");

            Category category3 = new Category();
            category3.setCategoryName("Comic Magzines");
            category3.setCategoryDescription("Pure Entertainment");

            categoryDAO.insertCategory(category1);
            categoryDAO.insertCategory(category2);
            categoryDAO.insertCategory(category3);

            Book book1 = new Book();
            book1.setCategoryId(1);
            book1.setBookName("Physics");
            book1.setUnitPrice("$198");


            Book book2 = new Book();
            book2.setCategoryId(1);
            book2.setBookName("Maths");
            book2.setUnitPrice("$100");

            Book book3 = new Book();
            book3.setCategoryId(1);
            book3.setBookName("Chemistry");
            book3.setUnitPrice("$250");


            Book book4 = new Book();
            book4.setCategoryId(2);
            book4.setBookName("Half Girlfriend");
            book4.setUnitPrice("$298");


            Book book5 = new Book();
            book5.setCategoryId(2);
            book5.setBookName("Two States");
            book5.setUnitPrice("$120");

            Book book6 = new Book();
            book6.setCategoryId(2);
            book6.setBookName("Alchemist");
            book6.setUnitPrice("$350");

            Book book7 = new Book();
            book7.setCategoryId(3);
            book7.setBookName("Marvels Universe IRON MAN");
            book7.setUnitPrice("$350");

            Book book8 = new Book();
            book8.setCategoryId(3);
            book8.setBookName("DC Universe BAT MAN");
            book8.setUnitPrice("$375");

            bookDAO.insertBook(book1);
            bookDAO.insertBook(book2);
            bookDAO.insertBook(book3);
            bookDAO.insertBook(book4);
            bookDAO.insertBook(book5);
            bookDAO.insertBook(book6);
            bookDAO.insertBook(book7);
            bookDAO.insertBook(book8);

            return null;
        }
    }

}
