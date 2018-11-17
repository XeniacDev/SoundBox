package ir.imuon.soundbox.dataProviders;

import java.util.ArrayList;
import java.util.List;

import ir.imuon.soundbox.R;
import ir.imuon.soundbox.models.DataItemMore;

public class MoreSettingsDataProvider {


    public static List<DataItemMore> dataItemMoreList;

    static {
        dataItemMoreList = new ArrayList<>();

        addItem(new DataItemMore("settings_language", R.string.moreFragment_settings_language));
        addItem(new DataItemMore("settings_light_mode", R.string.moreFragment_settings_light_mode));
    }

    private static void addItem(DataItemMore itemMore) {
        dataItemMoreList.add(itemMore);
    }

}
