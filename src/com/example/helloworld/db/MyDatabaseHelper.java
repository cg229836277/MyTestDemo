package com.example.helloworld.db;

import android.content.Context;
import com.chuck.commonlib.db.DatabaseHelper;

public class MyDatabaseHelper extends DatabaseHelper {	
	private static MyDatabaseHelper helper;
	
	public MyDatabaseHelper(Context context) {
		super(context);
	}
	
	public static MyDatabaseHelper getInstance(Context context){
		if (helper ==null||!helper.isOpen()) {
			helper = new MyDatabaseHelper(context);
		}
		return helper;		
	}
}
