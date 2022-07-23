package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.C0771R;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class StringResourceValueReader {
    private final Resources zzfi;
    private final String zzfj;

    public StringResourceValueReader(Context context) {
        Preconditions.checkNotNull(context);
        Resources resources = context.getResources();
        this.zzfi = resources;
        this.zzfj = resources.getResourcePackageName(C0771R.string.common_google_play_services_unknown_issue);
    }

    @Nullable
    public String getString(String str) {
        int identifier = this.zzfi.getIdentifier(str, TypedValues.Custom.S_STRING, this.zzfj);
        if (identifier == 0) {
            return null;
        }
        return this.zzfi.getString(identifier);
    }
}
