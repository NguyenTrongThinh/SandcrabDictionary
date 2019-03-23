package com.solo.sandcrabdictionary.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.databinding.SingleRandomWordBinding;
import com.solo.sandcrabdictionary.models.entries.ExampleModelWord;

import java.util.List;

public class RandomWordsAdapter extends RecyclerView.Adapter<RandomWordsAdapter.ViewHolder> {
    private SingleRandomWordBinding binding;
    private Context context;
    private View view;
    List<ExampleModelWord> exampleModelWordArrayList;

    public RandomWordsAdapter(Context context, List<ExampleModelWord> exampleModelWordArrayList) {
        this.context = context;
        this.exampleModelWordArrayList = exampleModelWordArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_random_word, viewGroup, false);
        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ExampleModelWord exampleModelWord = exampleModelWordArrayList.get(i);
        viewHolder.txtWord.setText(exampleModelWord.getWord());
        viewHolder.txtMeaning.setText(exampleModelWord.getMeaning());
        viewHolder.txtIPA.setText(exampleModelWord.getIpa());
    }

    @Override
    public int getItemCount() {
        return exampleModelWordArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtWord, txtMeaning, txtIPA;
        private ImageView imgDemo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIPA = binding.singleRandomWordIPA;
            txtMeaning = binding.singleRandomWordMeaning;
            txtWord = binding.singleRandomWordTitle;
            imgDemo = binding.singleRandomWordImage;
        }
    }
}
