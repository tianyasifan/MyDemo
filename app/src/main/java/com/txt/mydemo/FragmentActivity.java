package com.txt.mydemo;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.txt.fragment.TabFragment1;
import com.txt.fragment.TwoFragment;

public class FragmentActivity extends AppCompatActivity implements TabFragment1.OnFragmentInteractionListener{

    private FragmentManager manager;
//    private FragmentTransaction transaction;//只能作为局部变量，一次只能执行一个fragment的commit；
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        manager = getSupportFragmentManager();



    }

    public void tab1(View view){
        FragmentTransaction
        transaction = manager.beginTransaction();
        //设置动画效果，必须在add ，replace，remove之前调用
        //使用系统默认的动画
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //使用自定义的动画
        transaction.setCustomAnimations(R.anim.slide_in_from_right,R.anim.slide_in_from_right);
        transaction.add(R.id.content, TabFragment1.newInstance("1", "2"), "tab1");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void tab2(View view){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content, TwoFragment.newInstance("abc"),"tab2");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //activity向fragment传值
    private void setTextToFragment(){
        ((TabFragment1)manager.findFragmentByTag("tab1")).setTextByActivity();
    }

    //实现tab1的回调接口（即fragment向activity传值）
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
