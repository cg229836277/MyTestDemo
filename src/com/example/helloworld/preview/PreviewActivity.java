package com.example.helloworld.preview;

import java.io.IOException;

import com.example.helloworld.R;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PreviewActivity extends Activity implements SurfaceHolder.Callback , Camera.PreviewCallback{

	private SurfaceView preview;
	private SurfaceHolder holder;
	private Camera camera;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		
		preview = (SurfaceView)findViewById(R.id.preview);
		holder = preview.getHolder();
		holder.addCallback(this);		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if(camera == null){
			camera = Camera.open(0);
		}
		try {
			camera.setPreviewCallback(this);
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		camera.startPreview();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera=null;
		}
	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		
	}
}
