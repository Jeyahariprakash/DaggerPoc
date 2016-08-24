package com.hari.daggerpoc.service.weather.response;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public class ApparentTemperature {
    private Current current;
    private High high;
    private Low low;

    /**
     *
     * @return
     * The current
     */
    public Current getCurrent() {
        return current;
    }

    /**
     *
     * @param current
     * The current
     */
    public void setCurrent(Current current) {
        this.current = current;
    }

    /**
     *
     * @return
     * The high
     */
    public High getHigh() {
        return high;
    }

    /**
     *
     * @param high
     * The high
     */
    public void setHigh(High high) {
        this.high = high;
    }

    /**
     *
     * @return
     * The low
     */
    public Low getLow() {
        return low;
    }

    /**
     *
     * @param low
     * The low
     */
    public void setLow(Low low) {
        this.low = low;
    }

}

