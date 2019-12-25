package com.example.itime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class showClockActivity extends AppCompatActivity {
    private ImageView backgroundPicture;
    private NavigationView mess_menu;
    String name,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clock);
        this.mess_menu = (NavigationView)findViewById(R.id.show_menu);
        backgroundPicture=findViewById(R.id.clock_picture);
        backgroundPicture.setImageResource(R.drawable.back2);
        name=getIntent().getStringExtra("name");
        content=getIntent().getStringExtra("content");
        this.mess_menu.getMenu().findItem(R.id.clock_name).setTitle(name);
        this.mess_menu.getMenu().findItem(R.id.clock_content).setTitle(content);
    


}
