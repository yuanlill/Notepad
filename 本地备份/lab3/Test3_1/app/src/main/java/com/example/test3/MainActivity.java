package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView lv = findViewById(R.id.lv);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","Lion");
        map.put("img",R.drawable.lion);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Tiger");
        map.put("img",R.drawable.tiger);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Monkey");
        map.put("img",R.drawable.monkey);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Dog");
        map.put("img",R.drawable.dog);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Cat");
        map.put("img",R.drawable.cat);
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","Elephant");
        map.put("img",R.drawable.elephant);
        list.add(map);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.item,
                new String[]{"name","img"},
                new int[]{R.id.name,R.id.img}
        );

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,"点击"+list.get(i).get("name"),Toast.LENGTH_SHORT).show();
    }
}