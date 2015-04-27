package de.tobj.twitch.streamobserver.event;

import de.tobj.twitch.streamobserver.result.channel.Stream;

public class StreamUpdateEvent {

	private String channelName;
	private Stream streamData;

	public StreamUpdateEvent(String channelName, Stream streamData) {
		this.channelName = channelName;
		this.streamData = streamData;
	}

	public String getChannelName() {
		return channelName;
	}

	public Stream getStreamData() {
		return streamData;
	}

	@Override
	public String toString() {
		return "StreamUpdateEvent [channelName=" + channelName + ", streamData=" + streamData + "]";
	}
}