package com.yf.customview;

/**
 * Created by colin on 18-1-30.
 * 坐标点抽象类
 */

public class Position implements Comparable<Position>{
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{x=" + x +
                ", y=" + y +
                '}';
    }


    @Override
    public int compareTo(Position o) {
        return ((Integer)y).compareTo(o.y);
    }
}
