package de.tobj.twitch.streamobserver.channel.request;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import de.tobj.http.simplerequest.Request;
import de.tobj.http.simplerequest.request.Method;
import de.tobj.twitch.streamobserver.channel.Channel;

/**
 * StreamRequest object for HTTPRequestJSON
 */
public class StreamRequest extends Request {

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

	@Override
	public List<Header> getHttpHeader() {
		List<Header> list = new LinkedList<Header>();
		list.add(new BasicHeader("Accept", "application/vnd.twitchtv.v3+json"));
		return list;
	}
}