import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZaposleniDAO {
    public ArrayList<Zaposleni> getZaposleni(){
        DatabaseConnection dbc =  new DatabaseConnection();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Zaposleni> zaposleni= new ArrayList();
        try{
            c= dbc.getConnection();
            ps=c.prepareStatement("SELECT * FROM balkandb.zaposleni");
            rs=ps.executeQuery();
            while(rs.next())
            {
                Zaposleni zaposlen=new Zaposleni(rs.getString("jmbg"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("datumRodjenja"),
                        rs.getString("pol"),
                        rs.getString("adresa"),
                        rs.getString("drzava"),
                        rs.getBoolean("dozvolaBoravak"),
                        rs.getBoolean("dozvolaRad"),
                        rs.getString("strucnoObr"),
                        rs.getString("dodatnoObr"),
                        rs.getString("tip"),
                        rs.getDate("pocetakRada").toLocalDate(),
                        rs.getDate("ugovorORadu").toLocalDate(),
                        rs.getInt("maticna knjiga zaposlenih_redniBroj"));
                zaposleni.add(zaposlen);

            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }

        return zaposleni;

    }


    public static void main(String[] args) throws SQLException {
        ZaposleniDAO tdao=new ZaposleniDAO();
        ArrayList<Zaposleni>zap=tdao.getZaposleni();
        for( Zaposleni z :zap)
            System.out.println(z.getIme()+" "+z.getPrezime());

    }
}
