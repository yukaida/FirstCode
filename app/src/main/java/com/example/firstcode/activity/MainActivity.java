package com.example.firstcode.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.firstcode.R;
import com.example.firstcode.adapter.MainRvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yukaida
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private MainRvAdapter mainRvAdapter;
    private List<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null != savedInstanceState) {
            String data = savedInstanceState.getString("tempdata");
            Log.d(TAG, "onCreate: " + data);
        }

        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");

        recyclerView = findViewById(R.id.recyclerView);
        mainRvAdapter = new MainRvAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainRvAdapter);


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "tempData";
        outState.putString("tempdata", tempData);
        Log.d(TAG, "onSaveInstanceState: " + outState.getString("tempdata"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.testmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test_menu_relax:
                Toast.makeText(this, "放松", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, DialogActivity.class);
                startActivity(intent);

                break;
            case R.id.test_menu_sleep:
                Toast.makeText(this, "睡觉", Toast.LENGTH_SHORT).show();
                break;
            case R.id.test_menu_study:
                Toast.makeText(this, "学习", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}