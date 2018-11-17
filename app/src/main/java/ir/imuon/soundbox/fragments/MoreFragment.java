package ir.imuon.soundbox.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ir.imuon.soundbox.R;
import ir.imuon.soundbox.adapters.MoreItemAdapter;
import ir.imuon.soundbox.dataProviders.MoreAccountDataProvider;
import ir.imuon.soundbox.dataProviders.MoreSettingsDataProvider;
import ir.imuon.soundbox.dataProviders.MoreSupportDataProvider;
import ir.imuon.soundbox.models.DataItemMore;

public class MoreFragment extends Fragment {

    private View view;
    private Context context;

    private List<DataItemMore> dataItemMoreAccountList = MoreAccountDataProvider.dataItemMoreList;
    private List<DataItemMore> dataItemMoreSettingsList = MoreSettingsDataProvider.dataItemMoreList;
    private List<DataItemMore> dataItemMoreSupportList = MoreSupportDataProvider.dataItemMoreList;

    private TextView moreAboutUsTV;
    private RelativeLayout moreCheckForUpdateRL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more, container, false);
        context = view.getContext();

        moreAboutUsTV = view.findViewById(R.id.tv_more_about_us_text);
        moreCheckForUpdateRL = view.findViewById(R.id.ll_check_for_update);

        setMoreAdapters();
        moreAboutUsTVOnClick();
        moreCheckForUpdateClick();

        return view;
    }

    private void setMoreAdapters() {
        MoreItemAdapter accountAdapter = new MoreItemAdapter(context, dataItemMoreAccountList);
        MoreItemAdapter settingsAdapter = new MoreItemAdapter(context, dataItemMoreSettingsList);
        MoreItemAdapter supportAdapter = new MoreItemAdapter(context, dataItemMoreSupportList);

        RecyclerView accountRV = view.findViewById(R.id.rv_more_account);
        RecyclerView settingsRV = view.findViewById(R.id.rv_more_settings);
        RecyclerView supportRV = view.findViewById(R.id.rv_more_support);

        accountRV.setAdapter(accountAdapter);
        settingsRV.setAdapter(settingsAdapter);
        supportRV.setAdapter(supportAdapter);
    }

    private void moreAboutUsTVOnClick() {
        moreAboutUsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String websiteURL = "http://imuon.ir/";
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteURL));
                if (websiteIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(websiteIntent);
                }
            }
        });
    }

    private void moreCheckForUpdateClick() {
        moreCheckForUpdateRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storeURL = "https://play.google.com/store/apps?hl=en" +
                        context.getPackageName();
                Intent rate = new Intent(Intent.ACTION_VIEW, Uri.parse(storeURL));
                if (rate.resolveActivity(context.getPackageManager()) != null) {
                    startActivity(rate);
                }
            }
        });
    }

}
