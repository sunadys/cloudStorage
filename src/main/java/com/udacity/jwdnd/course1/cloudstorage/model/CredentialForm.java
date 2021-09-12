package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * This is data model used to collect new credentials from the user.
 */


public class CredentialForm {
    private String siteUrl;
    private String username;
    private String password;
    private String credentialId;

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

    public String getCredentialId() {
        return credentialId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }
}

