#TwitchStreamObserver (Java)

You can use this library to observe channel on [twitchTV](http://www.twitch.tv) and get informed if a stream goes online or offline

##Usage

- create an observer
- add channels 
- add listener

see [sample implementation](https://github.com/tobciu/TwitchStreamObserver/blob/master/src/test/java/main/TwitchStreamObserverMain.java)

		TwitchStreamObserver observer = new TwitchStreamObserver("tso-test");
		observer.addChannel("CHANNEL");
		observer.addListener(new StreamListener() {
			@Override
			public void streamUpdate(StreamUpdateEvent event) {
				logger.info("-> Update: " + event.toString());
			}

			@Override
			public void streamIsOffline(StreamStatusEvent event) {
				logger.info("-> now Offline: " + event.toString());
			}

			@Override
			public void streamIsOnline(StreamStatusEvent event) {
				logger.info("-> now Online: " + event.toString());
			}
		});
		observer.start();


###StreamStatusEvent

The ``StreamStatusEvent`` is fired every time when the status changes. It also contains a lot of information about the stream.

In the listener you have to override two methods to gain the events 
- ``streamIsOnline``
- ``streamIsOffline``

###StreamUpdateEvent

The ``StreamUpdateEvent`` is fired every time after a request was send to twitchTV. It contains a lot of information about the stream.

In the listener you have to override one method to gain this event
- ``streamUpdate``


##Installation

###Maven

Using Maven is recommended and its dependencies will be downloaded automatically.

Add the following to the `<dependencies>` section in your pom.xml. [Click here for Ivy, Gradle, and other configs](http://search.maven.org/#artifactdetails|de.tobj.twitch|streamobserver|1.0.1|jar).

    <dependency>
        <groupId>de.tobj.twitch</groupId>
        <artifactId>streamobserver</artifactId>
        <version>1.0.1</version>
    </dependency>

##Configuration

Following configurations have to/can be customized

- clientId (String; required): the twitchTV API requires an custom client id (see [Readme of twitchTV API](https://github.com/justintv/twitch-api))
- baseUrl (String; optional): the base URL for all API resources (Default: https://api.twitch.tv/kraken/ )
- timeout (long; optional): waiting time between requests (Default: 1 minute; time unit: seconds)


##Dependencies

    de.tobj.http.httprequestjson:1.0.1
    org.apache.logging.log4j:log4j-api:2.1

## Authors

TobJ [code@tobj.de](code@tobj.de)
