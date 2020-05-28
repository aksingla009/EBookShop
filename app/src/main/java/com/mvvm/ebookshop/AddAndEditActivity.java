package com.mvvm.ebookshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mvvm.ebookshop.databinding.ActivityAddAndEditBinding;
import com.mvvm.ebookshop.model.Book;

public class AddAndEditActivity extends AppCompatActivity {

    private Book book;
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_PRICE = "book_unit_price";

    //this class is auto generated because of layout tag in xml file
    private ActivityAddAndEditBinding activityAddAndEditBinding;

    private AddAndEditActivityClickHandler addAndEditActivityClickHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);
        book = new Book();
        activityAddAndEditBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_and_edit);
        activityAddAndEditBinding.setBook(book);


        addAndEditActivityClickHandler = new AddAndEditActivityClickHandler(this);
        activityAddAndEditBinding.setClickHandler(addAndEditActivityClickHandler);


        Intent intent = getIntent();
        if(intent.hasExtra(BOOK_ID)){
            //it means we will launch this activity as an edit operation
            setTitle("Edit Book Details");
            book.setBookName(intent.getStringExtra(BOOK_NAME));
            book.setUnitPrice(intent.getStringExtra(BOOK_PRICE));
        }else{
            setTitle("Add new book");
        }

    }

    public class AddAndEditActivityClickHandler{
        private Context context;

        public AddAndEditActivityClickHandler(Context context) {
            this.context = context;
        }

        public void OnSubmitButtonClicked(View view){
            if(book.getBookName() == null){
                Toast.makeText(context, "Enter the book name", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent();
                intent.putExtra(BOOK_NAME,book.getBookName());
                intent.putExtra(BOOK_PRICE,book.getUnitPrice());
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }
}
