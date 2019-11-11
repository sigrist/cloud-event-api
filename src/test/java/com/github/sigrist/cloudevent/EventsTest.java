package com.github.sigrist.cloudevent;

public class EventsTest {

	public static void main(String[] args) {
		TestEventFactory factory = new TestEventFactory();

		MyPayload payload = new MyPayload(41, "Paulo Sigrist");

		Event<MyPayload> event = factory.myPayloadEvent(payload);
		Event<Void> eventVoid = factory.myVoidEvent();

		System.out.println(event.data().get());
		System.out.println(event.subject().get());

		System.out.println(eventVoid.subject().get());
	}
}
