package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.MemoriesItemCardBinding;

import java.util.List;

public class MemoriesItemAdapter extends RecyclerView.Adapter<MemoriesItemAdapter.MemoriesViewHolder> {
    private final List<String> memoryTitle;
    private final List<String> memoryDate;
    private final List<Integer> memoryImages;
    Context context;
    public MemoriesItemAdapter(List<String> memoryTitle, List<String> memoryDate, List<Integer> memoryImages, Context context) {
        this.memoryTitle = memoryTitle;
        this.memoryDate = memoryDate;
        this.memoryImages = memoryImages;
        this.context = context;
    }

    @NonNull
    @Override
    public MemoriesItemAdapter.MemoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MemoriesItemCardBinding memoriesItemCardBinding = MemoriesItemCardBinding.inflate(inflater, parent, false);
        return new MemoriesViewHolder(memoriesItemCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoriesItemAdapter.MemoriesViewHolder holder, int position) {
    holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return memoryImages.size();
    }

    public class MemoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MemoriesItemCardBinding binding;
        public MemoriesViewHolder(MemoriesItemCardBinding memoriesItemCardBinding) {
            super(memoriesItemCardBinding.getRoot());
            this.binding = memoriesItemCardBinding;
            binding.getRoot().setOnClickListener(this);
        }

        public void bind(int position) {
            String title = memoryTitle.get(position);
            String date = memoryDate.get(position);
            Integer image = memoryImages.get(position);
            binding.memoriesTitleTextView.setText(title);
            binding.memoriesDateTextView.setText(date);
            binding.memoriesImageImageView.setImageResource(image);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
