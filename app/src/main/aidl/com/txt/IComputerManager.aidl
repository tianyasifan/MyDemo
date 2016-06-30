// IComputerManager.aidl
package com.txt;
import com.txt.bean.ComputerEntity;//由于使用到了其他AIDL文件，所以要显示的引用
import com.txt.IOnComputerArrivedListener;
// Declare any non-default types here with import statements

//实现自定义数据传递
interface IComputerManager {
   void addComputer(in ComputerEntity entity);
   List<ComputerEntity> getComputerList();
   void registerUser(IOnComputerArrivedListener listener);
   void unRegisterUser(IOnComputerArrivedListener listener);
}
