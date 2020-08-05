package com.example.firstcode.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.firstcode.R;
import com.example.firstcode.adapter.MainRvAdapter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    private Button button;
    String CHANNEL_ID = "TestCodeNotification";
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

        button = findViewById(R.id.button);

        try {
            FileOutputStream fileOutputStream = openFileOutput("firstCodeData", MODE_APPEND);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write("一串测试数据");
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "openFileOutput get an error :" + e.getMessage());
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "bufferedWriter.write get an error :" + e.getMessage());
//            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();

            }
        });


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

    private void sendMessage(){
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, DialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("textTitle")
                .setContentText("textContent")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, builder.build());


    }



}