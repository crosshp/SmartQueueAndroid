package com.cactushack.andrew.smartqueueandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 14.05.2016.
 */
public class QueueActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queue_layout);


        List<UserCard> userCards = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            UserCard userCard = new UserCard();
            userCard.setName("Василь Пупкин");
            userCard.setDateLess("3 min.");
            userCards.add(userCard);
        }
        UserAdapter userAdapter = new UserAdapter(userCards,getBaseContext());
        ListView listView = (ListView) findViewById(R.id.userList);
        listView.setAdapter(userAdapter);
    }
}
