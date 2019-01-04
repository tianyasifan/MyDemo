package com.txt.aidlpool;

import android.os.RemoteException;
import android.widget.Toast;

import com.txt.IMyAidlInterface;

/**
 * Created by txt on 2016/5/17.
 * 运行在服务端
 */
public class BinderOne extends IMyAidlInterface.Stub{
    @Override
    public int add(int first, int second) throws RemoteException {
        return first+second;

    }
}
