import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    Connection databaseLink=null;
    String databaseName="3306";
    String databaseUser="root";
    String databasePassword="root";
    String databaseURL="jdbc:mysql://localhost:"+databaseName;

    public  Connection getConnection(){
        try {
            databaseLink= DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

    public static void main(String[] args) {
       DatabaseConnection dbCon=new DatabaseConnection();
        Connection con=null;
       con= dbCon.getConnection();
        if (con != null)
            System.out.println("Connection established");
        else
            System.out.println("Connection not established");
    }
}
