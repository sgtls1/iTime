package com.example.itime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditClockActivity extends AppCompatActivity {

    private Button buttonOK,buttonCancel;
    private EditText editTextBookTitle,editTextBookPrice;
    private int insertPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clock);

        buttonOK=(Button)findViewById(R.id.button_ok);
        buttonCancel=(Button)findViewById(R.id.button_cancel);
        editTextBookPrice=(EditText)findViewById(R.id.edit_view_book_price);
        editTextBookTitle=(EditText)findViewById(R.id.edit_view_book_title);

        editTextBookTitle.setText(getIntent().getStringExtra("title"));
        double bookprice=getIntent().getDoubleExtra("price",0);
        editTextBookPrice.setText(bookprice+"");
        insertPosition=getIntent().getIntExtra("insert_position",0);

        buttonOK.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title",editTextBookTitle.getText().toString());
                intent.putExtra("insert_position",insertPosition);
                intent.putExtra("price",Double.parseDouble(editTextBookPrice.getText().toString()));
                setResult(RESULT_OK,intent);
                EditClockActivity.this.finish();
            }

        });
        buttonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditClockActivity.this.finish();
            }
        });
    }

}
