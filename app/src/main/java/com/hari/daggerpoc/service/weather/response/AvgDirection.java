package com.hari.daggerpoc.service.weather.response;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public class AvgDirection {
    private String cardinal;
    private String degrees;

    /**
     *
     * @return
     * The cardinal
     */
    public String getCardinal() {
        return cardinal;
    }

    /**
     *
     * @param cardinal
     * The cardinal
     */
    public void setCardinal(String cardinal) {
        this.cardinal = cardinal;
    }

    /**
     *
     * @return
     * The degrees
     */
    public String getDegrees() {
        return degrees;
    }

    /**
     *
     * @param degrees
     * The degrees
     */
    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }
}
