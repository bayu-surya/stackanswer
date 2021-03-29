package com.stackanswer.core.source.local.room.moviefavorite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class MovieFavorite implements Parcelable {

    @PrimaryKey()
    private int id;
    private String overview;
    private String originalLanguage;
    private String originalTitle;
    private boolean video;
    private String title;
    private String genreIds;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private double popularity;
    private double voteAverage;
    private boolean adult;
    private int voteCount;

    public MovieFavorite() {
    }

    protected MovieFavorite(Parcel in) {
        overview = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        video = in.readByte() != 0;
        title = in.readString();
        genreIds = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        releaseDate = in.readString();
        popularity = in.readDouble();
        voteAverage = in.readDouble();
        id = in.readInt();
        adult = in.readByte() != 0;
        voteCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(overview);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeString(title);
        dest.writeString(genreIds);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeString(releaseDate);
        dest.writeDouble(popularity);
        dest.writeDouble(voteAverage);
        dest.writeInt(id);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeInt(voteCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieFavorite> CREATOR = new Creator<MovieFavorite>() {
        @Override
        public MovieFavorite createFromParcel(Parcel in) {
            return new MovieFavorite(in);
        }

        @Override
        public MovieFavorite[] newArray(int size) {
            return new MovieFavorite[size];
        }
    };

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(String genreIds) {
        this.genreIds = genreIds;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
