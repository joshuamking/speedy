package com.speedy.app;

public abstract class CallbackWithType <T> {
	public abstract void onComplete (T t);

	public void onError () {}
}
