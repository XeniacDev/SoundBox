package ir.imuon.soundbox.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class DataItemHomeSongs implements Parcelable {

    private String id;
    private String cover;
    private String songTitle;
    private String bandTitle;
    private String albumTitle;
    private String songLengthMin;
    private String songLengthSec;
    private String songLength;
    private String songData;

    public DataItemHomeSongs() {
    }

    public DataItemHomeSongs(String id, String cover, String songTitle,
                             String bandTitle, String albumTitle, String songLengthMin,
                             String songLengthSec, String songLength, String songData) {

        if (id == null) {
            id = UUID.randomUUID().toString();
        }

        this.id = id;
        this.cover = cover;
        this.songTitle = songTitle;
        this.bandTitle = bandTitle;
        this.albumTitle = albumTitle;
        this.songLengthMin = songLengthMin;
        this.songLengthSec = songLengthSec;
        this.songLength = songLength;
        this.songData = songData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getBandTitle() {
        return bandTitle;
    }

    public void setBandTitle(String bandTitle) {
        this.bandTitle = bandTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getSongLengthMin() {
        return songLengthMin;
    }

    public void setSongLengthMin(String songLengthMin) {
        this.songLengthMin = songLengthMin;
    }

    public String getSongLengthSec() {
        return songLengthSec;
    }

    public void setSongLengthSec(String songLengthSec) {
        this.songLengthSec = songLengthSec;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }

    public String getSongData() {
        return songData;
    }

    public void setSongData(String songData) {
        this.songData = songData;
    }

    @Override
    public String toString() {
        return "DataItemHomeSongs{" +
                "id='" + id + '\'' +
                ", cover='" + cover + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", bandTitle='" + bandTitle + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", songLengthMin='" + songLengthMin + '\'' +
                ", songLengthSec='" + songLengthSec + '\'' +
                ", songLength='" + songLength + '\'' +
                ", songData='" + songData + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.cover);
        dest.writeString(this.songTitle);
        dest.writeString(this.bandTitle);
        dest.writeString(this.albumTitle);
        dest.writeString(this.songLengthMin);
        dest.writeString(this.songLengthSec);
        dest.writeString(this.songLength);
        dest.writeString(this.songData);
    }

    protected DataItemHomeSongs(Parcel in) {
        this.id = in.readString();
        this.cover = in.readString();
        this.songTitle = in.readString();
        this.bandTitle = in.readString();
        this.albumTitle = in.readString();
        this.songLengthMin = in.readString();
        this.songLengthSec = in.readString();
        this.songLength = in.readString();
        this.songData = in.readString();
    }

    public static final Creator<DataItemHomeSongs> CREATOR = new Creator<DataItemHomeSongs>() {
        @Override
        public DataItemHomeSongs createFromParcel(Parcel source) {
            return new DataItemHomeSongs(source);
        }

        @Override
        public DataItemHomeSongs[] newArray(int size) {
            return new DataItemHomeSongs[size];
        }
    };
}
