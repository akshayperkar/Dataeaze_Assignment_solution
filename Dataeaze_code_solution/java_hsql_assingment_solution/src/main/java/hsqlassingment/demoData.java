package hsqlassingment;

import java.io.IOException;
import java.sql.SQLException;

public class demoData {

	public static void main(String[] args) {
		fileUtils obj=new fileData();
		System.out.println(" inside main ");
		try {
			obj.inserData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("sql:"+e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj.retriveData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("class not found:"+e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("sql exception:"+e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOexception:"+e);
		}

	}

}

