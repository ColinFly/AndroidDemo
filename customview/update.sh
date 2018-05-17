#!/bin/bash
#Author:Colin
#Date & Time:2018-01-25 11:51:19
#Description:

gradle assembleDebug

adb push /home/colin/Android/AndroidDemo/customview/build/outputs/apk/customview-debug.apk /data/app

adb shell sync

sleep 2

adb shell am start -n "com.yf.customview/com.yf.customview.TrackerLineActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
