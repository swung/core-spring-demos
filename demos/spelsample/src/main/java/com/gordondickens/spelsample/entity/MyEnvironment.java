package com.gordondickens.spelsample.entity;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@RooJavaBean
@RooToString(toStringMethod = "toRooString")
@RooEntity
public class MyEnvironment {

	@Value("#{ systemProperties['user.language'] }")
	private String varOne;

	@Value("#{ systemProperties }")
	private java.util.Properties systemProperties;

	@Value("#{ systemEnvironment }")
	private java.util.Properties systemEnvironment;

	@Override
	public String toString() {
		return "\n\n********************** MyEnvironment: [\n\tvarOne="
				+ varOne + ", \n\tsystemProperties=" + systemProperties
				+ ", \n\tsystemEnvironment=" + systemEnvironment + "]";
	}

}
