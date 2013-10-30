package com.innovalynx.careltd.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("SdCardPath")
public class DBHelper extends SQLiteOpenHelper {

	        //The Android's default system path of your application database.
	        private static String DB_PATH = "/data/data/com.innovalynx.careltd/databases/";
	        private static String DB_NAME = "db_careltd";
	        static String KEY_ID = "_id";
	        public static String KEY_HOUSE = "house";
	        public static String KEY_PRICE = "price";
	        
	        static String DB_TABLE = "tblHouse";
	        private SQLiteDatabase myDataBase; 
	        private final Context myContext;

	        /**
	         * Constructor
	         * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	         * @param context
	         */
	        
	        public DBHelper(Context context) {
	                super(context, DB_NAME, null, 1);
	                this.myContext = context;
	        }       

	        /**
	         * Creates a empty database on the system and rewrites it with your own database.
	         * */
	        
	        public void createDataBase() throws IOException {

	                boolean dbExist = checkDataBase();
	                
	                myDataBase = null;
	                
	                 if (dbExist) {
	                        // do nothing - database already exist
	                    } else {

	                        // By calling this method and empty database will be created into
	                        // the default system path

	                        myDataBase = this.getReadableDatabase(); 
	                        myDataBase.close();
	        

	                        try {
	                                copyDataBase(); 
	                        } catch (IOException e) {
	                                throw new Error("Error copying database");
	                        }
	                }
	        }

	        /**
	         * Check if the database already exist to avoid re-copying the file each time you open the application.
	         * @return true if it exists, false if it doesn't
	         */
	        
	        private boolean checkDataBase(){
	                SQLiteDatabase checkDB = null;
	                try{
	                        String myPath = DB_PATH + DB_NAME;
	                        checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	                }catch(SQLiteException e){
	                        //database doesn't exist yet.
	                }
	                if(checkDB != null){
	                        checkDB.close();
	                }

	                return checkDB != null ? true : false;
	        }

	        /**
	         * Copies your database from your local assets-folder to the just created empty database in the
	         * system folder, from where it can be accessed and handled.
	         * This is done by transfering bytestream.
	         * */
	        
	        private void copyDataBase() throws IOException{

	                //Open your local db as the input stream
	                InputStream myInput = myContext.getAssets().open(DB_NAME);

	                // Path to the just created empty db
	                String outFileName = DB_PATH + DB_NAME;

	                //Open the empty db as the output stream
	                OutputStream myOutput = new FileOutputStream(outFileName);

	                //transfer bytes from the inputfile to the outputfile
	                byte[] buffer = new byte[1024];
	                int length;
	                while ((length = myInput.read(buffer))>0){
	                        myOutput.write(buffer, 0, length);
	                }

	                //Close the streams
	                myOutput.flush();
	                myOutput.close();
	                myInput.close();

	        }

	        public void openDataBase() throws SQLException {
	                //Open the database
	                myDataBase = this.getWritableDatabase();
	                String myPath = DB_PATH + DB_NAME;
	                myDataBase = SQLiteDatabase.openDatabase(myPath, null, 
	                                SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);
	                
	        }

	        @Override
	        public synchronized void close() {
	                if(myDataBase != null)
	                        myDataBase.close();
	                super.close();
	        }

	        @Override
	        public void onCreate(SQLiteDatabase db) {
	        }

	        @Override
	        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        }

	        public Cursor getAllHouses()
	        {
	            Cursor localCursor =  
	               this.myDataBase.query(DB_TABLE, new String[] { 
	                   KEY_ID, KEY_HOUSE, KEY_PRICE }, null, null, null, null, null);
	                                        
	            if (localCursor != null)
	              localCursor.moveToFirst();
	            return localCursor;
	        }
	        
	        
	        public String[] getAll(){
	        	Cursor localCursor =  
	 	               this.myDataBase.query(DB_TABLE, new String[] { 
	 	                   KEY_ID, KEY_HOUSE, KEY_PRICE }, null, null, null, null, null);
	        	String[] array = new String[localCursor.getCount()];
	        	int i = 0;
	        	while(localCursor.moveToNext()){
	        	    String uname = localCursor.getString(localCursor.getColumnIndex(DBHelper.KEY_HOUSE));
	        	    array[i] = uname;
	        	    i++;
	        	}
				return array;
	        }
	        
	        public String[] getAllPrices(){
	        	Cursor localCursor =  
	 	               this.myDataBase.query(DB_TABLE, new String[] { 
	 	                   KEY_ID, KEY_HOUSE, KEY_PRICE }, null, null, null, null, null);
	        	String[] array = new String[localCursor.getCount()];
	        	int i = 0;
	        	while(localCursor.moveToNext()){
	        	    String price = localCursor.getString(localCursor.getColumnIndex(DBHelper.KEY_PRICE));
	        	    
	        	    array[i] = price;
	        	    i++;
	        	}
				return array;
	        }
	        
	        public String[] sortHighestPrice(){
	        	Cursor localCursor =  
	 	               this.myDataBase.query(DB_TABLE, new String[] { 
	 	                   KEY_ID, KEY_HOUSE, KEY_PRICE }, 
	 	                   null, null, null, null, 
	 	                  KEY_PRICE + " DESC");
	        	String[] array = new String[localCursor.getCount()];
	        	int i = 0;
	        	while(localCursor.moveToNext()){
	        	    String price = localCursor.getString(localCursor.getColumnIndex(DBHelper.KEY_PRICE));
	        	    array[i] = price;
	        	    i++;
	        	}
				return array;
	        }
	        
	        public String[] sortLowestPrice(){
	        	Cursor localCursor =  
	 	               this.myDataBase.query(DB_TABLE, new String[] { 
	 	                   KEY_ID, KEY_HOUSE, KEY_PRICE }, 
	 	                   null, null, null, null, 
	 	                  KEY_PRICE + " ASC");
	        	String[] array = new String[localCursor.getCount()];
	        	int i = 0;
	        	while(localCursor.moveToNext()){
	        	    String uname = localCursor.getString(localCursor.getColumnIndex(DBHelper.KEY_PRICE));
	        	    array[i] = uname;
	        	    i++;
	        	}
				return array;
	        }

	        /*
	        public Cursor sortByHouse()
	        {
	            Cursor localCursor =
	                        this.myDataBase.query(DB_TABLE, new String[] { 
	                                        KEY_ID, KEY_HOUSE, KEY_PRICE }, 
	                                        null, null, null, null, KEY_HOUSE + " ASC");
	                                        
	            if (localCursor != null)
	              localCursor.moveToFirst();
	            return localCursor;
	        }
	        
	        public Cursor sortByPrice()
	        {
	            Cursor localCursor =
	                        this.myDataBase.query(DB_TABLE, new String[] { 
	                                        KEY_ID, KEY_HOUSE, KEY_PRICE }, 
	                                        null, null, null, null, KEY_PRICE + " ASC");
	                                        
	            if (localCursor != null)
	              localCursor.moveToFirst();
	            return localCursor;
	        } */
	        
	        public long createEntry( String strHouse, String strPrice ) {
	                // TODO Auto-generated method stub
	        
	                ContentValues cv = new ContentValues();
	                cv.put(KEY_HOUSE, strHouse);
	                cv.put(KEY_PRICE, strPrice);
	                
	                return myDataBase.insert(DB_TABLE, null, cv);
	                
	        }

	        public void updateEntry( long lId, String strHouse ) { 
	                
	                
	                ContentValues cvUpdate = new ContentValues();
	                
	                cvUpdate.put(KEY_HOUSE, strHouse);
	                myDataBase.update(DB_TABLE, cvUpdate, KEY_ID + " = " + lId, null);
	                
	        }

}