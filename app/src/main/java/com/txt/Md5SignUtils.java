package com.txt;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;


public class Md5SignUtils {

    private static final String TAG = "Md5SignUtils";
    public static final String SIGN_MD5_PRD = "6e8c297e5f39a3a3a2eade3adcf4406e";
    public static final String SIGN_MD5_USER = "6cbf437e24e858a31cc2519aece5ca10";
    public static final String SIGN_MD5_ENG = "8ddb342f2da5408402d7568af21e29f9";

    public static String getSignMD5(Context context) {
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager()
                .getPackageInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            Signature sign = signatures[0];
            LogUtils.e(TAG, "sign: " + sign);
            String md5 = MD5Util.byteArrayToHexString(MD5Util.MD5Encode(sign.toByteArray()));
            LogUtils.d(TAG, "getSignMD5 :" + md5);
            return md5;
        } catch (Exception e) {
            LogUtils.d(TAG, "Exception " + e);
        }
        return null;
    }

}
