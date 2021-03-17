import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreation {
    public static void main(String[] args) throws SQLException {
        /*DatabaseConnection dbCon=new DatabaseConnection();
        Connection con=null;
        con=dbCon.getConnection();
        if(con!=null){
            Statement statement=null;
            statement=con.createStatement();
            String queryString="insert into balkandb.maticna knjiga zaposlenih values (1)";

        }*/
        RasporedDAO rdao= new RasporedDAO();
        rdao.getRasporedi();

    }
}
