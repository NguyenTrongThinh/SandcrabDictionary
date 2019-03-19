package com.solo.sandcrabdictionary.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.databinding.FragmentWordDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordDetailsFragment extends Fragment {

    private FragmentWordDetailsBinding binding;
    private View view;

    public WordDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_details, container, false);

        view = binding.getRoot();
        return view;
    }

    public void setVisibility(int visibility) {
        if (view != null)
            view.setVisibility(visibility);
    }

}
