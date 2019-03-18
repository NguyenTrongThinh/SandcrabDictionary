package com.solo.sandcrabdictionary.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {

    ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        setSupportActionBar(binding.activityAboutToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.activityAboutToolbar.setTitle(getResources().getString(R.string.app_name));
        binding.activityAboutToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
