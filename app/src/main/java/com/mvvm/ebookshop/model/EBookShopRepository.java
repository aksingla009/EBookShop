package com.mvvm.ebookshop.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EBookShopRepository {

    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private LiveData<List<Category>> categoriesList;
    private LiveData<List<Book>> booksList;

    public EBookShopRepository(Application application) {
        BooksDataBase booksDataBase = BooksDataBase.getInstance(application);
        categoryDAO = booksDataBase.getCategoryDAO();
        bookDAO = booksDataBase.getBookDAO();
    }

    public LiveData<List<Category>> getCategoriesList() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Book>> getBooksListForParticularCategoryID(int catID) {
        return bookDAO.getBooksForParticularCategory(catID);
    }

    public void insertCategory(Category category){
        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insertCategory(categories[0]);
            return null;
        }
    }

    public void deleteCategory(Category category){
        new DeleteCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        private CategoryDAO categoryDAO;

        public DeleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.deleteCategory(categories[0]);
            return null;
        }
    }

    public void updateCategory(Category category){
        new UpdateCategoryAsyncTask(categoryDAO).execute(category);
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        private CategoryDAO categoryDAO;

        public UpdateCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.updateCategory(categories[0]);
            return null;
        }
    }

    public void insertBook(final Book book){
        //new InsertBookAsyncTask(bookDAO).execute(book);

        Executor addBookExecutor = Executors.newSingleThreadExecutor();
        addBookExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDAO.insertBook(book);
            }
        });
    }

    /*public void insertBook(Book book){
        new InsertBookAsyncTask(bookDAO).execute(book);
    }

    private static class InsertBookAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDAO bookDAO;

        public InsertBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.insertBook(books[0]);
            return null;
        }
    }*/

    public void deleteBook(Book book){
        new DeleteBookAsyncTask(bookDAO).execute(book);
    }

    private static class DeleteBookAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDAO bookDAO;

        public DeleteBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.deleteBook(books[0]);
            return null;
        }
    }

    public void updateBook(Book book){
        new UpdateBookAsyncTask(bookDAO).execute(book);
    }

    private static class UpdateBookAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDAO bookDAO;

        public UpdateBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.updateBook(books[0]);
            return null;
        }
    }
}
