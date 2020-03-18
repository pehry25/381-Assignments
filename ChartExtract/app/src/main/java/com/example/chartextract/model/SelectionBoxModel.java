package com.example.chartextract.model;

public class SelectionBoxModel {

    private float left, top, width, height;

    public SelectionBoxModel(float nLeft, float nTop, float nWidth, float nHeight){
        this.left = nLeft;
        this.top = nTop;
        this.height = nHeight;
        this.width = nWidth;

    }

    public void resize(float dx, float dy){
        width += dx;
        height += dy;
    }

    public void move(float dx, float dy) {
        left += dx;
        top += dy;
    }

    public float getLeft() {
        return left;
    }

    public float getTop() {
        return top;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean selectedResizeHandles(float nx, float ny, float nwidth, float nheight){

        float MOE = 10; //Margin or Error

        float x = nx * nwidth;
        float y = ny * nheight;
        float nleft = getLeft() * nwidth;
        float ntop = getTop() * nheight;
        float nright = (getLeft() + getWidth()) * nwidth + 100;
        float nbottom = (getTop() + getHeight()) * nheight + 100;

        //Bottom Right
        if(x <= (nright + MOE) && x >= (nright - MOE) && y <= (nbottom + MOE) && y >= (nbottom - MOE)){
            return true;
        }

        //Bottom Left
        if(x <= (nleft + MOE) && x >= (nleft - MOE) && y <= (nbottom + MOE) && y >= (nbottom - MOE)){
            return true;
        }

        //Top Right
        if(x <= (nright + MOE) && x >= (nright - MOE) && y <= (ntop + MOE) && y >= (ntop - MOE)){
            return true;
        }

        return false;
    }

    public boolean selectedMoveHandle(float nx, float ny, float nwidth, float nheight){

        float MOE = 10; //Margin or Error

        float x = nx * nwidth;
        float y = ny * nheight;
        float nleft = getLeft() * nwidth;
        float ntop = getTop() * nheight;
        float nright = (getLeft() + getWidth()) * nwidth + 100;
        float nbottom = (getTop() + getHeight()) * nheight + 100;

        //Top Left
        if(x <= (nleft + MOE) && x >= (nleft - MOE) && y <= (ntop + MOE) && y >= (ntop - MOE)){
            return true;
        }


        return false;

    }

}
