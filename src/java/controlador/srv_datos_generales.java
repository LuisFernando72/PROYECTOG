package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente_pregunta;
import modelo.Clientes;
import modelo.Condicion_cliente;
import modelo.Funciones;
import modelo.Registros;
import org.json.simple.JSONObject;

/* @author Luis Fernando Paxel
 */
public class srv_datos_generales extends HttpServlet {

    ArrayList<Integer> estrellas = new ArrayList<>();
    Clientes clientes = new Clientes();
    Registros registros;
    Cliente_pregunta cPregunta;
    Funciones funciones;
    Condicion_cliente condicion_cliente;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String operacion = request.getParameter("accion");

            switch (operacion) {

                case "insertar_cliente":
                    insertar_cliente(request, response);
                    break;

                case "traer_registro":
                    traer_registro(request, response);
                    break;

            }

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void insertar_cliente(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Integer> id_pregunta = new ArrayList<>();
        ArrayList<Integer> respuesta_pregunta = new ArrayList<>();
        
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            int cont_pregunta = -1, index_Id_pregunta = -1, index_ResPregunta = -1;
            String star;
//            DATOS PRIMERA PAGINA

            String NombresC = request.getParameter("txtnombresCliente");
            String ApellidosC = request.getParameter("txtApellidosCliente");
            String CorreoCl = request.getParameter("txtcorreoCliente");
            String FechaN = request.getParameter("txtfechaNacimiento");
            String Cui = request.getParameter("txtxCUIC");
            String Sexo = request.getParameter("txtsexo");
            String TelC = request.getParameter("telC");
            String Edad = request.getParameter("txtEdad");
            String estatura = request.getParameter("txtEstatura");
            String NomEmergencia = request.getParameter("txtnombresEmergencia");
            String ApeEmergencia = request.getParameter("txtapellidosEmergencia");
            String TelEmergencia = request.getParameter("telE");
            String obs_datosg = request.getParameter("observacion1");

//Aquí obtienes el formato que deseas
            respuesta_pregunta.clear();
            id_pregunta.clear();
//llenando el array con valores, para despues solo modificar con la respuesta
            while (cont_pregunta != 23) {
                cont_pregunta++;
                respuesta_pregunta.add(cont_pregunta);
                id_pregunta.add(cont_pregunta);
            }
            for (int i = 1; i <= 24; i++) {
                index_Id_pregunta++;
                String pregunta_radio = request.getParameter("radio" + i);
                String IdPregunta = request.getParameter("id_Pregunta" + i);

                id_pregunta.set(index_Id_pregunta, Integer.valueOf(IdPregunta));
                if ("si".equalsIgnoreCase(pregunta_radio)) {
                    index_ResPregunta++;
                    respuesta_pregunta.set(index_ResPregunta, 1);
                } else {
                    index_ResPregunta++;
                    respuesta_pregunta.set(index_ResPregunta, 0);
                }
            }

            //ESTRELLAS
            estrellas.clear();

            for (int i = 1; i <= 4; i++) {
                //          String dos = star + String.valueOf(i);
                star = request.getParameter("star" + i);

                switch (star) {
                    case "Muy mala":
                        estrellas.add(1);
                        break;
                    case "Mala":
                        estrellas.add(2);
                        break;
                    case "Regular":
                        estrellas.add(3);
                        break;
                    case "Buena":
                        estrellas.add(4);
                        break;
                    case "Excelente":
                        estrellas.add(5);
                        break;

                    default:
                        //estrellas.add(0);
                        out.println("<h6>Error condicion</h6>");

                }
            }

            int duplicado = clientes.buscar_duplicadoC(Cui, CorreoCl);

            if (duplicado == 0) {

                clientes = new Clientes(NombresC, ApellidosC, Cui, CorreoCl, TelC, Integer.valueOf(Sexo), estatura, FechaN, Integer.valueOf(Edad), NomEmergencia, ApeEmergencia, TelEmergencia, 0);
                int retorno_cliente = clientes.insertarCliente();
                if (retorno_cliente == 1) {
                    int id_Cliente = clientes.buscar_idCliente(Cui, CorreoCl);
                    //  out.println("<h5> IDCLIENTE: " + id_Cliente + "</h5>");
                    registros = new Registros(id_Cliente, "");
                    int retorno_registro = registros.InsertarRegistro();

                    if (retorno_registro == 1) {
                        int idRegistro = registros.buscar_idRegistro(id_Cliente);

                        //    out.println("<h5> IDREGISTRO: " + idRegistro + "</h5>");
                        cPregunta = new Cliente_pregunta();
                        int retorno_pregunta = cPregunta.insertar_clienteHPre(id_pregunta, respuesta_pregunta, idRegistro);
                        //  out.println("<h5> retornoPre: " + retorno_pregunta + "</h5>");
                        //  out.println("<h5> IDREGISTRO: " + idRegistro + "</h5>");
                        if (!obs_datosg.isEmpty()) {
                            funciones = new Funciones();
                            int retornoObsClinica = funciones.Insertar_obsClinica(obs_datosg, idRegistro);
                            //    out.println("<h5> RETORNO UPDATE CLINICA: " + retornoObsClinica + "</h5>");
                        }
                        // out.println("<h5> IDREGISTRO55: " + idRegistro + "</h5>");
                        condicion_cliente = new Condicion_cliente();
                        int retorno_condicion = condicion_cliente.insertarCondiciones_E(estrellas, idRegistro);
                        //  out.println("<h5> retorno_condicion: " + retorno_condicion + "</h5>");
                        if (retorno_condicion == 1) {
                            response.getWriter().println(id_Cliente);
                        } else {
                            response.getWriter().println("0");
                        }
                    }

                } else {
                    // out.println("<h5> Error al insertar el cliente </h5>");

                }

            } else {
                // out.println("<h5> CKIENTE DUPLICADO ERROR </h5>");
            }

        } catch (IOException ex) {
            Logger.getLogger(srv_datos_generales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void traer_registro(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String idCliente = request.getParameter("id_cliente");
            Clientes clientes = new Clientes();
            clientes = clientes.traerRegistro(Integer.valueOf(idCliente));
            JSONObject json = new JSONObject();
            json.put("cod_registro", clientes.getIdRegistro());
            json.put("nomC", clientes.getNombres());
            json.put("apeC", clientes.getApellidos());
            json.put("email", clientes.getCorreo());
            json.put("edad", clientes.getEdad());
            json.put("altura", clientes.getEstatura());
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(srv_datos_generales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

}
