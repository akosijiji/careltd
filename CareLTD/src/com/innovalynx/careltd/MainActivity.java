package com.innovalynx.careltd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnItemClickListener, OnQueryTextListener {
	
	GridView gv;
	
	static String[] title;
	
	static final int[] imgs = {
			R.drawable.view,
			R.drawable.guide,
			R.drawable.contact,
			R.drawable.info
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        title = getResources().getStringArray(R.array.home_menu);

        gv = (GridView) findViewById (R.id.gridView1);
        
        gv.setAdapter(new ImageAdapter(imgs, title));
        gv.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchview_in_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
               (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	Intent i;
    	switch(position){
		case 0:
			i = new Intent(this, ViewHouseAndLand.class);
			startActivity(i);
			break;
		case 1:
			i = new Intent(this, RealEstateGuide.class);
			startActivity(i);
			break;
		case 2:
			i = new Intent(this, Contact.class);
			startActivity(i);
			break;
		case 3:
			i = new Intent(this, Info.class);
			startActivity(i);
			break;
		}
	}

	public class ImageAdapter extends BaseAdapter {
		
		String[] data_text;
	    int[] data_image;

	    ImageAdapter() {
		    data_text = null;
		    data_image = null;
		}

	    ImageAdapter(int[] image, String[] text) {
		    data_text = text;
		    data_image = image;
		}

		@Override
		public int getCount() {
			return data_text.length;
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
			LayoutInflater inflater = getLayoutInflater();

	           	View gridView;

	           	gridView = inflater.inflate(R.layout.row_grid, null);

		   	    TextView textview1 = (TextView) gridView.findViewById(R.id.item_text);
		   	    ImageView imageview = (ImageView) gridView.findViewById(R.id.item_image);
		   	    
		   	    imageview.setScaleType(ImageView.ScaleType.FIT_XY);
		   	    textview1.setText(data_text[position]);
		   	    imageview.setImageResource(data_image[position]);

	   	    return gridView;

		}
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

}
