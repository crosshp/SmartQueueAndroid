package com.cactushack.andrew.smartqueueandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andrew on 14.05.2016.
 */
public class UserAdapter extends BaseAdapter {
    private List<UserCard> userCards = null;
    private Context context = null;

    public UserAdapter(List<UserCard> userCards, Context context) {
        this.userCards = userCards;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userCards.size();
    }

    @Override
    public Object getItem(int position) {
        return userCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // задаем вид элемента списка, который мы создали высше
        View view = inflater.inflate(R.layout.user_card, parent, false);
        TextView nameUser = (TextView) view.findViewById(R.id.valueName);
        nameUser.setText(userCards.get(position).getName());
        TextView positionValue  = (TextView) view.findViewById(R.id.valuePosition);
        positionValue.setText(String.valueOf(position));
        TextView dateLess = (TextView) view.findViewById(R.id.valueDateLess);
        dateLess.setText(String.valueOf(userCards.get(position).getDateLess()));
        Button button = (Button) view.findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
