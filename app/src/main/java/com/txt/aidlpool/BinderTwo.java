package com.txt.aidlpool;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.txt.IComputerManager;
import com.txt.IOnComputerArrivedListener;
import com.txt.bean.ComputerEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txt on 2016/5/17.
 */
public class BinderTwo extends IComputerManager.Stub {
    private String tag = BinderTwo.class.getSimpleName();
    //创建一个实体对象，用于向客户端传递
    private List<ComputerEntity> mList = new ArrayList<>();
    //用RemoteCallbackList封装了一下监听器，必须这么做
    private RemoteCallbackList<IOnComputerArrivedListener> mListener = new RemoteCallbackList<>();

    @Override
    public void addComputer(ComputerEntity entity) throws RemoteException {
        mList.add(entity);
    }

    @Override
    public List<ComputerEntity> getComputerList() throws RemoteException {
        return mList;
    }

    /**
     * 注册一个远程回调监听器，用于主动更新客户端
     * @param listener
     * @throws RemoteException
     */
    @Override
    public void registerUser(IOnComputerArrivedListener listener) throws RemoteException {
        mListener.register(listener);//执行注册操作
        //这里可以做一些主动更新客户端的操作，如：
        new Thread(new Runnable() {
            @Override
            public void run() {
                int counts = mListener.beginBroadcast();
                int count = counts;
                Log.i(tag, "count:" + count);
                int sum = 3;
                while (sum>0) {
                    try {
                        Thread.sleep(3000);
                        ComputerEntity entity = new ComputerEntity(sum,"adc","abc+"+sum);
                        count = counts;
                        while(count>0){
                            count--;
                            try {
                                mListener.getBroadcastItem(count).onComputerArrived(entity);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum--;
                }
                mListener.finishBroadcast();
            }
        }).start();
    }

    /**
     * 取消监听器
     * @param listener
     * @throws RemoteException
     */
    @Override
    public void unRegisterUser(IOnComputerArrivedListener listener) throws RemoteException {
        mListener.unregister(listener);
    }
}
