package com.wcy.qq_sildingmenu;

import android.R.integer;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {

	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;

	private int mScreenWidth;
	private int mMenuRightPadding;
	private int mMenuWidth;

	/*
	 * 自定义属性时的构造方法
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;

		// 把dp转化为px
		mMenuRightPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 200, context
						.getResources().getDisplayMetrics());
	}

	// 决定布局的位置
	//通过设置偏移量 将menu隐藏 
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
      
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(mMenuWidth, 0);
		}
	}

	// 测量内部子布局的宽高和 自己的宽高
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWapper= (LinearLayout) getChildAt(0);
         mMenu =  (ViewGroup) mWapper.getChildAt(0);
         mContent=(ViewGroup) mWapper.getChildAt(1);
         mMenuWidth = mMenu.getLayoutParams().width=mScreenWidth - mMenuRightPadding;
         mContent.getLayoutParams().width = mScreenWidth;
         
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			float x= getScrollX();
			if(x>=mMenuWidth/2){
				this.smoothScrollTo(mMenuWidth, 0);
			}else{
				this.smoothScrollTo(0, 0); 
			}
			return true;

		
		}
		return super.onTouchEvent(ev);
	}

}
