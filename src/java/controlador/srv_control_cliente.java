package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Clientes;
import modelo.ControlMedidasCorporales;
import modelo.Control_cliente;
import modelo.Control_peso;
import org.json.simple.JSONObject;

/**
 *
 * @author Luis Fernando Paxel
 */
public class srv_control_cliente extends HttpServlet {

    Control_cliente controlCliente = new Control_cliente();
    Control_peso controlPeso;
    ControlMedidasCorporales controlmedidas;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String operacion = request.getParameter("accion");
            switch (operacion) {
                case "insertar_controlCliente":
                    InsertarControlCliente(request, response);

                    break;

            }

            out.println("<h5>Servlet 89</h5>");

//            String operacion = request.getParameter("accion");
//            switch (operacion) {
//                case "":
//                    
//                    break;
//            }
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

    private void InsertarControlCliente(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            //            ///////////// COMIENZA LAS MEDIDAS CORPORALES
            String IdRegistro = request.getParameter("txt_idRegControl");
            String peso = request.getParameter("txtPeso");
            String Mpeso = request.getParameter("Mpeso");
            String fpeso = peso + " " + Mpeso;
            String grasaC = request.getParameter("txtGrasac"); //%
            String aguaP = request.getParameter("aguaP"); //%
            String masaM = request.getParameter("masaM"); //%
            String valorF = request.getParameter("valorF"); //%
            String metabolismoB = request.getParameter("metabolismoB"); //%
            String edadM = request.getParameter("edadM"); //NUM
            String masaO = request.getParameter("masaO");
            String Mmasao = request.getParameter("Mmasao");
            String fmasaOsea = masaO + " " + Mmasao;

            String grasaV = request.getParameter("grasaV"); //%

            ///ULTIMO FORMULARIO XDXDXD
            String Pectoral = request.getParameter("txtPextoral");
            String pectoralM = request.getParameter("pectoralM");
            String fPectoral = Pectoral + " " + pectoralM;

            String dorsal = request.getParameter("txtDorsal");
            String dorsalM = request.getParameter("dorsalM");
            String fDorsal = dorsal + " " + dorsalM;

            String biceps = request.getParameter("txtBiceps");
            String bicepsM = request.getParameter("bicepsM");
            String fBiceps = biceps + " " + bicepsM;

            String antebrazos = request.getParameter("txtAntebrazos");
            String antebrazosM = request.getParameter("antebrazosM");

            String fAntebrazos = antebrazos + " " + antebrazosM;

            String cintura = request.getParameter("txrCintura");
            String cinturaM = request.getParameter("cinturaM");
            String fCintura = cintura + " " + cinturaM;

            String cadera = request.getParameter("txtCadera");
            String caderaM = request.getParameter("caderaM");
            String fcadera = cadera + " " + caderaM;

            String muslo = request.getParameter("txtMuslo");
            String musloM = request.getParameter("musloM");
            String fMuslo = muslo + " " + musloM;

            String pantorrilla = request.getParameter("txtPantorrila");
            String pantM = request.getParameter("pantM");
            String fPantorrila = pantorrilla + " " + pantM;
            out.println("<h3>" + IdRegistro + "</h3>");

            int duplicadoControl = controlCliente.DuplicadoidControlRegistro(Integer.valueOf(IdRegistro));

            if (duplicadoControl == 0) {
                Control_cliente controlCliente = new Control_cliente(Integer.valueOf(IdRegistro), "");
                int retorno_control = controlCliente.InsertarControlCliente();
                if (retorno_control == 1) {
                    int idControlCliente = controlCliente.getIdcontrolCliente(Integer.valueOf(IdRegistro));

                    controlPeso = new Control_peso(idControlCliente, fpeso, grasaC + " %", aguaP + " %", masaM + " %", valorF + " %", metabolismoB + " %", edadM, fmasaOsea, grasaV + " %", "");
                    int retornoControlPeso = controlPeso.InsertarControlPeso();
                    controlmedidas = new ControlMedidasCorporales(idControlCliente, fPectoral, fDorsal, fBiceps, fAntebrazos, fCintura, fcadera, fMuslo, fPantorrila, "");
                    int retorno_controlMedidas = controlmedidas.insertarControl_Corporal();

                    if ((retornoControlPeso & retorno_controlMedidas) == 1) {
                        response.getWriter().println("1");
                        //   out.println("<h3> Estado Finalizado</h3>");
                    } else {
                        //     out.println("<h3> Doble Error carnal</h3>");
                        response.getWriter().println("0");
                    }
                }

            } else {
                int idControlCliente = controlCliente.getIdcontrolCliente(Integer.valueOf(IdRegistro));

                controlPeso = new Control_peso(idControlCliente, fpeso, grasaC + " %", aguaP + " %", masaM + " %", valorF + " %", metabolismoB + " %", edadM, fmasaOsea, grasaV + " %", "");
                int retornoControlPeso = controlPeso.InsertarControlPeso();
                controlmedidas = new ControlMedidasCorporales(idControlCliente, fPectoral, fDorsal, fBiceps, fAntebrazos, fCintura, fcadera, fMuslo, fPantorrila, "");
                int retorno_controlMedidas = controlmedidas.insertarControl_Corporal();

                if ((retornoControlPeso & retorno_controlMedidas) == 1) {
                    // out.println("<h3> Estado Finalizado</h3>");
                    response.getWriter().println("1");
                } else {
                    response.getWriter().println("0");
                    //    out.println("<h3> Doble Error carnal</h3>");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(srv_control_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
