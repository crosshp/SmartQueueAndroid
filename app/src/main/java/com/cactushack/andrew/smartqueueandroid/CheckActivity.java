package com.cactushack.andrew.smartqueueandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Andrew on 15.05.2016.
 */
public class CheckActivity extends AppCompatActivity {
    Activity activity = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_layout);
        Button button = (Button) findViewById(R.id.nextPerson);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,QueueListActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                activity.startActivity(intent);
            }
        });
    }
}
