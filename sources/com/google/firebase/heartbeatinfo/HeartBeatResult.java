package com.google.firebase.heartbeatinfo;

import com.google.firebase.heartbeatinfo.HeartBeatInfo;

public abstract class HeartBeatResult {
    public abstract HeartBeatInfo.HeartBeat getHeartBeat();

    public abstract long getMillis();

    public abstract String getSdkName();

    public static HeartBeatResult create(String sdkName, long millis, HeartBeatInfo.HeartBeat heartBeat) {
        return new AutoValue_HeartBeatResult(sdkName, millis, heartBeat);
    }
}
