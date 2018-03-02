package com.here.name.website.SnapchatUI.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.ArgbEvaluator;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.here.name.website.SnapchatUI.R;

/**
 * Created by Charles on 6/27/2017.
 */

public class SnapTabs extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ImageView mCenterImage;
    private ImageView mLeftImage;
    private ImageView mRightImage;
    private ImageView mBottomImage;

    private View mIndicator;

    private ArgbEvaluator mArgbEvaluator;
    private int centerColor;
    private int sideColor;

    private int mEndViewsTranslationX;
    private int mIndicatorTranslationX;
    private int mCenterTranslationY;

    public SnapTabs(@NonNull Context context){
        this(context,null);
    }

    public SnapTabs(@NonNull Context context, @Nullable AttributeSet attrs){
      this(context, attrs, 0);
    }

    public SnapTabs(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init();
    }
public void setUpWithViewPager(final ViewPager viewPager){
    viewPager.addOnPageChangeListener(this);

    mLeftImage.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewPager.getCurrentItem()!=0){
                viewPager.setCurrentItem(0);
            }
        }
    });

    mRightImage.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewPager.getCurrentItem()!=2){
                viewPager.setCurrentItem(2);
            }
        }
    });

}
    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_snap_tabs,this,true);
        mCenterImage= (ImageView) findViewById(R.id.centerButton);
        mLeftImage= (ImageView) findViewById(R.id.leftButton);
        mRightImage= (ImageView) findViewById(R.id.rightButton);
        mBottomImage= (ImageView) findViewById(R.id.bottomButton);
        mIndicator= findViewById(R.id.indicator);
        centerColor= ContextCompat.getColor(getContext(),R.color.white);
        sideColor= ContextCompat.getColor(getContext(),R.color.gray);

        mArgbEvaluator= new ArgbEvaluator();
        mIndicatorTranslationX= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,80,getResources().getDisplayMetrics());

        mBottomImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mEndViewsTranslationX= (int) ((mBottomImage.getX()-mLeftImage.getX())-mIndicatorTranslationX);
                mBottomImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                mCenterTranslationY=getHeight()-mBottomImage.getBottom();
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position==0){
            setColor(1-positionOffset);
            moveViews(1-positionOffset);

            moveAndScaleCenter(1-positionOffset);

            mIndicator.setTranslationX((positionOffset-1)*mIndicatorTranslationX);
        }else if(position==1){
            setColor(positionOffset);
            moveViews(positionOffset);

            moveAndScaleCenter(positionOffset);
            mIndicator.setTranslationX(positionOffset*mIndicatorTranslationX);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void moveAndScaleCenter(float fractionFromCenter){
        float scale= .7f+((1-fractionFromCenter)*.3f);
        mCenterImage.setScaleX(scale);
        mCenterImage.setScaleY(scale);

        int translation=(int) (fractionFromCenter*mCenterTranslationY);

        mCenterImage.setTranslationY(translation);
        mBottomImage.setTranslationY(translation);
        mBottomImage.setAlpha(1-fractionFromCenter);
    }

    private void moveViews(float fractionFromCenter){
        mLeftImage.setTranslationX(fractionFromCenter*mEndViewsTranslationX);
        mRightImage.setTranslationX(-fractionFromCenter*mEndViewsTranslationX);
        mIndicator.setAlpha(fractionFromCenter);
        mIndicator.setScaleX(fractionFromCenter);
    }

    private void setColor(float fractionFromCenter){
        int color=(int) mArgbEvaluator.evaluate(fractionFromCenter, centerColor,sideColor);

        mCenterImage.setColorFilter(color);
        mLeftImage.setColorFilter(color);;
        mRightImage.setColorFilter(color);
    }
}
