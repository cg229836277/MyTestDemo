package com.example.helloworld;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GetKeycodeActivity extends Activity {

	private TextView view;
	//home键
	static public final String KEYCODE_CLOUDROOM_HOME = "keycode_cloudroom_home";
	//最近任务键
	static public final String KEYCODE_CLOUDROOM_RECENT = "keycode_cloudroom_recent";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_keycode);
		
		view = (TextView)findViewById(R.id.get_keycode);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(KEYCODE_CLOUDROOM_HOME);
		filter.addAction(KEYCODE_CLOUDROOM_RECENT);
		registerReceiver(new MyBroadcatsReceiver(), filter);
	}
	
	class MyBroadcatsReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent != null){
				if(KEYCODE_CLOUDROOM_HOME.equals(intent.getAction())){
					Toast.makeText(GetKeycodeActivity.this, "Home 键按下", Toast.LENGTH_LONG).show();
					view.setText(view.getText() + "\n" + "Home 键按下");
				}else if(KEYCODE_CLOUDROOM_RECENT.equals(intent.getAction())){
					Toast.makeText(GetKeycodeActivity.this, "最近任务 键按下", Toast.LENGTH_LONG).show();
					view.setText(view.getText() + "\n" + "最近任务 键按下");
				}
			}
		}		
	}
}
