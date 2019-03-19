package com.solo.sandcrabdictionary.activities;

import android.app.ActivityOptions;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

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
    private SearchView searchView;
    private EditText searchViewEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initActionBar();
        initFragments();
        initNavigationDrawer();
    }

    private void initActionBar() {
        setSupportActionBar(binding.activityMainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_blue_light_24dp);
        binding.activityMainToolbar.setTitle(getResources().getString(R.string.app_name));
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


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent != null && Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchView.setQuery(query, true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.mnuSearch);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menuItem.getActionView();
        ImageView searchViewIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchViewEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchViewEditText.setSelectAllOnFocus(true);
        ViewGroup linearLayoutSearchView =(ViewGroup) searchViewIcon.getParent();
        linearLayoutSearchView.removeView(searchViewIcon);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (wordDetailsFragment.getVisibility() == View.GONE) {
                        recentWordsFragment.setVisibility(View.VISIBLE);
                        randomWordsFragment.setVisibility(View.GONE);
                    }
                }
                else {
                    if (wordDetailsFragment.getVisibility() == View.GONE) {
                        recentWordsFragment.setVisibility(View.VISIBLE);
                        randomWordsFragment.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                binding.activityMainLoading.show();
                final OxfordDictionaryInterface oxfordDictionaryInterface = OxfordDictionaryClient.getService();
                final Call<OxfordWord> callback = oxfordDictionaryInterface.lookUpWord(searchViewEditText.getText().toString());
                callback.enqueue(new Callback<OxfordWord>() {
                    @Override
                    public void onResponse(Call<OxfordWord> call, Response<OxfordWord> response) {
                        OxfordWord oxfordWord = response.body();
                        binding.activityMainLoading.hide();
                        recentWordsFragment.setVisibility(View.GONE);
                        randomWordsFragment.setVisibility(View.GONE);
                        wordDetailsFragment.setPageContent(oxfordWord);
                        wordDetailsFragment.setVisibility(View.VISIBLE);
                        searchViewEditText.setText("");
                        searchView.clearFocus();


                    }

                    @Override
                    public void onFailure(Call<OxfordWord> call, Throwable t) {
                        binding.activityMainLoading.hide();
                        callback.cancel();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recentWordsFragment.filter(newText);
                return false;
            }
        });
        handleIntent(getIntent());
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.activityMainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.activityMainDrawerLayout.closeDrawers();
        } else if (wordDetailsFragment.getVisibility() == View.VISIBLE) {
            searchViewEditText.setText("");
            wordDetailsFragment.setVisibility(View.GONE);
            recentWordsFragment.setVisibility(View.VISIBLE);
            randomWordsFragment.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                try {
                    binding.activityMainDrawerLayout.openDrawer(GravityCompat.START);
                } catch (Exception e){
                    e.printStackTrace();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
