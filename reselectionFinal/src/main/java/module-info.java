module rslFxNewMaven {
	exports db.connector;
	exports reselection;
	exports reselection.results;
	exports reselection.search;
	exports db.equipment;
	exports db.exceptions;
	exports reselection.model;
	exports reselection.view;
	exports db.config;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires c3p0;
	requires java.desktop;
	requires org.controlsfx.controls;
	requires java.base;
	requires javafxsvg;
	requires batik.transcoder;
	
	opens reselection.view to javafx.fxml;
	opens reselection.search to javafx.fxml;
	opens reselection.results to javafx.fxml;
	opens reselection.editdb to javafx.fxml;
	opens reselection.comparison to javafx.fxml;
}