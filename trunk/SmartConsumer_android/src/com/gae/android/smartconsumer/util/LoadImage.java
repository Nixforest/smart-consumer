package com.gae.android.smartconsumer.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class LoadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView imgView;
    public LoadImage(ImageView img) {
        this.imgView = img;
    }
    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap img = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            img = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (IOException ex) {
            Log.e("error", ex.getMessage());
            ex.printStackTrace();
        }
        return img;
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        this.imgView.setImageBitmap(result);
    }
}
