package com.epam.autum.selection.service;

import java.util.ResourceBundle;

public class PageConfigurator {
	public static final String LOGIN_PAGE = "LOGIN";
	public static final String INDEX_PAGE = "INDEX";
	public static final String ADMIN_PAGE = "ADMIN";
	public static final String APPLICANT_PAGE = "APPLICANT";
	public static final String GUEST_PAGE = "GUEST";
	public static final String ERROR_PAGE = "ERROR";
	public static final String FACULTY_PAGE = "FACULTY";
	public static final String FACULTIES_PAGE = "FACULTIES";
	public static final String REGISTRATION_PAGE = "REGISTRATION";
	public static final String CHANGE_APPLICANT_INFORMATION_PAGE = "CHANGE_APPLICANT_INFORMATION";
	public static final String CHANGE_ADMINISTRATOR_INFORMATION_PAGE = "CHANGE_ADMINISTRATOR_INFORMATION";
	public static final String NEW_ADMINISTRATOR_PAGE = "NEW_ADMINISTRATOR";
	public static final String REJECTED_APPLICATIONS_PAGE = "REJECTED_APPLICATIONS";

	private static final String RESOURCE_FILE = "pages";
	private static ResourceBundle resource;
	private static PageConfigurator configurator;
	
	private PageConfigurator(){
	}
	
	public static PageConfigurator getConfigurator(){
		if(configurator == null){
			configurator = new PageConfigurator();
			resource = ResourceBundle.getBundle(RESOURCE_FILE);
		}
		return configurator;
	}
	
	public String getPage(String key){
		return resource.getString(key);
	}
}
