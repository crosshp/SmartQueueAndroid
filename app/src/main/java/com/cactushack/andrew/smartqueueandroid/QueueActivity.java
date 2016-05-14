package com.cactushack.andrew.smartqueueandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QueueActivity extends AppCompatActivity {
    RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
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

    }
}
