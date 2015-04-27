package de.tobj.twitch.streamobserver;

import java.util.Date;

public class Channel {

	private boolean isOnline = false;
	private String channel;
	private Date lastStatusChange;

	public Channel(String channel) {
		super();
		this.channel = channel;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public String getChannelName() {
		return channel;
	}

	public void setLastStatusChange(Date lastStatusChange) {
		this.lastStatusChange = lastStatusChange;
	}

	public Date getLastStatusChange() {
		return lastStatusChange;
	}
}