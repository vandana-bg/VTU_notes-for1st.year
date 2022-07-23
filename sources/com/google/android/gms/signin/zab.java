package com.google.android.gms.signin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class zab {
    public static final Api<SignInOptions> API;
    private static final Api.ClientKey<SignInClientImpl> CLIENT_KEY;
    public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zapv;
    private static final Scope zar = new Scope(Scopes.PROFILE);
    private static final Scope zas = new Scope("email");
    private static final Api.ClientKey<SignInClientImpl> zasj;
    private static final Api.AbstractClientBuilder<SignInClientImpl, Object> zask;
    private static final Api<Object> zasl;

    static {
        Api.ClientKey<SignInClientImpl> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        Api.ClientKey<SignInClientImpl> clientKey2 = new Api.ClientKey<>();
        zasj = clientKey2;
        zaa zaa = new zaa();
        zapv = zaa;
        zad zad = new zad();
        zask = zad;
        API = new Api<>("SignIn.API", zaa, clientKey);
        zasl = new Api<>("SignIn.INTERNAL_API", zad, clientKey2);
    }
}
