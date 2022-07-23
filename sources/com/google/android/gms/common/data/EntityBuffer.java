package com.google.android.gms.common.data;

import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zamh = false;
    private ArrayList<Integer> zami;

    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public abstract T getEntry(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract String getPrimaryDataMarkerColumn();

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0065, code lost:
        if (r6.mDataHolder.getString(r4, r7, r3) == null) goto L_0x006b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get(int r7) {
        /*
            r6 = this;
            r6.zabz()
            int r0 = r6.zah(r7)
            r1 = 0
            if (r7 < 0) goto L_0x006a
            java.util.ArrayList<java.lang.Integer> r2 = r6.zami
            int r2 = r2.size()
            if (r7 != r2) goto L_0x0013
            goto L_0x006a
        L_0x0013:
            java.util.ArrayList<java.lang.Integer> r2 = r6.zami
            int r2 = r2.size()
            r3 = 1
            int r2 = r2 - r3
            if (r7 != r2) goto L_0x0032
            com.google.android.gms.common.data.DataHolder r2 = r6.mDataHolder
            int r2 = r2.getCount()
            java.util.ArrayList<java.lang.Integer> r4 = r6.zami
            java.lang.Object r4 = r4.get(r7)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            int r2 = r2 - r4
            goto L_0x004d
        L_0x0032:
            java.util.ArrayList<java.lang.Integer> r2 = r6.zami
            int r4 = r7 + 1
            java.lang.Object r2 = r2.get(r4)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.util.ArrayList<java.lang.Integer> r4 = r6.zami
            java.lang.Object r4 = r4.get(r7)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            int r2 = r2 - r4
        L_0x004d:
            if (r2 != r3) goto L_0x0068
            int r7 = r6.zah(r7)
            com.google.android.gms.common.data.DataHolder r3 = r6.mDataHolder
            int r3 = r3.getWindowIndex(r7)
            java.lang.String r4 = r6.getChildDataMarkerColumn()
            if (r4 == 0) goto L_0x0068
            com.google.android.gms.common.data.DataHolder r5 = r6.mDataHolder
            java.lang.String r7 = r5.getString(r4, r7, r3)
            if (r7 != 0) goto L_0x0068
            goto L_0x006b
        L_0x0068:
            r1 = r2
            goto L_0x006b
        L_0x006a:
        L_0x006b:
            java.lang.Object r7 = r6.getEntry(r0, r1)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.EntityBuffer.get(int):java.lang.Object");
    }

    public int getCount() {
        zabz();
        return this.zami.size();
    }

    private final void zabz() {
        synchronized (this) {
            if (!this.zamh) {
                int count = this.mDataHolder.getCount();
                ArrayList<Integer> arrayList = new ArrayList<>();
                this.zami = arrayList;
                if (count > 0) {
                    arrayList.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    int i = 1;
                    while (i < count) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                        if (string2 != null) {
                            if (!string2.equals(string)) {
                                this.zami.add(Integer.valueOf(i));
                                string = string2;
                            }
                            i++;
                        } else {
                            StringBuilder sb = new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(primaryDataMarkerColumn);
                            sb.append(", at row: ");
                            sb.append(i);
                            sb.append(", for window: ");
                            sb.append(windowIndex);
                            throw new NullPointerException(sb.toString());
                        }
                    }
                }
                this.zamh = true;
            }
        }
    }

    private final int zah(int i) {
        if (i >= 0 && i < this.zami.size()) {
            return this.zami.get(i).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public String getChildDataMarkerColumn() {
        return null;
    }
}
