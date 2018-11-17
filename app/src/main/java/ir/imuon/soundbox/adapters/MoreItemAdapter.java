package ir.imuon.soundbox.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ir.imuon.soundbox.LoginActivity;
import ir.imuon.soundbox.MainActivity;
import ir.imuon.soundbox.R;
import ir.imuon.soundbox.models.DataItemMore;

public class MoreItemAdapter extends RecyclerView.Adapter<MoreItemAdapter.ViewHolder> {

    private List<DataItemMore> mItems;
    private Context mContext;
    private Activity mActivity;

    public MoreItemAdapter(Context context, List<DataItemMore> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public MoreItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item_more, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreItemAdapter.ViewHolder holder, int position) {
        final DataItemMore itemMore = mItems.get(position);

        holder.textView.setText(itemMore.getTitle());


        // TODO: Define red color resource
        if (itemMore.getId().equals("account_premium")) {
            holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorGreen));
        }
        if (itemMore.getId().equals("account_logout")) {
            holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorRed));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (itemMore.getId()) {

//                    TODO: Account Options
                    case "account_premium":
                        Snackbar premiumSnackbar = Snackbar.make(view,
                                R.string.moreFragment_account_premium_account_message,
                                Snackbar.LENGTH_SHORT);
                        premiumSnackbar.show();
                        break;

                    case "account_edit":
                        Snackbar editSnackbar = Snackbar.make(view,
                                R.string.moreFragment_account_edit_account_message,
                                Snackbar.LENGTH_SHORT);
                        editSnackbar.show();
                        break;

                    case "account_logout":
                        SharedPreferences.Editor editor = mContext.getSharedPreferences(
                                LoginActivity.LOG_IN_CHECK, Context.MODE_PRIVATE).edit();
                        editor.putBoolean(LoginActivity.LOG_IN_KEY, false);
                        editor.apply();
                        mContext.startActivity(new Intent(mContext, LoginActivity.class));
                        mActivity.finish();
                        break;

//                        TODO: Setting Options

//                        TODO: Support Options
                    case "support_feedback":
                        String EMAIL = "yousef.r1999@gmail.com";
                        String[] emailAddress = {EMAIL};
                        Intent feedback = new Intent(Intent.ACTION_SENDTO);
                        feedback.setData(Uri.parse("mailto:"));
                        feedback.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                        feedback.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.app_name));
                        feedback.putExtra(Intent.EXTRA_TEXT,
                                MainActivity.DEVICE_INFORMATION + "\nApp Version: " +
                                        MainActivity.APP_VERSION +
                                        "\n--------------------------------------\nFeedback:");
                        mContext.startActivity(Intent.createChooser(feedback,
                                mContext.getString(R.string.moreFragment_support_feedback_via)));
                        break;

//                        TODO: Invite Options
                    case "support_invite_friends":
                        Intent invite = new Intent(Intent.ACTION_SEND);
                        invite.setType("text/plain");
                        invite.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.app_name));
                        String inviteString = mContext.getString(R.string.moreFragment_support_invite_friends);
                        inviteString += "https://play.google.com/store/apps/details?id=" +
                                mContext.getPackageName();
                        invite.putExtra(Intent.EXTRA_TEXT, inviteString);
                        mContext.startActivity(Intent.createChooser(invite,
                                mContext.getString(R.string.moreFragment_support_invite_friends_via)));
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        View mView;

        ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_list_more);

            mView = itemView;
        }
    }
}
