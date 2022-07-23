package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CustomAttribute {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod = false;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    public AttributeType getType() {
        return this.mType;
    }

    public boolean isContinuous() {
        switch (C01881.f14xbc2a0812[this.mType.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return false;
            default:
                return true;
        }
    }

    public void setFloatValue(float value) {
        this.mFloatValue = value;
    }

    public void setColorValue(int value) {
        this.mColorValue = value;
    }

    public void setIntValue(int value) {
        this.mIntegerValue = value;
    }

    public void setStringValue(String value) {
        this.mStringValue = value;
    }

    public int numberOfInterpolatedValues() {
        switch (this.mType) {
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                return 4;
            default:
                return 1;
        }
    }

    public float getValueToInterpolate() {
        switch (this.mType) {
            case BOOLEAN_TYPE:
                return this.mBooleanValue ? 1.0f : 0.0f;
            case STRING_TYPE:
                throw new RuntimeException("Cannot interpolate String");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case INT_TYPE:
                return (float) this.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue;
            case DIMENSION_TYPE:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] ret) {
        switch (this.mType) {
            case BOOLEAN_TYPE:
                ret[0] = this.mBooleanValue ? 1.0f : 0.0f;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int i = this.mColorValue;
                ret[0] = (float) Math.pow((double) (((float) ((i >> 16) & 255)) / 255.0f), 2.2d);
                ret[1] = (float) Math.pow((double) (((float) ((i >> 8) & 255)) / 255.0f), 2.2d);
                ret[2] = (float) Math.pow((double) (((float) (i & 255)) / 255.0f), 2.2d);
                ret[3] = ((float) ((i >> 24) & 255)) / 255.0f;
                return;
            case INT_TYPE:
                ret[0] = (float) this.mIntegerValue;
                return;
            case FLOAT_TYPE:
                ret[0] = this.mFloatValue;
                return;
            case DIMENSION_TYPE:
                ret[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public void setValue(float[] value) {
        boolean z = true;
        switch (C01881.f14xbc2a0812[this.mType.ordinal()]) {
            case 1:
            case 6:
                this.mIntegerValue = (int) value[0];
                return;
            case 2:
                if (((double) value[0]) <= 0.5d) {
                    z = false;
                }
                this.mBooleanValue = z;
                return;
            case 3:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 4:
            case 5:
                int hsvToRgb = hsvToRgb(value[0], value[1], value[2]);
                this.mColorValue = hsvToRgb;
                this.mColorValue = (hsvToRgb & ViewCompat.MEASURED_SIZE_MASK) | (clamp((int) (value[3] * 255.0f)) << 24);
                return;
            case 7:
                this.mFloatValue = value[0];
                return;
            case 8:
                this.mFloatValue = value[0];
                return;
            default:
                return;
        }
    }

    public static int hsvToRgb(float hue, float saturation, float value) {
        int h = (int) (hue * 6.0f);
        float f = (6.0f * hue) - ((float) h);
        int p = (int) ((value * 255.0f * (1.0f - saturation)) + 0.5f);
        int q = (int) ((value * 255.0f * (1.0f - (f * saturation))) + 0.5f);
        int t = (int) ((value * 255.0f * (1.0f - ((1.0f - f) * saturation))) + 0.5f);
        int v = (int) ((255.0f * value) + 0.5f);
        switch (h) {
            case 0:
                return -16777216 | ((v << 16) + (t << 8) + p);
            case 1:
                return -16777216 | ((q << 16) + (v << 8) + p);
            case 2:
                return -16777216 | ((p << 16) + (v << 8) + t);
            case 3:
                return -16777216 | ((p << 16) + (q << 8) + v);
            case 4:
                return -16777216 | ((t << 16) + (p << 8) + v);
            case 5:
                return -16777216 | ((v << 16) + (p << 8) + q);
            default:
                return 0;
        }
    }

    public boolean diff(CustomAttribute CustomAttribute) {
        if (CustomAttribute == null || this.mType != CustomAttribute.mType) {
            return false;
        }
        switch (C01881.f14xbc2a0812[this.mType.ordinal()]) {
            case 1:
            case 6:
                if (this.mIntegerValue == CustomAttribute.mIntegerValue) {
                    return true;
                }
                return false;
            case 2:
                if (this.mBooleanValue == CustomAttribute.mBooleanValue) {
                    return true;
                }
                return false;
            case 3:
                if (this.mIntegerValue == CustomAttribute.mIntegerValue) {
                    return true;
                }
                return false;
            case 4:
            case 5:
                if (this.mColorValue == CustomAttribute.mColorValue) {
                    return true;
                }
                return false;
            case 7:
                if (this.mFloatValue == CustomAttribute.mFloatValue) {
                    return true;
                }
                return false;
            case 8:
                if (this.mFloatValue == CustomAttribute.mFloatValue) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public CustomAttribute(String name, AttributeType attributeType) {
        this.mName = name;
        this.mType = attributeType;
    }

    public CustomAttribute(String name, AttributeType attributeType, Object value, boolean method) {
        this.mName = name;
        this.mType = attributeType;
        this.mMethod = method;
        setValue(value);
    }

    public CustomAttribute(CustomAttribute source, Object value) {
        this.mName = source.mName;
        this.mType = source.mType;
        setValue(value);
    }

    public void setValue(Object value) {
        switch (C01881.f14xbc2a0812[this.mType.ordinal()]) {
            case 1:
            case 6:
                this.mIntegerValue = ((Integer) value).intValue();
                return;
            case 2:
                this.mBooleanValue = ((Boolean) value).booleanValue();
                return;
            case 3:
                this.mStringValue = (String) value;
                return;
            case 4:
            case 5:
                this.mColorValue = ((Integer) value).intValue();
                return;
            case 7:
                this.mFloatValue = ((Float) value).floatValue();
                return;
            case 8:
                this.mFloatValue = ((Float) value).floatValue();
                return;
            default:
                return;
        }
    }

    public static HashMap<String, CustomAttribute> extractAttributes(HashMap<String, CustomAttribute> base, Object view) {
        HashMap<String, CustomAttribute> ret = new HashMap<>();
        Class<?> cls = view.getClass();
        for (String name : base.keySet()) {
            CustomAttribute CustomAttribute = base.get(name);
            try {
                ret.put(name, new CustomAttribute(CustomAttribute, cls.getMethod("getMap" + name, new Class[0]).invoke(view, new Object[0])));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return ret;
    }

    public static void setAttributes(Object view, HashMap<String, CustomAttribute> map) {
        Class<?> cls = view.getClass();
        for (String name : map.keySet()) {
            CustomAttribute CustomAttribute = map.get(name);
            String methodName = name;
            if (!CustomAttribute.mMethod) {
                methodName = "set" + methodName;
            }
            try {
                switch (C01881.f14xbc2a0812[CustomAttribute.mType.ordinal()]) {
                    case 1:
                        cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(CustomAttribute.mIntegerValue)});
                        break;
                    case 2:
                        cls.getMethod(methodName, new Class[]{Boolean.TYPE}).invoke(view, new Object[]{Boolean.valueOf(CustomAttribute.mBooleanValue)});
                        break;
                    case 3:
                        cls.getMethod(methodName, new Class[]{CharSequence.class}).invoke(view, new Object[]{CustomAttribute.mStringValue});
                        break;
                    case 4:
                        cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(CustomAttribute.mColorValue)});
                        break;
                    case 6:
                        cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(CustomAttribute.mIntegerValue)});
                        break;
                    case 7:
                        cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(CustomAttribute.mFloatValue)});
                        break;
                    case 8:
                        cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(CustomAttribute.mFloatValue)});
                        break;
                }
            } catch (NoSuchMethodException e) {
                Utils.loge(TAG, e.getMessage());
                Utils.loge(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                StringBuilder sb = new StringBuilder();
                sb.append(cls.getName());
                sb.append(" must have a method ");
                sb.append(methodName);
                Utils.loge(TAG, sb.toString());
            } catch (IllegalAccessException e2) {
                Utils.loge(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                Utils.loge(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
                e3.printStackTrace();
            }
        }
    }

    public void applyCustom(Object view) {
        Class<?> cls = view.getClass();
        String name = this.mName;
        String methodName = name;
        if (!this.mMethod) {
            methodName = "set" + methodName;
        }
        try {
            switch (C01881.f14xbc2a0812[this.mType.ordinal()]) {
                case 1:
                case 6:
                    cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(this.mIntegerValue)});
                    return;
                case 2:
                    cls.getMethod(methodName, new Class[]{Boolean.TYPE}).invoke(view, new Object[]{Boolean.valueOf(this.mBooleanValue)});
                    return;
                case 3:
                    cls.getMethod(methodName, new Class[]{CharSequence.class}).invoke(view, new Object[]{this.mStringValue});
                    return;
                case 4:
                    cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(view, new Object[]{Integer.valueOf(this.mColorValue)});
                    return;
                case 7:
                    cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(this.mFloatValue)});
                    return;
                case 8:
                    cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(this.mFloatValue)});
                    return;
                default:
                    return;
            }
        } catch (NoSuchMethodException e) {
            Utils.loge(TAG, e.getMessage());
            Utils.loge(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            sb.append(" must have a method ");
            sb.append(methodName);
            Utils.loge(TAG, sb.toString());
        } catch (IllegalAccessException e2) {
            Utils.loge(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            Utils.loge(TAG, " Custom Attribute \"" + name + "\" not found on " + cls.getName());
            e3.printStackTrace();
        }
    }

    private static int clamp(int c) {
        int c2 = (c & ((c >> 31) ^ -1)) - 255;
        return (c2 & (c2 >> 31)) + 255;
    }

    public void setInterpolatedValue(Object view, float[] value) {
        Object obj = view;
        Class<?> cls = view.getClass();
        String methodName = "set" + this.mName;
        try {
            switch (this.mType) {
                case BOOLEAN_TYPE:
                    Method method = cls.getMethod(methodName, new Class[]{Boolean.TYPE});
                    boolean z = true;
                    Object[] objArr = new Object[1];
                    if (value[0] <= 0.5f) {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    method.invoke(obj, objArr);
                    return;
                case STRING_TYPE:
                    throw new RuntimeException("unable to interpolate strings " + this.mName);
                case COLOR_TYPE:
                    cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf((clamp((int) (value[3] * 255.0f)) << 24) | (clamp((int) (((float) Math.pow((double) value[0], 0.45454545454545453d)) * 255.0f)) << 16) | (clamp((int) (((float) Math.pow((double) value[1], 0.45454545454545453d)) * 255.0f)) << 8) | clamp((int) (((float) Math.pow((double) value[2], 0.45454545454545453d)) * 255.0f)))});
                    return;
                case INT_TYPE:
                    cls.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf((int) value[0])});
                    return;
                case FLOAT_TYPE:
                    cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(value[0])});
                    return;
                case DIMENSION_TYPE:
                    cls.getMethod(methodName, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(value[0])});
                    return;
                default:
                    return;
            }
        } catch (NoSuchMethodException e) {
            Utils.loge(TAG, "no method " + methodName + " on View \"" + view.getClass().getName() + "\"");
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            Utils.loge(TAG, "cannot access method " + methodName + " on View \"" + view.getClass().getName() + "\"");
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }
}
