package com.yftech.debugtool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 开机广播
 * @author lishanfeng
 * @version 2014-10-11
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

	public static final String BOOT_BROADCAST_START = "bootBroadcastStart";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MyService.class);
//        service.putExtra(BOOT_BROADCAST_START, true);
        context.startService(service);
    }
}
