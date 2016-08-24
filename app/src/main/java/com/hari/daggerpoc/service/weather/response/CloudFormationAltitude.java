package com.hari.daggerpoc.service.weather.response;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public class CloudFormationAltitude {
    private String ft;
    private String m;
    private String yds;

    /**
     *
     * @return
     * The ft
     */
    public String getFt() {
        return ft;
    }

    /**
     *
     * @param ft
     * The ft
     */
    public void setFt(String ft) {
        this.ft = ft;
    }

    /**
     *
     * @return
     * The m
     */
    public String getM() {
        return m;
    }

    /**
     *
     * @param m
     * The m
     */
    public void setM(String m) {
        this.m = m;
    }

    /**
     *
     * @return
     * The yds
     */
    public String getYds() {
        return yds;
    }

    /**
     *
     * @param yds
     * The yds
     */
    public void setYds(String yds) {
        this.yds = yds;
    }
}
