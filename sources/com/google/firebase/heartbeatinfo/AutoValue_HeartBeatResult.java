package com.google.firebase.heartbeatinfo;

import com.google.firebase.heartbeatinfo.HeartBeatInfo;

final class AutoValue_HeartBeatResult extends HeartBeatResult {
    private final HeartBeatInfo.HeartBeat heartBeat;
    private final long millis;
    private final String sdkName;

    AutoValue_HeartBeatResult(String sdkName2, long millis2, HeartBeatInfo.HeartBeat heartBeat2) {
        if (sdkName2 != null) {
            this.sdkName = sdkName2;
            this.millis = millis2;
            if (heartBeat2 != null) {
                this.heartBeat = heartBeat2;
                return;
            }
            throw new NullPointerException("Null heartBeat");
        }
        throw new NullPointerException("Null sdkName");
    }

    public String getSdkName() {
        return this.sdkName;
    }

    public long getMillis() {
        return this.millis;
    }

    public HeartBeatInfo.HeartBeat getHeartBeat() {
        return this.heartBeat;
    }

    public String toString() {
        return "HeartBeatResult{sdkName=" + this.sdkName + ", millis=" + this.millis + ", heartBeat=" + this.heartBeat + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HeartBeatResult)) {
            return false;
        }
        HeartBeatResult that = (HeartBeatResult) o;
        if (!this.sdkName.equals(that.getSdkName()) || this.millis != that.getMillis() || !this.heartBeat.equals(that.getHeartBeat())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.millis;
        return (((((1 * 1000003) ^ this.sdkName.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.heartBeat.hashCode();
    }
}
