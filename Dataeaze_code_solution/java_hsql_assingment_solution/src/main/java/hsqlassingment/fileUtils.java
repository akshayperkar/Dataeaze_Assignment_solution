package hsqlassingment;

import java.io.IOException;
import java.sql.SQLException;

public interface fileUtils {
	
	public void connectDatabase() throws Exception;
	
	public void inserData() throws SQLException,ClassNotFoundException;
	
	public void retriveData() throws SQLException, ClassNotFoundException, IOException;

}
