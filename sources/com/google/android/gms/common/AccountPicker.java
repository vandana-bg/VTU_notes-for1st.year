package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class AccountPicker {
    private AccountPicker() {
    }

    @Deprecated
    public static Intent newChooseAccountIntent(Account account, ArrayList<Account> arrayList, String[] strArr, boolean z, String str, String str2, String[] strArr2, Bundle bundle) {
        Intent intent = new Intent();
        Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", arrayList);
        intent.putExtra("allowableAccountTypes", strArr);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", account);
        intent.putExtra("alwaysPromptForAccount", z);
        intent.putExtra("descriptionTextOverride", str);
        intent.putExtra("authTokenType", str2);
        intent.putExtra("addAccountRequiredFeatures", strArr2);
        intent.putExtra("setGmsCoreAccount", false);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        intent.putExtra("hostedDomainFilter", (String) null);
        return intent;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public static class AccountChooserOptions {
        /* access modifiers changed from: private */
        public Account zze;
        /* access modifiers changed from: private */
        public ArrayList<Account> zzf;
        /* access modifiers changed from: private */
        public ArrayList<String> zzg;
        /* access modifiers changed from: private */
        public boolean zzh;
        /* access modifiers changed from: private */
        public String zzi;
        /* access modifiers changed from: private */
        public Bundle zzj;
        /* access modifiers changed from: private */
        public boolean zzk;
        /* access modifiers changed from: private */
        public int zzl;
        /* access modifiers changed from: private */
        public String zzm;
        /* access modifiers changed from: private */
        public boolean zzn;
        /* access modifiers changed from: private */
        public zza zzo;
        /* access modifiers changed from: private */
        public String zzp;

        /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
        public static class zza {
        }

        /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
        public static class Builder {
            private Account zze;
            private ArrayList<Account> zzf;
            private ArrayList<String> zzg;
            private boolean zzh = false;
            private String zzi;
            private Bundle zzj;
            private boolean zzk = false;
            private int zzl = 0;
            private boolean zzn = false;

            public Builder setSelectedAccount(Account account) {
                this.zze = account;
                return this;
            }

            public Builder setAllowableAccounts(List<Account> list) {
                this.zzf = list == null ? null : new ArrayList<>(list);
                return this;
            }

            public Builder setAllowableAccountsTypes(List<String> list) {
                this.zzg = list == null ? null : new ArrayList<>(list);
                return this;
            }

            public Builder setAlwaysShowAccountPicker(boolean z) {
                this.zzh = z;
                return this;
            }

            public Builder setTitleOverrideText(String str) {
                this.zzi = str;
                return this;
            }

            public Builder setOptionsForAddingAccount(Bundle bundle) {
                this.zzj = bundle;
                return this;
            }

            public AccountChooserOptions build() {
                Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
                Preconditions.checkArgument(true, "Consent is only valid for account chip styled account picker");
                AccountChooserOptions accountChooserOptions = new AccountChooserOptions();
                ArrayList unused = accountChooserOptions.zzg = this.zzg;
                ArrayList unused2 = accountChooserOptions.zzf = this.zzf;
                boolean unused3 = accountChooserOptions.zzh = this.zzh;
                zza unused4 = accountChooserOptions.zzo = null;
                String unused5 = accountChooserOptions.zzm = null;
                Bundle unused6 = accountChooserOptions.zzj = this.zzj;
                Account unused7 = accountChooserOptions.zze = this.zze;
                boolean unused8 = accountChooserOptions.zzk = false;
                String unused9 = accountChooserOptions.zzp = null;
                int unused10 = accountChooserOptions.zzl = 0;
                String unused11 = accountChooserOptions.zzi = this.zzi;
                boolean unused12 = accountChooserOptions.zzn = false;
                return accountChooserOptions;
            }
        }
    }

    public static Intent newChooseAccountIntent(AccountChooserOptions accountChooserOptions) {
        String str;
        Intent intent = new Intent();
        int i = 0;
        if (!accountChooserOptions.zzn) {
            Preconditions.checkArgument(accountChooserOptions.zzm == null, "We only support hostedDomain filter for account chip styled account picker");
            Preconditions.checkArgument(accountChooserOptions.zzo == null, "Consent is only valid for account chip styled account picker");
        }
        if (accountChooserOptions.zzn) {
            str = "com.google.android.gms.common.account.CHOOSE_ACCOUNT_USERTILE";
        } else {
            str = "com.google.android.gms.common.account.CHOOSE_ACCOUNT";
        }
        intent.setAction(str);
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", accountChooserOptions.zzf);
        if (accountChooserOptions.zzg != null) {
            intent.putExtra("allowableAccountTypes", (String[]) accountChooserOptions.zzg.toArray(new String[0]));
        }
        intent.putExtra("addAccountOptions", accountChooserOptions.zzj);
        intent.putExtra("selectedAccount", accountChooserOptions.zze);
        intent.putExtra("alwaysPromptForAccount", accountChooserOptions.zzh);
        intent.putExtra("descriptionTextOverride", accountChooserOptions.zzi);
        intent.putExtra("setGmsCoreAccount", accountChooserOptions.zzk);
        intent.putExtra("realClientPackage", accountChooserOptions.zzp);
        intent.putExtra("overrideTheme", accountChooserOptions.zzl);
        if (accountChooserOptions.zzn) {
            i = 2;
        }
        intent.putExtra("overrideCustomTheme", i);
        intent.putExtra("hostedDomainFilter", accountChooserOptions.zzm);
        Bundle bundle = new Bundle();
        if (accountChooserOptions.zzn && !TextUtils.isEmpty(accountChooserOptions.zzi)) {
            bundle.putString("title", accountChooserOptions.zzi);
        }
        if (accountChooserOptions.zzo != null) {
            bundle.putBoolean("should_show_consent", true);
            bundle.putString("privacy_policy_url", (String) null);
            bundle.putString("terms_of_service_url", (String) null);
        }
        if (!bundle.isEmpty()) {
            intent.putExtra("first_party_options_bundle", bundle);
        }
        return intent;
    }
}
