package com.example.itime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Calendar;
import java.util.List;
;


public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 901;
    public static final int CODE = REQUEST_CODE;
    public static final int REQUEST_CODE1 = CODE;
    public static final int REQUEST_Choose = 902;
    public static final int RESULT_DELETE=903;
    String str;
    private AppBarConfiguration mAppBarConfiguration;
    private List<Clock> ListClocks=new ArrayList<>();
    private ListView listViewClocks;
    ClockSaver clockSaver;
    private ClockAdapter adapter;
    private Calendar now;
    long ms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listViewClocks= (ListView) this.findViewById(R.id.clock_list);
        clockSaver=new ClockSaver(this);
        ListClocks=clockSaver.load();

        if(ListClocks.size()==0)
        { init();}

        adapter = new ClockAdapter(
                MainActivity.this,R.layout.listview_item_clock, (ArrayList<Clock>) ListClocks);
        listViewClocks.setAdapter(adapter);

        listViewClocks.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
                Intent intent = new Intent(MainActivity.this, showClockActivity.class);
                intent.putExtra("name", ListClocks.get(position).getName());
                intent.putExtra("insert_position",position);
                intent.putExtra("content",ListClocks.get(position).getContent());
                intent.putExtra("countdown",ListClocks.get(position).getCountdown());
                intent.putExtra("ms",ms);
                startActivityForResult(intent, REQUEST_Choose);

            }
        });




        //点击按钮新增闹钟
        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditClockActivity.class);
                intent.putExtra("name", "无名");
                intent.putExtra("countdown","Time:");
                intent.putExtra("insert_position",0);
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
                    String name=data.getStringExtra("name");
                    int insertPosition=data.getIntExtra("insert_position",0);
                    String content=data.getStringExtra("content");
                    String countdown=data.getStringExtra("countdown");
                    String daoshu=data.getStringExtra("daoshu");
                    Clock clock=new Clock(name,content,R.drawable.time,countdown,daoshu);
                    String miaoshu=data.getStringExtra("miaoshu");
                    ms=Long.valueOf(miaoshu);
                    getListClocks().add(insertPosition,clock);

                    adapter.notifyDataSetChanged();

                }


                break;
            case REQUEST_Choose:
                if (resultCode == RESULT_OK) {
                    int insertPosition=data.getIntExtra("insert_position",0);
                    Clock clockAtPosition=getListClocks().get(insertPosition);
                    clockAtPosition.setName(data.getStringExtra("name"));
                    clockAtPosition.setContent(data.getStringExtra("content"));
                    clockAtPosition.setCountdown(data.getStringExtra("countdown"));
                    clockAtPosition.setDaoshu(data.getStringExtra("daoshu"));
                    adapter.notifyDataSetChanged();
                }
                else if (resultCode == RESULT_DELETE) {
                    int insertPosition2=data.getIntExtra("insert_position",0);
                    ListClocks.remove(insertPosition2);
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
        Calendar newyear=Calendar.getInstance();
        newyear.set(2019,0,1,0,0);
        str=String.format("%tY年%<tm月%<td日 %<tH:%<tM",newyear.getTime());
        String daoshu="只剩  天";
        ListClocks.add(new Clock("春节", "新年快乐",R.drawable.time,str,daoshu));


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
            ((ImageView) view.findViewById(R.id.image_view_clock_cover)).setImageResource(clock.getClockId());
            ((TextView) view.findViewById(R.id.text_view_clock_name)).setText(clock.getName());

            ((TextView) view.findViewById(R.id.text_view_clock_time)).setText(clock.getCountdown());
            ((TextView) view.findViewById(R.id.textView_interval)).setText(clock.getDaoshu());
            return view;
        }
    }
}
