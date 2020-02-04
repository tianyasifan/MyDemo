package com.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tongxiaotao on 19-4-11.
 */
public class MessageBean implements Parcelable {
    private String content;
    private int level;

    /* 没有这个构造会出现
    错误: 无法将类 MessageBean中的构造器 MessageBean应用到给定类型;
_arg0 = new com.aidl.MessageBean();
        ^
  需要: Parcel
  找到: 没有参数
  原因: 实际参数列表和形式参数列表长度不同*/
    public MessageBean(){}

    public MessageBean(String content, int level){
        this.content = content;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    protected MessageBean(Parcel in) {
        content = in.readString();
        level = in.readInt();
    }

    public static final Creator<MessageBean> CREATOR = new Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel in) {
            return new MessageBean(in);
        }

        @Override
        public MessageBean[] newArray(int size) {
            return new MessageBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeInt(level);
    }

    //如果需要支持定向tag为out,inout，就要重写该方法
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        this.content = dest.readString();
        this.level = dest.readInt();
    }

    @Override
    public String toString() {
        return content + "," + level;
    }
}
