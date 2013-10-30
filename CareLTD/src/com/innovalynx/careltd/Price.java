package com.innovalynx.careltd;

public class Price {
		  private long id;
		  private String price;

		  public long getId() {
		    return id;
		  }

		  public void setId(long id) {
		    this.id = id;
		  }

		  public String getprice() {
		    return price;
		  }

		  public void setprice(String price) {
		    this.price = price;
		  }

		  // Will be used by the ArrayAdapter in the ListView
		  @Override
		  public String toString() {
		    return price;
		  }
		  
} 