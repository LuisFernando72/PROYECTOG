/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luis Fernando Paxel
 */
public class Cliente_pregunta {

    private int idPregunta, idRegistro, respuesta;

    public Cliente_pregunta() {
    }

    public Cliente_pregunta(int idPregunta, int respuesta, int idRegistro) {
        this.idPregunta = idPregunta;
        this.idRegistro = idRegistro;
        this.respuesta = respuesta;

    }

    Conexion cn;

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int insertar_clienteHPre(ArrayList<Integer> id_Pregunta, ArrayList<Integer> Respuesta, int idReg) {
        int retorno = 0;
        {
            try {
                cn = new Conexion();
                cn.openConexion();
                for (int i = 0; i < id_Pregunta.size(); i++) {

                    PreparedStatement parametro;
                    String query = "insert into historial_clientesclinico(Id_pregunta, Respuesta, id_registro)values(?,?,?);";
                    parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                    parametro.setInt(1, id_Pregunta.get(i));
                    parametro.setInt(2, Respuesta.get(i));
                    parametro.setInt(3, idReg);
                    retorno = parametro.executeUpdate();
                }
                cn.closedConexion();

            } catch (SQLException ex) {
                System.out.println("Error  al insertar historial Cliente Pregunta " + ex);
                retorno = 0;
            }
            return retorno;
        }

    }

}
