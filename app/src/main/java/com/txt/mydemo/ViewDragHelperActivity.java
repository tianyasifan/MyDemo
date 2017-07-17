package com.txt.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by txt on 2015/12/15.
 */
public class ViewDragHelperActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdrag);

    }

    public void button(View view){
        print(view);
        view.setX(100);
        System.out.println("==============");
        print(view);
    }

    private void print(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        float x = view.getX();
        float y = view.getY();
        int left = view.getLeft();
        int top = view.getTop();
        int bottom = view.getBottom();
        int right = view.getRight();

        System.out.println("width" + width);
        System.out.println("height" + height);
        System.out.println("translationX" + translationX);
        System.out.println("translationY" + translationY);
        System.out.println("x" + x);
        System.out.println("y" + y);
        System.out.println("left" + left);
        System.out.println("top" + top);
        System.out.println("bottom" + bottom);
        System.out.println("right" + right);
    }
}
