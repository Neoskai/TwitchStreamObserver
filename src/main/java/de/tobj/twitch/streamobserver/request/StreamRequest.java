package de.tobj.twitch.streamobserver.request;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

import de.tobj.http.simplerequest.Request;
import de.tobj.http.simplerequest.request.Method;
import de.tobj.twitch.streamobserver.Channel;

public class StreamRequest implements Request {

	private Channel channel;

	public StreamRequest(Channel channel) {
		this.channel = channel;
	}

	@Override
	public String getEndpointUrl() {
		return "streams/" + channel.getChannelName();
	}

	@Override
	public Method getHttpMethod() {
		return Method.GET;
	}

	@Override
	public List<NameValuePair> getHttpParams() {
		return Collections.emptyList();
	}
}