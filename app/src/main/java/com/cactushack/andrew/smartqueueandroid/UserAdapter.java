package com.cactushack.andrew.smartqueueandroid;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andrew on 14.05.2016.
 */
public class UserAdapter extends BaseAdapter {
    private List<UserCard> userCards = null;
    private Context context = null;
    private Activity parentActivity;

    public UserAdapter(List<UserCard> userCards, Context context, Activity activity) {
        this.userCards = userCards;
        this.context = context;
        this.parentActivity = activity;
    }
    private int [] photos = {R.drawable.semenuk,
            R.drawable.ignatenko,R.drawable.philimonchyk,R.drawable.pavlenko,
            R.drawable.sytnik,R.drawable.mazur,R.drawable.yarema,R.drawable.tatus};

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
        TextView positionValue = (TextView) view.findViewById(R.id.valuePosition);
        positionValue.setText(String.valueOf(position+1));
        TextView dateLess = (TextView) view.findViewById(R.id.valueDateLess);
        dateLess.setText(String.valueOf(userCards.get(position).getDateLess()));
        ImageView userImage = (ImageView) view.findViewById(R.id.userImage);
        Bitmap bitmapFactory = BitmapFactory.decodeResource(parentActivity.getResources(),photos[position]);
        bitmapFactory = Bitmap.createScaledBitmap(bitmapFactory, 64, 64, false);
        userImage.setImageBitmap(getRoundedCornerBitmap(bitmapFactory,64));
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
