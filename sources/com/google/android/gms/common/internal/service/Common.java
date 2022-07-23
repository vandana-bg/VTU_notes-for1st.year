package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class Common {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.ClientKey<zah> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zah, Api.ApiOptions.NoOptions> zapv;
    public static final zab zapw = new zae();

    static {
        Api.ClientKey<zah> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zac zac = new zac();
        zapv = zac;
        API = new Api<>("Common.API", zac, clientKey);
    }
}
