package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/*
 * @author Luis Fernando Paxel
 */
public class Funciones {

    public Funciones() {

    }

    Conexion cn;

    public static String devolver_Fecha(String fecha) {
        String retorno;

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechan = LocalDate.parse(fecha, formato);
        retorno = String.valueOf(fechan);
        return retorno;
    }

    public static String CalcularEdad(String fecha_nacimiento) {
        String resultado_edad = "";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(fecha_nacimiento, fmt);
        LocalDate fecha_actual = LocalDate.now();

        Period periodo = Period.between(fechaNac, fecha_actual);
        resultado_edad = String.valueOf(periodo.getYears());;

//        System.out.println("Tiene: " + periodo.getYears());
//        System.out.println("Su edad es " + periodo.getYears() + " años " + periodo.getMonths() + " meses y " + periodo.getDays() + " dias");
        return resultado_edad;
    }

    public int Insertar_obsClinica(String observacion, int id_registro) {
        int retorno;
        {
            try {
                String query = "update registros set observacion_clinica = ? where idregistros = ?;";

                cn = new Conexion();
                cn.openConexion();
                PreparedStatement parametro;

                parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                parametro.setString(1, observacion);
                parametro.setInt(2, id_registro);
                retorno = parametro.executeUpdate();
                cn.closedConexion();
            } catch (SQLException ex) {
                System.out.println("Error func(observacionesClinica) " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }
}
