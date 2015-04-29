package de.tobj.twitch.streamobserver.channel.request;

import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

import de.tobj.http.simplerequest.Request;
import de.tobj.http.simplerequest.request.Method;
import de.tobj.twitch.streamobserver.channel.Channel;

/**
 * StreamRequest object for HTTPRequestJSON
 */
public class StreamRequest implements Request {

	private Channel channel;

	/**
	 * Construtor with channel
	 * 
	 * @param channel
	 */
	public StreamRequest(Channel channel) {
		this.channel = channel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tobj.http.simplerequest.Request#getEndpointUrl()
	 */
	@Override
	public String getEndpointUrl() {
		return "streams/" + channel.getChannelName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tobj.http.simplerequest.Request#getHttpMethod()
	 */
	@Override
	public Method getHttpMethod() {
		return Method.GET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tobj.http.simplerequest.Request#getHttpParams()
	 */
	@Override
	public List<NameValuePair> getHttpParams() {
		return Collections.emptyList();
	}
}