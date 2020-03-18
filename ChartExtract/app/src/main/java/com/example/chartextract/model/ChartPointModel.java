package com.example.chartextract.model;

import java.text.DecimalFormat;

public class ChartPointModel {

    private float x, y;
    private float xCoord, yCoord;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private float Width, Height;

    public ChartPointModel(float nx, float ny, float nwidth, float nheight, SelectionBoxModel SB){
        this.x = nx;
        this.y = ny;
        this.Width = nwidth;
        this.Height = nheight;

        float x = nx * nwidth;
        float y = ny * nheight;
        float nleft = SB.getLeft() * nwidth;
        float nbottom = (SB.getTop() + SB.getHeight()) * nheight + 100;

        setxCoord(x, nleft);
        setyCoord(y, nbottom);
    }

    public void setxCoord(float xCoord, float nleft) {
        DecimalFormat f = new DecimalFormat("##.00");
        float xGridDiff = (float) 75;
        float tmp = (Math.round(xCoord) - Math.round(nleft)) / xGridDiff;
        this.xCoord = Float.parseFloat(f.format(tmp));
    }

    public void setyCoord(float yCoord, float nbottom) {
        DecimalFormat f = new DecimalFormat("##.00");
        float yGridDiff = (float) 110;
        float tmp = ((Math.round(nbottom) - Math.round(yCoord)) / yGridDiff) * 20;
        this.yCoord = Float.parseFloat(f.format(tmp));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void move(float dx, float dy, SelectionBoxModel SB) {
        x += dx;
        y += dy;

        float nleft = SB.getLeft() * this.Width;
        float nbottom = (SB.getTop() + SB.getHeight()) * this.Height + 100;

        setxCoord(x * this.Width, nleft);
        setyCoord(y * this.Height, nbottom);
    }

    public float getyCoord() {
        return yCoord;
    }

    public float getxCoord() {
        return xCoord;
    }

    public boolean contains(float dx, float dy){

        float MOE = (float) 0.025;

        return (dx >= x-MOE && dx <= x+MOE && dy >= y-MOE && dy <= y+MOE);
    }


}
