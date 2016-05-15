package com.cactushack.andrew.smartqueueandroid;

/**
 * Created by Andrew on 14.05.2016.
 */
public class QueueCard {
    private String dateLess;
    private long positionInQueue;
    private String name;
    private long countOfPerson;
    private int image;

    public QueueCard(String dateLess, long positionInQueue, String name, long countOfPerson, int image) {
        this.dateLess = dateLess;
        this.positionInQueue = positionInQueue;
        this.name = name;
        this.countOfPerson = countOfPerson;
        this.image = image;
    }

    public QueueCard() {
    }

    public QueueCard(String dateLess, long positionInQueue, String name, long countOfPerson) {
        this.dateLess = dateLess;
        this.positionInQueue = positionInQueue;
        this.name = name;
        this.countOfPerson = countOfPerson;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDateLess() {
        return dateLess;
    }

    public void setDateLess(String dateLess) {
        this.dateLess = dateLess;
    }

    public long getPositionInQueue() {
        return positionInQueue;
    }

    public void setPositionInQueue(long positionInQueue) {
        this.positionInQueue = positionInQueue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCountOfPerson() {
        return countOfPerson;
    }

    public void setCountOfPerson(long countOfPerson) {
        this.countOfPerson = countOfPerson;
    }
}
