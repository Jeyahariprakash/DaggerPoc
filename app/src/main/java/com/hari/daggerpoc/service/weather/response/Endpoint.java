package com.hari.daggerpoc.service.weather.response;

/**
 * Created by jeyahariprakash on 17/08/16.
 */
public class Endpoint {
    private String url;
    private Double version;
    private String githubProject;
    private String copyright;

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The version
     */
    public Double getVersion() {
        return version;
    }

    /**
     *
     * @param version
     * The version
     */
    public void setVersion(Double version) {
        this.version = version;
    }

    /**
     *
     * @return
     * The githubProject
     */
    public String getGithubProject() {
        return githubProject;
    }

    /**
     *
     * @param githubProject
     * The github_project
     */
    public void setGithubProject(String githubProject) {
        this.githubProject = githubProject;
    }

    /**
     *
     * @return
     * The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     *
     * @param copyright
     * The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "url='" + url + '\'' +
                ", version=" + version +
                ", githubProject='" + githubProject + '\'' +
                ", copyright='" + copyright + '\'' +
                '}';
    }
}
