package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/* * @author Luis Fernando Paxel
 */
public class Registros {

    private int idRegistro, idCliente;
    private String observacion_clinica, fecha_inicio;

    public Registros() {
    }
    Conexion cn;

    public Registros(int idCliente, String fecha_inicio) {
        this.idCliente = idCliente;
        this.fecha_inicio = fecha_inicio;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getObservacion_clinica() {
        return observacion_clinica;
    }

    public void setObservacion_clinica(String observacion_clinica) {
        this.observacion_clinica = observacion_clinica;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int InsertarRegistro() {
        int retorno;
        {
            try {
                SimpleDateFormat formato = new SimpleDateFormat(
                        "yyyy/MM/dd");
                String fecha = formato.format(new Date());

                cn = new Conexion();
                cn.openConexion();
                PreparedStatement parametro;
                String query = "insert into registros(id_cliente,fecha_inicio)values(?, ?);";
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);

                parametro.setInt(1, this.getIdCliente());
                parametro.setString(2, fecha);
                retorno = parametro.executeUpdate();
                cn.closedConexion();

            } catch (SQLException ex) {
                System.out.println("Error InsertarRegistro " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }

    public int buscar_idRegistro(int id_cliente) {
        String query = "select idregistros from registros where id_cliente = ?;";
        int retorno = 0;

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id_cliente);
            ResultSet watch = parametro.executeQuery();

            while (watch.next()) {
                retorno = watch.getInt("idregistros");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(buscar_idRegistro) " + ex);
            retorno = 0;
        }

        return retorno;
    }

}
