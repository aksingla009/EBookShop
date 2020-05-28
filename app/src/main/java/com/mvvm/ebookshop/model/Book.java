package com.mvvm.ebookshop.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;


@Entity (tableName = "books_table",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "category_id",
                childColumns = "category_id",
                onDelete = CASCADE))
public class Book extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    private int mBookID;

    @ColumnInfo(name = "book_name")
    private String mBookName;

    @ColumnInfo(name = "unit_price")
    private String mUnitPrice;

    @ColumnInfo(name = "category_id")
    private int mCategoryId;

    @Ignore
    public Book() {
    }

    public Book(int mBookID, String mBookName, String mUnitPrice, int mCategoryId) {
        this.mBookID = mBookID;
        this.mBookName = mBookName;
        this.mUnitPrice = mUnitPrice;
        this.mCategoryId = mCategoryId;
    }

    @Bindable
    public int getBookID() {
        return mBookID;
    }

    public void setBookID(int mBookID) {
        this.mBookID = mBookID;
        notifyPropertyChanged(com.mvvm.ebookshop.BR.bookID);
    }

    @Bindable
    public String getBookName() {
        return mBookName;
    }

    public void setBookName(String mBookName) {
        this.mBookName = mBookName;
        notifyPropertyChanged(com.mvvm.ebookshop.BR.bookName);
    }

    @Bindable
    public String getUnitPrice() {
        return mUnitPrice;
    }

    public void setUnitPrice(String mUnitPrice) {
        this.mUnitPrice = mUnitPrice;
        notifyPropertyChanged(com.mvvm.ebookshop.BR.unitPrice);
    }

    @Bindable
    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int mCategoryId) {
        this.mCategoryId = mCategoryId;
        notifyPropertyChanged(com.mvvm.ebookshop.BR.categoryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return mBookID == book.mBookID &&
                mCategoryId == book.mCategoryId &&
                mBookName.equals(book.mBookName) &&
                mUnitPrice.equals(book.mUnitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mBookID, mBookName, mUnitPrice, mCategoryId);
    }
}
