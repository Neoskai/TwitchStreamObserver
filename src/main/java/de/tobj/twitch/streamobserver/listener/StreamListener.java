package de.tobj.twitch.streamobserver.listener;

import de.tobj.twitch.streamobserver.event.StreamStatusEvent;
import de.tobj.twitch.streamobserver.event.StreamUpdateEvent;

public abstract class StreamListener {

	public void streamIsOnline(StreamStatusEvent event) {

	}

	public void streamIsOffline(StreamStatusEvent event) {

	}

	public void streamUpdate(StreamUpdateEvent event) {

	}
}