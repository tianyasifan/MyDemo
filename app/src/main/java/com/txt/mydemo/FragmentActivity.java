package com.txt.mydemo;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.txt.fragment.OneFragment;
import com.txt.fragment.TwoFragment;

public class FragmentActivity extends AppCompatActivity implements OneFragment.OnFragmentInteractionListener{

    private FragmentManager manager;
    private static final String TAG1 = "TAG1", TAG2 = "TAG2";
//    private FragmentTransaction transaction;//只能作为局部变量，一次只能执行一个fragment的commit；
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        manager = getSupportFragmentManager();
    }

    public void tab1(View view){
//        Fragment fragment = manager.findFragmentByTag(TAG1);
        FragmentTransaction
        transaction = manager.beginTransaction();
        //设置动画效果，必须在add ，replace，remove之前调用
        //使用系统默认的动画
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //使用自定义的动画
        transaction.setCustomAnimations(R.anim.slide_in_from_right,R.anim.slide_in_from_right);
//        if(manager.findFragmentByTag(TAG2) != null){
//            transaction.hide(manager.findFragmentByTag(TAG2));
//        }
//        if( fragment != null ){
//            transaction.show(fragment);
//        }else {
            transaction.add(R.id.content, OneFragment.newInstance("1", "2"), TAG1);
            transaction.addToBackStack(null);//添加到回退栈中，在这里只是记录一个标记，其实是在commit中，调用fragmentManager的allocBackStackIndex方法，所以回退栈是由fragmentManager管理的。必须在commit之前
//        }
        transaction.commit();
    }

    public void tab2(View view){
        Fragment fragment = manager.findFragmentByTag(TAG2);
        FragmentTransaction transaction = manager.beginTransaction();
        if (manager.findFragmentByTag(TAG1) != null) {
            transaction.hide(manager.findFragmentByTag(TAG1));
        }
        if( fragment != null ) {
            transaction.show(fragment);

        }else {
            transaction.add(R.id.content, TwoFragment.newInstance("abc"), TAG2);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void replaceTab1(View view){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, TwoFragment.newInstance("abc"), TAG2);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //activity向fragment传值
    private void setTextToFragment(){
        ((OneFragment)manager.findFragmentByTag("tab1")).setTextByActivity();
    }

    //实现tab1的回调接口（即fragment向activity传值）
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
