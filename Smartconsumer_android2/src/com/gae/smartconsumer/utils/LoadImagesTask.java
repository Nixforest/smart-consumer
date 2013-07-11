/**
 * SmartConsumer_Fsoft
 * com.gae.smartconsumer.utils
 * Jul 10, 2013
 * NguyenPT
 */
package com.gae.smartconsumer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

/**
 * Class to load image in a background task.
 * @author NguyenPT
 *
 */
public class LoadImagesTask extends AsyncTask<String, Void, Bitmap>{
	/**
	 * Image to load.
	 */
	private ImageView mImgView;
	/**
	 * Constructor.
	 * @param img ImageView
	 */
	public LoadImagesTask(ImageView img) {
		this.mImgView = img;
	}
	/**
	 * Do in background method.
	 */
	@Override
	protected Bitmap doInBackground(String... urls) {
		String urlDisplay = urls[0];
		Bitmap img = null;
		try {
			InputStream in = new java.net.URL(urlDisplay).openStream();
			img = BitmapFactory.decodeStream(in);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			Log.e(LoadImagesTask.class.toString(), ex.getMessage());
			ex.printStackTrace();
		}
		return img;
	}
	/**
	 * Override onPostExecute method.
	 */
	@Override
	protected void onPostExecute(Bitmap result) {
		this.mImgView.setImageBitmap(result);
	}
}
