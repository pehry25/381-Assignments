package com.example.chartextract.model;

public class ChartPoint {

    private float x, y;
    private float xCoord, yCoord;

    public ChartPoint(float nx, float ny){
        this.x = nx;
        this.y = ny;
        this.xCoord = 0;
        this.yCoord = 0;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }

    public float getyCoord() {
        return yCoord;
    }

    public void setyCoord(float yCoord) {
        this.yCoord = yCoord;
    }

    public float getxCoord() {
        return xCoord;
    }

    public void setxCoord(float xCoord) {
        this.xCoord = xCoord;
    }
}
