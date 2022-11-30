package conexion;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* @author Luis Fernando Paxel
 */
public class Conexion {

    public Connection conexiondb;
    public final String bd = "bd_gym";
    public final String urlCon = String.format("jdbc:mysql://localhost:3306/%s", bd);
    public final String user = "db_Gym";
    public final String passw = "#gym522.%%%";
    public final String jdbc = "com.mysql.cj.jdbc.Driver";

    public void openConexion() {
        try {
            Class.forName(jdbc);
            conexiondb = DriverManager.getConnection(urlCon, user, passw);
            // JOptionPane.showMessageDialog(null, "Conexion Exitosa","Exito",JOptionPane.INFORMATION_MESSAGE);
            //   System.out.println("Conexion Exitosa");
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
            System.out.println("--  Error4444  --" + ex.getMessage());
        }
    }

    public void closedConexion() {
        try {
            conexiondb.close();
        } catch (SQLException ex) {

            System.out.println("--  Error  --" + ex.getMessage());
        }
    }
}
