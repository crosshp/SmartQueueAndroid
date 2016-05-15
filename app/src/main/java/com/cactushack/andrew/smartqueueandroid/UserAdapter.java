package com.cactushack.andrew.smartqueueandroid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by Andrew on 14.05.2016.
 */
public class UserAdapter extends BaseAdapter {
    private List<UserCard> userCards = null;
    private Context context = null;
    private Activity parentActivity;
    private int myPosition = 0;
    BaseAdapter adapter = this;
    private String nameQueue;

    public UserAdapter(List<UserCard> userCards, Context context, Activity activity, int myPosition, String nameOfQueue) {
        this.userCards = userCards;
        this.context = context;
        this.parentActivity = activity;
        this.myPosition = myPosition;
        this.nameQueue = nameOfQueue;
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // задаем вид элемента списка, который мы создали высше
        View view = inflater.inflate(R.layout.user_card, parent, false);

        if (myPosition <= 1) {
            String neighbour = "";
            for (int i = 0; i < myPosition; i++) {
                neighbour += userCards.get(i).getName();
            }
            neighbour = neighbour.length() == 0 ? "Вы следующий" : "Перед вами:" + neighbour;
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

            Intent intent = new Intent(context, QueueActivity.class);
            intent.putExtra("position", myPosition);
            intent.putExtra("name", nameQueue);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
            builder.setSmallIcon(R.drawable.ic_account_alert)
                    // .setContentText("Ваша очередь подойдёт через " + myPosition * 4 + 4 + "мин." + "\n" + neighbour)
                    .setContentTitle("Очередь '" + nameQueue + "'")
                    .setContentIntent(pendingIntent)
                    .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText(neighbour + "\nВаша очередь подойдёт через " + (myPosition * 4 + 4) + "мин." + "\n"));
            NotificationManagerCompat.from(context).notify(1, builder.build());
        }
        if (position == myPosition)
            view.setBackgroundColor(parentActivity.getResources().getColor(R.color.colorAccentTransparent));
        TextView nameUser = (TextView) view.findViewById(R.id.valueName);
        nameUser.setText(userCards.get(position).getName());
        TextView positionValue = (TextView) view.findViewById(R.id.valuePosition);
        positionValue.setText(String.valueOf(position + 1));
        TextView dateLess = (TextView) view.findViewById(R.id.valueDateLess);
        dateLess.setText(4 + position * 4 + "мин.");
        final ImageView userImage = (ImageView) view.findViewById(R.id.userImage);
        Bitmap bitmapFactory = BitmapFactory.decodeResource(parentActivity.getResources(), userCards.get(position).getPhoto());
        bitmapFactory = Bitmap.createScaledBitmap(bitmapFactory, 64, 64, false);
        userImage.setImageBitmap(getRoundedCornerBitmap(bitmapFactory, 64));
        ImageButton button = (ImageButton) view.findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
                builder.setTitle("Попросить пройти вне очереди");
                View view = View.inflate(parentActivity, R.layout.dialog_queue, null);
                builder.setView(view);
                builder.setPositiveButton("Попросить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserCard me = userCards.remove(myPosition);
                        UserCard removeCard = userCards.remove(position);
                        userCards.add(position, me);
                        userCards.add(position + 1, removeCard);
                        myPosition = position;
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Отмена", null);
                builder.show();
            }
        });
        return view;
    }

    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
