package com.sample.kognitiv.apierror;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferException {

	private static PropertiesConfig propertiesConfig;

	@Autowired
	public OfferException(PropertiesConfig propertiesConfig) {
		OfferException.propertiesConfig = propertiesConfig;
	}

	public class OfferNotSavedException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public OfferNotSavedException(String message) {
			super(message);
		}
	}

	public RuntimeException throwException(ExceptionType exceptionType, String... args) {
		if (ExceptionType.UNABLE_TO_SAVE_OFFER.equals(exceptionType)) {
			return new OfferNotSavedException(format(exceptionType.getValue(), args));
		}
		return new RuntimeException(format(exceptionType.getValue(), args));
	}

	public static String format(String template, String... args) {
		Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
		if (templateContent.isPresent()) {
			return MessageFormat.format(templateContent.get(), args);
		}
		return String.format(template, args);
	}
}
