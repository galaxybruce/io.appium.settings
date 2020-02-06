package io.appium.settings.custom.netty;


/**
 * @date 2020-02-04 11:44
 * @author bruce.zhang
 * @description (亲，我是做什么的)
 * <p>
 * modification history:
 */
public class DeviceInfo {
    /**
     * 作为设备唯一标识，sn在usb或者wifi连接时值是不一样的
     */
    private String serialNo;

    /**
     * serialNumber
     */
    private String sn, brand, version, battery;

    private int w, h;

    private String ip;
    private String netState;

    /**
     * 音量
     */
    private int volume;

    public DeviceInfo() {

    }

    public String getSerialNo() {
        return serialNo;
    }

    public String getSn() {
        return sn;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public String getBrand() {
        return brand;
    }

    public String getVersion() {
        return version;
    }

    public String getBattery() {
        return battery;
    }

    public String getIp() {
        return ip;
    }

    public String getNetState() {
        return netState;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setNetState(String netState) {
        this.netState = netState;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "{"
                + "\"serialNo\":\"" + serialNo + "\","
                + "\"ip\":\""       + ip + "\","
                + "\"w\":"          + w + ","
                + "\"h\":"          + h + ","
                + "\"ip\":\""       + ip + "\","
                + "\"netState\":\"" + netState + "\","
                + "\"volume\":"     + volume + ","
                + "}";
    }
}
