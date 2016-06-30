package com.txt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * Created by txt on 2015/12/29.
 */
public class SwipeDismissListView extends ListView {
    private int mMinFlingVelocity;
    private int mMaxFlingVeloctiy;
    private int mSlop;//触发滑动事件的最短距离，取系统的默认值
    private float mDownX,mDownY;
    private int mDownPosition;
    private View mDownView;
    private int mViewWidth;
    private VelocityTracker mVelocityTracker;
    private boolean mSwipping;//是否可以滑动
    private int mAnimationTime = 600;
    private OnDismissCallback mOnDismissCallback;

    public SwipeDismissListView(Context context) {
        this(context,null,0);
    }

    public SwipeDismissListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwipeDismissListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewConfiguration vf = ViewConfiguration.get(context);
        mSlop = vf.getScaledTouchSlop();
        mMinFlingVelocity = vf.getScaledMinimumFlingVelocity();//获得允许执行一个fling手势动作的最小速度值
        mMaxFlingVeloctiy = vf.getScaledMaximumFlingVelocity();//获得允许执行一个fling手势动作的最大速度值
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                handleActionDown(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                return handleActionMove(ev);
            case MotionEvent.ACTION_UP:
                handleActionUp(ev);
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void handleActionUp(MotionEvent ev) {
        if (mVelocityTracker == null || mDownView == null|| !mSwipping) {
            return;
        }
        float deltaX = ev.getX() - mDownX;

        mVelocityTracker.computeCurrentVelocity(1000);//计算速度，单位为秒
        float velocityX = Math.abs(mVelocityTracker.getXVelocity());
        float velocityY = Math.abs(mVelocityTracker.getYVelocity());

        boolean dismiss = false; //item是否要滑出屏幕
        boolean dismissRight = false;//是否往右边删除

        //当拖动item的距离大于item的一半，item滑出屏幕
        if(Math.abs(deltaX)>mViewWidth/2){
            dismiss = true;
            dismissRight = deltaX > 0;
        }else if(mMinFlingVelocity<=velocityX && mMaxFlingVeloctiy>= velocityX && velocityX>velocityY){
            //手指在屏幕滑动的速度在某个范围内，也使得item滑出屏幕
            dismiss = true;
            dismissRight = mVelocityTracker.getXVelocity()>0;
        }

        if(dismiss){
            ViewPropertyAnimator.animate(mDownView)
                    .translationX(dismissRight ? mViewWidth : -mViewWidth)//X轴方向的移动距离，判断向左还是向右
                    .alpha(0)
                    .setDuration(mAnimationTime)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //Item滑出界面之后执行删除
                            performDismiss(mDownView,mDownPosition);
                        }
                    });
        }else{
            //将item移动到开始位置
            ViewPropertyAnimator.animate(mDownView)
                    .translationX(0)
                    .alpha(1)
                    .setDuration(mAnimationTime)
                    .setListener(null);
        }

        //移除速度检测器
        releaseVelocityTracker();
        mSwipping = false;
    }

    /**
     * 在此方法中执行item删除之后，其他的item向上或者向下滚动的动画，并且将position回调到方法onDismiss()中
     * @param dismissView
     * @param dismissPosition
     */
    private void performDismiss(final View dismissView, int dismissPosition) {
        final ViewGroup.LayoutParams params = dismissView.getLayoutParams();//获得布局属性，为后续动态设置布局属性做准备
        final int originalHeight = dismissView.getHeight();//获取原始高度

        //使用ValueAnimator代替ObjectAnimator，因为v默认使用的Interpolator(插补器)是AccelerateDecelerateInterpolator(开始和结束的时候慢，中间快)
        //这样效果更好
        //而valueAnimator作用的是某个值，而不是这里的view，所以把view的高度值给valueAnimator
        //然后再在updatelistner监听中动态的根据height值的变化来设置待删除的item的高度
        //最后，当动画执行完成后，再把待删除item还原回原来的样子，然后去刷新listview的数据（因为实际上并不能真正的删除listview中的item--不可以这样做）
        ValueAnimator animator = ValueAnimator.ofInt(originalHeight,0).setDuration(mAnimationTime);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.height = (int) animation.getAnimatedValue();//动画在执行过程中一直执行该方法，动态的获得当前的高度，设置给dismissview
                dismissView.setLayoutParams(params);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mOnDismissCallback != null) {
                    mOnDismissCallback.onDismiss(mDownPosition);
                }
                //这段代码很重要，因为我们并没有将item从ListView中移除，而是将item的高度设置为0
                //所以我们在动画执行完毕之后将item设置回来
                ViewHelper.setAlpha(dismissView, 1);
                ViewHelper.setTranslationX(dismissView, 0);
                params.height = originalHeight;
                dismissView.setLayoutParams(params);
            }
        });
        animator.start();
    }

    private boolean handleActionMove(MotionEvent ev) {
        if(mVelocityTracker==null || mDownView ==null)
            return super.onTouchEvent(ev);
        // 获取X,Y方向滑动的距离
        float deltaX = ev.getX()-mDownX;
        float deltaY = ev.getY()-mDownY;

        // X方向滑动的距离大于mSlop并且Y方向滑动的距离小于mSlop，表示可以滑动
        if(Math.abs(deltaX)>mSlop && Math.abs(deltaY)<mSlop){
            mSwipping = true;
            //当手指滑动item,取消item的点击事件，不然我们滑动Item也伴随着item点击事件的发生
            MotionEvent cancelEvent = MotionEvent.obtain(ev);
            cancelEvent.setAction(MotionEvent.ACTION_CANCEL |
                    (ev.getActionIndex()<< MotionEvent.ACTION_POINTER_INDEX_SHIFT));
            onTouchEvent(cancelEvent);
        }

        if(mSwipping){
            // 跟谁手指移动item
            ViewHelper.setTranslationX(mDownView,deltaX);
            // 透明度渐变
            ViewHelper.setAlpha(mDownView,Math.max(0f, Math.min(1f, 1f - 2f * Math.abs(deltaX)/ mViewWidth)));
            // 手指滑动的时候,返回true，表示SwipeDismissListView自己处理onTouchEvent,其他的就交给父类来处理
            return true;
        }
        return super.onTouchEvent(ev);
    }

    private void handleActionDown(MotionEvent ev) {
        mDownX = ev.getX();
        mDownY = ev.getY();

        mDownPosition = pointToPosition((int)mDownX,(int)mDownY);
        if(mDownPosition == AbsListView.INVALID_POSITION){//无效的位置
            return;
        }
        //以上处理为了能找到手指按下去时的子item
        mDownView = getChildAt(mDownPosition-getFirstVisiblePosition());
        if(mDownView!=null){
            mViewWidth = mDownView.getWidth();
        }

        //加入速度检测
        addVelocityTracker(ev);
    }

    //添加速度检测器
    private void addVelocityTracker(MotionEvent ev){
        if(mVelocityTracker==null){
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);
    }

    //移除速度检测器
    private void releaseVelocityTracker(){
        if(mVelocityTracker!=null){
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public void setOnDismissCallback(OnDismissCallback onDismissCallback){
        mOnDismissCallback = onDismissCallback;
    }

    /**
     * 删除item后的回调接口
     */
    public interface OnDismissCallback{
        void onDismiss(int dismissPosition);
    }
}
