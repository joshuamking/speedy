package com.speedy.app.events;

public abstract class BaseEvent <T> {
	private T t;

	public BaseEvent (T t) { this.t = t; }

	public T getPayload () {
		return t;
	}

	public Class getPayloadClass () {
		return t.getClass();
	}
}

