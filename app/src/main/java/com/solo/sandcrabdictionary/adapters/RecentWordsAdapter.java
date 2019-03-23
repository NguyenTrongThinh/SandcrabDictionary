package com.solo.sandcrabdictionary.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.databinding.SingleWordLineBinding;
import com.solo.sandcrabdictionary.models.entries.ExampleModelWord;

import java.util.ArrayList;
import java.util.List;

public class RecentWordsAdapter extends RecyclerView.Adapter<RecentWordsAdapter.ViewHolder> implements Filterable {
    private SingleWordLineBinding binding;
    private Context context;
    private View view;
    private List<ExampleModelWord> exampleModelWordList;
    private List<ExampleModelWord> exampleModelWordListFull;
    //Need to be modified this function
    public RecentWordsAdapter(Context context, List<ExampleModelWord> exampleModelWordList) {
        this.context = context;
        this.exampleModelWordList = exampleModelWordList;
        this.exampleModelWordListFull = new ArrayList<>(exampleModelWordList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_word_line, viewGroup, false);
        view = binding.getRoot();
        return new ViewHolder(view);
    }

    //Need to be modified this function
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ExampleModelWord exampleModelWord = exampleModelWordList.get(i);
        viewHolder.txtWord.setText(exampleModelWord.getWord());
        viewHolder.txtMeaning.setText(exampleModelWord.getMeaning());
        viewHolder.txtIPA.setText(exampleModelWord.getIpa());
    }

    @Override
    public int getItemCount() {
        return exampleModelWordList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleModelWord> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() <= 0) {
                filteredList.addAll(exampleModelWordListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ExampleModelWord exampleModelWord : exampleModelWordListFull) {
                    if (exampleModelWord.getWord().toLowerCase().contains(filterPattern)) {
                        filteredList.add(exampleModelWord);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleModelWordList.clear();
            exampleModelWordList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imgSpeak;
        TextView txtWord, txtMeaning, txtIPA;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMeaning = binding.singleWordLineMeaning;
            txtWord = binding.singleWordLineWord;
            txtIPA = binding.singleWordLineIPA;
            imgSpeak = binding.singleWordLineSound;
        }
    }
}