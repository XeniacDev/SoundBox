package ir.imuon.soundbox.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import ir.imuon.soundbox.R;
import ir.imuon.soundbox.adapters.HomeRecentItemAdaptor;
import ir.imuon.soundbox.adapters.HomeSongsItemAdaptor;
import ir.imuon.soundbox.dataProviders.HomeRecentDataProvider;
import ir.imuon.soundbox.dataProviders.HomeSongsDataProvider;
import ir.imuon.soundbox.models.DataItemHomeRecent;
import ir.imuon.soundbox.models.DataItemHomeSongs;

public class HomeFragment extends Fragment {

    private View view;
    private Context context;

    private List<DataItemHomeRecent> dataItemHomeRecentList = HomeRecentDataProvider.dataItemHomeRecent;
    private List<DataItemHomeSongs> dataItemHomeSongsList = HomeSongsDataProvider.dataItemHomeSongs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();

        dataItemHomeSongsList.clear();

//        TODO: Reference methods
        setHomeRecentAdaptor();
        setHomeSongsAdaptor();
        mediaPlayerMethod();
        getMusics();
        shuffleOnClick();

        return view;
    }


    private void setHomeRecentAdaptor() {

        HomeRecentItemAdaptor recentItemAdaptor = new HomeRecentItemAdaptor(context, dataItemHomeRecentList);

        RecyclerView recentRV = view.findViewById(R.id.rv_home_recent);
        recentRV.setAdapter(recentItemAdaptor);

    }

    private void setHomeSongsAdaptor() {

        HomeSongsItemAdaptor songsItemAdaptor = new HomeSongsItemAdaptor(context, dataItemHomeSongsList);

        RecyclerView songsRV = view.findViewById(R.id.rv_home_songs);
        songsRV.setAdapter(songsItemAdaptor);

    }

    private void mediaPlayerMethod() {

        Uri myUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(context, myUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getMusics() {
        ContentResolver cr = Objects.requireNonNull(getActivity()).getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count;

        if (cur != null) {
            count = cur.getCount();

            if (count > 0) {
                while (cur.moveToNext()) {
                    String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
//                    String cover = cur.getString(cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
                    String title = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String artist = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String album = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String duration = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    // Just for test to know code is working
//                    Toast.makeText(context, data, Toast.LENGTH_SHORT).show();

//                    TODO: Convert time
                    int durationInt = Integer.parseInt(duration) / 1000;
                    String lengthMin = String.valueOf(durationInt / 60);
                    int second = durationInt % 60;
                    String lengthSec;
                    if (second >= 10) {
                        lengthSec = String.valueOf(second);
                    } else {
                        lengthSec = "0" + String.valueOf(second);
                    }

                    // Save to your list here | HardCore:"cover_test.jpg"
                    dataItemHomeSongsList.add(new DataItemHomeSongs(null,
                            "sound_box_music_cover_01", title, artist, album,
                            lengthMin, lengthSec, duration, data));
                    setHomeSongsAdaptor();
                }

            }
        }

        Objects.requireNonNull(cur).close();
    }

    private void shuffleOnClick() {
        RelativeLayout homeWlcShuffleRL = view.findViewById(R.id.rl_home_wlc_shuffle);
        homeWlcShuffleRL.setOnClickListener(view ->
                Toast.makeText(context, R.string.playerActivity_premium_account_message, Toast.LENGTH_SHORT).show());
    }

}
