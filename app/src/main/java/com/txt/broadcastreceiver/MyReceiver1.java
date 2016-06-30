package com.txt.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by txt on 2016/5/12.
 */
public class MyReceiver1 extends BroadcastReceiver {

    private String tag = MyReceiver1.class.getSimpleName();

    //不能执行耗时操作，否则产生anr，生命周期随着onReveive的结束而结束
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Constant.BROADCAST_ACTION.equals(intent.getAction())){
            Log.d(tag,intent.getStringExtra(Constant.CONFERENCE_KEY));
        }
    }
}
