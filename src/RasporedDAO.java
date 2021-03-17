import java.sql.*;
import java.util.ArrayList;

public class RasporedDAO {
    public ArrayList<RasporedInfo> getRasporedi(){
        DatabaseConnection dbc =  new DatabaseConnection();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<RasporedInfo> rasporedi= new ArrayList();
        try{
            c= dbc.getConnection();
            ps=c.prepareStatement("SELECT raspored.id,datum, raspored.tip,ime,prezime,kategorija FROM balkandb.raspored\n" +
                    "INNER JOIN balkandb.zaposleni WHERE zaposleni_jmbg=jmbg ORDER BY datum ");
            rs=ps.executeQuery();
            while(rs.next())
            {
                Raspored raspored=new Raspored(rs.getDate("datum").toLocalDate(),
                        rs.getTime("datum").toLocalTime(),
                        rs.getTime("datum").toLocalTime().plusMinutes(45),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("raspored.tip"),
                        rs.getString("kategorija"),
                        rs.getInt("id"));
                System.out.println(raspored.toStringVerbose());
                System.out.println("==========");
            rasporedi.add(raspored);

            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }

        return rasporedi;

    }
    public void removeRaspored(int id){
        DatabaseConnection dbc =  new DatabaseConnection();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c= dbc.getConnection();
            ps=c.prepareStatement("DELETE FROM balkandb.raspored WHERE raspored.id = "+Integer.toString(id)+";");
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void insertRaspored(Raspored raspored,String jmbg){
        DatabaseConnection dbc =  new DatabaseConnection();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c= dbc.getConnection();
            String stm="INSERT INTO balkandb.raspored (datum,tip,kategorija,zaposleni_jmbg) VALUES ('" +
                    Date.valueOf(raspored.getDatumPocetka()).toString()+" "+Time.valueOf(raspored.getVrijemePocetka()).toString()+"','"+
                    raspored.getTip()+"','"+raspored.getKategorija()+"','"+jmbg+"')";
            System.out.println(stm);
            ps=c.prepareStatement(stm);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public static void main(String[] args) {
        DatabaseConnection dbc =  new DatabaseConnection();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c= dbc.getConnection();
            String stm="INSERT INTO balkandb.raspored (datum,tip,kategorija,zaposleni_jmbg) VALUES ('2021-01-04 01:00:00','Teorija','A1','1')";
            System.out.println(stm);
            ps=c.prepareStatement(stm);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
