package ir.imuon.soundbox.dataProviders;

import java.util.ArrayList;
import java.util.List;

import ir.imuon.soundbox.R;
import ir.imuon.soundbox.models.DataItemHomeRecent;

public class HomeRecentDataProvider {

    public static List<DataItemHomeRecent> dataItemHomeRecent;

    static {
        dataItemHomeRecent = new ArrayList<>();

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_01",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_02",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_01",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_01",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_02",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_01",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_02",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_01",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_02",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));

        addItem(new DataItemHomeRecent(null, "sound_box_music_cover_01",
                R.string.homeFragment_recent_song_title, R.string.homeFragment_recent_song_album));


    }

    private static void addItem(DataItemHomeRecent itemHomeRecent) {
        dataItemHomeRecent.add(itemHomeRecent);
    }

}
