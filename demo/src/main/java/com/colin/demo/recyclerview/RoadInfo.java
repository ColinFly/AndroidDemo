package com.colin.demo.recyclerview;

import com.colin.demo.R;

public class RoadInfo implements IAdapterType {

    protected String time;
    protected String distance;
    protected String traffic;
    protected String line;


    public RoadInfo(String time, String distance, String traffic, String line) {
        this.time = time;
        this.distance = distance;
        this.traffic = traffic;
        this.line = line;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_recycler_view_multi_route;
    }
}
