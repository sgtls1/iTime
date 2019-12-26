package com.example.itime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 901;
    public static final int CODE = REQUEST_CODE;
    public static final int REQUEST_CODE1 = CODE;
    public static final int REQUEST_CODE2 = 902;

    private AppBarConfiguration mAppBarConfiguration;
    private List<Clock> ListClocks=new ArrayList<>();
    private ListView listViewClocks;
    ClockSaver clockSaver;
    private ClockAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listViewClocks= (ListView) this.findViewById(R.id.clock_list);

        ListClocks=clockSaver.load();

        if(ListClocks.size()==0)
        { init();}
        adapter = new ClockAdapter(
                MainActivity.this,R.layout.listview_item_clock, (ArrayList<Clock>) ListClocks);
        listViewClocks.setAdapter(adapter);

        listViewClocks.setOnItemClickListener(new OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> arg0, View view, int arg2,long arg3) {
                Intent intent = new Intent(MainActivity.this, EditClockActivity.class);
                intent.putExtra("name", "无名");
                intent.putExtra("insert_position","无名");
                intent.putExtra("content","备注");
                startActivityForResult(intent, REQUEST_CODE2);

            }
        });


        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //点击按钮新增闹钟
        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditClockActivity.class);
                intent.putExtra("name", "无名");

                intent.putExtra("insert_position","无名");
                intent.putExtra("content","备注");
                startActivityForResult(intent,901);

            }
        });

       //导航栏
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public List<Clock> getListClocks(){
        return ListClocks;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE1:
                if (resultCode == RESULT_OK) {
                    String name=data.getStringExtra("title");
                    int insertPosition=data.getIntExtra("insert_position",0);
                    String content=data.getStringExtra("price");
                    getListClocks().add(insertPosition, new Clock(name,content,R.drawable.time));

                    adapter.notifyDataSetChanged();
                }
                break;
            case REQUEST_CODE2:
                if (resultCode == RESULT_OK) {
                    int insertPosition=data.getIntExtra("insert_position",0);
                    Clock bookAtPosition=getListClocks().get(insertPosition);
                    bookAtPosition.setName(data.getStringExtra("title"));
                    bookAtPosition.setContent(data.getStringExtra("content"));
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        clockSaver.save();
    }

    private void init() {

        ListClocks.add(new Clock("春节", "新年快乐",R.drawable.time));


    }
    class ClockAdapter extends ArrayAdapter<Clock> {

        private int resourceId;

        public ClockAdapter(Context context, int resource, List<Clock> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }
        @NonNull
        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {
            Clock clock = getItem(position);//获取当前项的实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            ((ImageView) view.findViewById(R.id.image_view_book_cover)).setImageResource(clock.getClockeId());
            ((TextView) view.findViewById(R.id.text_view_book_title)).setText(clock.getName());
            ((TextView) view.findViewById(R.id.text_view_book_price)).setText(clock.getContent());
            return view;
        }
    }
}
