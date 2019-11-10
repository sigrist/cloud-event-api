package com.github.sigrist.cloudevent;

public class MyPayload {

	private final Integer age;
	private final String name;

	public MyPayload(final Integer age, final String name) {
		this.age = age;
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
}
