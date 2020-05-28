package com.mvvm.ebookshop.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity (tableName = "categories_table")
public class Category extends BaseObservable {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "category_id")
    private int id;

    @ColumnInfo(name = "category_name")
    private String mCategoryName;

    @ColumnInfo(name = "category_description")
    private String mCategoryDescription;

    @Ignore
    public Category() {
    }

    public Category(int id, String mCategoryName, String mCategoryDescription) {
        this.id = id;
        this.mCategoryName = mCategoryName;
        this.mCategoryDescription = mCategoryDescription;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(com.mvvm.ebookshop.BR.id);
    }

    @Bindable
    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
        notifyPropertyChanged(com.mvvm.ebookshop.BR.categoryName);
    }

    @Bindable
    public String getCategoryDescription() {
        return mCategoryDescription;
    }

    public void setCategoryDescription(String mCategoryDescription) {
        this.mCategoryDescription = mCategoryDescription;
        notifyPropertyChanged(com.mvvm.ebookshop.BR.categoryDescription);
    }

    @NonNull
    @Override
    public String toString() {
        return this.mCategoryName;
    }
}
