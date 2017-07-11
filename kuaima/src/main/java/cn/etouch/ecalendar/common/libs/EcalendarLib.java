package cn.etouch.ecalendar.common.libs;

import android.content.Context;

/**
 * Created by colin on 17-6-9.
 */

public class EcalendarLib {
    private static EcalendarLib ecalendarLib = null;

    static {
        System.loadLibrary("EcalendarLib");
    }

    private EcalendarLib() {
    }

    private native String doEncrypt(Context context, String str, int i);

    private native String doSecrypt(Context context, String str, int i);

    public static EcalendarLib getInstance() {
        if (ecalendarLib == null) {
            ecalendarLib = new EcalendarLib();
        }
        return ecalendarLib;
    }

    public native String ab();

    public native String ad();

    public native String doInit(Context context, int i);

    public String doTheEncrypt(Context context, String str, int i) {
        return doEncrypt(context, str, i);
    }

    public String doTheSecrypt(Context context, String str, int i) {
        return doSecrypt(context, str, i);
    }
}
