package com.innovalynx.careltd;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Info extends Activity implements OnClickListener {
	
	Button back;
	ProgressDialog pd;
	private TextView tvJA, tvJR;
	private ImageView imgView1, imgView2;
    private String strURLJohnA = "http://careltd.com.au/Websites/care/Images/I-JohnA.jpg";
    private String strURLJohnR = "http://careltd.com.au/Websites/care/Images/I-JohnR.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		back = (Button) findViewById (R.id.btBack);
		back.setOnClickListener(this);
		
		tvJA = (TextView) findViewById (R.id.tvJA);
		tvJR = (TextView) findViewById (R.id.tvJR);
		
		String ja = getString(R.string.johna);
		String jr = getString(R.string.johnr);
		
		tvJA.setText(ja);
		tvJR.setText(jr);
		
		imgView1 = (ImageView) findViewById(R.id.ivInfo1);
		imgView2 = (ImageView) findViewById (R.id.ivInfo2);
		
		// Create an object for subclass of AsyncTask
        GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[] { strURLJohnA, strURLJohnR });
		
    }
  
	private class GetXMLTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {
        @Override
        protected ArrayList<Bitmap> doInBackground(String... urls) {
        	ArrayList<Bitmap> map = new ArrayList<Bitmap>();
	        	//map.add(downloadImage(urls[0]));
	            //map.add(downloadImage(urls[1]));
	            
	            for (String url : urls) {
	                map.add(downloadImage(url));
	            }
            return map;
        }

        @Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
        	super.onPreExecute();
        	pd = ProgressDialog.show(Info.this, "Please wait", "Downloading content", false, true);
		}

		// Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(ArrayList<Bitmap> result) {
            imgView1.setImageBitmap(result.get(0));
            imgView2.setImageBitmap(result.get(1));
            pd.dismiss();
        }
 
        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;
 
            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }
 
        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
 
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
 
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }

	@Override
	public void onClick(View v) {
		finish();
	}

}
