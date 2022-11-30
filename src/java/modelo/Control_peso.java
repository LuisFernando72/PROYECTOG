package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Luis Fernando Paxel
 */
public class Control_peso {

    private int idControlCliente;
    private String peso, grasaC, aguap, masa_muscular, valor_fisico, metabolismoB, edad_metabolica, masaOsea, grasa_visceral, fecha_control_peso;

    public Control_peso() {
    }

    public Control_peso(int idControlCliente, String peso, String grasaC, String aguap, String masa_muscular, String valor_fisico, String metabolismoB, String edad_metabolica, String masaOsea, String grasa_visceral, String fecha_control_peso) {
        this.idControlCliente = idControlCliente;
        this.peso = peso;
        this.grasaC = grasaC;
        this.aguap = aguap;
        this.masa_muscular = masa_muscular;
        this.valor_fisico = valor_fisico;
        this.metabolismoB = metabolismoB;
        this.edad_metabolica = edad_metabolica;
        this.masaOsea = masaOsea;
        this.grasa_visceral = grasa_visceral;
        this.fecha_control_peso = fecha_control_peso;
    }

   
    Conexion cn;

    public int getIdControlCliente() {
        return idControlCliente;
    }

    public void setIdControlCliente(int idControlCliente) {
        this.idControlCliente = idControlCliente;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getGrasaC() {
        return grasaC;
    }

    public void setGrasaC(String grasaC) {
        this.grasaC = grasaC;
    }

    public String getAguap() {
        return aguap;
    }

    public void setAguap(String aguap) {
        this.aguap = aguap;
    }

    public String getMasa_muscular() {
        return masa_muscular;
    }

    public void setMasa_muscular(String masa_muscular) {
        this.masa_muscular = masa_muscular;
    }

    public String getValor_fisico() {
        return valor_fisico;
    }

    public void setValor_fisico(String valor_fisico) {
        this.valor_fisico = valor_fisico;
    }

    public String getMetabolismoB() {
        return metabolismoB;
    }

    public void setMetabolismoB(String metabolismoB) {
        this.metabolismoB = metabolismoB;
    }

    public String getEdad_metabolica() {
        return edad_metabolica;
    }

    public void setEdad_metabolica(String edad_metabolica) {
        this.edad_metabolica = edad_metabolica;
    }

    public String getMasaOsea() {
        return masaOsea;
    }

    public void setMasaOsea(String masaOsea) {
        this.masaOsea = masaOsea;
    }

    public String getGrasa_visceral() {
        return grasa_visceral;
    }

    public void setGrasa_visceral(String grasa_visceral) {
        this.grasa_visceral = grasa_visceral;
    }

    public String getFecha_control_peso() {
        return fecha_control_peso;
    }

    public void setFecha_control_peso(String fecha_control_peso) {
        this.fecha_control_peso = fecha_control_peso;
    }

    public int InsertarControlPeso() {
        int retorno;
        {
            try {
                cn = new Conexion();
                cn.openConexion();
                String fechaControlPeso = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                PreparedStatement parametro;
                String query = " insert into control_pesos(idControlCliente, peso,grasa_corporal,porcentaje_agua,masa_muscular, valor_fisico, metabolismo_basal, \n"
                        + " edad_metabolica, masa_osea, grasa_visceral, fecha_control_peso)\n"
                        + " values(?,?,?,?,?,?,?,?,?,?,?);";
                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setInt(1, this.getIdControlCliente());
                parametro.setString(2, this.getPeso());
                parametro.setString(3, this.getGrasaC());
                parametro.setString(4, this.aguap);
                parametro.setString(5, this.getMasa_muscular());
                parametro.setString(6, this.getValor_fisico());
                parametro.setString(7, this.getMetabolismoB());
                parametro.setString(8, this.getEdad_metabolica());
                parametro.setString(9, this.getMasaOsea());
                parametro.setString(10, this.getGrasa_visceral());
                parametro.setString(11, fechaControlPeso);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (SQLException ex) {
                System.out.println("error func (InsertarControlPeso) " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }

}
