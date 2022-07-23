package com.google.android.material.navigation;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.C0052R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.util.Pools;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.TextScale;
import java.util.HashSet;

public abstract class NavigationBarMenuView extends ViewGroup implements MenuView {
    private static final long ACTIVE_ANIMATION_DURATION_MS = 115;
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int ITEM_POOL_SIZE = 5;
    private SparseArray<BadgeDrawable> badgeDrawables = new SparseArray<>(5);
    private NavigationBarItemView[] buttons;
    private Drawable itemBackground;
    private int itemBackgroundRes;
    private int itemIconSize;
    private ColorStateList itemIconTint;
    private final Pools.Pool<NavigationBarItemView> itemPool = new Pools.SynchronizedPool(5);
    private int itemTextAppearanceActive;
    private int itemTextAppearanceInactive;
    private final ColorStateList itemTextColorDefault = createDefaultColorStateList(16842808);
    private ColorStateList itemTextColorFromUser;
    private int labelVisibilityMode;
    /* access modifiers changed from: private */
    public MenuBuilder menu;
    private final View.OnClickListener onClickListener;
    private final SparseArray<View.OnTouchListener> onTouchListeners = new SparseArray<>(5);
    /* access modifiers changed from: private */
    public NavigationBarPresenter presenter;
    private int selectedItemId = 0;
    private int selectedItemPosition = 0;
    private final TransitionSet set;

    /* access modifiers changed from: protected */
    public abstract NavigationBarItemView createNavigationBarItemView(Context context);

    public NavigationBarMenuView(Context context) {
        super(context);
        AutoTransition autoTransition = new AutoTransition();
        this.set = autoTransition;
        autoTransition.setOrdering(0);
        autoTransition.setDuration((long) ACTIVE_ANIMATION_DURATION_MS);
        autoTransition.setInterpolator((TimeInterpolator) new FastOutSlowInInterpolator());
        autoTransition.addTransition(new TextScale());
        this.onClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                MenuItem item = ((NavigationBarItemView) v).getItemData();
                if (!NavigationBarMenuView.this.menu.performItemAction(item, NavigationBarMenuView.this.presenter, 0)) {
                    item.setChecked(true);
                }
            }
        };
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    public void initialize(MenuBuilder menu2) {
        this.menu = menu2;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        AccessibilityNodeInfoCompat.wrap(info).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.menu.getVisibleItems().size(), false, 1));
    }

    public void setIconTintList(ColorStateList tint) {
        this.itemIconTint = tint;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                item.setIconTintList(tint);
            }
        }
    }

    public ColorStateList getIconTintList() {
        return this.itemIconTint;
    }

    public void setItemIconSize(int iconSize) {
        this.itemIconSize = iconSize;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                item.setIconSize(iconSize);
            }
        }
    }

    public int getItemIconSize() {
        return this.itemIconSize;
    }

    public void setItemTextColor(ColorStateList color) {
        this.itemTextColorFromUser = color;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                item.setTextColor(color);
            }
        }
    }

    public ColorStateList getItemTextColor() {
        return this.itemTextColorFromUser;
    }

    public void setItemTextAppearanceInactive(int textAppearanceRes) {
        this.itemTextAppearanceInactive = textAppearanceRes;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                item.setTextAppearanceInactive(textAppearanceRes);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    item.setTextColor(colorStateList);
                }
            }
        }
    }

    public int getItemTextAppearanceInactive() {
        return this.itemTextAppearanceInactive;
    }

    public void setItemTextAppearanceActive(int textAppearanceRes) {
        this.itemTextAppearanceActive = textAppearanceRes;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                item.setTextAppearanceActive(textAppearanceRes);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    item.setTextColor(colorStateList);
                }
            }
        }
    }

    public int getItemTextAppearanceActive() {
        return this.itemTextAppearanceActive;
    }

    public void setItemBackgroundRes(int background) {
        this.itemBackgroundRes = background;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                item.setItemBackground(background);
            }
        }
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.itemBackgroundRes;
    }

    public void setItemBackground(Drawable background) {
        this.itemBackground = background;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                item.setItemBackground(background);
            }
        }
    }

    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr == null || navigationBarItemViewArr.length <= 0) {
            return this.itemBackground;
        }
        return navigationBarItemViewArr[0].getBackground();
    }

    public void setLabelVisibilityMode(int labelVisibilityMode2) {
        this.labelVisibilityMode = labelVisibilityMode2;
    }

    public int getLabelVisibilityMode() {
        return this.labelVisibilityMode;
    }

    public void setItemOnTouchListener(int menuItemId, View.OnTouchListener onTouchListener) {
        if (onTouchListener == null) {
            this.onTouchListeners.remove(menuItemId);
        } else {
            this.onTouchListeners.put(menuItemId, onTouchListener);
        }
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                if (item.getItemData().getItemId() == menuItemId) {
                    item.setOnTouchListener(onTouchListener);
                }
            }
        }
    }

    public ColorStateList createDefaultColorStateList(int baseColorThemeAttr) {
        TypedValue value = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(baseColorThemeAttr, value, true)) {
            return null;
        }
        ColorStateList baseColor = AppCompatResources.getColorStateList(getContext(), value.resourceId);
        if (!getContext().getTheme().resolveAttribute(C0052R.attr.colorPrimary, value, true)) {
            return null;
        }
        int colorPrimary = value.data;
        int defaultColor = baseColor.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{baseColor.getColorForState(iArr, defaultColor), colorPrimary, defaultColor});
    }

    public void setPresenter(NavigationBarPresenter presenter2) {
        this.presenter = presenter2;
    }

    public void buildMenuView() {
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView item : navigationBarItemViewArr) {
                if (item != null) {
                    this.itemPool.release(item);
                    item.removeBadge();
                }
            }
        }
        if (this.menu.size() == 0) {
            this.selectedItemId = 0;
            this.selectedItemPosition = 0;
            this.buttons = null;
            return;
        }
        removeUnusedBadges();
        this.buttons = new NavigationBarItemView[this.menu.size()];
        boolean shifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
        for (int i = 0; i < this.menu.size(); i++) {
            this.presenter.setUpdateSuspended(true);
            this.menu.getItem(i).setCheckable(true);
            this.presenter.setUpdateSuspended(false);
            NavigationBarItemView child = getNewItem();
            this.buttons[i] = child;
            child.setIconTintList(this.itemIconTint);
            child.setIconSize(this.itemIconSize);
            child.setTextColor(this.itemTextColorDefault);
            child.setTextAppearanceInactive(this.itemTextAppearanceInactive);
            child.setTextAppearanceActive(this.itemTextAppearanceActive);
            child.setTextColor(this.itemTextColorFromUser);
            Drawable drawable = this.itemBackground;
            if (drawable != null) {
                child.setItemBackground(drawable);
            } else {
                child.setItemBackground(this.itemBackgroundRes);
            }
            child.setShifting(shifting);
            child.setLabelVisibilityMode(this.labelVisibilityMode);
            MenuItemImpl item2 = (MenuItemImpl) this.menu.getItem(i);
            child.initialize(item2, 0);
            child.setItemPosition(i);
            int itemId = item2.getItemId();
            child.setOnTouchListener(this.onTouchListeners.get(itemId));
            child.setOnClickListener(this.onClickListener);
            int i2 = this.selectedItemId;
            if (i2 != 0 && itemId == i2) {
                this.selectedItemPosition = i;
            }
            setBadgeIfNeeded(child);
            addView(child);
        }
        int min = Math.min(this.menu.size() - 1, this.selectedItemPosition);
        this.selectedItemPosition = min;
        this.menu.getItem(min).setChecked(true);
    }

    public void updateMenuView() {
        MenuBuilder menuBuilder = this.menu;
        if (menuBuilder != null && this.buttons != null) {
            int menuSize = menuBuilder.size();
            if (menuSize != this.buttons.length) {
                buildMenuView();
                return;
            }
            int previousSelectedId = this.selectedItemId;
            for (int i = 0; i < menuSize; i++) {
                MenuItem item = this.menu.getItem(i);
                if (item.isChecked()) {
                    this.selectedItemId = item.getItemId();
                    this.selectedItemPosition = i;
                }
            }
            if (previousSelectedId != this.selectedItemId) {
                TransitionManager.beginDelayedTransition(this, this.set);
            }
            boolean shifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
            for (int i2 = 0; i2 < menuSize; i2++) {
                this.presenter.setUpdateSuspended(true);
                this.buttons[i2].setLabelVisibilityMode(this.labelVisibilityMode);
                this.buttons[i2].setShifting(shifting);
                this.buttons[i2].initialize((MenuItemImpl) this.menu.getItem(i2), 0);
                this.presenter.setUpdateSuspended(false);
            }
        }
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView item = this.itemPool.acquire();
        if (item == null) {
            return createNavigationBarItemView(getContext());
        }
        return item;
    }

    public int getSelectedItemId() {
        return this.selectedItemId;
    }

    /* access modifiers changed from: protected */
    public boolean isShifting(int labelVisibilityMode2, int childCount) {
        if (labelVisibilityMode2 == -1) {
            if (childCount > 3) {
                return true;
            }
        } else if (labelVisibilityMode2 == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void tryRestoreSelectedItemId(int itemId) {
        int size = this.menu.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = this.menu.getItem(i);
            if (itemId == item.getItemId()) {
                this.selectedItemId = itemId;
                this.selectedItemPosition = i;
                item.setChecked(true);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.badgeDrawables;
    }

    /* access modifiers changed from: package-private */
    public void setBadgeDrawables(SparseArray<BadgeDrawable> badgeDrawables2) {
        this.badgeDrawables = badgeDrawables2;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView itemView : navigationBarItemViewArr) {
                itemView.setBadge(badgeDrawables2.get(itemView.getId()));
            }
        }
    }

    public BadgeDrawable getBadge(int menuItemId) {
        return this.badgeDrawables.get(menuItemId);
    }

    /* access modifiers changed from: package-private */
    public BadgeDrawable getOrCreateBadge(int menuItemId) {
        validateMenuItemId(menuItemId);
        BadgeDrawable badgeDrawable = this.badgeDrawables.get(menuItemId);
        if (badgeDrawable == null) {
            badgeDrawable = BadgeDrawable.create(getContext());
            this.badgeDrawables.put(menuItemId, badgeDrawable);
        }
        NavigationBarItemView itemView = findItemView(menuItemId);
        if (itemView != null) {
            itemView.setBadge(badgeDrawable);
        }
        return badgeDrawable;
    }

    /* access modifiers changed from: package-private */
    public void removeBadge(int menuItemId) {
        validateMenuItemId(menuItemId);
        BadgeDrawable badgeDrawable = this.badgeDrawables.get(menuItemId);
        NavigationBarItemView itemView = findItemView(menuItemId);
        if (itemView != null) {
            itemView.removeBadge();
        }
        if (badgeDrawable != null) {
            this.badgeDrawables.remove(menuItemId);
        }
    }

    private void setBadgeIfNeeded(NavigationBarItemView child) {
        BadgeDrawable badgeDrawable;
        int childId = child.getId();
        if (isValidId(childId) && (badgeDrawable = this.badgeDrawables.get(childId)) != null) {
            child.setBadge(badgeDrawable);
        }
    }

    private void removeUnusedBadges() {
        HashSet<Integer> activeKeys = new HashSet<>();
        for (int i = 0; i < this.menu.size(); i++) {
            activeKeys.add(Integer.valueOf(this.menu.getItem(i).getItemId()));
        }
        for (int i2 = 0; i2 < this.badgeDrawables.size(); i2++) {
            int key = this.badgeDrawables.keyAt(i2);
            if (!activeKeys.contains(Integer.valueOf(key))) {
                this.badgeDrawables.delete(key);
            }
        }
    }

    public NavigationBarItemView findItemView(int menuItemId) {
        validateMenuItemId(menuItemId);
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr == null) {
            return null;
        }
        for (NavigationBarItemView itemView : navigationBarItemViewArr) {
            if (itemView.getId() == menuItemId) {
                return itemView;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder getMenu() {
        return this.menu;
    }

    private boolean isValidId(int viewId) {
        return viewId != -1;
    }

    private void validateMenuItemId(int viewId) {
        if (!isValidId(viewId)) {
            throw new IllegalArgumentException(viewId + " is not a valid view id");
        }
    }
}
