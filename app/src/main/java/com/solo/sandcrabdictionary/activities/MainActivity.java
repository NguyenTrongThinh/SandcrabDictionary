package com.solo.sandcrabdictionary.activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.databinding.ActivityMainBinding;
import com.solo.sandcrabdictionary.fragments.RandomWordsFragment;
import com.solo.sandcrabdictionary.fragments.RecentWordsFragment;
import com.solo.sandcrabdictionary.fragments.WordDetailsFragment;
import com.solo.sandcrabdictionary.models.OxfordWord;
import com.solo.sandcrabdictionary.servers.OxfordDictionaryClient;
import com.solo.sandcrabdictionary.servers.OxfordDictionaryInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private RecentWordsFragment recentWordsFragment;
    private RandomWordsFragment randomWordsFragment;
    private WordDetailsFragment wordDetailsFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initActionBar();
        initFragments();
        initNavigationDrawer();
    }

    private void initActionBar() {
        binding.activityMainsearchBar.setHint(getResources().getString(R.string.text_demo_word));
        binding.activityMainsearchBar.setSpeechMode(true);
        binding.activityMainsearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (enabled) {
                    if (wordDetailsFragment.getVisibility() == View.GONE) {
                        recentWordsFragment.setVisibility(View.VISIBLE);
                        randomWordsFragment.setVisibility(View.GONE);
                    } else {
                        wordDetailsFragment.setVisibility(View.GONE);
                        recentWordsFragment.setVisibility(View.VISIBLE);
                        randomWordsFragment.setVisibility(View.GONE);
                    }
                } else {
                    if (wordDetailsFragment.getVisibility() == View.GONE) {
                        recentWordsFragment.setVisibility(View.VISIBLE);
                        randomWordsFragment.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                binding.activityMainLoading.show();
                lookUpWord(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode){
                    case MaterialSearchBar.BUTTON_NAVIGATION:
                        binding.activityMainDrawerLayout.openDrawer(GravityCompat.START);
                        break;
                    case MaterialSearchBar.BUTTON_SPEECH:
                        break;
                    case MaterialSearchBar.BUTTON_BACK:
                        binding.activityMainsearchBar.disableSearch();
                        break;
                }
            }
        });

        binding.activityMainsearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recentWordsFragment.filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initFragments() {
        fragmentManager = getSupportFragmentManager();
        recentWordsFragment = (RecentWordsFragment) fragmentManager.findFragmentById(R.id.activityMainRecentWords);
        randomWordsFragment = (RandomWordsFragment) fragmentManager.findFragmentById(R.id.activityMainRandomWords);
        wordDetailsFragment = (WordDetailsFragment) fragmentManager.findFragmentById(R.id.activityMainWordDetails);
        wordDetailsFragment.setVisibility(View.GONE);
    }

    private void initNavigationDrawer() {
        binding.activityMainNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                binding.activityMainDrawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.mnuFavourite:
                        break;
                    case R.id.mnuWatchAds:
                        Intent intentwatchAds = new Intent(MainActivity.this, AdsActivity.class);
                        ActivityOptions optionsWatchAds =
                                ActivityOptions.makeCustomAnimation(getApplication(), R.anim.fade_in, R.anim.fade_out);
                        startActivity(intentwatchAds, optionsWatchAds.toBundle());
                        break;
                    case R.id.mnuSetting:
                        break;
                    case R.id.mnuAbout:
                        Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
                        ActivityOptions optionsAbout =
                                ActivityOptions.makeCustomAnimation(getApplication(), R.anim.fade_in, R.anim.fade_out);
                        startActivity(intentAbout, optionsAbout.toBundle());
                        break;
                }
                return true;
            }
        });
    }

    private void lookUpWord(String word) {
        final OxfordDictionaryInterface oxfordDictionaryInterface = OxfordDictionaryClient.getService();
        final Call<OxfordWord> callback = oxfordDictionaryInterface.lookUpWord(word);
        callback.enqueue(new Callback<OxfordWord>() {
            @Override
            public void onResponse(Call<OxfordWord> call, Response<OxfordWord> response) {
                OxfordWord oxfordWord = response.body();
                binding.activityMainLoading.hide();
                recentWordsFragment.setVisibility(View.GONE);
                randomWordsFragment.setVisibility(View.GONE);
                wordDetailsFragment.setPageContent(oxfordWord);
                wordDetailsFragment.setVisibility(View.VISIBLE);
                binding.activityMainsearchBar.disableSearch();
            }

            @Override
            public void onFailure(Call<OxfordWord> call, Throwable t) {
                binding.activityMainLoading.hide();
                callback.cancel();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (binding.activityMainsearchBar.isSearchEnabled()) {
            binding.activityMainsearchBar.disableSearch();
        } else if (binding.activityMainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.activityMainDrawerLayout.closeDrawers();
        } else if (wordDetailsFragment.getVisibility() == View.VISIBLE) {
            wordDetailsFragment.setVisibility(View.GONE);
            recentWordsFragment.setVisibility(View.VISIBLE);
            randomWordsFragment.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
