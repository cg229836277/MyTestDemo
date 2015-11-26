package com.example.helloworld.http;

import com.chuck.commonlib.util.http.HttpDownloadUtil;
import com.chuck.commonlib.util.http.HttpDownloadUtil.DownloadListenner;
import com.chuck.commonlib.util.http.HttpRequestUtil;
import com.chuck.commonlib.util.http.HttpRequestUtil.HttpRequestCallBackListenner;
import com.example.helloworld.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpTestActivity extends Activity implements DownloadListenner{

	TextView downloadText;
	String url = "http://10.0.7.20:8080/fileDownload/2/%E4%BA%91%E5%B1%8B%E5%8A%A8%E7%94%BB01%7E1.mp4";
	HttpDownloadUtil util;
	String requestUrl = "http://www.hao123.com";
	String requestTag = "chuck";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_test);
		
		downloadText = (TextView)findViewById(R.id.download_view);
		startGetRequest();
	}
	
	public void startDownloadFile(){
		util = new HttpDownloadUtil(this);
		util.setDownloadListenner(this);

		util.downloadFile(url, "/mnt/sdcard/mp4_test.mp4");
	}
	
	public void startGetRequest(){
		try {
			HttpRequestUtil.getAsyncRequest(requestUrl, requestTag, new HttpRequestCallBackListenner() {
				
				@Override
				public String requestSuccess(final String successInfo) {
					downloadText.setText(successInfo);												
					return null;
				}
				
				@Override
				public String requestFail(String failInfo) {
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void downloadProgress(String percent) {
		downloadText.setText(percent);
	}

	@Override
	public void downloadSuccess(String filePath) {
		downloadText.setText("下载成功" + filePath);
	}

	@Override
	public void downloadFail(String errorInfo) {
		downloadText.setText("下载失败" + errorInfo);
	}
	
	@Override
	protected void onDestroy() {
		util.cancelDownload();
		super.onDestroy();
	}
}
