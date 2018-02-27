package com.txt.javatest;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.txt.javatest.bean.ResponseBean;

/**
 * Created by tongxiaotao on 18-2-10.
 */

public class JsonTest {
    String jsonString = "{\"code\":\"200\",\"message\":\"\",\"redirect\":\"\",\"value\":{\"cp\":\"celltick\",\"index\":0,\"news\":null}}";
    String jsonString2 = "{\"code\":\"200\",\"message\":\"\",\"redirect\":\"\",\"value\":\"null\"}";
    String jsonString3 = "{\"code\":\"200\",\"message\":\"\",\"redirect\":\"\",\"value\":null}";
    String jsonString4 = "{\"code\":\"200\",\"message\":\"\",\"redirect\":\"\",\"value\":{}";
    String jsonString5 = "{\"code\":\"200\",\"message\":\"\",\"redirect\":\"\",\"value\":{null}";
    String jsonString6 = "<HTML><HEAD><TITLE> Web Authentication Redirect</TITLE><META http-equiv=\"Cache-control\" content=\"no-cache\"><META http-equiv=\"Pragma\" content=\"no-cache\"><META http-equiv=\"Expires\" content=\"-1\"><META http-equiv=\"refresh\" content=\"1; URL=http://172.16.82.109/wxwifi/login?switch_url=http://1.1.1.2/login.html&ap_mac=58:97:bd:fd:84:50&client_mac=90:f0:52:0b:e4:a2&wlan=Guest&redirect=browser.in.meizu.com/api/hotnews.do?reqno=1518263715885&imei=867343030113274&sn=871QBDS2222E3&model=M1871&os=Flyme%207.0.1.0G&ver=7.0.100-2018021017&locale=zh_CN&id=223e1e65-9816-4ee6-bb70-f1ebe682975e&index=5\"></HEAD></HTML>";

    public void json(){
        try {
            ResponseBean bean = JSON.parseObject(jsonString3, ResponseBean.class);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("news", "json数据格式不对：" + jsonString3);
            return;
        }
        Log.d("news", "*********************");
    }
}
