package com.google.android.material.navigationrail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.widget.TintTypedArray;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.google.android.material.C0774R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.navigation.NavigationBarView;

public class NavigationRailView extends NavigationBarView {
    private static final int DEFAULT_HEADER_GRAVITY = 49;
    static final int DEFAULT_MENU_GRAVITY = 49;
    static final int MAX_ITEM_COUNT = 7;
    private View headerView;
    private final int topMargin;

    public NavigationRailView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NavigationRailView(Context context, AttributeSet attrs) {
        this(context, attrs, C0774R.attr.navigationRailStyle);
    }

    public NavigationRailView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, C0774R.style.Widget_MaterialComponents_NavigationRailView);
    }

    public NavigationRailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.topMargin = getResources().getDimensionPixelSize(C0774R.dimen.mtrl_navigation_rail_margin);
        TintTypedArray attributes = ThemeEnforcement.obtainTintedStyledAttributes(getContext(), attrs, C0774R.styleable.NavigationRailView, defStyleAttr, defStyleRes, new int[0]);
        int headerLayoutRes = attributes.getResourceId(C0774R.styleable.NavigationRailView_headerLayout, 0);
        if (headerLayoutRes != 0) {
            addHeaderView(headerLayoutRes);
        }
        setMenuGravity(attributes.getInt(C0774R.styleable.NavigationRailView_menuGravity, 49));
        attributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minWidthSpec = makeMinWidthSpec(widthMeasureSpec);
        super.onMeasure(minWidthSpec, heightMeasureSpec);
        if (isHeaderViewVisible()) {
            measureChild(getNavigationRailMenuView(), minWidthSpec, View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - this.headerView.getMeasuredHeight()) - this.topMargin, Integer.MIN_VALUE));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        NavigationRailMenuView menuView = getNavigationRailMenuView();
        int offsetY = 0;
        if (isHeaderViewVisible()) {
            int usedTop = this.headerView.getBottom() + this.topMargin;
            int menuTop = menuView.getTop();
            if (menuTop < usedTop) {
                offsetY = usedTop - menuTop;
            }
        } else if (menuView.isTopGravity() != 0) {
            offsetY = this.topMargin;
        }
        if (offsetY > 0) {
            menuView.layout(menuView.getLeft(), menuView.getTop() + offsetY, menuView.getRight(), menuView.getBottom() + offsetY);
        }
    }

    public void addHeaderView(int layoutRes) {
        addHeaderView(LayoutInflater.from(getContext()).inflate(layoutRes, this, false));
    }

    public void addHeaderView(View headerView2) {
        removeHeaderView();
        this.headerView = headerView2;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
        params.gravity = 49;
        params.topMargin = this.topMargin;
        addView(headerView2, 0, params);
    }

    public View getHeaderView() {
        return this.headerView;
    }

    public void removeHeaderView() {
        View view = this.headerView;
        if (view != null) {
            removeView(view);
            this.headerView = null;
        }
    }

    public void setMenuGravity(int gravity) {
        getNavigationRailMenuView().setMenuGravity(gravity);
    }

    public int getMenuGravity() {
        return getNavigationRailMenuView().getMenuGravity();
    }

    public int getMaxItemCount() {
        return 7;
    }

    private NavigationRailMenuView getNavigationRailMenuView() {
        return (NavigationRailMenuView) getMenuView();
    }

    /* access modifiers changed from: protected */
    public NavigationRailMenuView createNavigationBarMenuView(Context context) {
        return new NavigationRailMenuView(context);
    }

    private int makeMinWidthSpec(int measureSpec) {
        int minWidth = getSuggestedMinimumWidth();
        if (View.MeasureSpec.getMode(measureSpec) == 1073741824 || minWidth <= 0) {
            return measureSpec;
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(measureSpec), minWidth + getPaddingLeft() + getPaddingRight()), BasicMeasure.EXACTLY);
    }

    private boolean isHeaderViewVisible() {
        View view = this.headerView;
        return (view == null || view.getVisibility() == 8) ? false : true;
    }
}
