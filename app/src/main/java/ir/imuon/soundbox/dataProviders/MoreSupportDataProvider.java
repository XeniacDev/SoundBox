package ir.imuon.soundbox.dataProviders;

import java.util.ArrayList;
import java.util.List;

import ir.imuon.soundbox.R;
import ir.imuon.soundbox.models.DataItemMore;

public class MoreSupportDataProvider {

    public static List<DataItemMore> dataItemMoreList;

    static {
        dataItemMoreList = new ArrayList<>();

        addItem(new DataItemMore("support_feedback", R.string.moreFragment_support_feedback));
        addItem(new DataItemMore("support_invite_friends", R.string.moreFragment_support_invite_friends));
    }

    private static void addItem(DataItemMore itemMore) {
        dataItemMoreList.add(itemMore);
    }

}
