package ir.imuon.soundbox.dataProviders;

import java.util.ArrayList;
import java.util.List;

import ir.imuon.soundbox.R;
import ir.imuon.soundbox.models.DataItemMore;

public class MoreAccountDataProvider {

    public static List<DataItemMore> dataItemMoreList;

    static {
        dataItemMoreList = new ArrayList<>();

        addItem(new DataItemMore("account_premium", R.string.moreFragment_account_premium_account));
        addItem(new DataItemMore("account_edit", R.string.moreFragment_account_edit_account));
        addItem(new DataItemMore("account_logout", R.string.moreFragment_account_log_out));
    }

    private static void addItem(DataItemMore itemMore) {
        dataItemMoreList.add(itemMore);
    }

}
