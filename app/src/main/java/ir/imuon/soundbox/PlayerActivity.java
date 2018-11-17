package ir.imuon.soundbox;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import ir.imuon.soundbox.adapters.HomeSongsItemAdaptor;
import ir.imuon.soundbox.models.DataItemHomeSongs;

public class PlayerActivity extends AppCompatActivity {

    private DataItemHomeSongs itemHomeSongs;

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler;

    boolean favoriteSong = false;

    private ImageButton playBtn;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        itemHomeSongs = Objects.requireNonNull(getIntent().getExtras()).
                getParcelable(HomeSongsItemAdaptor.ITEM_KEY);

        playBtn = findViewById(R.id.btn_play);

        play();
        seekBarActions();
        playCycle();
    }

    private void play() {
//      Display Data
        TextView songTitleTV = findViewById(R.id.tv_player_song_title);
        TextView albumTitleTV = findViewById(R.id.tv_player_album_title);
        TextView songLengthMin = findViewById(R.id.tv_player_time_length_min);
        TextView songLengthSec = findViewById(R.id.tv_player_time_length_sec);
        ImageView coverIV = findViewById(R.id.iv_player_song_cover);

        if (itemHomeSongs != null) {
            songTitleTV.setText(itemHomeSongs.getSongTitle());
            albumTitleTV.setText(itemHomeSongs.getAlbumTitle());
            songLengthMin.setText(itemHomeSongs.getSongLengthMin());
            songLengthSec.setText(itemHomeSongs.getSongLengthSec());

            String imageFile = itemHomeSongs.getCover();
            int resource = getResources().getIdentifier(imageFile,
                    "drawable", getPackageName());
            coverIV.setImageResource(resource);

//          Play Song
            String data = itemHomeSongs.getSongData();
            Uri uri = Uri.parse(data);
            mediaPlayer = MediaPlayer.create(this, uri);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
    }

    private void seekBarActions() {
//            TODO: Handle Song
        handler = new Handler();

        seekBar = findViewById(R.id.sb_music_progress);
        seekBar.setMax(mediaPlayer.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void playCycle() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        int maxLength = mediaPlayer.getDuration();

        if (mediaPlayer.isPlaying() && mediaPlayer.getCurrentPosition() != maxLength) {
            Runnable runnable = this::playCycle;
            handler.postDelayed(runnable, 1000);
            durationTV();
        } else {
            Runnable runnable = this::playCycle;
            handler.postDelayed(runnable, 1000);
            durationTV();

            if (!mediaPlayer.isLooping()) {
                mediaPlayer.reset();

                String data = itemHomeSongs.getSongData();
                Uri uri = Uri.parse(data);
                mediaPlayer = MediaPlayer.create(this, uri);

                Drawable drawable = getResources().getDrawable(R.drawable.ic_play_white);
                playBtn.setImageDrawable(drawable);
            }
        }
    }

    private void durationTV() {
        TextView currentSecTV = findViewById(R.id.tv_player_time_current_sec);
        TextView currentMinTV = findViewById(R.id.tv_player_time_current_min);

        int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
        String lengthMin = String.valueOf(mCurrentPosition / 60);

        int second = mCurrentPosition % 60;
        String lengthSec;
        if (second >= 10) {
            lengthSec = String.valueOf(second);
        } else {
            lengthSec = "0" + String.valueOf(second);
        }

        currentSecTV.setText(lengthSec);
        currentMinTV.setText(lengthMin);
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        super.onNavigateUpFromChild(this);
    }

    public void repeatOnClick(View view) {
        ImageButton repeatBtn = findViewById(R.id.btn_repeat);
        if (mediaPlayer.isLooping()) {
            mediaPlayer.setLooping(false);
            Drawable drawable = getResources().getDrawable(R.drawable.ic_repeat_off_white);
            repeatBtn.setImageDrawable(drawable);
        } else {
            mediaPlayer.setLooping(true);
            Drawable drawable = getResources().getDrawable(R.drawable.ic_repeat_one_white);
            repeatBtn.setImageDrawable(drawable);

        }
    }

    public void backwardOnClick(View view) {
        int mCurrentPosition = mediaPlayer.getCurrentPosition();
        int backwardTime = mCurrentPosition - 5000;
        mediaPlayer.seekTo(backwardTime);
    }

    public void forwardOnClick(View view) {
        int mCurrentPosition = mediaPlayer.getCurrentPosition();
        int forwardTime = mCurrentPosition + 5000;
        mediaPlayer.seekTo(forwardTime);
    }

    public void shuffleOnClick(View view) {
        Snackbar.make(view, R.string.playerActivity_premium_account_message,
                Snackbar.LENGTH_LONG).show();
    }

    public void playOnClick(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Drawable drawable = getResources().getDrawable(R.drawable.ic_play_white);
            playBtn.setImageDrawable(drawable);
        } else {
            mediaPlayer.start();
            Drawable drawable = getResources().getDrawable(R.drawable.ic_pause_white);
            playBtn.setImageDrawable(drawable);
        }
    }

    public void favoriteOnClick(View view) {
        ImageButton favoriteBtn = findViewById(R.id.btn_favorite);
        if (!favoriteSong) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_favorite_white);
            favoriteBtn.setImageDrawable(drawable);
            favoriteSong = true;
        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_favorite_border_white);
            favoriteBtn.setImageDrawable(drawable);
            favoriteSong = false;
        }
    }
}
