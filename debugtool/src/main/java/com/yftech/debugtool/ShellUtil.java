package com.yftech.debugtool;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by colin on 16-11-23.
 */

public class ShellUtil {
    private static String TAG = "ShellUtil";

    public static String runCommand(String command) {// 运行普通linux命令
        Process process = null;
        String result = "false";
        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            result = inputStreamToString(process.getInputStream());
            Log.e(TAG, result);
        } catch (Exception e) {
            return result;
        } finally {
            try {
                process.destroy();
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static String runRootCmd(String cmd) {// 运行需要root权限的命令
        Process process = null;
        DataOutputStream os = null;
        String result = "false";
        try {
            process = Runtime.getRuntime().exec("su");
            OutputStream outstream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outstream);
            String temp = cmd + "\n";
            dataOutputStream.writeBytes(temp);
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            result = inputStreamToString(process.getInputStream());
            Log.e(TAG, result);

        } catch (Exception e) {
            return result;
        } finally {

            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return result;
    }

    // inputStream转换成String
    public static String inputStreamToString(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
