package com.example.itime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.Date;
import java.util.List;

public class EditClockActivity extends AppCompatActivity {
    private List<Clock> ListClocks = new ArrayList<>();
    public static final int RESULT_DELETE = 903;
    private Button buttonOK, buttonCancel;
    private Button buttonDate, buttonTime, buttonDelete;
    private EditText editTextClockTitle, editClockContent;
    private ListView listView;
    private int insertPosition;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    String[] items = new String[]{"Date", "Repete", "Picture", "Label"};
    private Calendar calendar, calendar2;
    private String time1, Date1,daoshu,name,content,countdown;
    long time;
    int hourOfDay1, minute1, year1, monthOfYear1, dayOfMonth1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clock);

        buttonOK = (Button) findViewById(R.id.button_ok);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        listView = findViewById(R.id.list);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                EditClockActivity.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        editClockContent = (EditText) findViewById(R.id.edit_view_book_price);
        editTextClockTitle = (EditText) findViewById(R.id.edit_view_book_title);
        calendar = Calendar.getInstance();

        editTextClockTitle.setText(getIntent().getStringExtra("name"));
        editClockContent.setText(getIntent().getStringExtra("content"));
        insertPosition = getIntent().getIntExtra("insert_position", 0);
        items[0]=getIntent().getStringExtra("countdown");
        adapter.notifyDataSetChanged();

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c= Calendar.getInstance();
                c.set(year1,monthOfYear1,dayOfMonth1,hourOfDay1,minute1);
                long t1=c.getTimeInMillis();
                Calendar calendar1= Calendar.getInstance();
                calendar1.setTime(new Date());
                long Time1=calendar1.getTimeInMillis();
                long daoshu1=Math.abs(t1-Time1);
                long second=daoshu1;
                long daoshu2=daoshu1/(1000*60*60*24);

                daoshu="只剩"+daoshu2+"天";
                Intent intent = new Intent();
                intent.putExtra("name", editTextClockTitle.getText().toString());
                intent.putExtra("insert_position", insertPosition);
                intent.putExtra("content", editClockContent.getText().toString());
                intent.putExtra("countdown", items[0]);
                intent.putExtra("daoshu",daoshu);
                intent.putExtra("miaoshu",String.valueOf(second));
                setResult(RESULT_OK, intent);
                EditClockActivity.this.finish();
            }

        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditClockActivity.this.finish();
            }
        });



        //为listview中的item添加点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {

                    //选择时间
                    Calendar c = Calendar.getInstance();

                    new TimePickerDialog(EditClockActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view,
                                                      int hourOfDay, int minute) {
                                    items[0] = items[0] + "  时间：" + hourOfDay + "：" + minute;
                                    hourOfDay1 = hourOfDay;
                                    minute1 = minute;
                                    adapter.notifyDataSetChanged();
                                }
                            }

                            , c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                            // true表示采用24小时制
                            true).show();


                    //选择日期
                    final Calendar calendar;
                    DatePickerDialog dialog;
                    calendar = Calendar.getInstance();
                    dialog = new DatePickerDialog(EditClockActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    System.out.println("年-->" + year + "月-->" + monthOfYear + "日-->" + dayOfMonth);
                                    monthOfYear++;
                                    items[0] = "Time：" + year + "年" + monthOfYear + "月" + dayOfMonth + "日";
                                    monthOfYear--;
                                    year1 = year;
                                    monthOfYear1 = monthOfYear;
                                    dayOfMonth1 = dayOfMonth;

                                    adapter.notifyDataSetChanged();
                                }
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));


                    dialog.show();
                    //选择周期
                } else if (i == 1) {

                    final String[] item = {"Year", "Month", "Week", "Day", "Custom", "None"};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(EditClockActivity.this);
                    dialog.setTitle("Repeat")
                            .setItems(item, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int position) {
                                    items[1] = "Repeat：" + item[position];
                                    adapter.notifyDataSetChanged();
                                }
                            });
                    dialog.show();
                }
                else if (i == 2) {
                    //选择标签
                } else if (i == 3) {
                    final String[] item2 = {"Anniversary", "Live", "Work", "Custom", "None"};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(EditClockActivity.this);
                    dialog.setTitle("Label")
                            .setItems(item2, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int position) {
                                    items[3] = "Label：" + item2[position];
                                    adapter.notifyDataSetChanged();
                                }
                            });
                    dialog.show();

                }

            }

        });

    }



}