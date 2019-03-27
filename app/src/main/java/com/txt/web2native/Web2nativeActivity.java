package com.txt.web2native;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.txt.mydemo.R;

public class Web2nativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web2native);
    }

    public void openWeb(View view){
        Uri uri = Uri.parse("http://www.erdian.net/m.html");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.addCategory("liberty"); // 匹配category
        intent.setComponent(new ComponentName("com.android.browser","com.android.browser.BrowserActivity"));//匹配包名类名
        startActivity(intent);
        // 网页中test按钮的连接是“<a href="znn://aa.bb:80/test?p=12&d=1">test</a>  ”
        // scheme是znn，所以我们在自定义的scheme（即browserapp的主activity的）应该改成这个，否则启动不了
    }
}
