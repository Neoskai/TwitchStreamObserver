package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tobj.twitch.streamobserver.TwitchStreamObserver;
import de.tobj.twitch.streamobserver.channel.event.StreamStatusEvent;
import de.tobj.twitch.streamobserver.channel.event.StreamUpdateEvent;
import de.tobj.twitch.streamobserver.channel.listener.StreamListener;

public class TwitchStreamObserverMain {
	private static final Logger logger = LogManager.getLogger(TwitchStreamObserverMain.class);

	public static void main(String[] args) {

		TwitchStreamObserver observer = new TwitchStreamObserver("tso-test");

		observer.addChannel("rocketbeanstv");
		observer.addListener(new StreamListener() {
			@Override
			public void streamUpdate(StreamUpdateEvent event) {
				super.streamUpdate(event);
				logger.info("-> Update: " + event.toString());
			}

			@Override
			public void streamIsOffline(StreamStatusEvent event) {
				super.streamIsOffline(event);
				logger.info("-> now Offline: " + event.toString());
			}

			@Override
			public void streamIsOnline(StreamStatusEvent event) {
				super.streamIsOnline(event);
				logger.info("-> now Online: " + event.toString());
			}
		});
		observer.start();
		logger.info(":-)");
	}
}