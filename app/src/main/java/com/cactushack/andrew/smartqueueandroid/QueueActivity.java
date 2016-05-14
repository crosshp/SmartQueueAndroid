package com.cactushack.andrew.smartqueueandroid;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 14.05.2016.
 */
public class QueueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queue_layout);

        List<UserCard> userCards = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            UserCard userCard = new UserCard();
            userCard.setName("Василь Пупкин");
            userCard.setDateLess("3 min.");
            userCards.add(userCard);
        }
        UserAdapter userAdapter = new UserAdapter(userCards, getBaseContext(),this);
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
