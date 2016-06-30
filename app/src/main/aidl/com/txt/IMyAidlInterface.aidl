// IMyAidlInterface.aidl
package com.txt;

// Declare any non-default types here with import statements
//实现普通数据传递
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int add(int first, int second);
}
