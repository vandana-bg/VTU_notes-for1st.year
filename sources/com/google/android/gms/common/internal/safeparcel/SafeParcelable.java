package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public interface SafeParcelable extends Parcelable {
    public static final String NULL = "SAFE_PARCELABLE_NULL_STRING";

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public @interface Class {
        String creator();

        boolean validate() default false;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public @interface Constructor {
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public @interface Field {
        String defaultValue() default "SAFE_PARCELABLE_NULL_STRING";

        String defaultValueUnchecked() default "SAFE_PARCELABLE_NULL_STRING";

        String getter() default "SAFE_PARCELABLE_NULL_STRING";

        /* renamed from: id */
        int mo12351id();

        String type() default "SAFE_PARCELABLE_NULL_STRING";
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public @interface Indicator {
        String getter() default "SAFE_PARCELABLE_NULL_STRING";
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public @interface Param {
        /* renamed from: id */
        int mo12354id();
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public @interface Reserved {
        int[] value();
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public @interface VersionField {
        String getter() default "SAFE_PARCELABLE_NULL_STRING";

        /* renamed from: id */
        int mo12357id();

        String type() default "SAFE_PARCELABLE_NULL_STRING";
    }
}
