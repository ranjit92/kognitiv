package com.sample.kognitiv.apierror;

public enum ExceptionType {

	UNABLE_TO_SAVE_OFFER("unable.to.save.offer");
	String value;

	ExceptionType(String value) {
		this.value = value;
	}

	String getValue() {
		return this.value;
	}
}
