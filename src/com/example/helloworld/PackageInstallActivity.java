package com.example.helloworld;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.content.pm.ApkOperateManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PackageInstallActivity extends Activity {
	private final String PACKAGE_NAME = "com.example.helloworld";
	private final String APP_NAME = "chuck_test";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_package_install);
		
		EventBus.getDefault().register(this);
			
		Button startUpdate = (Button)findViewById(R.id.start_update);
//		startUpdate.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
				ApkOperateManager.installApkDefaul(PackageInstallActivity.this, "/mnt/sdcard/HelloWorld_2.0.apk",PACKAGE_NAME);
//			}
//		});
	}
	
	public void onEventMainThread(String msg){
		if(msg.equals(ApkOperateManager.PACKAGE_DELETED)){
			Toast.makeText(getApplicationContext(), "卸载成功", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(getApplicationContext(), "安装成功", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		EventBus.getDefault().unregister(this);
	}
}
