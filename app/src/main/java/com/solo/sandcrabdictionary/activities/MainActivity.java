package com.solo.sandcrabdictionary.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.databinding.ActivityMainBinding;
import com.solo.sandcrabdictionary.fragments.RecentWordsFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private RecentWordsFragment recentWordsFragment;
    private FragmentManager fragmentManager;
    private SearchView searchView;
    private BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.fragmentSearchToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_blue_light_24dp);
        binding.fragmentSearchToolbar.setTitle(getResources().getString(R.string.app_name));

        initBottomSheet();


        fragmentManager = getSupportFragmentManager();
        recentWordsFragment = (RecentWordsFragment) fragmentManager.findFragmentById(R.id.activityMainRecentWords);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                binding.bottomSheet.bottomSheetWord.setText("State Changed");
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                binding.bottomSheet.bottomSheetWord.setText("State Slide");
            }
        });
        binding.activityMainNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                binding.activityMainDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.mnuFavourite:
                        break;
                    case R.id.mnuWatchAds:
                        break;
                    case R.id.mnuSetting:
                        break;
                    case R.id.mnuAbout:
                        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

    }

    private void initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.bottomSheetRelativeLayout);
//        Display display = getWindowManager().getDefaultDisplay();
//        DisplayMetrics outMetrics = new DisplayMetrics ();
//        display.getMetrics(outMetrics);
//
//        ViewGroup.LayoutParams layoutParams = binding.bottomSheet.bottomSheetRelativeLayout.getLayoutParams();
//        layoutParams.height = outMetrics.heightPixels - android.R.attr.actionBarSize;
//        binding.bottomSheet.bottomSheetRelativeLayout.setLayoutParams(layoutParams);
//        binding.bottomSheet.bottomSheetRelativeLayout.requestLayout();
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
        ImageView searchViewIcon = (ImageView)searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);

        ViewGroup linearLayoutSearchView =(ViewGroup) searchViewIcon.getParent();
        linearLayoutSearchView.removeView(searchViewIcon);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
//        searchView.setBackground(getDrawable(R.drawable.random_words_shape));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: " + query);
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
