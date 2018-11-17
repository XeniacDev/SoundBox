package ir.imuon.soundbox.dataProviders;

import java.util.ArrayList;
import java.util.List;

import ir.imuon.soundbox.models.DataItemHomeSongs;

public class HomeSongsDataProvider {

    public static List<DataItemHomeSongs> dataItemHomeSongs;

    static {

        dataItemHomeSongs = new ArrayList<>();
    }

    public static void addItem(DataItemHomeSongs itemHomeSongs) {
        dataItemHomeSongs.add(itemHomeSongs);
    }

}
