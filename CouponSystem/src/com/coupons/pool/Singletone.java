package com.coupons.pool;

class Singleton{
	//Private reference to the only instance of the Singleton object.
	private static Singleton mySingleton = null;
	
	//Private constructor to prohibit direct object instantiation.
	private Singleton(){}
	
	//Method to retrieve a reference to the only instance.
	public static synchronized Singleton getInstance(){
	//If null create the instance
	if(null == mySingleton){
	
	mySingleton = new Singleton();
	
	//If not null return the only instance
	}else{
	
	}
	return mySingleton;
	}//end getInstance()
	}//end Singleton class
