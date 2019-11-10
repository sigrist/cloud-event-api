package com.github.sigrist.cloudevent;

public class EventsTest {

	public static void main(String[] args) {
		Event<MyPayload> event;
		EventFactory factory = new TestEventFactory();
		
		MyPayload payload = new MyPayload(41, "Paulo Sigrist");
		
		
		event = factory.create("payload", payload);
		Event<Void> eventVoid = factory.create("void");
		
		System.out.println(event);
		System.out.println(eventVoid.subject().get());
	}
}
