package com.hari.daggerpoc.service.weather.response;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public class Humidity {
    private String current;
    private String high;
    private String low;
    private String trend;

    /**
     *
     * @return
     * The current
     */
    public String getCurrent() {
        return current;
    }

    /**
     *
     * @param current
     * The current
     */
    public void setCurrent(String current) {
        this.current = current;
    }

    /**
     *
     * @return
     * The high
     */
    public String getHigh() {
        return high;
    }

    /**
     *
     * @param high
     * The high
     */
    public void setHigh(String high) {
        this.high = high;
    }

    /**
     *
     * @return
     * The low
     */
    public String getLow() {
        return low;
    }

    /**
     *
     * @param low
     * The low
     */
    public void setLow(String low) {
        this.low = low;
    }

    /**
     *
     * @return
     * The trend
     */
    public String getTrend() {
        return trend;
    }

    /**
     *
     * @param trend
     * The trend
     */
    public void setTrend(String trend) {
        this.trend = trend;
    }

}
