package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* * @author Luis Fernando Paxel
 */
public class Clientes {
    
    private int genero, edad, idRegistro;
    
    private String nombres, apellidos, dpi, correo, telefono, estatura, NombreE, apellidoE, TelefonoE, fecha_actualizacion, fecha_nacimiento;
    private String SelectGeneral = "select idPreguntaCliente,DescripcionPregunta from preguntascliente where id_tipo_pregunta = 1 and idPreguntaCliente";
    private String SelectEspecifica = "select idPreguntaCliente,DescripcionPregunta from preguntascliente where id_tipo_pregunta = 2;";
    
    public Clientes() {
        
    }
    Conexion cn;
    
//    public Clientes(String nombres, String apellidos, String dpi, String correo, String telefono, int genero, String estatura, String fecha_nacimiento, int edad, String NombreE, String apellidoE, String TelefonoE) {
//        this.genero = genero;
//        this.edad = edad;
//        this.nombres = nombres;
//        this.apellidos = apellidos;
//        this.dpi = dpi;
//        this.correo = correo;
//        this.telefono = telefono;
//        this.estatura = estatura;
//        this.fecha_nacimiento = fecha_nacimiento;
//        this.NombreE = NombreE;
//        this.apellidoE = apellidoE;
//        this.TelefonoE = TelefonoE;
//        
//    }
    
    public Clientes(String nombres, String apellidos, String dpi, String correo, String telefono, int genero, String estatura, String fecha_nacimiento, int edad, String NombreE, String apellidoE, String TelefonoE, int idRegistro) {
        this.genero = genero;
        this.edad = edad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dpi = dpi;
        this.correo = correo;
        this.telefono = telefono;
        this.estatura = estatura;
        this.fecha_nacimiento = fecha_nacimiento;
        this.NombreE = NombreE;
        this.apellidoE = apellidoE;
        this.TelefonoE = TelefonoE;
        this.idRegistro = idRegistro;
        
    }
    
    public int getGenero() {
        return genero;
    }
    
    public void setGenero(int genero) {
        this.genero = genero;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getDpi() {
        return dpi;
    }
    
    public void setDpi(String dpi) {
        this.dpi = dpi;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEstatura() {
        return estatura;
    }
    
    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }
    
    public String getNombreE() {
        return NombreE;
    }
    
    public void setNombreE(String NombreE) {
        this.NombreE = NombreE;
    }
    
    public String getApellidoE() {
        return apellidoE;
    }
    
    public void setApellidoE(String apellidoE) {
        this.apellidoE = apellidoE;
    }
    
    public String getTelefonoE() {
        return TelefonoE;
    }
    
    public void setTelefonoE(String TelefonoE) {
        this.TelefonoE = TelefonoE;
    }
    
    public String getFecha_actualizacion() {
        return fecha_actualizacion;
    }
    
    public void setFecha_actualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
    
    public String getSelectGeneral() {
        return SelectGeneral;
    }
    
    public void setSelectGeneral(String SelectGeneral) {
        this.SelectGeneral = SelectGeneral;
    }
    
    public String getSelectEspecifica() {
        return SelectEspecifica;
    }
    
    public void setSelectEspecifica(String SelectEspecifica) {
        this.SelectEspecifica = SelectEspecifica;
    }
    
    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    public int getIdRegistro() {
        return idRegistro;
    }
    
    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }
    
    public int insertarCliente() {
        int retorno;
        {
            try {
                cn = new Conexion();
                cn.openConexion();
                PreparedStatement parametro;
                String query = "insert into clientes(Nombres, Apellidos, NoDPI, Correo_electronico, No_telefono, Genero, Estatura,Fecha_nacimiento, edad, Nombres_emergencia,\n"
                        + " Apellidos_emergencia,Telefono_emergencia)\n"
                        + " values(?,?,?,?, ?, ?, ?, ?, ?, ?, ?,?);";
                
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setString(1, this.getNombres());
                parametro.setString(2, this.getApellidos());
                parametro.setString(3, this.getDpi());
                parametro.setString(4, this.getCorreo());
                parametro.setString(5, this.getTelefono());
                parametro.setInt(6, this.getGenero());
                parametro.setString(7, this.getEstatura());
                parametro.setString(8, this.getFecha_nacimiento());
                parametro.setInt(9, this.getEdad());
                parametro.setString(10, this.getNombreE());
                parametro.setString(11, this.getApellidoE());
                parametro.setString(12, this.getTelefonoE());
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (SQLException ex) {
                System.out.println("Error insertarCliente " + ex);
                retorno = 0;
            }
            
            return retorno;
        }
        
    }
    
    public int buscar_idCliente(String dpi, String email) {
        int retorno = 0;
        Control_cliente control = new Control_cliente();
        String query = "select * from clientes where NoDPI= ? and Correo_electronico = ?;";
        int tr = 0;
        
        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, dpi);
            parametro.setString(2, email);
            ResultSet watch = parametro.executeQuery();
            while (watch.next()) {
                control.setDpi(watch.getString("NoDPI"));
                control.setEmail(watch.getString("Correo_electronico"));
                tr = watch.getInt("id_Cliente");
                retorno = 1;
            }
            //  this.setIdtraer(tr);
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error val control cliente " + ex);
            retorno = 0;
            tr = 0;
        }
        
        return tr;
    }

    // 
//20/09/2022
    public int buscar_duplicadoC(String dpi, String email) {
        int retorno = 0;
        String query = "select  NoDPI, Correo_electronico from clientes where NoDPI = ? and Correo_electronico = ?;";
        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setString(1, dpi);
            parametro.setString(2, email);
            ResultSet watch = parametro.executeQuery();
            while (watch.next()) {
                retorno = 1;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error cliente duplicado " + ex);
            retorno = 0;
            
        }
        
        return retorno;
    }
    
    public Clientes traerRegistro(int idCliente) {
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select r.idregistros, c.Nombres, c.Apellidos,  c.Correo_electronico, c.edad, c.Estatura\n"
                    + "from registros as r inner join clientes as c on r.id_cliente = c.id_Cliente where c.id_Cliente=?";
            
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, idCliente);
            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                Clientes clientes = new Clientes();
                clientes.setIdRegistro(consulta.getInt("r.idregistros"));
                clientes.setNombres(consulta.getString("c.Nombres"));
                clientes.setApellidos(consulta.getString("c.Apellidos"));
                clientes.setCorreo(consulta.getString("c.Correo_electronico"));
                clientes.setEdad(consulta.getInt("c.edad"));
                clientes.setEstatura(consulta.getString("c.Estatura"));
                return clientes;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(traerRegistro) " + ex);
        }
        return null;
    }
    
    public int ModificarCliente() {
        int retorno;
        try {
            cn = new Conexion();
            cn.openConexion();
            PreparedStatement parametro;
            String fechaActualizacion = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            String query = " update clientes set Nombres = ?, Apellidos = ?, NoDPI = ?, Correo_electronico =?, No_telefono = ?,\n"
                    + "Genero = ?, Estatura = ?, Fecha_nacimiento =  ?, edad =?, Nombres_emergencia = ?, Apellidos_emergencia = ?, Telefono_emergencia = ?,\n"
                    + " Fecha_actualizacion = ? where id_Cliente = ?;";
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            
            parametro.setString(1, this.getNombres());
            parametro.setString(2, this.getApellidos());
            parametro.setString(3, this.getDpi());
            parametro.setString(4, this.getCorreo());
            parametro.setString(5, this.getTelefono());
            parametro.setInt(6, this.getGenero());
            parametro.setString(7, this.getEstatura());
            parametro.setString(8, this.getFecha_nacimiento());
            parametro.setInt(9, this.getEdad());
            parametro.setString(10, this.getNombreE());
            parametro.setString(11, this.getApellidoE());
            parametro.setString(12, this.getTelefonoE());
            parametro.setString(13, fechaActualizacion);
            parametro.setInt(14, this.getIdRegistro());
            retorno = parametro.executeUpdate();
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la func(ModificarCliente) " + ex);
            retorno = 0;
        }
        return retorno;
    }
    
    public Clientes DatosCliente(int idCliente) {
        try {
            cn = new Conexion();
            cn.openConexion();
            String query = "select id_Cliente, Nombres, Apellidos, NoDPI, Correo_electronico, No_telefono, Genero, \n"
                    + " Estatura, Fecha_nacimiento, edad, Nombres_emergencia, Apellidos_emergencia, Telefono_emergencia from clientes where id_Cliente = ?;";
            
            PreparedStatement parametro;
            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
            parametro.setInt(1, idCliente);
            ResultSet consulta = parametro.executeQuery();
            while (consulta.next()) {
                Clientes clientes = new Clientes();
                clientes.setIdRegistro(consulta.getInt("id_Cliente"));
                clientes.setNombres(consulta.getString("Nombres"));
                clientes.setApellidos(consulta.getString("Apellidos"));
                clientes.setDpi(consulta.getString("NoDPI"));
                clientes.setCorreo(consulta.getString("Correo_electronico"));
                clientes.setTelefono(consulta.getString("No_telefono"));
                clientes.setGenero(consulta.getInt("Genero"));
                clientes.setEstatura(consulta.getString("Estatura"));
                clientes.setFecha_nacimiento(consulta.getString("Fecha_nacimiento"));
                clientes.setEdad(consulta.getInt("edad"));
                clientes.setNombreE(consulta.getString("Nombres_emergencia"));
                clientes.setApellidoE(consulta.getString("Apellidos_emergencia"));
                clientes.setTelefonoE(consulta.getString("Telefono_emergencia"));
                return clientes;
            }
            cn.closedConexion();
        } catch (SQLException ex) {
            System.out.println("Error func(traerRegistro) " + ex);
        }
        return null;
    }
    
}
