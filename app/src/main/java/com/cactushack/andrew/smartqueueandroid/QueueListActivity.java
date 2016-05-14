package com.cactushack.andrew.smartqueueandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class QueueListActivity extends AppCompatActivity {
    RecyclerView recyclerView = null;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queue_list);
        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        List<QueueCard> queueCardList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            QueueCard queueCard = new QueueCard();
            queueCard.setName("Name" + i);
            queueCard.setCountOfPerson(20);
            queueCard.setPositionInQueue(i);
            queueCard.setDateLess("5 мин.");
            queueCardList.add(queueCard);
        }
        QueueAdapter queueAdapter = new QueueAdapter(queueCardList, getBaseContext());
        recyclerView.setAdapter(queueAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this) {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
                Intent intentForNote = new Intent(activity, QueueActivity.class);
                intentForNote.putExtra("name","Сети");
                activity.startActivity(intentForNote);
            }
        });

    }
}
