package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.os.CancellationSignal;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() {
        public int compare(byte[] l, byte[] r) {
            if (l.length != r.length) {
                return l.length - r.length;
            }
            for (int i = 0; i < l.length; i++) {
                if (l[i] != r[i]) {
                    return l[i] - r[i];
                }
            }
            return 0;
        }
    };

    private FontProvider() {
    }

    static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, FontRequest request, CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfo = getProvider(context.getPackageManager(), request, context.getResources());
        if (providerInfo == null) {
            return FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[]) null);
        }
        return FontsContractCompat.FontFamilyResult.create(0, query(context, request, providerInfo.authority, cancellationSignal));
    }

    static ProviderInfo getProvider(PackageManager packageManager, FontRequest request, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = request.getProviderAuthority();
        ProviderInfo info = packageManager.resolveContentProvider(providerAuthority, 0);
        if (info == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (info.packageName.equals(request.getProviderPackage())) {
            List<byte[]> signatures = convertToByteArrayList(packageManager.getPackageInfo(info.packageName, 64).signatures);
            Collections.sort(signatures, sByteArrayComparator);
            List<List<byte[]>> requestCertificatesList = getCertificates(request, resources);
            for (int i = 0; i < requestCertificatesList.size(); i++) {
                List<byte[]> requestSignatures = new ArrayList<>(requestCertificatesList.get(i));
                Collections.sort(requestSignatures, sByteArrayComparator);
                if (equalsByteArrayList(signatures, requestSignatures)) {
                    return info;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + request.getProviderPackage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0132  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.core.provider.FontsContractCompat.FontInfo[] query(android.content.Context r20, androidx.core.provider.FontRequest r21, java.lang.String r22, android.os.CancellationSignal r23) {
        /*
            r1 = r22
            java.lang.String r0 = "result_code"
            java.lang.String r2 = "font_italic"
            java.lang.String r3 = "font_weight"
            java.lang.String r4 = "font_ttc_index"
            java.lang.String r5 = "file_id"
            java.lang.String r6 = "_id"
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            android.net.Uri$Builder r8 = new android.net.Uri$Builder
            r8.<init>()
            java.lang.String r9 = "content"
            android.net.Uri$Builder r8 = r8.scheme(r9)
            android.net.Uri$Builder r8 = r8.authority(r1)
            android.net.Uri r8 = r8.build()
            android.net.Uri$Builder r10 = new android.net.Uri$Builder
            r10.<init>()
            android.net.Uri$Builder r9 = r10.scheme(r9)
            android.net.Uri$Builder r9 = r9.authority(r1)
            java.lang.String r10 = "file"
            android.net.Uri$Builder r9 = r9.appendPath(r10)
            android.net.Uri r9 = r9.build()
            r19 = 0
            r10 = 7
            java.lang.String[] r12 = new java.lang.String[r10]     // Catch:{ all -> 0x012f }
            r15 = 0
            r12[r15] = r6     // Catch:{ all -> 0x012f }
            r14 = 1
            r12[r14] = r5     // Catch:{ all -> 0x012f }
            r10 = 2
            r12[r10] = r4     // Catch:{ all -> 0x012f }
            r10 = 3
            java.lang.String r11 = "font_variation_settings"
            r12[r10] = r11     // Catch:{ all -> 0x012f }
            r10 = 4
            r12[r10] = r3     // Catch:{ all -> 0x012f }
            r10 = 5
            r12[r10] = r2     // Catch:{ all -> 0x012f }
            r10 = 6
            r12[r10] = r0     // Catch:{ all -> 0x012f }
            int r10 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x012f }
            r11 = 16
            if (r10 <= r11) goto L_0x007f
            android.content.ContentResolver r10 = r20.getContentResolver()     // Catch:{ all -> 0x012f }
            java.lang.String r13 = "query = ?"
            java.lang.String[] r11 = new java.lang.String[r14]     // Catch:{ all -> 0x012f }
            java.lang.String r16 = r21.getQuery()     // Catch:{ all -> 0x012f }
            r11[r15] = r16     // Catch:{ all -> 0x012f }
            r16 = 0
            r17 = r11
            r11 = r8
            r1 = 1
            r14 = r17
            r15 = r16
            r16 = r23
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x012f }
            r11 = 0
            goto L_0x009a
        L_0x007f:
            r1 = 1
            android.content.ContentResolver r13 = r20.getContentResolver()     // Catch:{ all -> 0x012f }
            java.lang.String r16 = "query = ?"
            java.lang.String[] r10 = new java.lang.String[r1]     // Catch:{ all -> 0x012f }
            java.lang.String r11 = r21.getQuery()     // Catch:{ all -> 0x012f }
            r15 = 0
            r10[r15] = r11     // Catch:{ all -> 0x012f }
            r18 = 0
            r14 = r8
            r11 = 0
            r15 = r12
            r17 = r10
            android.database.Cursor r10 = r13.query(r14, r15, r16, r17, r18)     // Catch:{ all -> 0x012f }
        L_0x009a:
            if (r10 == 0) goto L_0x011e
            int r13 = r10.getCount()     // Catch:{ all -> 0x011a }
            if (r13 <= 0) goto L_0x011e
            int r0 = r10.getColumnIndex(r0)     // Catch:{ all -> 0x011a }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ all -> 0x011a }
            r13.<init>()     // Catch:{ all -> 0x011a }
            r7 = r13
            int r6 = r10.getColumnIndex(r6)     // Catch:{ all -> 0x011a }
            int r5 = r10.getColumnIndex(r5)     // Catch:{ all -> 0x011a }
            int r4 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x011a }
            int r3 = r10.getColumnIndex(r3)     // Catch:{ all -> 0x011a }
            int r2 = r10.getColumnIndex(r2)     // Catch:{ all -> 0x011a }
        L_0x00c0:
            boolean r13 = r10.moveToNext()     // Catch:{ all -> 0x011a }
            if (r13 == 0) goto L_0x0117
            r13 = -1
            if (r0 == r13) goto L_0x00ce
            int r15 = r10.getInt(r0)     // Catch:{ all -> 0x011a }
            goto L_0x00cf
        L_0x00ce:
            r15 = 0
        L_0x00cf:
            r14 = r15
            if (r4 == r13) goto L_0x00d7
            int r15 = r10.getInt(r4)     // Catch:{ all -> 0x011a }
            goto L_0x00d8
        L_0x00d7:
            r15 = 0
        L_0x00d8:
            if (r5 != r13) goto L_0x00eb
            long r16 = r10.getLong(r6)     // Catch:{ all -> 0x011a }
            r18 = r16
            r16 = r12
            r11 = r18
            android.net.Uri r17 = android.content.ContentUris.withAppendedId(r8, r11)     // Catch:{ all -> 0x011a }
            r11 = r17
            goto L_0x00f7
        L_0x00eb:
            r16 = r12
            long r11 = r10.getLong(r5)     // Catch:{ all -> 0x011a }
            android.net.Uri r17 = android.content.ContentUris.withAppendedId(r9, r11)     // Catch:{ all -> 0x011a }
            r11 = r17
        L_0x00f7:
            if (r3 == r13) goto L_0x00fe
            int r12 = r10.getInt(r3)     // Catch:{ all -> 0x011a }
            goto L_0x0100
        L_0x00fe:
            r12 = 400(0x190, float:5.6E-43)
        L_0x0100:
            if (r2 == r13) goto L_0x010a
            int r13 = r10.getInt(r2)     // Catch:{ all -> 0x011a }
            if (r13 != r1) goto L_0x010a
            r13 = 1
            goto L_0x010b
        L_0x010a:
            r13 = 0
        L_0x010b:
            androidx.core.provider.FontsContractCompat$FontInfo r1 = androidx.core.provider.FontsContractCompat.FontInfo.create(r11, r15, r12, r13, r14)     // Catch:{ all -> 0x011a }
            r7.add(r1)     // Catch:{ all -> 0x011a }
            r12 = r16
            r1 = 1
            r11 = 0
            goto L_0x00c0
        L_0x0117:
            r16 = r12
            goto L_0x0120
        L_0x011a:
            r0 = move-exception
            r19 = r10
            goto L_0x0130
        L_0x011e:
            r16 = r12
        L_0x0120:
            if (r10 == 0) goto L_0x0125
            r10.close()
        L_0x0125:
            r0 = 0
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = new androidx.core.provider.FontsContractCompat.FontInfo[r0]
            java.lang.Object[] r0 = r7.toArray(r0)
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = (androidx.core.provider.FontsContractCompat.FontInfo[]) r0
            return r0
        L_0x012f:
            r0 = move-exception
        L_0x0130:
            if (r19 == 0) goto L_0x0135
            r19.close()
        L_0x0135:
            goto L_0x0137
        L_0x0136:
            throw r0
        L_0x0137:
            goto L_0x0136
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.query(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }

    private static List<List<byte[]>> getCertificates(FontRequest request, Resources resources) {
        if (request.getCertificates() != null) {
            return request.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, request.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> signatures, List<byte[]> requestSignatures) {
        if (signatures.size() != requestSignatures.size()) {
            return false;
        }
        for (int i = 0; i < signatures.size(); i++) {
            if (!Arrays.equals(signatures.get(i), requestSignatures.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatures) {
        List<byte[]> shaList = new ArrayList<>();
        for (Signature byteArray : signatures) {
            shaList.add(byteArray.toByteArray());
        }
        return shaList;
    }
}
