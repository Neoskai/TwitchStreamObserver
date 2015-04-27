package de.tobj.twitch.streamobserver;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tobj.http.simplerequest.SimpleHTTPRequestJSON;
import de.tobj.http.simplerequest.exception.ConnectorException;
import de.tobj.http.simplerequest.exception.ParserException;
import de.tobj.http.simplerequest.exception.StatusCodeException;
import de.tobj.twitch.streamobserver.event.StreamStatusEvent;
import de.tobj.twitch.streamobserver.event.StreamUpdateEvent;
import de.tobj.twitch.streamobserver.listener.StreamListener;
import de.tobj.twitch.streamobserver.request.StreamRequest;
import de.tobj.twitch.streamobserver.result.channel.StreamResult;

public class TwitchStreamObserver extends Thread {
	private static final Logger logger = LogManager.getLogger(TwitchStreamObserver.class);

	private SimpleHTTPRequestJSON twitchClient = null;
	private List<Channel> channels = new LinkedList<Channel>();
	private List<StreamListener> streamListeners = new LinkedList<StreamListener>();
	private static String defaultBaseUrl = "https://api.twitch.tv/kraken/";
	private static long defaultTimeout = TimeUnit.MINUTES.toSeconds(1);
	private long timeout = 0;

	public TwitchStreamObserver(String baseUrl, long timeout) {
		this.twitchClient = new SimpleHTTPRequestJSON(baseUrl);
		this.timeout = timeout;
	}

	public TwitchStreamObserver(String baseUrl) {
		this(baseUrl, defaultTimeout);
	}

	public TwitchStreamObserver(long timout) {
		this(defaultBaseUrl, timout);
	}

	public TwitchStreamObserver() {
		this(defaultBaseUrl, defaultTimeout);
	}

	@Override
	public void run() {
		logger.debug("run()...");
		while (true) {
			logger.debug("checkStreams...");
			checkStreams();
			logger.debug("checkStreams... done!");

			try {
				logger.debug("sleep for {} seconds, or {} millis...", timeout, TimeUnit.SECONDS.toMillis(timeout));
				Thread.sleep(TimeUnit.SECONDS.toMillis(timeout));
			} catch (InterruptedException e) {
				logger.info(e);
			}
		}
	}

	private void checkStreams() {
		for (Channel channel : channels) {
			checkStream(channel);
		}
	}

	private void checkStream(Channel channel) {
		try {
			StreamResult stream = twitchClient.request(new StreamRequest(channel), new StreamResult());
			if (channel.isOnline() != stream.isOnline()) {
				Date lastStatusChange = channel.getLastStatusChange();
				Date currentStatusChangeDate = new Date();

				StreamStatusEvent event = new StreamStatusEvent(channel.getChannelName(), stream.getStreamData(),
						stream.isOnline(), lastStatusChange, currentStatusChangeDate);
				logger.info("new streamStatusEvent: {}", event.toString());
				new NotifyStatusUpdateRunner(streamListeners, event).start();

				channel.setOnline(stream.isOnline());
				channel.setLastStatusChange(currentStatusChangeDate);
			}
			if (stream.isOnline()) {
				StreamUpdateEvent event = new StreamUpdateEvent(channel.getChannelName(), stream.getStreamData());
				logger.info("new streamUpdateEvent: {}", event.toString());
				new NotifyStreamUpdateRunner(streamListeners, event).start();
			}
		} catch (ConnectorException | ParserException | StatusCodeException e) {
			logger.error("error at checking stream: {}", channel.getChannelName());
			logger.error(e);
		}
	}

	public void addChannel(String channelName) {
		this.channels.add(new Channel(channelName));
	}

	public void addListener(StreamListener listener) {
		this.streamListeners.add(listener);
	}
}