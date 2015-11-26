package com.example.helloworld.bitmap;

import com.chuck.commonlib.base.CommonLibInit;
import com.chuck.commonlib.util.BitmapUtil;
import com.example.helloworld.R;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class ShowBitmapActivity extends Activity {
	private ListView showBitmapView;
	String onlineUrl = "http://www.jycoder.com/json/Image/";
	String localPath = "/mnt/sdcard/chuck/pictures/";
	String[] urls = {"1.jpg","2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg",
			"9.jpg","10.jpg","11.jpg","12.jpg","13.jpg","14.jpg","15.jpg","16.jpg",
			"17.jpg","18.jpg"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_bitmap);
		
		CommonLibInit.getInstance().init(this);
		
		showBitmapView = (ListView)findViewById(R.id.show_bitmap);
		ShowBitmapAdapter dapter = new ShowBitmapAdapter();
		showBitmapView.setAdapter(dapter);
		
//		HttpDownloadUtil download = new HttpDownloadUtil(this);
		
//		File file = new File("/mnt/sdcard/chuck/pictures/");
//		if(!file.exists()){
//			file.mkdirs();
//		}
//		for(int i = 0 ; i < urls.length ; i++){
//			String fileName = FileUtil.getFileName(urls[i], File.separator, true);
//			String filePath = file.getAbsolutePath() + file.separator + fileName;
//			download.downloadFile(urls[i], filePath);
//		}
	}
	
	public class ShowBitmapAdapter extends BaseAdapter{
		private LayoutInflater inflater;
		
		public ShowBitmapAdapter(){
			inflater = LayoutInflater.from(ShowBitmapActivity.this);
		}
		
		@Override
		public int getCount() {
			return urls.length;
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
			View view;
			if(convertView == null){
				view = inflater.inflate(R.layout.bitmap_item_layout, null);
			}else{
				view = convertView;
			}
			ImageView imageView = (ImageView)view.findViewById(R.id.bitmap_view);
			BitmapUtil.displayImage(localPath + urls[position], imageView);
			return view;
		}		
	}
}
