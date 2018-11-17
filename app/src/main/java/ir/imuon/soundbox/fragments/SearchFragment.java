package ir.imuon.soundbox.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.List;

import ir.imuon.soundbox.R;
import ir.imuon.soundbox.adapters.HomeSongsItemAdaptor;
import ir.imuon.soundbox.dataProviders.HomeSongsDataProvider;
import ir.imuon.soundbox.models.DataItemHomeSongs;

public class SearchFragment extends Fragment {

    private View view;
    private Context context;
    private List<DataItemHomeSongs> dataItemHomeSongsList = HomeSongsDataProvider.dataItemHomeSongs;
    private HomeSongsItemAdaptor homeSongsItemAdaptor;
    private RecyclerView searchRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        context = view.getContext();

        searchViewMethod();

        return view;
    }

    private void searchViewMethod() {

        SearchView searchView = view.findViewById(R.id.search_view);
        homeSongsItemAdaptor = new HomeSongsItemAdaptor(context, dataItemHomeSongsList);
        searchRecyclerView = view.findViewById(R.id.rv_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    homeSongsItemAdaptor.filter("");
                    searchRecyclerView.scrollToPosition(0);
                    searchRecyclerView.setAdapter(null);
                } else {
                    homeSongsItemAdaptor.filter(s);
                    searchRecyclerView.setAdapter(homeSongsItemAdaptor);
                }

                return true;
            }
        });
    }

}
