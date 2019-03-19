package com.solo.sandcrabdictionary.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.databinding.ActivityAdsBinding;

public class AdsActivity extends AppCompatActivity {

    ActivityAdsBinding binding;
    private static final String TAG = "AdsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ads);

        MobileAds.initialize(this, getResources().getString(R.string.admod_app_id));
        initTopAds();

        initBottomAds();
    }

    private void initBottomAds() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("B6E832929390E817BFEC1D0D23316E21")
                .build();
        binding.adViewBottom.loadAd(adRequest);
        binding.adViewBottom.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.d(TAG, "onAdClosed: ");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.d(TAG, "onAdFailedToLoad: " + errorCode);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.d(TAG, "onAdLeftApplication: ");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.d(TAG, "onAdOpened: ");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(TAG, "onAdLoaded: ");
            }
        });
    }

    private void initTopAds() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("B6E832929390E817BFEC1D0D23316E21")
                .build();
        binding.adViewTop.loadAd(adRequest);
        binding.adViewTop.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.d(TAG, "onAdClosed: ");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.d(TAG, "onAdFailedToLoad: " + errorCode);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.d(TAG, "onAdLeftApplication: ");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.d(TAG, "onAdOpened: ");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(TAG, "onAdLoaded: ");
            }
        });
    }

    @Override
    protected void onPause() {
        if (binding.adViewTop != null) {
            binding.adViewTop.pause();
        }
        if (binding.adViewBottom != null) {
            binding.adViewBottom.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding.adViewTop != null) {
            binding.adViewTop.resume();
        }
        if (binding.adViewBottom != null) {
            binding.adViewBottom.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (binding.adViewTop != null) {
            binding.adViewTop.destroy();
        }
        if (binding.adViewBottom != null) {
            binding.adViewBottom.destroy();
        }
        super.onDestroy();
    }
}
