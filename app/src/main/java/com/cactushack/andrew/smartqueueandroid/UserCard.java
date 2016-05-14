package com.cactushack.andrew.smartqueueandroid;

/**
 * Created by Andrew on 14.05.2016.
 */
public class UserCard {
    private String name;
    private String dateLess;

    public UserCard() {
    }

    public UserCard(String name, String dateLess) {
        this.name = name;
        this.dateLess = dateLess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateLess() {
        return dateLess;
    }

    public void setDateLess(String dateLess) {
        this.dateLess = dateLess;
    }
}
/*
*     <LinearLayout
            android:orientation="horizontal"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <View
                android:layout_width="0dp"
                android:layout_height="0dp"
                android:layout_weight="3"/>

            <Button
                android:id="@+id/connectButton"
                style="?android:attr/borderlessButtonStyle"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/connect"
                android:textColor="?attr/colorPrimary" />
        </LinearLayout>*/