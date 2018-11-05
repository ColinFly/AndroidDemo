package com.colin.demo.android_room_with_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colin.demo.R;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    private LayoutInflater mInflater;
    private List<Word> mWords;
    public class WordViewHolder extends RecyclerView.ViewHolder{
        private TextView wordItemView;
        public WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.room_view_recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            holder.wordItemView.setText("No Word");
        }
    }

    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        }
        return 0;
    }


    void setWord(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }
}
