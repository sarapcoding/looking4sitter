package com.dad;

public class SitterProfile {
	private final long id;
    private final String sitterurl;

    public SitterProfile(long id, String sitterurl) {
        this.id = id;
        this.sitterurl = sitterurl;
    }

    public long getId() {
        return id;
    }

    public String getSitterurl() {
        return sitterurl;
    }
}
