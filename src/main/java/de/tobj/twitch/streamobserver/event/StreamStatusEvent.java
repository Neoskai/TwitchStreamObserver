package de.tobj.twitch.streamobserver.event;

import java.util.Date;

import de.tobj.twitch.streamobserver.result.channel.Stream;

public class StreamStatusEvent {

	private String channelName;
	private Stream streamData;
	private boolean isOnline;
	private Date lastStatusChange;
	private Date currentStatusChangeDate;

	public StreamStatusEvent(String channelName, Stream streamData, boolean isOnline, Date lastStatusChange,
			Date currentStatusChangeDate) {
		this.channelName = channelName;
		this.streamData = streamData;
		this.isOnline = isOnline;
		this.lastStatusChange = lastStatusChange;
		this.currentStatusChangeDate = currentStatusChangeDate;
	}

	public String getChannelName() {
		return channelName;
	}

	public Stream getStreamData() {
		return streamData;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public Date getCurrentStatusChangeDate() {
		return currentStatusChangeDate;
	}

	public Date getLastStatusChange() {
		return lastStatusChange;
	}

	@Override
	public String toString() {
		return "StreamStatusEvent [channelName=" + channelName + ", isOnline=" + isOnline + ", streamData="
				+ streamData + ", lastStatusChange=" + lastStatusChange + ", currentStatusChangeDate="
				+ currentStatusChangeDate + "]";
	}
}