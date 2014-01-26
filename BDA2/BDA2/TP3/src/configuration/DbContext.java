package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DbContext {

	private static String DB4OFILENAME;

	private static DbContext instance = new DbContext();

	public static ObjectContainer db;

	private DbContext() {
	}

	public static DbContext getInstance() {
		if(db!=null)closeConnection();
		if (instance == null) {
			instance = new DbContext();
		}
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("./database.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// load a properties file
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB4OFILENAME = prop.getProperty("database");
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				DB4OFILENAME);
		return instance;
	}

	public static void closeConnection() {
		db.close();
	}
}
