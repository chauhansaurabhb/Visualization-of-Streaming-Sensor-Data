package deviceImpl;

import framework.*;

// 1. Rename this classname in case more than one database is supported.
// in this case, the file name could be MongoDBProfileDB.java, MySQLProfileDB.java

public class ImplProfileDB implements IProfileDB {

	@Override
	public void setprofile(String newIndex, TempStruct newprofileValue) {
		// Use this function, when storage is considered
		// as an actuator - It stores data from various sources.

	}

	@Override
	public TempStruct getprofile(String index) {
		// Use this function, when storage is considered as
		// sensor - It allows to reterive stored data.
		return null;
	}

}