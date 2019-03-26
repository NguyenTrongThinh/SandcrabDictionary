package com.solo.sandcrabdictionary.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.solo.sandcrabdictionary.R;
import com.solo.sandcrabdictionary.databinding.SingleEntryLayoutBinding;
import com.solo.sandcrabdictionary.models.entries.LexicalEntry;

import java.util.List;

public class WordDetailsAdapter extends RecyclerView.Adapter<WordDetailsAdapter.ViewHolder> {
    private SingleEntryLayoutBinding binding;
    private Context context;
    private List<LexicalEntry> oxfordWordList;
    private View view;

    public WordDetailsAdapter(Context context, List<LexicalEntry> oxfordWordList) {
        this.context = context;
        this.oxfordWordList = oxfordWordList;
    }

    public void setItems(List<LexicalEntry> oxfordWordList) {
        this.oxfordWordList = oxfordWordList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_entry_layout, viewGroup, false);
        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LexicalEntry wordEntry = oxfordWordList.get(i);
        viewHolder.txtWord.setText(wordEntry.getText());
        viewHolder.txtType.setText(wordEntry.getLexicalCategory().toUpperCase());
        
    }

    @Override
    public int getItemCount() {
        return oxfordWordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtWord, txtIPA, txtRegion, txtType, txtDetails;
        ImageButton imgSound, imgLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDetails = binding.singleEntryLayoutWordDetails;
            txtIPA = binding.singleEntryLayoutIPA;
            txtRegion = binding.singleEntryLayoutRegion;
            txtType = binding.singleEntryLayoutType;
            txtWord = binding.singleEntryLayoutWord;
            imgLike = binding.singleEntryLayoutLike;
            imgSound = binding.singleEntryLayoutSound;
        }
    }
}

