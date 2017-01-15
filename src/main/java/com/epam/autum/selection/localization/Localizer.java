package com.epam.autum.selection.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localizer {
	private static final String RESOURCE_NAME = "localization/content";

	public static ResourceBundle loadResources(Locale locale){
		return ResourceBundle.getBundle(RESOURCE_NAME, locale);
	}
	
	public static Locale getLocale(String country){
		switch (country) {
		case "en":
			return Locale.ENGLISH;

		default:
			return Locale.ENGLISH;
		}
	}
}
