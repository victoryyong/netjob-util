package com.util.ip;

/**
 * IP entry
 *
 * @author 李小勇
 */
public class IPEntry {
    //起始IP
    public String beginIp;
    //结束IP
    public String endIp;
    //国家
    public String country;
    //区域
    public String area;

    public IPEntry() {
        beginIp = "";
        endIp = "";
        country = "";
        area = "";
    }

    @Override
    public String toString() {
    	return this.area + "  " + this.country + "IP范围：" + this.beginIp + "-" + this.endIp;
    }
}
