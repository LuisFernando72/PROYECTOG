package modelo;

import com.mysql.cj.protocol.Resultset;
import conexion.Conexion;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Luis Fernando Paxel
 */
public class Control_cliente {

    private int idControlCliente, idRegistro, idtraer;
    private String fechaControl;
    private String dpi, email;

    public Control_cliente() {

    }

    public Control_cliente(int idRegistro, String fechaControl) {
        this.idRegistro = idRegistro;
        this.fechaControl = fechaControl;
    }

    Conexion cn;

    public int getIdControlCliente() {
        return idControlCliente;
    }

    public void setIdControlCliente(int idControlCliente) {
        this.idControlCliente = idControlCliente;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getFechaControl() {
        return fechaControl;
    }

    public void setFechaControl(String fechaControl) {
        this.fechaControl = fechaControl;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdtraer() {
        return idtraer;
    }

    public void setIdtraer(int idtraer) {
        this.idtraer = idtraer;
    }

    public Control_cliente validar(String dpi, String email) {
        Control_cliente control = new Control_cliente();
        String query = "select * from clientes where NoDPI= ? and Correo_electronico = ?;";
        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, dpi);
            parametro.setString(2, email);
            ResultSet watch = parametro.executeQuery();
            int tr = 0;
            while (watch.next()) {
                control.setDpi(watch.getString("NoDPI"));
                control.setEmail(watch.getString("Correo_electronico"));
                tr = watch.getInt("id_Cliente");
            }
            this.setIdtraer(tr);
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error val control cliente " + ex);
        }
        return control;
    }

    public int InsertarControlCliente() {
        int retorno;
        {
            try {
                String fechaH = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                cn = new Conexion();
                cn.openConexion();
                PreparedStatement parametro;
                String query = "insert into control_clientes(Id_registro, fecha_inicio_control)values(?,?);";

                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, this.getIdRegistro());
                parametro.setString(2, fechaH);
                retorno = parametro.executeUpdate();
                cn.closedConexion();

            } catch (SQLException ex) {

                System.out.println("Error func(InsertarControlCliente) " + ex);
                retorno = 0;

            }
            return retorno;

        }

    }

    public int eliminarControlPeso(int idcontrol) {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from control_pesos where idControlCliente = ?;";
                cn = new Conexion();
                cn.openConexion();
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, idcontrol);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error func (eliminarControlPeso)" + ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
    }

    public int eliminarControlMedida(int idcontrol) {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from control_medidas_corporales where idControlc = ?;";
                cn = new Conexion();
                cn.openConexion();
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, idcontrol);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error func (eliminarControlMedida)" + ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
    }

    public int eliminarControl(int idcontrol) {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from control_clientes where idControl_cliente = ?;";
                cn = new Conexion();
                cn.openConexion();
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, idcontrol);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error func(eliminarControl)" + ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
    }

    public int getIdcontrolCliente(int id_registro) {
        String query = "select idControl_cliente from control_clientes where Id_registro = ?;";
        int retorno = 0;

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id_registro);
            ResultSet watch = parametro.executeQuery();
            while (watch.next()) {
                retorno = watch.getInt("idControl_cliente");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(getIdControlCliente) " + ex);
            retorno = 0;
        }

        return retorno;
    }

    public int getIdCliente(int id_registro) {
        String query = " select id_cliente from registros where idregistros =?;";
        int retorno = 0;

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id_registro);
            ResultSet watch = parametro.executeQuery();
            while (watch.next()) {
                retorno = watch.getInt("id_cliente");
            }

            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(getIdCliente) " + ex);
            retorno = 0;
        }

        return retorno;
    }

    public int eliminarCondicionesEnergia(int idRegistro) {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = " delete from condiciones_energia where idRegistro=?;";
                cn = new Conexion();
                cn.openConexion();
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, idRegistro);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error func(eliminarCondicionesEnergia)" + ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
    }

    public int eliminarHistorialClienteC(int idRegistro) {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = " delete from historial_clientesclinico where id_registro = ?;";
                cn = new Conexion();
                cn.openConexion();
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, idRegistro);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error func(eliminarHistorialClienteC)" + ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
    }

    public int eliminarRegistroCliente(int id_cliente) {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from registros where id_cliente = ?;";
                cn = new Conexion();
                cn.openConexion();
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, id_cliente);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error func(eliminarRegistroCliente)" + ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
    }

    public int eliminarCliente(int id_cliente) {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from clientes where id_Cliente =?;";
                cn = new Conexion();
                cn.openConexion();
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, id_cliente);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error func(eliminarCliente)" + ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
    }

    public int getIdregistroCliente(int id_cliente) {
        String query = "select idregistros from registros where id_cliente=?;";
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
            System.out.println("Error func(getIdregistroCliente) " + ex);
            retorno = 0;
        }

        return retorno;
    }

    public int getidControlCliente(int id_Registro) {
        int retorno = 0;
        String query = " select idControl_cliente from control_clientes where Id_registro = ?;";

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id_Registro);
            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                retorno = consulta.getInt("idControl_cliente");
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(getidControlCliente) " + ex);
            retorno = 0;
        }
        return retorno;
    }

    public int DuplicadoidControlRegistro(int id_Registro) {
        int retorno = 0;
        String query = "select idControl_cliente from control_clientes where Id_registro = ?;";

        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, id_Registro);
            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                consulta.getInt("idControl_cliente");
                retorno = 1;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(DuplicadosIdControl) " + ex);
            retorno = 0;
        }
        return retorno;
    }

}
