package com.example.itime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class showClockActivity extends AppCompatActivity {
    private ImageView backgroundPicture;
    private NavigationView mess_menu;
    private TextView clock;
    ActionBar actionBar;
    String name, content, str, countdown;
    private int insertPosition;
    public static final int RESULT_DELETE = 903;
<<<<<<< HEAD
    long qq;
    String pp;
=======
    long time;
    String text;
>>>>>>> origin/master

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clock);
        this.clock = findViewById(R.id.textView_interval2);
        this.mess_menu = (NavigationView) findViewById(R.id.show_menu);
        backgroundPicture = findViewById(R.id.clock_picture);
        backgroundPicture.setImageResource(R.drawable.back2);
        name = getIntent().getStringExtra("name");
        insertPosition = getIntent().getIntExtra("insert_position", 0);
        content = getIntent().getStringExtra("content");
        countdown = getIntent().getStringExtra("countdown");
<<<<<<< HEAD
        long mm = getIntent().getLongExtra("ms", 0);
        String ss = stringForTime(mm);
        clock.setText(ss);
        qq = mm;
=======
        long total= getIntent().getLongExtra("ms", 0);
        String str = stringForTime(total);
        clock.setText(str);
        time = total;
>>>>>>> origin/master


        this.mess_menu.getMenu().findItem(R.id.clock_name).setTitle(name);
        this.mess_menu.getMenu().findItem(R.id.clock_content).setTitle(content);
        this.mess_menu.getMenu().findItem(R.id.clock_time).setTitle(countdown);

        @SuppressLint("HandlerLeak")
        final Handler handler = new Handler ( ) {
            public void handleMessage(Message msg) {
                //计算剩余时间
<<<<<<< HEAD
                clock.setText(pp);
=======
                clock.setText(text);
>>>>>>> origin/master
                super.handleMessage (msg);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Message msg = new Message ( );
<<<<<<< HEAD
                        qq=qq-1000;
                        pp=stringForTime(qq);
=======
                        time=time-1000;
                        text=stringForTime(time);
>>>>>>> origin/master
                        handler.sendMessage (msg);
                        Thread.sleep (1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace ( );
                }
            }
        }).start();



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.day_mess_toolbar_menu, menu);
        return true;
    }
<<<<<<< HEAD
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 904:
                if (resultCode == RESULT_OK) {
                    String name=data.getStringExtra("name");
                    int insertPosition=data.getIntExtra("insert_position",0);
                    String content=data.getStringExtra("content");
                    String countdown=data.getStringExtra("countdown");
                    String daoshu=data.getStringExtra("daoshu");
                    Intent intent = new Intent(showClockActivity.this, EditClockActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("insert_position",insertPosition);
                    intent.putExtra("content",content);
                    intent.putExtra("countdown",countdown);
                    intent.putExtra("daoshu",daoshu);
                    setResult(RESULT_OK, intent);
                    showClockActivity.this.finish();

                }


                break;

        }
    }
=======

>>>>>>> origin/master
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            new android.app.AlertDialog.Builder(this)
                    .setTitle("询问")
                    .setMessage("你确定删除该倒计时吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent();
                            setResult(RESULT_DELETE, intent);
                            intent.putExtra("insert_position", insertPosition);
                            showClockActivity.this.finish();

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create().show();

        }else if(id == R.id.action_edit){

            Intent intent = new Intent(showClockActivity.this, EditClockActivity.class);
            intent.putExtra("name", name);
<<<<<<< HEAD
            intent.putExtra("insert_position",insertPosition);
            intent.putExtra("content",content);
            intent.putExtra("countdown",countdown);
            startActivityForResult(intent,904);

=======

            intent.putExtra("content",content);
            intent.putExtra("countdown",countdown);
            startActivityForResult(intent,901);
            showClockActivity.this.finish();
>>>>>>> origin/master
        }else if(id == R.id.clock_back){
            showClockActivity.this.finish();
        }
        else{

        }
        return super.onOptionsItemSelected(item);

    }
    public static String stringForTime(long timeMs){
        long totalSeconds = timeMs/1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds/60)%60;
        long hours = totalSeconds/3600;
        return new Formatter().format("%02d小时%02d分钟%02d秒",hours,minutes,seconds).toString();
    }
}
