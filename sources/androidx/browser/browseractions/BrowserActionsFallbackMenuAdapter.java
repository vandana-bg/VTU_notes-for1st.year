package androidx.browser.browseractions;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.browser.C0160R;
import androidx.core.content.res.ResourcesCompat;
import java.util.List;

class BrowserActionsFallbackMenuAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<BrowserActionItem> mMenuItems;

    BrowserActionsFallbackMenuAdapter(List<BrowserActionItem> menuItems, Context context) {
        this.mMenuItems = menuItems;
        this.mContext = context;
    }

    public int getCount() {
        return this.mMenuItems.size();
    }

    public Object getItem(int position) {
        return this.mMenuItems.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        BrowserActionItem menuItem = this.mMenuItems.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(C0160R.layout.browser_actions_context_menu_row, (ViewGroup) null);
            viewHolder = new ViewHolderItem();
            viewHolder.mIcon = (ImageView) convertView.findViewById(C0160R.C0163id.browser_actions_menu_item_icon);
            viewHolder.mText = (TextView) convertView.findViewById(C0160R.C0163id.browser_actions_menu_item_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        viewHolder.mText.setText(menuItem.getTitle());
        if (menuItem.getIconId() != 0) {
            viewHolder.mIcon.setImageDrawable(ResourcesCompat.getDrawable(this.mContext.getResources(), menuItem.getIconId(), (Resources.Theme) null));
        } else {
            viewHolder.mIcon.setImageDrawable((Drawable) null);
        }
        return convertView;
    }

    static class ViewHolderItem {
        ImageView mIcon;
        TextView mText;

        ViewHolderItem() {
        }
    }
}
