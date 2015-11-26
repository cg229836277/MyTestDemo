package com.example.helloworld;

import com.chuck.commonlib.view.CustomListView;
import com.chuck.commonlib.view.CustomListView.OnRefreshLoadListenner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListViewActivity extends Activity implements OnRefreshLoadListenner{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_list_view);
		
		CustomListView listView = (CustomListView)findViewById(R.id.custom_list_view);
		listView.setAdapter(new CustomListAdapter());
		listView.setOnRefreshLoadListenner(this);
	}
	
	class CustomListAdapter extends BaseAdapter{

		@Override
		public int getCount() {

			return 50;
		}

		@Override
		public Object getItem(int position) {

			return null;
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = null;
			if(convertView == null){
				view = new TextView(CustomListViewActivity.this);
				view.setText("这是第" + position + "个子item");
				view.setGravity(Gravity.CENTER);
				view.setTextSize(24f);
			}else{
				view = (TextView)convertView;
			}
			return view;
		}
		
	}

	@Override
	public void startRefresh() {
		
	}

	@Override
	public void startOnLoadMore() {
		
	}
}
