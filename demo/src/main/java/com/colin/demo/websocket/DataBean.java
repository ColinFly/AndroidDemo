package com.colin.demo.websocket;

public class DataBean {

    /**
     * type : 02
     * start_time : 2019-06-27-15:36:34
     * lane_num : 2
     * lane_right : 白实线
     * lane_left : 双黄实线
     * img_name : 00181.jpg
     * lane_current : 1
     */

    private String type;
    private String start_time;
    private String lane_num;
    private String lane_right;
    private String lane_left;
    private String img_name;
    private String lane_current;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getLane_num() {
        return lane_num;
    }

    public void setLane_num(String lane_num) {
        this.lane_num = lane_num;
    }

    public String getLane_right() {
        return lane_right;
    }

    public void setLane_right(String lane_right) {
        this.lane_right = lane_right;
    }

    public String getLane_left() {
        return lane_left;
    }

    public void setLane_left(String lane_left) {
        this.lane_left = lane_left;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getLane_current() {
        return lane_current;
    }

    public void setLane_current(String lane_current) {
        this.lane_current = lane_current;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "type='" + type + '\'' +
                ", start_time='" + start_time + '\'' +
                ", lane_num='" + lane_num + '\'' +
                ", lane_right='" + lane_right + '\'' +
                ", lane_left='" + lane_left + '\'' +
                ", img_name='" + img_name + '\'' +
                ", lane_current='" + lane_current + '\'' +
                '}';
    }
}
