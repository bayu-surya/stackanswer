package com.stackanswer.favorite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stackanswer.core.BuildConfig;
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavorite;
import com.stackanswer.core.utils.ImageUtils;
import com.stackanswer.favorite.R;

import java.util.List;

public class MovieFavoriteKtAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<MovieFavorite> filmList;
    private Callback callback;

    public MovieFavoriteKtAdapter(Context context, List<MovieFavorite> filmList) {
        this.context = context;
        this.filmList = filmList;
    }

    public interface Callback{
        void onItemClick(MovieFavorite film, int position);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        RecyclerView.ViewHolder viewHolder;

        view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        viewHolder = new ViewHolderOne(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final MovieFavorite film = filmList.get(position);
        ViewHolderOne viewHolderOne = (ViewHolderOne) holder;

        String url= BuildConfig.BASE_URL_IMAGE +film.getPosterPath();
        ImageUtils.fromUrlWithSize(context, url, viewHolderOne.ivGambar, 100, 140);
        viewHolderOne.tvJudul.setText(film.getTitle());
        viewHolderOne.tvDetail.setText(film.getOverview());

        viewHolderOne.v.setOnClickListener(view -> callback.onItemClick(film, position));
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        View v;
        ImageView ivGambar;
        TextView tvJudul;
        TextView tvDetail;

        ViewHolderOne(View v) {
            super(v);
            this.v = itemView;
            ivGambar = v.findViewById(R.id.iv_image);
            tvJudul = v.findViewById(R.id.tv_nama);
            tvDetail = v.findViewById(R.id.tv_detail);
        }
    }
}
