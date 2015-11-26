package com.example.helloworld.db;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "data_test")
public class DataTest implements Serializable {	 
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id = true,columnName = "id")
	private String id;
	
	@DatabaseField(columnName = "date")
	private String date;
	
	@DatabaseField(columnName = "time")
	private String time;
	
	@DatabaseField(columnName = "name")
	private String name;
	@DatabaseField(columnName = "qin")
	private String qin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQin() {
		return qin;
	}

	public void setQin(String qin) {
		this.qin = qin;
	}
}
