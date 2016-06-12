package com.example.ivanildo.fastpass02;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.simplify.android.sdk.Simplify;

public class SimplifyApplication extends Application {

    String mAndroidPayPublicKey;

    @Override
    public void onCreate() {
        super.onCreate();

        // init Simplify SDK with public api key stored in metadata
                Simplify.init("sbpb_OTVkZWY0ZWUtZDk4Yy00NWZhLTkwNTctZTBjYjM0NzRmYTZk");

    }

    String getAndroidPayPublicKey() {
        return mAndroidPayPublicKey;
    }
}
