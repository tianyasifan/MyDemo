// IOnComputerArrivedListener.aidl
package com.txt;
import com.txt.bean.ComputerEntity;
// Declare any non-default types here with import statements
//主动通知客户端的接口
interface IOnComputerArrivedListener {
    //非基本数据类型需要在参数前面增加in or out
    void onComputerArrived(in ComputerEntity computer);
}
