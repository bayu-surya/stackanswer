package com.stackanswer.tidakdigunakan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.stackanswer.BuildConfig;
import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;
import com.stackanswer.databinding.ItemMovieBinding;
import com.stackanswer.utils.ImageUtils;

public class MovieFavoriteAdapter extends PagedListAdapter<MovieFavorite, MovieFavoriteAdapter.ViewHolderOne> {

    private final Context context;
    private Callback callback;

    public MovieFavoriteAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<MovieFavorite> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieFavorite>() {
                @Override
                public boolean areItemsTheSame(MovieFavorite oldNote, MovieFavorite newNote) {
                    return oldNote.getTitle().equals(newNote.getTitle()) && oldNote.getOverview().equals(newNote.getOverview());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieFavorite oldItem, @NonNull MovieFavorite newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public interface Callback{
        void onItemClick(MovieFavorite film, int position);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolderOne onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderOne(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOne holder, final int position) {

        final MovieFavorite film = getItem(position);
        holder.bind(film, context);
        holder.binding.getRoot().setOnClickListener(view -> callback.onItemClick(film, position));
    }

    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        final ItemMovieBinding binding;

        ViewHolderOne(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieFavorite film, Context context) {
            String url= BuildConfig.BASE_URL_IMAGE+film.getPosterPath();
            ImageUtils.fromUrlWithSize(context, url, binding.ivImage, 100, 140);
            binding.tvNama.setText(film.getTitle());
            binding.tvDetail.setText(film.getOverview());
        }
    }
}
