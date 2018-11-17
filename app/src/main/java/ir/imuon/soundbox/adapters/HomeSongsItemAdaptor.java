package ir.imuon.soundbox.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ir.imuon.soundbox.PlayerActivity;
import ir.imuon.soundbox.R;
import ir.imuon.soundbox.models.DataItemHomeSongs;


public class HomeSongsItemAdaptor extends RecyclerView.Adapter<HomeSongsItemAdaptor.ViewHolder> {

    public static final String HOME_SONGS_ITEM_ID_KEY = "HomeSongsItemIdKey";
    public static final String ITEM_KEY = "item_key";
    private List<DataItemHomeSongs> mItems;
    private ArrayList<DataItemHomeSongs> arrayList;
    private Context mContext;

    public HomeSongsItemAdaptor(Context context, List<DataItemHomeSongs> items) {
        this.mContext = context;
        this.mItems = items;
        this.arrayList = new ArrayList<DataItemHomeSongs>();
        this.arrayList.addAll(items);
    }


    @NonNull
    @Override
    public HomeSongsItemAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item_home_songs, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSongsItemAdaptor.ViewHolder holder, int position) {
        final DataItemHomeSongs itemHomeSongs = mItems.get(position);

        holder.songTitleTV.setText(itemHomeSongs.getSongTitle());
        holder.bandTitleTV.setText(itemHomeSongs.getBandTitle());
        holder.songLengthMinTV.setText(itemHomeSongs.getSongLengthMin());
        holder.songLengthSecTV.setText(itemHomeSongs.getSongLengthSec());

        String imageFile = itemHomeSongs.getCover();
        int resource = mContext.getResources().getIdentifier(imageFile,
                "drawable", mContext.getPackageName());
        holder.imageView.setImageResource(resource);

        holder.relativeLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, PlayerActivity.class);
            intent.putExtra(ITEM_KEY, itemHomeSongs);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView songTitleTV, bandTitleTV, songLengthMinTV, songLengthSecTV;
        ImageView imageView;
        RelativeLayout relativeLayout;
        View mView;

        ViewHolder(View itemView) {
            super(itemView);

            songTitleTV = itemView.findViewById(R.id.tv_list_home_song_title);
            bandTitleTV = itemView.findViewById(R.id.tv_list_home_song_band);
            songLengthMinTV = itemView.findViewById(R.id.tv_list_home_song_length_min);
            songLengthSecTV = itemView.findViewById(R.id.tv_list_home_song_length_sec);
            imageView = itemView.findViewById(R.id.iv_list_home_songs_cover);
            relativeLayout = itemView.findViewById(R.id.rl_list_home_songs);

            mView = itemView;
        }

    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mItems.clear();

        if (charText.length() == 0) {
            mItems.addAll(arrayList);
        } else {
            for (DataItemHomeSongs itemHomeSongs : arrayList) {
                if (itemHomeSongs.
                        getSongTitle().toLowerCase(Locale.getDefault()).contains(charText) ||
                        itemHomeSongs.getAlbumTitle().
                                toLowerCase(Locale.getDefault()).contains(charText) ||
                        itemHomeSongs.getBandTitle().
                                toLowerCase(Locale.getDefault()).contains(charText)) {
                    mItems.add(itemHomeSongs);
                }
            }
        }
        notifyDataSetChanged();
    }

}


