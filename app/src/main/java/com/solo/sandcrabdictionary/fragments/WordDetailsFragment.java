package com.solo.sandcrabdictionary.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.adapters.WordDetailsAdapter;
import com.solo.sandcrabdictionary.databinding.FragmentWordDetailsBinding;
import com.solo.sandcrabdictionary.models.entries.LexicalEntry;
import com.solo.sandcrabdictionary.models.entries.OxfordWord;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordDetailsFragment extends Fragment {

    private FragmentWordDetailsBinding binding;
    private View view;
    private static int visibilityStatus = View.GONE;
    private List<LexicalEntry> lexicalEntryList = new ArrayList<>();
    private WordDetailsAdapter wordDetailsAdapter;
    public WordDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_details, container, false);
        wordDetailsAdapter = new WordDetailsAdapter(getActivity(), lexicalEntryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.fragmentWordDetailsRecyclerView.setLayoutManager(linearLayoutManager);
        binding.fragmentWordDetailsRecyclerView.setAdapter(wordDetailsAdapter);
        view = binding.getRoot();
        return view;
    }

    public void setPageContent(OxfordWord oxfordWord) {

        List<LexicalEntry> lexicalEntryList = oxfordWord.getResults().get(0).getLexicalEntries();
        wordDetailsAdapter.setItems(lexicalEntryList);
    }

    public int getVisibility() {
        return visibilityStatus;
    }

    public void setVisibility(int visibility) {
        if (view != null) {
            visibilityStatus = visibility;
            view.setVisibility(visibility);
        }
    }

}
