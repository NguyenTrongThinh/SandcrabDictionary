package com.solo.sandcrabdictionary.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.adapters.RecentWordsAdapter;
import com.solo.sandcrabdictionary.databinding.FragmentRecentWordsBinding;
import com.solo.sandcrabdictionary.models.ExampleModelWord;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentWordsFragment extends Fragment {


    private FragmentRecentWordsBinding binding;
    private View view = null;
    private RecentWordsAdapter recentWordsAdapter;
    private static final String TAG = "RecentWordsFragment";
    public RecentWordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recent_words, container, false);

        //Stupid code goes here
        List<ExampleModelWord> exampleModelWordsList = new ArrayList<>();
        ExampleModelWord exampleModelWord = new ExampleModelWord("First", "First Item", "/wɜːk/ ");
        exampleModelWordsList.add(exampleModelWord);
        ExampleModelWord exampleModelWord1 = new ExampleModelWord("Second", "Second Item", "/wɜːk/ ");
        exampleModelWordsList.add(exampleModelWord1);
        ExampleModelWord exampleModelWord2 = new ExampleModelWord("Third", "Third Item", "/wɜːk/");
        exampleModelWordsList.add(exampleModelWord2);
        //Stupid code end here
        recentWordsAdapter = new RecentWordsAdapter(getActivity(), exampleModelWordsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.fragmentRecentWordsRecyclerViewList.setLayoutManager(linearLayoutManager);
        binding.fragmentRecentWordsRecyclerViewList.setAdapter(recentWordsAdapter);

        view = binding.getRoot();
        return view;
    }

    public void setVisibility(int visibility) {
        if (view != null)
            view.setVisibility(visibility);
    }

    public void filter(CharSequence newText) {
        recentWordsAdapter.getFilter().filter(newText);
    }

}
