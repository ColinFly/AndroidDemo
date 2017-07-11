package com.kuaima.bean;

/**
 * Created by colin on 17-6-13.
 */

public class Data {


    /**
     * uid : 100106189412
     * yesterday_income : 0.03
     * phone : 188****3175
     * is_bind_alipay : false
     * nickname : 188****3175
     * yue : 1.04
     * invite_commission : 1
     * avatar :
     * all_income : 1.04
     * tips :
     * today_coin_income : 0
     */

    private DataEntity data;
    /**
     * data : {"uid":100106189412,"yesterday_income":0.03,"phone":"188****3175","is_bind_alipay":false,"nickname":"188****3175","yue":1.04,"invite_commission":1,"avatar":"","all_income":1.04,"tips":"","today_coin_income":0}
     * status : 1000
     * desc : OK
     */

    private int status;
    private String desc;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class DataEntity {
        private long uid;
        private double yesterday_income;
        private String phone;
        private boolean is_bind_alipay;
        private String nickname;
        private double yue;
        private int invite_commission;
        private String avatar;
        private double all_income;
        private String tips;
        private int today_coin_income;

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public double getYesterday_income() {
            return yesterday_income;
        }

        public void setYesterday_income(double yesterday_income) {
            this.yesterday_income = yesterday_income;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isIs_bind_alipay() {
            return is_bind_alipay;
        }

        public void setIs_bind_alipay(boolean is_bind_alipay) {
            this.is_bind_alipay = is_bind_alipay;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public double getYue() {
            return yue;
        }

        public void setYue(double yue) {
            this.yue = yue;
        }

        public int getInvite_commission() {
            return invite_commission;
        }

        public void setInvite_commission(int invite_commission) {
            this.invite_commission = invite_commission;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public double getAll_income() {
            return all_income;
        }

        public void setAll_income(double all_income) {
            this.all_income = all_income;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public int getToday_coin_income() {
            return today_coin_income;
        }

        public void setToday_coin_income(int today_coin_income) {
            this.today_coin_income = today_coin_income;
        }
    }
}
