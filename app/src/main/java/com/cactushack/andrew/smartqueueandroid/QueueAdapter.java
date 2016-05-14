package com.cactushack.andrew.smartqueueandroid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class QueueAdapter extends RecyclerView.Adapter<QueueAdapter.QueueViewHolder> {
    private View view = null;

    private Context context = null;

    public void setContext(Context context) {
        this.context = context;
    }


    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    Activity activity = null;
    List<QueueCard> data;


    public List<QueueCard> getData() {
        return data;
    }

    public void addData(ArrayList<QueueCard> arrayList) {
        for (QueueCard queueCard : arrayList) {
            data.add(queueCard);
        }
    }

    public QueueAdapter(List<QueueCard> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public QueueViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_queue, viewGroup, false);
        QueueViewHolder pvh = new QueueViewHolder(v);
        view = v;
        return pvh;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(final QueueViewHolder personViewHolder, final int i) {
        final int index = i;
        QueueCard objectItem = data.get(i);
        personViewHolder.name.setText(objectItem.getName());
        personViewHolder.countPeople.setText(String.valueOf(objectItem.getCountOfPerson()));
        personViewHolder.endInQueue.setText(String.valueOf(objectItem.getCountOfPerson()));
        personViewHolder.positionInQueue.setText(String.valueOf(objectItem.getPositionInQueue()));
        personViewHolder.timeLess.setText(objectItem.getDateLess());
        personViewHolder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.hospital));
        personViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {

            public void showAlert() {
                AlertDialog.Builder ad = new AlertDialog.Builder(personViewHolder.itemView.getContext());
                ad.setTitle("Внимание");  // заголовок
                ad.setMessage("Вы уверены, что хотите удалить"); // сообщение
                ad.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        int position = personViewHolder.getAdapterPosition();
                    }
                });
                ad.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        return;
                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        return;
                    }
                }).show();
            }

            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

    }

    public void onG(View v) {

    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class QueueViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView countPeople;
        TextView timeLess;
        TextView positionInQueue;
        TextView endInQueue;
        ImageButton deleteButton;
        ImageView imageView;

        QueueViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.queueName);
            countPeople = (TextView) itemView.findViewById(R.id.countPeopleValue);
            timeLess = (TextView) itemView.findViewById(R.id.countTimeValue);
            positionInQueue = (TextView) itemView.findViewById(R.id.countPositionStart);
            endInQueue = (TextView) itemView.findViewById(R.id.countPositionEnd);
            deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);
            imageView = (ImageView) itemView.findViewById(R.id.imageQueue);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}



