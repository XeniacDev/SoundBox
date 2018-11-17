package ir.imuon.soundbox.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ir.imuon.soundbox.PlayerActivity;
import ir.imuon.soundbox.R;
import ir.imuon.soundbox.models.DataItemHomeRecent;

public class HomeRecentItemAdaptor extends RecyclerView.Adapter<HomeRecentItemAdaptor.ViewHolder> {

    private List<DataItemHomeRecent> mItems;
    private Context mContext;

    public HomeRecentItemAdaptor(Context context, List<DataItemHomeRecent> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public HomeRecentItemAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item_home_recent, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecentItemAdaptor.ViewHolder holder, int position) {
        final DataItemHomeRecent itemHomeRecent = mItems.get(position);

        holder.songTitleTV.setText(itemHomeRecent.getSongTitle());
        holder.bandTitleTV.setText(itemHomeRecent.getBandTitle());

        String imageFile = itemHomeRecent.getCover();
//            InputStream inputStream = mContext.getAssets().open(imageFile);
//            Drawable drawable = Drawable.createFromStream(inputStream, null);
        int resource = mContext.getResources().getIdentifier(imageFile,
                "drawable", mContext.getPackageName());
        holder.imageView.setImageResource(resource);


        holder.relativeLayout.setOnClickListener(view -> {
            Snackbar.make(view, "Not available on alpha version!!", Snackbar.LENGTH_LONG).show();
//            Intent intent = new Intent(mContext, PlayerActivity.class);
//            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView songTitleTV, bandTitleTV;
        ImageView imageView;
        RelativeLayout relativeLayout;
        View mView;

        ViewHolder(View itemView) {
            super(itemView);

            songTitleTV = itemView.findViewById(R.id.tv_list_recent_song);
            bandTitleTV = itemView.findViewById(R.id.tv_list_recent_band);
            imageView = itemView.findViewById(R.id.iv_list_recent);
            relativeLayout = itemView.findViewById(R.id.rl_list_home_recent);

            mView = itemView;
        }
    }
}
