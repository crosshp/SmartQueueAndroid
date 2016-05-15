package com.cactushack.andrew.smartqueueandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 14.05.2016.
 */
public class QueueActivity extends AppCompatActivity {
    Activity activity = this;
    private String[] names = {"Вадим Cеменюк", "Александр Игнатенко", "Игорь Филимончук", "Оля Павленко", "Яна Сытник", "Дима Мазур", "Богдан Ярема", "Таня Татусь"};
    private int[] photos = {R.drawable.semenuk,
            R.drawable.ignatenko, R.drawable.philimonchyk, R.drawable.pavlenko,
            R.drawable.sytnik, R.drawable.mazur, R.drawable.yarema, R.drawable.tatus};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queue_layout);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.goQueueFab);
        assert floatingActionButton != null;
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CheckActivity.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                activity.startActivity(intent);
            }
        });
        TextView textView = (TextView) findViewById(R.id.queueNameHeader);
        textView.setText(getIntent().getStringExtra("name"));
        List<UserCard> userCards = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            UserCard userCard = new UserCard();
            userCard.setName(names[i]);
            //userCard.setDateLess();
            userCard.setPhoto(photos[i]);
            userCards.add(userCard);
        }
        UserAdapter userAdapter = new UserAdapter(userCards, getBaseContext(), this, getIntent().getIntExtra("position", 4), getIntent().getStringExtra("name"));
        ListView listView = (ListView) findViewById(R.id.userList);
        View header = LayoutInflater.from(getBaseContext())
                .inflate(R.layout.header_list_user,
                        null);
        ImageView imageView = (ImageView) header.findViewById(R.id.imageQueueHeader);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.laba));
        listView.addHeaderView(header);
        listView.setAdapter(userAdapter);
    }
}
