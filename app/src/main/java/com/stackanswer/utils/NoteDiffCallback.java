package com.stackanswer.utils;

import androidx.recyclerview.widget.DiffUtil;

import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;

import java.util.List;

public class NoteDiffCallback extends DiffUtil.Callback {
    private final List<MovieFavorite> mOldNoteList;
    private final List<MovieFavorite> mNewNoteList;
    public NoteDiffCallback(List<MovieFavorite> oldNoteList, List<MovieFavorite> newNoteList) {
        this.mOldNoteList = oldNoteList;
        this.mNewNoteList = newNoteList;
    }
    @Override
    public int getOldListSize() {
        return mOldNoteList.size();
    }
    @Override
    public int getNewListSize() {
        return mNewNoteList.size();
    }
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldNoteList.get(oldItemPosition).getId() == mNewNoteList.get(newItemPosition).getId();
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final MovieFavorite oldEmployee = mOldNoteList.get(oldItemPosition);
        final MovieFavorite newEmployee = mNewNoteList.get(newItemPosition);
        return oldEmployee.getTitle().equals(newEmployee.getTitle()) && oldEmployee.getOverview().equals(newEmployee.getOverview());
    }
}