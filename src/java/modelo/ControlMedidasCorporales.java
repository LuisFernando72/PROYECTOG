package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Luis Fernando Paxel
 */
public class ControlMedidasCorporales {

    private int idControlCliente;

    public ControlMedidasCorporales() {
    }
    private String pectoral, dorsal, biceps, antebrazos, cintura, cadera, muslo, pantorrilla, fecha_medidac;

    public ControlMedidasCorporales(int idControlCliente, String pectoral, String dorsal, String biceps, String antebrazos, String cintura, String cadera, String muslo, String pantorrilla, String fecha_medidac) {
        this.idControlCliente = idControlCliente;
        this.pectoral = pectoral;
        this.dorsal = dorsal;
        this.biceps = biceps;
        this.antebrazos = antebrazos;
        this.cintura = cintura;
        this.cadera = cadera;
        this.muslo = muslo;
        this.pantorrilla = pantorrilla;
        this.fecha_medidac = fecha_medidac;
    }

    public int getIdControlCliente() {
        return idControlCliente;
    }

    public void setIdControlCliente(int idControlCliente) {
        this.idControlCliente = idControlCliente;
    }

    public String getPectoral() {
        return pectoral;
    }

    public void setPectoral(String pectoral) {
        this.pectoral = pectoral;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getBiceps() {
        return biceps;
    }

    public void setBiceps(String biceps) {
        this.biceps = biceps;
    }

    public String getAntebrazos() {
        return antebrazos;
    }

    public void setAntebrazos(String antebrazos) {
        this.antebrazos = antebrazos;
    }

    public String getCintura() {
        return cintura;
    }

    public void setCintura(String cintura) {
        this.cintura = cintura;
    }

    public String getCadera() {
        return cadera;
    }

    public void setCadera(String cadera) {
        this.cadera = cadera;
    }

    public String getMuslo() {
        return muslo;
    }

    public void setMuslo(String muslo) {
        this.muslo = muslo;
    }

    public String getPantorrilla() {
        return pantorrilla;
    }

    public void setPantorrilla(String pantorrilla) {
        this.pantorrilla = pantorrilla;
    }

    public String getFecha_medidac() {
        return fecha_medidac;
    }

    public void setFecha_medidac(String fecha_medidac) {
        this.fecha_medidac = fecha_medidac;
    }

    Conexion cn;

    public int insertarControl_Corporal() {
        int retorno;
        {
            try {
                cn = new Conexion();
                cn.openConexion();
                String fechaControl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                PreparedStatement parametro;

                String query = "insert into control_medidas_corporales(idControlC, Pectoral, Dorsal, Biceps, Antebrazos, Cintura, Cadera, Muslo, Pantorrila, Fecha_medida_corporal )\n"
                        + " values(?,?, ?, ?, ?, ?, ?,?,?,? );";
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);

                parametro.setInt(1, this.getIdControlCliente());
                parametro.setString(2, this.getPectoral());
                parametro.setString(3, this.getDorsal());
                parametro.setString(4, this.getBiceps());
                parametro.setString(5, this.getAntebrazos());
                parametro.setString(6, this.getCintura());
                parametro.setString(7, this.getCadera());
                parametro.setString(8, this.getMuslo());
                parametro.setString(9, this.getPantorrilla());
                parametro.setString(10, fechaControl);
                retorno = parametro.executeUpdate();

                cn.closedConexion();

            } catch (SQLException ex) {
                System.out.println("Error al insertar control corporal " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }

}
