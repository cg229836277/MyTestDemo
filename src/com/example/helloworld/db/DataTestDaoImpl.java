package com.example.helloworld.db;

import android.content.Context;
import com.chuck.commonlib.db.DaoImpl;

public class DataTestDaoImpl extends DaoImpl<DataTest, String>{
	private Context mContext;
	public DataTestDaoImpl(Context context){
		super(DataTest.class , MyDatabaseHelper.getInstance(context));
		mContext = context;
	}
	
	public void addColumn(String tableName ,String columnName , String dataType){
		MyDatabaseHelper.getInstance(mContext).addColumn(tableName, columnName, dataType);
	}
}
