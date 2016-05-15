package com.cactushack.andrew.smartqueueandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class QueueListActivity extends AppCompatActivity {
    RecyclerView recyclerView = null;
    Activity activity = this;

    private int[] photos = {R.drawable.phoca, R.drawable.jkh, R.drawable.laba, R.drawable.priymalka, R.drawable.queue, R.drawable.post};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queue_list);


        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Premium версия").withIcon(R.drawable.ic_cash_usd);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("Настройки").withIcon(R.drawable.ic_settings);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("Информация").withIcon(R.drawable.ic_information_outline);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("Про приложение").withIcon(R.drawable.ic_help_circle);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName("Выход").withIcon(R.drawable.ic_logout);

//create the drawer and remember the `Drawer` result object
        AccountHeaderBuilder accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.iniy_gradient)
                .addProfiles(new ProfileDrawerItem()
                        .withEmail("test@mail.com")
                        .withName("Yana Sytnyk"));
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(accountHeader.build())
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2, item3, item4, new DividerDrawerItem(), item5
                )
                .build();


        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        final List<QueueCard> queueCardList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            QueueCard queueCard = new QueueCard();
            queueCard.setName("Name" + i);
            queueCard.setCountOfPerson(20);
            queueCard.setPositionInQueue(i);
            queueCard.setImage(photos[i]);
            queueCardList.add(queueCard);
        }
        QueueAdapter queueAdapter = new QueueAdapter(queueCardList, getBaseContext());
        recyclerView.setAdapter(queueAdapter);
        String name = getIntent().getStringExtra("name");
        if (name != null) {
            queueAdapter.removeByName(name);
            queueAdapter.notifyDataSetChanged();
        }
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this) {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
                Intent intentForNote = new Intent(activity, QueueActivity.class);
                intentForNote.putExtra("name", queueCardList.get(position).getName());
                activity.startActivity(intentForNote);
            }
        });

    }

    public List<QueueCard> getQueues() {
        AsyncHttpClient client = new AsyncHttpClient();
        final List<QueueCard> list = new ArrayList<>();
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        client.get("http://192.168.1.2:8080/getUser/1", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // If the response is JSONObject instead of expected JSONArray
                for (int i = 0; i < response.length(); i++) {
                    QueueCard queueCard = new QueueCard();
                    try {
                        JSONObject jsonArray = response.getJSONObject(i);
                        queueCard.setName(jsonArray.getString("queueName"));
                        queueCard.setCountOfPerson(7);
                        queueCard.setPositionInQueue(4);
                        queueCard.setImage(photos[i]);
                        list.add(queueCard);
                        Log.e("name", list.get(i).getName());
                      /*Log.e("",list.get(i).getName());
                        Log.e("name",list.get(i).getName());*/
                        progressBar.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        return list;
    }
}
