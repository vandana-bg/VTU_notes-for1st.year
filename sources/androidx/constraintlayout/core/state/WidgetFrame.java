package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.state.Transition;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;
import java.util.Set;

public class WidgetFrame {
    private static final boolean OLD_SYSTEM = true;
    public static float phone_orientation = Float.NaN;
    public float alpha = Float.NaN;
    public int bottom = 0;
    public float interpolatedPos = Float.NaN;
    public int left = 0;
    public final HashMap<String, CustomVariable> mCustom = new HashMap<>();
    public String name = null;
    public float pivotX = Float.NaN;
    public float pivotY = Float.NaN;
    public int right = 0;
    public float rotationX = Float.NaN;
    public float rotationY = Float.NaN;
    public float rotationZ = Float.NaN;
    public float scaleX = Float.NaN;
    public float scaleY = Float.NaN;
    public int top = 0;
    public float translationX = Float.NaN;
    public float translationY = Float.NaN;
    public float translationZ = Float.NaN;
    public int visibility = 0;
    public ConstraintWidget widget = null;

    public int width() {
        return Math.max(0, this.right - this.left);
    }

    public int height() {
        return Math.max(0, this.bottom - this.top);
    }

    public WidgetFrame() {
    }

    public WidgetFrame(ConstraintWidget widget2) {
        this.widget = widget2;
    }

    public WidgetFrame(WidgetFrame frame) {
        this.widget = frame.widget;
        this.left = frame.left;
        this.top = frame.top;
        this.right = frame.right;
        this.bottom = frame.bottom;
        updateAttributes(frame);
    }

    public void updateAttributes(WidgetFrame frame) {
        this.pivotX = frame.pivotX;
        this.pivotY = frame.pivotY;
        this.rotationX = frame.rotationX;
        this.rotationY = frame.rotationY;
        this.rotationZ = frame.rotationZ;
        this.translationX = frame.translationX;
        this.translationY = frame.translationY;
        this.translationZ = frame.translationZ;
        this.scaleX = frame.scaleX;
        this.scaleY = frame.scaleY;
        this.alpha = frame.alpha;
        this.visibility = frame.visibility;
        this.mCustom.clear();
        if (frame != null) {
            for (CustomVariable c : frame.mCustom.values()) {
                this.mCustom.put(c.getName(), c.copy());
            }
        }
    }

    public boolean isDefaultTransform() {
        if (!Float.isNaN(this.rotationX) || !Float.isNaN(this.rotationY) || !Float.isNaN(this.rotationZ) || !Float.isNaN(this.translationX) || !Float.isNaN(this.translationY) || !Float.isNaN(this.translationZ) || !Float.isNaN(this.scaleX) || !Float.isNaN(this.scaleY) || !Float.isNaN(this.alpha)) {
            return false;
        }
        return OLD_SYSTEM;
    }

    public static void interpolate(int parentWidth, int parentHeight, WidgetFrame frame, WidgetFrame start, WidgetFrame end, Transition transition, float progress) {
        int startWidth;
        int startHeight;
        float startAlpha;
        int endHeight;
        int startY;
        int startX;
        float f;
        float progressPosition;
        int interpolateStartFrame;
        int i = parentWidth;
        int i2 = parentHeight;
        WidgetFrame widgetFrame = frame;
        WidgetFrame widgetFrame2 = start;
        WidgetFrame widgetFrame3 = end;
        Transition transition2 = transition;
        int frameNumber = (int) (progress * 100.0f);
        int startX2 = widgetFrame2.left;
        int startY2 = widgetFrame2.top;
        int endX = widgetFrame3.left;
        int endY = widgetFrame3.top;
        int startWidth2 = widgetFrame2.right - widgetFrame2.left;
        int startHeight2 = widgetFrame2.bottom - widgetFrame2.top;
        int endWidth = widgetFrame3.right - widgetFrame3.left;
        int startWidth3 = startWidth2;
        int endHeight2 = widgetFrame3.bottom - widgetFrame3.top;
        float progressPosition2 = progress;
        float startAlpha2 = widgetFrame2.alpha;
        int startHeight3 = startHeight2;
        float endAlpha = widgetFrame3.alpha;
        if (widgetFrame2.visibility == 8) {
            startX2 = (int) (((float) startX2) - (((float) endWidth) / 2.0f));
            startY2 = (int) (((float) startY2) - (((float) endHeight2) / 2.0f));
            int startWidth4 = endWidth;
            startHeight = endHeight2;
            if (Float.isNaN(startAlpha2)) {
                startWidth = startWidth4;
                startAlpha = 0.0f;
            } else {
                float f2 = startAlpha2;
                startWidth = startWidth4;
                startAlpha = f2;
            }
        } else {
            startAlpha = startAlpha2;
            startWidth = startWidth3;
            startHeight = startHeight3;
        }
        int endHeight3 = endHeight2;
        int startX3 = startX2;
        if (widgetFrame3.visibility == 8) {
            endX = (int) (((float) endX) - (((float) startWidth) / 2.0f));
            endY = (int) (((float) endY) - (((float) startHeight) / 2.0f));
            endWidth = startWidth;
            endHeight = startHeight;
            if (Float.isNaN(endAlpha)) {
                endAlpha = 0.0f;
            }
        } else {
            endHeight = endHeight3;
        }
        if (Float.isNaN(startAlpha) && !Float.isNaN(endAlpha)) {
            startAlpha = 1.0f;
        }
        if (!Float.isNaN(startAlpha) && Float.isNaN(endAlpha)) {
            endAlpha = 1.0f;
        }
        if (widgetFrame.widget == null || !transition.hasPositionKeyframes()) {
            f = progress;
            int i3 = frameNumber;
            startY = startY2;
            progressPosition = progressPosition2;
            startX = startX3;
        } else {
            Transition.KeyPosition firstPosition = transition2.findPreviousPosition(widgetFrame.widget.stringId, frameNumber);
            int startY3 = startY2;
            Transition.KeyPosition lastPosition = transition2.findNextPosition(widgetFrame.widget.stringId, frameNumber);
            if (firstPosition == lastPosition) {
                lastPosition = null;
            }
            int interpolateEndFrame = 100;
            if (firstPosition != null) {
                int i4 = frameNumber;
                startX3 = (int) (firstPosition.f35x * ((float) i));
                int startY4 = (int) (firstPosition.f36y * ((float) i2));
                interpolateStartFrame = firstPosition.frame;
                startY3 = startY4;
            } else {
                interpolateStartFrame = 0;
            }
            if (lastPosition != null) {
                Transition.KeyPosition keyPosition = firstPosition;
                endY = (int) (lastPosition.f36y * ((float) i2));
                interpolateEndFrame = lastPosition.frame;
                endX = (int) (lastPosition.f35x * ((float) i));
            }
            f = progress;
            progressPosition = ((100.0f * f) - ((float) interpolateStartFrame)) / ((float) (interpolateEndFrame - interpolateStartFrame));
            startY = startY3;
            startX = startX3;
        }
        WidgetFrame widgetFrame4 = start;
        widgetFrame.widget = widgetFrame4.widget;
        float startAlpha3 = startAlpha;
        int i5 = (int) (((float) startX) + (((float) (endX - startX)) * progressPosition));
        widgetFrame.left = i5;
        int i6 = startX;
        int i7 = (int) (((float) startY) + (((float) (endY - startY)) * progressPosition));
        widgetFrame.top = i7;
        float f3 = progressPosition;
        int height = (int) (((1.0f - f) * ((float) startHeight)) + (((float) endHeight) * f));
        widgetFrame.right = i5 + ((int) (((1.0f - f) * ((float) startWidth)) + (((float) endWidth) * f)));
        widgetFrame.bottom = i7 + height;
        int i8 = height;
        widgetFrame.pivotX = interpolate(widgetFrame4.pivotX, widgetFrame3.pivotX, 0.5f, f);
        widgetFrame.pivotY = interpolate(widgetFrame4.pivotY, widgetFrame3.pivotY, 0.5f, f);
        widgetFrame.rotationX = interpolate(widgetFrame4.rotationX, widgetFrame3.rotationX, 0.0f, f);
        widgetFrame.rotationY = interpolate(widgetFrame4.rotationY, widgetFrame3.rotationY, 0.0f, f);
        widgetFrame.rotationZ = interpolate(widgetFrame4.rotationZ, widgetFrame3.rotationZ, 0.0f, f);
        widgetFrame.scaleX = interpolate(widgetFrame4.scaleX, widgetFrame3.scaleX, 1.0f, f);
        widgetFrame.scaleY = interpolate(widgetFrame4.scaleY, widgetFrame3.scaleY, 1.0f, f);
        widgetFrame.translationX = interpolate(widgetFrame4.translationX, widgetFrame3.translationX, 0.0f, f);
        widgetFrame.translationY = interpolate(widgetFrame4.translationY, widgetFrame3.translationY, 0.0f, f);
        widgetFrame.translationZ = interpolate(widgetFrame4.translationZ, widgetFrame3.translationZ, 0.0f, f);
        widgetFrame.alpha = interpolate(startAlpha3, endAlpha, 1.0f, f);
    }

    private static float interpolate(float start, float end, float defaultValue, float progress) {
        boolean isStartUnset = Float.isNaN(start);
        boolean isEndUnset = Float.isNaN(end);
        if (isStartUnset && isEndUnset) {
            return Float.NaN;
        }
        if (isStartUnset) {
            start = defaultValue;
        }
        if (isEndUnset) {
            end = defaultValue;
        }
        return ((end - start) * progress) + start;
    }

    public float centerX() {
        int i = this.left;
        return ((float) i) + (((float) (this.right - i)) / 2.0f);
    }

    public float centerY() {
        int i = this.top;
        return ((float) i) + (((float) (this.bottom - i)) / 2.0f);
    }

    public WidgetFrame update() {
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget != null) {
            this.left = constraintWidget.getLeft();
            this.top = this.widget.getTop();
            this.right = this.widget.getRight();
            this.bottom = this.widget.getBottom();
            updateAttributes(this.widget.frame);
        }
        return this;
    }

    public WidgetFrame update(ConstraintWidget widget2) {
        if (widget2 == null) {
            return this;
        }
        this.widget = widget2;
        update();
        return this;
    }

    public void addCustomColor(String name2, int color) {
        setCustomAttribute(name2, (int) TypedValues.Custom.TYPE_COLOR, color);
    }

    public int getCustomColor(String name2) {
        if (this.mCustom.containsKey(name2)) {
            return this.mCustom.get(name2).getColorValue();
        }
        return -21880;
    }

    public void addCustomFloat(String name2, float value) {
        setCustomAttribute(name2, (int) TypedValues.Custom.TYPE_FLOAT, value);
    }

    public float getCustomFloat(String name2) {
        if (this.mCustom.containsKey(name2)) {
            return this.mCustom.get(name2).getFloatValue();
        }
        return Float.NaN;
    }

    public void setCustomAttribute(String name2, int type, float value) {
        if (this.mCustom.containsKey(name2)) {
            this.mCustom.get(name2).setFloatValue(value);
        } else {
            this.mCustom.put(name2, new CustomVariable(name2, type, value));
        }
    }

    public void setCustomAttribute(String name2, int type, int value) {
        if (this.mCustom.containsKey(name2)) {
            this.mCustom.get(name2).setIntValue(value);
        } else {
            this.mCustom.put(name2, new CustomVariable(name2, type, value));
        }
    }

    public void setCustomAttribute(String name2, int type, boolean value) {
        if (this.mCustom.containsKey(name2)) {
            this.mCustom.get(name2).setBooleanValue(value);
        } else {
            this.mCustom.put(name2, new CustomVariable(name2, type, value));
        }
    }

    public void setCustomAttribute(String name2, int type, String value) {
        if (this.mCustom.containsKey(name2)) {
            this.mCustom.get(name2).setStringValue(value);
        } else {
            this.mCustom.put(name2, new CustomVariable(name2, type, value));
        }
    }

    public CustomVariable getCustomAttribute(String name2) {
        return this.mCustom.get(name2);
    }

    public Set<String> getCustomAttributeNames() {
        return this.mCustom.keySet();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setValue(java.lang.String r4, androidx.constraintlayout.core.parser.CLElement r5) throws androidx.constraintlayout.core.parser.CLParsingException {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 1
            r2 = 0
            switch(r0) {
                case -1881940865: goto L_0x00c6;
                case -1383228885: goto L_0x00bb;
                case -1349088399: goto L_0x00b0;
                case -1249320806: goto L_0x00a6;
                case -1249320805: goto L_0x009c;
                case -1249320804: goto L_0x0092;
                case -1225497657: goto L_0x0088;
                case -1225497656: goto L_0x007e;
                case -1225497655: goto L_0x0074;
                case -987906986: goto L_0x006a;
                case -987906985: goto L_0x005f;
                case -908189618: goto L_0x0053;
                case -908189617: goto L_0x0047;
                case 115029: goto L_0x003b;
                case 3317767: goto L_0x002f;
                case 92909918: goto L_0x0023;
                case 108511772: goto L_0x0017;
                case 642850769: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x00d1
        L_0x000b:
            java.lang.String r0 = "interpolatedPos"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 11
            goto L_0x00d2
        L_0x0017:
            java.lang.String r0 = "right"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 15
            goto L_0x00d2
        L_0x0023:
            java.lang.String r0 = "alpha"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 10
            goto L_0x00d2
        L_0x002f:
            java.lang.String r0 = "left"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 14
            goto L_0x00d2
        L_0x003b:
            java.lang.String r0 = "top"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 13
            goto L_0x00d2
        L_0x0047:
            java.lang.String r0 = "scaleY"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 9
            goto L_0x00d2
        L_0x0053:
            java.lang.String r0 = "scaleX"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 8
            goto L_0x00d2
        L_0x005f:
            java.lang.String r0 = "pivotY"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 1
            goto L_0x00d2
        L_0x006a:
            java.lang.String r0 = "pivotX"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 0
            goto L_0x00d2
        L_0x0074:
            java.lang.String r0 = "translationZ"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 7
            goto L_0x00d2
        L_0x007e:
            java.lang.String r0 = "translationY"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 6
            goto L_0x00d2
        L_0x0088:
            java.lang.String r0 = "translationX"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 5
            goto L_0x00d2
        L_0x0092:
            java.lang.String r0 = "rotationZ"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 4
            goto L_0x00d2
        L_0x009c:
            java.lang.String r0 = "rotationY"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 3
            goto L_0x00d2
        L_0x00a6:
            java.lang.String r0 = "rotationX"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 2
            goto L_0x00d2
        L_0x00b0:
            java.lang.String r0 = "custom"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 17
            goto L_0x00d2
        L_0x00bb:
            java.lang.String r0 = "bottom"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 16
            goto L_0x00d2
        L_0x00c6:
            java.lang.String r0 = "phone_orientation"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0009
            r0 = 12
            goto L_0x00d2
        L_0x00d1:
            r0 = -1
        L_0x00d2:
            switch(r0) {
                case 0: goto L_0x014d;
                case 1: goto L_0x0146;
                case 2: goto L_0x013f;
                case 3: goto L_0x0138;
                case 4: goto L_0x0131;
                case 5: goto L_0x012a;
                case 6: goto L_0x0123;
                case 7: goto L_0x011c;
                case 8: goto L_0x0115;
                case 9: goto L_0x010e;
                case 10: goto L_0x0107;
                case 11: goto L_0x0100;
                case 12: goto L_0x00f9;
                case 13: goto L_0x00f2;
                case 14: goto L_0x00eb;
                case 15: goto L_0x00e3;
                case 16: goto L_0x00db;
                case 17: goto L_0x00d6;
                default: goto L_0x00d5;
            }
        L_0x00d5:
            return r2
        L_0x00d6:
            r3.parseCustom(r5)
            goto L_0x0154
        L_0x00db:
            int r0 = r5.getInt()
            r3.bottom = r0
            goto L_0x0154
        L_0x00e3:
            int r0 = r5.getInt()
            r3.right = r0
            goto L_0x0154
        L_0x00eb:
            int r0 = r5.getInt()
            r3.left = r0
            goto L_0x0154
        L_0x00f2:
            int r0 = r5.getInt()
            r3.top = r0
            goto L_0x0154
        L_0x00f9:
            float r0 = r5.getFloat()
            phone_orientation = r0
            goto L_0x0154
        L_0x0100:
            float r0 = r5.getFloat()
            r3.interpolatedPos = r0
            goto L_0x0154
        L_0x0107:
            float r0 = r5.getFloat()
            r3.alpha = r0
            goto L_0x0154
        L_0x010e:
            float r0 = r5.getFloat()
            r3.scaleY = r0
            goto L_0x0154
        L_0x0115:
            float r0 = r5.getFloat()
            r3.scaleX = r0
            goto L_0x0154
        L_0x011c:
            float r0 = r5.getFloat()
            r3.translationZ = r0
            goto L_0x0154
        L_0x0123:
            float r0 = r5.getFloat()
            r3.translationY = r0
            goto L_0x0154
        L_0x012a:
            float r0 = r5.getFloat()
            r3.translationX = r0
            goto L_0x0154
        L_0x0131:
            float r0 = r5.getFloat()
            r3.rotationZ = r0
            goto L_0x0154
        L_0x0138:
            float r0 = r5.getFloat()
            r3.rotationY = r0
            goto L_0x0154
        L_0x013f:
            float r0 = r5.getFloat()
            r3.rotationX = r0
            goto L_0x0154
        L_0x0146:
            float r0 = r5.getFloat()
            r3.pivotY = r0
            goto L_0x0154
        L_0x014d:
            float r0 = r5.getFloat()
            r3.pivotX = r0
        L_0x0154:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.state.WidgetFrame.setValue(java.lang.String, androidx.constraintlayout.core.parser.CLElement):boolean");
    }

    /* access modifiers changed from: package-private */
    public void parseCustom(CLElement custom) throws CLParsingException {
        CLObject obj = (CLObject) custom;
        int n = obj.size();
        for (int i = 0; i < n; i++) {
            CLKey k = (CLKey) obj.get(i);
            String content = k.content();
            CLElement v = k.getValue();
            String vStr = v.content();
            if (vStr.matches("#[0-9a-fA-F]+")) {
                setCustomAttribute(k.content(), (int) TypedValues.Custom.TYPE_COLOR, Integer.parseInt(vStr.substring(1), 16));
            } else if (v instanceof CLNumber) {
                setCustomAttribute(k.content(), (int) TypedValues.Custom.TYPE_FLOAT, v.getFloat());
            } else {
                setCustomAttribute(k.content(), (int) TypedValues.Custom.TYPE_STRING, vStr);
            }
        }
    }

    public StringBuilder serialize(StringBuilder ret) {
        return serialize(ret, false);
    }

    public StringBuilder serialize(StringBuilder ret, boolean sendPhoneOrientation) {
        ret.append("{\n");
        add(ret, "left", this.left);
        add(ret, "top", this.top);
        add(ret, "right", this.right);
        add(ret, "bottom", this.bottom);
        add(ret, "pivotX", this.pivotX);
        add(ret, "pivotY", this.pivotY);
        add(ret, "rotationX", this.rotationX);
        add(ret, "rotationY", this.rotationY);
        add(ret, "rotationZ", this.rotationZ);
        add(ret, "translationX", this.translationX);
        add(ret, "translationY", this.translationY);
        add(ret, "translationZ", this.translationZ);
        add(ret, "scaleX", this.scaleX);
        add(ret, "scaleY", this.scaleY);
        add(ret, "alpha", this.alpha);
        add(ret, "visibility", this.left);
        add(ret, "interpolatedPos", this.interpolatedPos);
        if (sendPhoneOrientation) {
            add(ret, "phone_orientation", phone_orientation);
        }
        if (sendPhoneOrientation) {
            add(ret, "phone_orientation", phone_orientation);
        }
        if (this.mCustom.size() != 0) {
            ret.append("custom : {\n");
            for (String s : this.mCustom.keySet()) {
                CustomVariable value = this.mCustom.get(s);
                ret.append(s);
                ret.append(": ");
                switch (value.getType()) {
                    case TypedValues.Custom.TYPE_INT:
                        ret.append(value.getIntegerValue());
                        ret.append(",\n");
                        break;
                    case TypedValues.Custom.TYPE_FLOAT:
                    case TypedValues.Custom.TYPE_DIMENSION:
                        ret.append(value.getFloatValue());
                        ret.append(",\n");
                        break;
                    case TypedValues.Custom.TYPE_COLOR:
                        ret.append("'");
                        ret.append(CustomVariable.colorString(value.getIntegerValue()));
                        ret.append("',\n");
                        break;
                    case TypedValues.Custom.TYPE_STRING:
                        ret.append("'");
                        ret.append(value.getStringValue());
                        ret.append("',\n");
                        break;
                    case TypedValues.Custom.TYPE_BOOLEAN:
                        ret.append("'");
                        ret.append(value.getBooleanValue());
                        ret.append("',\n");
                        break;
                }
            }
            ret.append("}\n");
        }
        ret.append("}\n");
        return ret;
    }

    private static void add(StringBuilder s, String title, int value) {
        s.append(title);
        s.append(": ");
        s.append(value);
        s.append(",\n");
    }

    private static void add(StringBuilder s, String title, float value) {
        if (!Float.isNaN(value)) {
            s.append(title);
            s.append(": ");
            s.append(value);
            s.append(",\n");
        }
    }

    /* access modifiers changed from: package-private */
    public void printCustomAttributes() {
        String ss;
        StackTraceElement s = new Throwable().getStackTrace()[1];
        String ss2 = (".(" + s.getFileName() + ":" + s.getLineNumber() + ") " + s.getMethodName()) + " " + (hashCode() % 1000);
        if (this.widget != null) {
            ss = ss2 + "/" + (this.widget.hashCode() % 1000) + " ";
        } else {
            ss = ss2 + "/NULL ";
        }
        HashMap<String, CustomVariable> hashMap = this.mCustom;
        if (hashMap != null) {
            for (String key : hashMap.keySet()) {
                System.out.println(ss + this.mCustom.get(key).toString());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void logv(String str) {
        String ss;
        StackTraceElement s = new Throwable().getStackTrace()[1];
        String ss2 = (".(" + s.getFileName() + ":" + s.getLineNumber() + ") " + s.getMethodName()) + " " + (hashCode() % 1000);
        if (this.widget != null) {
            ss = ss2 + "/" + (this.widget.hashCode() % 1000);
        } else {
            ss = ss2 + "/NULL";
        }
        System.out.println(ss + " " + str);
    }
}
