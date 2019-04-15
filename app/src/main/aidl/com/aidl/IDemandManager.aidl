// IDemandManager.aidl
package com.aidl;

//编写完成后执行Build->Rebuild project就能编译生成IDemandManager.java接口了，在本项目中可以直接使用，如果用到其他项目，则需要copy出来
// Declare any non-default types here with import statements
import com.aidl.MessageBean; // 需要手动导入依赖的bean文件

interface IDemandManager {
    MessageBean getDemand();

    void setDemandIn(in MessageBean msg);//客户端->服务端

    //out和inout都需要重写MessageBean的readFromParcel方法
    void setDemandOut(out MessageBean msg);//服务端->客户端

    void setDemandInOut(inout MessageBean msg);//客户端<->服务端
}
