package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * This is credential data model. Consisting of constructor, getters and setters for all attributes of Credential object.
 * Using String object to store the url, username, password and key of the user credentials.
 */

public class Credential {
    private Integer credentialId;
    private String siteUrl;
    private String username;
    private String password;
    private String key;
    private Integer userId;

    public Credential(Integer credentialId, String siteUrl, String username, String password, String key, Integer userId) {
        this.credentialId = credentialId;
        this.siteUrl = siteUrl;
        this.username = username;
        this.password = password;
        this.key = key;
        this.userId = userId;
    }

    public Credential(String siteUrl, String username, String password) {
        this.siteUrl = siteUrl;
        this.username = username;
        this.password = password;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
