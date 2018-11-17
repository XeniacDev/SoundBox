package ir.imuon.soundbox.models;

import java.util.UUID;

public class DataItemHomeRecent {

    private String id;
    private String cover;
    private int songTitle;
    private int bandTitle;

    public DataItemHomeRecent() {
    }

    public DataItemHomeRecent(String id, String cover, int songTitle, int albumTitle) {

        if (id == null) {
            id = UUID.randomUUID().toString();
        }

        this.id = id;
        this.cover = cover;
        this.songTitle = songTitle;
        this.bandTitle = albumTitle;
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

    public int getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(int songTitle) {
        this.songTitle = songTitle;
    }

    public int getBandTitle() {
        return bandTitle;
    }

    public void setBandTitle(int bandTitle) {
        this.bandTitle = bandTitle;
    }

    @Override
    public String toString() {
        return "DataItemHomeRecent{" +
                "id='" + id + '\'' +
                ", cover='" + cover + '\'' +
                ", songTitle=" + songTitle +
                ", bandTitle=" + bandTitle +
                '}';
    }
}
