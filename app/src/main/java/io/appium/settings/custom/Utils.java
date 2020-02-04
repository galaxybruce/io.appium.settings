package io.appium.settings.custom;

import android.annotation.SuppressLint;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * @author bruce.zhang
 * @date 2020-02-03 17:44
 * @description (亲 ， 我是做什么的)
 * <p>
 * modification history:
 */
public class Utils {

    @SuppressLint({"NewApi", "MissingPermission"})
    public static String getSerialNumber() {

        String serial = "";
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//8.0+
                serial = Build.getSerial();
            } else {//8.0-
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                serial = (String) get.invoke(c, "ro.serialno");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serial;
    }
}
