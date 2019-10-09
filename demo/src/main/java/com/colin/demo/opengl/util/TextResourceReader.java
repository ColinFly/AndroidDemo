package com.colin.demo.opengl.util;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextResourceReader {
    public static String readTextFileFromResource(Context context, int resId) {
        StringBuilder body=new StringBuilder();
        InputStream inputStream = context.getResources().openRawResource(resId);
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String nextLine;
        try {
            while ((nextLine = bufferedReader.readLine()) != null) {
                body.append(nextLine);
                body.append('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not open resource: " + resId, e);

        } catch (Resources.NotFoundException nfe) {
            throw new RuntimeException("Resource not found: " + resId, nfe);
        }
        return body.toString();
    }

}
