package com.innovalynx.careltd;

public class House {
		  private long id;
		  private String house;

		  public long getId() {
		    return id;
		  }

		  public void setId(long id) {
		    this.id = id;
		  }

		  public String getHouse() {
		    return house;
		  }

		  public void setHouse(String house) {
		    this.house = house;
		  }

		  // Will be used by the ArrayAdapter in the ListView
		  @Override
		  public String toString() {
		    return house;
		  }
		  
} 