package com.example.helloworld;

import java.util.Date;
import com.chuck.commonlib.base.CommonLibInit;
import com.chuck.commonlib.db.DaoUtil;
import com.chuck.commonlib.db.DatabaseInfo;
import com.chuck.commonlib.db.Transaction;
import com.example.helloworld.db.DataTest;
import com.example.helloworld.db.DataTestDaoImpl;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class MainActivity extends Activity{
	
	private static final String SAMPLE = Environment.getExternalStorageDirectory() + "/cloudroom/videos/%E4%BA%91%E5%B1%8B%E5%8A%A8%E7%94%BB01%7E1.mp4";  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        
        CommonLibInit.getInstance().init(this);
        
        Class[] clazz = {DataTest.class};
        
        DatabaseInfo info = new DatabaseInfo();
        info.setClazz(clazz);
        info.setDatabaseName("data_test");
        info.setDatabasePath("/mnt/sdcard/data_test.db");
        info.setVersion(2);       
        DaoUtil.init(info);
        
        try {
			DataTestDaoImpl daoImpl = new DataTestDaoImpl(this);
			//daoImpl.addColumn("data_test", "qin", "String");
			DataTest test = new DataTest();
			test.setId("126");
			test.setDate("" + new Date());
			test.setTime("" + new Date());
			test.setName("chuck chan");
			test.setQin("yan");
			
			Transaction trans = daoImpl.getTransaction();
			trans.begainTransaction();
			daoImpl.saveOrUpdate(test);
			trans.commit();
			trans.endTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
  
    protected void onDestroy() {  
        super.onDestroy();  
    }  
}
