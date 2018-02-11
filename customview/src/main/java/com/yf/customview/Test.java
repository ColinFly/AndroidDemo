package com.yf.customview;

/**
 * Created by colin on 18-1-30.
 */

public class Test {
    public static void main(String[] args) {
        int x = 1000, y = 2022;
        int result = x << 16 | y;
        System.out.println("0x"+Integer.toHexString(result));
    }
}
