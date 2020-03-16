package com.example.chartextract.model;

public class SelectionBox {

    private float left, top, width, height;

    public SelectionBox(float nLeft, float nTop, float nWidth, float nHeight){
        this.left = nLeft;
        this.top = nTop;
        this.height = nHeight;
        this.width = nWidth;
    }

    public void resize(float dx, float dy){
        width += dx;
        height += dy;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
