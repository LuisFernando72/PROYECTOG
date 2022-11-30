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
import modelo.Control_cliente;
import org.json.simple.JSONObject;

/**
 *
 * @author Luis Fernando Paxel
 */
public class srv_operaciones_cliente extends HttpServlet {

    Clientes clientes;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("accion");

            switch (operacion) {
                case "datos_cliente":
                    datos_cliente(request, response);
                    break;

                case "modificar_cliente":
                    ModificarCliente(request, response);
                    break;

                case "eliminar_registro":
                    EliminarRegistro(request, response);
                    break;

                default:

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

    private void ModificarCliente(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            String NombresC = request.getParameter("txtnombresClienteM");
            String ApellidosC = request.getParameter("txtApellidosClienteM");
            String CorreoCl = request.getParameter("txtcorreoClienteM");
            String FechaN = request.getParameter("txtfechaNacimientoM");
            String Cui = request.getParameter("txtxCUICM");
            String Sexo = request.getParameter("txtsexoM");
            String TelC = request.getParameter("telC_mod");
            String Edad = request.getParameter("txtEdadM");
            String estatura = request.getParameter("txtEstaturaM");
            String NomEmergencia = request.getParameter("txtnombresEmergenciaM");
            String ApeEmergencia = request.getParameter("txtapellidosEmergenciaM");
            String TelEmergencia = request.getParameter("telEm_mod");
            String IdCliente = request.getParameter("txtidCliente");
            String Tel_clienteExtension = request.getParameter("txtTelefonoM");
            String TelEmergenciaExtension = request.getParameter("txttelefonoEM");
            String TelefonoClienteM = "";
            String TelefonoClienteEM = "";

            if (TelC.length() == 0) {
                TelefonoClienteM = Tel_clienteExtension;
            } else {
                TelefonoClienteM = TelC;
            }

            if (TelEmergencia.length() == 0) {
                TelefonoClienteEM = TelEmergenciaExtension;
            } else {
                TelefonoClienteEM = TelEmergencia;
            }

            clientes = new Clientes(NombresC, ApellidosC, Cui, CorreoCl, TelefonoClienteM, Integer.valueOf(Sexo), estatura, FechaN, Integer.valueOf(Edad), NomEmergencia, ApeEmergencia, TelefonoClienteEM, Integer.valueOf(IdCliente));
            int retorno = clientes.ModificarCliente();
            if (retorno == 1) {
                response.getWriter().println("1");

            } else {
                response.getWriter().println("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(srv_operaciones_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void datos_cliente(HttpServletRequest request, HttpServletResponse response) {

        try (PrintWriter out = response.getWriter()) {
            String idCliente = request.getParameter("idCliente");
            Clientes clientes = new Clientes();
            clientes = clientes.DatosCliente(Integer.valueOf(idCliente));
            JSONObject json = new JSONObject();
            json.put("id_cliente", clientes.getIdRegistro());
            json.put("nombres_c", clientes.getNombres());
            json.put("apellidos_c", clientes.getApellidos());
            json.put("correo_c", clientes.getCorreo());
            json.put("fn_cliente", clientes.getFecha_nacimiento());
            json.put("cui_c", clientes.getDpi());
            json.put("genero_c", clientes.getGenero());
            json.put("telefonon_c", clientes.getTelefono());
            json.put("edad_c", clientes.getEdad());
            json.put("estatura_c", clientes.getEstatura());
            json.put("nombres_emergencia", clientes.getNombreE());
            json.put("apellidos_emergencia", clientes.getApellidoE());
            json.put("telefono_emergencia", clientes.getTelefonoE());
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(srv_control_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void EliminarRegistro(HttpServletRequest request, HttpServletResponse response) {

        try (PrintWriter out = response.getWriter()) {
            String idCliente = request.getParameter("txtidCliente");
            Control_cliente control_cliente = new Control_cliente();

            int idRegistro = control_cliente.getIdregistroCliente(Integer.valueOf(idCliente));

            int idControlCliente = control_cliente.getIdcontrolCliente(idRegistro);

            //SE ELIMINA DE PRIMERO EL DETALLE
            int retorno_medida = control_cliente.eliminarControlMedida(idControlCliente);
            int retorno_peso = control_cliente.eliminarControlPeso(idControlCliente);
            int retorno_control = control_cliente.eliminarControl(idControlCliente);

            //SE ELIMINA LA CABECERA
            int retorno_energia = control_cliente.eliminarCondicionesEnergia(idRegistro);
            int retorno_historial = control_cliente.eliminarHistorialClienteC(idRegistro);
            int retorno_registro = control_cliente.eliminarRegistroCliente(Integer.valueOf(idCliente));
            int retorno_cliente = control_cliente.eliminarCliente(Integer.valueOf(idCliente));
            response.getWriter().println(retorno_medida + " " + retorno_peso + " " + retorno_control + " " + retorno_energia + " " + retorno_historial + " " + retorno_registro + " " + retorno_cliente);

            if (retorno_cliente == 1) {
                response.getWriter().println("1");
            } else {
                response.getWriter().println("0");
            }

        } catch (IOException ex) {
            Logger.getLogger(srv_control_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
