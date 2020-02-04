package com.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlService extends Service {

    private String TAG = this.getClass().getSimpleName();
    @Override
    public IBinder onBind(Intent intent) {
        return demandManager;
    }

    //Stub内部继承Binder，具有跨进程传输能力
    IDemandManager.Stub demandManager = new IDemandManager.Stub() {
        @Override
        public MessageBean getDemand() throws RemoteException { // 这个方法会在binder线程池中执行，执行这个的时候，会挂起调用者线程
            MessageBean demand = new MessageBean("首先，看到我要敬礼", 1);
            Log.i(TAG, "get: " + demand.toString() + ", thread:" + Thread.currentThread());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return demand;
        }

        @Override
        public void setDemandIn(MessageBean msg) throws RemoteException {//客户端数据流向服务端
            Log.i(TAG, "程序员:" + msg.toString());
        }

        @Override
        public void setDemandOut(MessageBean msg) throws RemoteException {//服务端数据流向客户端
            Log.i(TAG, "程序员:" + msg.toString());//msg内容一定为空

            msg.setContent("我不想听解释，下班前把所有工作都搞好！");
            msg.setLevel(5);
        }

        @Override
        public void setDemandInOut(MessageBean msg) throws RemoteException {//数据互通
            Log.i(TAG, "程序员:" + msg.toString());

            msg.setContent("把用户交互颜色都改成粉色");
            msg.setLevel(3);
        }
    };
}
