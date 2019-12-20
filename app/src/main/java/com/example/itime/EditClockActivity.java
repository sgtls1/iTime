package com.example.itime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditClockActivity extends AppCompatActivity {

    private Button buttonOK,buttonCancel;
    private EditText editTextClockTitle,editClockContent;
    private int insertPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clock);

        buttonOK=(Button)findViewById(R.id.button_ok);
        buttonCancel=(Button)findViewById(R.id.button_cancel);
        editClockContent=(EditText)findViewById(R.id.edit_view_Clock_content);
        editTextClockTitle=(EditText)findViewById(R.id.edit_view_Clock_title);

        editTextClockTitle.setText(getIntent().getStringExtra("title"));
        
        editClockContent.setText(getIntent().getStringExtra("content"));
        insertPosition=getIntent().getIntExtra("insert_position",0);

        buttonOK.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title",editTextClockTitle.getText().toString());
                intent.putExtra("insert_position",insertPosition);
                intent.putExtra("content",editClockContent.getText().toString());
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
