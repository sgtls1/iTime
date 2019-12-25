package com.example.itime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.DialogInterface;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditClockActivity extends AppCompatActivity {
    private List<Clock> ListClocks = new ArrayList<>();

    private Button buttonOK, buttonCancel;
    private Button buttonDate, buttonTime, buttonDelete;
    private EditText editTextClockTitle, editClockContent;
    private TextView textDate, textTime;
    private int insertPosition;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Calendar calendar,calendar2;
    private String time1,Date1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clock);

        buttonOK = (Button) findViewById(R.id.button_ok);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonDate =  findViewById(R.id.button_date);
        buttonTime =  findViewById(R.id.button_time);
        buttonDelete = findViewById(R.id.button_delete);

        editClockContent = (EditText) findViewById(R.id.edit_view_book_price);
        editTextClockTitle = (EditText) findViewById(R.id.edit_view_book_title);
        textDate = (TextView) findViewById(R.id.date_text);
        textTime = (TextView) findViewById(R.id.time_text);
        calendar=Calendar.getInstance();


        editTextClockTitle.setText(getIntent().getStringExtra("name"));

        editClockContent.setText(getIntent().getStringExtra("content"));
        insertPosition = getIntent().getIntExtra("insert_position", 0);


        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", editTextClockTitle.getText().toString());
                intent.putExtra("insert_position", insertPosition);
                intent.putExtra("content", editClockContent.getText().toString());

                setResult(RESULT_OK, intent);
                EditClockActivity.this.finish();
            }

        });
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDate();

            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditClockActivity.this.finish();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ListClocks.remove(insertPosition);


                EditClockActivity.this.finish();
            }

        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime();



        }

        });
    }

    private void showDate(){
        datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthofYear, int dayofMonth) {
                String time=String.valueOf(year)+" "+String.valueOf(monthofYear+1)+" "+Integer.toString(dayofMonth);
                textDate.setText(time);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
    private void showTime(){
        timePickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view ,int hourofDay, int minute) {
                String time=Integer.toString(hourofDay)+":"+Integer.toString(minute);
                textTime.setText(time);
            }
        },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),true);

        timePickerDialog.show();
        timePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
    }
}
