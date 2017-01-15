<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   	<form action="Controller" method="POST">
    	<input type="hidden" name="command" value="header" />
        <nav class="navbar navbar-dark navbar-fixed-top" id="navbar">
            <!-- Collapse button-->
        <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse" data-target="#header">
            <i class="fa fa-bars"></i>
        </button>

        <div class="container" id="nav_container">

            <!--Collapse content-->
            <div class="collapse navbar-toggleable-xs" id="header">
                <!--Links-->
                <span class="icon-kpi-logo navbar-brand">
                    
                </span>
                <ul class="nav navbar-nav" id="navigation">
                    <li class="nav-item active">
                        <button name="home" value=""><a class="nav-link">${content.getString("header.home")}</a></button>
                    </li>
                    <li class="nav-item active">
                    	<c:if test="${user != null}">
                    		<button name="log_out" value=""><a class="nav-link">${content.getString("header.log_out")}</a></button>
                    	</c:if>
                    	<c:if test="${user == null}">
                    		<button name="log_out" value="" disabled="disabled"><a class="nav-link">${content.getString("header.log_out")}</a></button>
                    	</c:if>
                        
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${content.getString("header.language")}</a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <button class="dropdown-item" name="locale" value="en"><img src="img/eng-flag.svg" class="flag"/>English</button>
                            <button class="dropdown-item" name="locale" value="ru"><img src="img/ru-flag.svg" class="flag"/>Русский</button>
                            <button class="dropdown-item" name="locale" value="uk"><img src="img/ua-flag.svg" class="flag"/>Українська</button>
                        </div>
                    </li>
                </ul>
            </div>
            <!--/.Collapse content-->

        </div>
        </nav>
	</form>