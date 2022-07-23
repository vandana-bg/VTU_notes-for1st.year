package androidx.core.util;

import android.os.Build;
import androidx.core.graphics.ColorUtils$$ExternalSynthetic0;
import java.util.Arrays;

public class ObjectsCompat {
    private ObjectsCompat() {
    }

    public static boolean equals(Object a, Object b) {
        if (Build.VERSION.SDK_INT >= 19) {
            return ColorUtils$$ExternalSynthetic0.m6m0(a, b);
        }
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(Object o) {
        if (o != null) {
            return o.hashCode();
        }
        return 0;
    }

    public static int hash(Object... values) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Arrays.hashCode(values);
        }
        return Arrays.hashCode(values);
    }

    public static String toString(Object o, String nullDefault) {
        return o != null ? o.toString() : nullDefault;
    }
}
