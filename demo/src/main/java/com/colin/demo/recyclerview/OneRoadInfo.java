package com.colin.demo.recyclerview;

import com.colin.demo.R;

public class OneRoadInfo extends RoadInfo implements IAdapterType {

    public OneRoadInfo(String time, String distance, String traffic, String line) {
        super(time, distance, traffic, line);
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_recycler_view_one_route;
    }
}
