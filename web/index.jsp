<%-- 
    Document   : index
    Created on : Nov 19, 2022, 10:47:18 PM
    Author     : Luis Fernando Paxel
--%>

<%@page import="conexion.Conexion"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Tablas"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="modelo.Clientes"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Principal</title>
        <link rel="stylesheet" href="css/estilo_table.css"/>
        <link rel="stylesheet" href="css/estilo_datos_generales.css"/>
        <link rel="stylesheet" href="css/estilo-control_cliente.css"/>
        <link rel="stylesheet" href="css/estilo_modificar_cliente.css">


        <!--        scripts pagina-->
        <script src="library/sweetalert2.min.js"></script>
        <!--iconos -->
        <script src="library/fontawesome/js/all.min.js"></script>
        <script src="library/jQuery3.js" ></script>

        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css"
            />

        <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

    </head>
    <body>

        <!-- FORMULARIO DATOS GENERALES CLIENTE -->
        <dialog id="modal_datos_generales">
            <div class="container">
                <div class="info-container" id="InfoCont">
                    <!-- <div class="container-form"> -->
                    <div id="btn_cancelarDatosG" class="btn_cancelar">
                        <i class="fa-solid fa-circle-xmark ex"> </i>
                    </div>
                    <header>Registro General</header>
                    <br />
                    <div class="progressbar">
                        <div class="progressbar__option">
                            <p>Información general</p>
                        </div>
                        <div class="progressbar__option">
                            <p>Historial clínico</p>
                        </div>
                        <div class="progressbar__option">
                            <p>Historial clínico</p>
                        </div>
                        <div class="progressbar__option">
                            <p>Condición energía</p>
                        </div>
                    </div>

                    <div class="form-principalDT">
                        <form id="form_datosg"  action="srv_datos_generales" method="post">
                            <!-- PAGINA NO 1 DATOS GENERALES -->
                            <div class="pagina movPag">
                                <div class="gridpagina">
                                    <div class="titulo bloc3">
                                        <i class="fa fa-user-circle"></i> Datos Generales
                                    </div>

                                    <!-- Nombres -->
                                    <div class="campo">
                                        <div class="label">
                                            Nombres <b class="error" id="txtnombresCliente-error"></b>
                                        </div>

                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            id="txtnombresCliente"
                                            name="txtnombresCliente"
                                            placeholder="Nombres"
                                            />
                                    </div>

                                    <!-- Apellidos -->
                                    <div class="campo">
                                        <div class="label">
                                            Apellidos
                                            <b class="error" id="txtxapellidosCliente-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtApellidosCliente"
                                            id="txtxapellidoCliente"
                                            placeholder="Apellidos"
                                            />
                                    </div>

                                    <!-- Correo Electrónico -->
                                    <div class="campo">
                                        <div class="label">
                                            Correo Electrónico
                                            <b class="error" id="txtcorreoCliente-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-envelope" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtcorreoCliente"
                                            id="txtcorreoCliente"
                                            placeholder="Correo Electrónico"
                                            />
                                    </div>
                                    <!-- Fecha Nacimiento -->
                                    <div class="campo">
                                        <div class="label">
                                            Fecha Nacimiento
                                            <b class="error" id="txtfechaNacimiento-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-calendar" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="date"
                                            name="txtfechaNacimiento"
                                            id="txtfechaNacimiento"
                                            style="height: calc(100% + 4px)"
                                            />
                                    </div>

                                    <!-- NO DPI -->
                                    <div class="campo">
                                        <div class="label">
                                            CUI
                                            <b class="error" id="txtxcuic-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input type="text"name="txtxCUIC" id="txtxCUIC" placeholder="CUI" />
                                    </div>

                                    <!-- Sexo -->
                                    <div class="campo">
                                        <div class="label">
                                            Sexo<b class="error" id="sexo-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-angle-double-down" aria-hidden="true"></i
                                            ></span>
                                        <select name="txtsexo" id="txtsexo">
                                            <option value="-1" disabled selected>Seleccionar</option>
                                            <option value="0">Femenino</option>
                                            <option value="1">Masculino</option>
                                            <option value="0">Otro</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="grid3">
                                    <!-- Teléfono -->
                                    <div class="campo">
                                        <div class="label">
                                            No. Teléfono
                                            <b class="error" id="txtTelefono-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            style="padding-left: 42px; height: calc(100% + 6px)"
                                            type="tel"
                                            name="txtTelefono"
                                            id="txtTelefono"
                                            />
                                        <input type="text" id="telC" name="telC" class="hide" />
                                    </div>
                                    <!-- Edad -->
                                    <div class="campo">
                                        <div class="label">
                                            Edad
                                            <b class="error" id="txtEdad-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtEdad"
                                            id="txtEdad"
                                            placeholder="Edad"
                                            readonly
                                            />
                                    </div>

                                    <!-- Estatura -->
                                    <div class="campo">
                                        <div class="label">
                                            Estatura
                                            <b class="error" id="txtEstatura-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-calendar" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="number"
                                            name="txtEstatura"
                                            id="txtEstatura"
                                            placeholder="Estatura"
                                            pattern="^[0-9]+"
                                            min="1"
                                            step="0.01"
                                            />
                                        <div class="iconEstaturaM">
                                            <label for="cm">cm</label>
                                        </div>
                                    </div>
                                    <!-- </div>
                  
                              <div class="grid3"> -->
                                    <div class="titulo bloc3">Datos Emergencia</div>

                                    <!-- Nombres -->
                                    <div class="campo">
                                        <div class="label">
                                            Nombres
                                            <b class="error" id="txtnombresEmergencia-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtnombresEmergencia"
                                            id="txtnombresEmergencia"
                                            placeholder="Nombres"
                                            />
                                    </div>

                                    <!-- Apellidos -->
                                    <div class="campo">
                                        <div class="label">
                                            Apellidos
                                            <b class="error" id="txtapellidosEmergencia-error"></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtapellidosEmergencia"
                                            id="txtapellidosEmergencia"
                                            placeholder="Apellidos"
                                            />
                                    </div>

                                    <!-- Teléfono -->
                                    <div class="campo">
                                        <div class="label">
                                            No. Teléfono<b
                                                class="error"
                                                id="txttelefonoEmergencia-error"
                                                ></b>
                                        </div>
                                        <span id="icoError" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            style="padding-left: 42px; height: calc(100% + 6px)"
                                            type="text"
                                            name="txttelefonoEmergencia"
                                            id="txttelefonoEmergencia"
                                            />
                                        <input type="text" name="telE" id="telE" class="hide" />
                                    </div>
                                </div>
                                <div class="campo bloc3">
                                    <button
                                        class="sigPag"
                                        name="btn_cliente"
                                        id="btn_siguienteDT"
                                        value="Insertar_Cliente"
                                        type="button"
                                        disabled
                                        >
                                        Siguiente
                                    </button>
                                </div>
                            </div>

                            <!-- PAGINA NO 2 HISTORIAL CLINICO 1   -->
                            <div class="pagina">
                                <div class="gridpagina">
                                    <div class="titulo bloc3">
                                        <i class="fa fa-user-circle"></i>Historial Clínico - Parte 1
                                    </div>
                                    <%
                                        try {
                                            Conexion cn;
                                            Clientes cli = new Clientes();
                                            cn = new Conexion();
                                            cn.openConexion();
                                            int mas = 0;
                                            String query = cli.getSelectGeneral() + "<=" + 10;
                                            ResultSet consulta = cn.conexiondb.createStatement().executeQuery(query);
                                            while (consulta.next()) {
                                                mas++;

                                    %>

                                    <!--                                COLOCAR OTRO INPUT SOLO QUE ESTA VEZ SEA PARA EL ID DE LA PREGUNTA
                                    -->
                                    <!-- Pregunta 1 -->
                                    <div class="campo-pregunta">
                                        <div class="label">
                                            <%= consulta.getString("idPreguntaCliente") + ") " + consulta.getString("DescripcionPregunta")%>
                                            <b class="error" id="correo-error"></b>
                                        </div>
                                        <input type="radio"   name="radio<%=mas%>" value="si" id="si<%=mas%>"   />
                                        <label class="lblPregunta" for="si<%=mas%>" >Si</label>

                                        <input type="text" name="id_Pregunta<%=mas%>" value="<%=consulta.getString("idPreguntaCliente")%>" style="display:none;" >
                                        <input
                                            type="radio"
                                            name="radio<%=mas%>"
                                            id="no<%=mas%>"
                                            value="no"
                                            checked="checked"
                                            />
                                        <label class="lblPregunta" for="no<%=mas%>" >No</label>
                                    </div>
                                    <%        }
                                            cn.closedConexion();
                                        } catch (SQLException ex) {
                                            System.out.println("Error en: " + ex);
                                        }

                                    %>

                                    <div class="campo btns bloc3">
                                        <button class="volver-pag1 volver" type="button" >Atras</button>
                                        <button class="adelante-pag3 adelante" type="button">Siguiente</button>
                                    </div>
                                </div>
                            </div>

                            <!-- PAGINA NO 3 HISTORIAL CLINICO 2 -->
                            <div class="pagina pagHistorial">
                                <div class="gridpagina">
                                    <div class="titulo bloc3">
                                        <i class="fa fa-user-circle"></i> Historial Clínico - Parte
                                        2
                                    </div>
                                    <!-- Pregunta 11 -->

                                    <%                                    try {
                                            Conexion cn;
                                            Clientes cli = new Clientes();
                                            cn = new Conexion();
                                            cn.openConexion();
                                            int mas = 10;

                                            String query = cli.getSelectGeneral() + ">=" + 11;
                                            ResultSet consulta = cn.conexiondb.createStatement().executeQuery(query);
                                            while (consulta.next()) {
                                                mas++;
                                    %>

                                    <div class="campo-pregunta">
                                        <div class="label">
                                            <%= consulta.getString("idPreguntaCliente") + ") " + consulta.getString("DescripcionPregunta")%>
                                            <b class="error" id="numCel-error"></b>
                                        </div>
                                        <input type="radio"  name="radio<%=mas%>" value="si" id="si<%=mas%>" />
                                        <label class="lblPregunta" for="si<%=mas%>">Si</label>

                                        <input type="text" name="id_Pregunta<%=mas%>" value="<%=consulta.getString("idPreguntaCliente")%>" style="display:none;" >

                                        <input
                                            type="radio"
                                            name="radio<%=mas%>"
                                            id="no<%=mas%>"
                                            value="no"
                                            checked="checked"
                                            />
                                        <label class="lblPregunta" for="no<%=mas%>">No</label>
                                    </div>

                                    <%     }
                                            cn.closedConexion();
                                        } catch (SQLException ex) {
                                            System.out.println("Error en: " + ex);
                                        }

                                    %>
                                    <br />

                                    <!-- Observaciones -->
                                    <div class="campo-pregunta bloc3">
                                        <div class="label">
                                            Observaciones<b class="error" id="numCel-error"></b>
                                        </div>
                                        <textarea id="observaciones" name="observacion1" rows="3"></textarea>
                                    </div>

                                    <div class="campo btns bloc3">
                                        <button class="volver-pag2 volver" type="button">Atras</button>
                                        <button class="adelante-pag4 adelante" type="button">Siguiente</button>
                                    </div>
                                </div>
                            </div>

                            <!-- PAGINA NO 4 CONDICIONES DE ENERGIA -->
                            <div class="pagina">
                                <div class="gridpagina">
                                    <div class="titulo bloc3">
                                        <i class="fa fa-user-circle"></i> Condiciones de energía
                                    </div>

                                    <!-- Pregunta 21 -->
                                    <%                                    try {

                                            Conexion cn;
                                            Clientes cli = new Clientes();
                                            cn = new Conexion();
                                            cn.openConexion();
                                            int mas = 20;

                                            String query = cli.getSelectEspecifica();
                                            ResultSet consulta = cn.conexiondb.createStatement().executeQuery(query);
                                            while (consulta.next()) {
                                                mas++;
                                    %>

                                    <div class="campo-pregunta ocultarC">
                                        <div class="label">
                                            <%= consulta.getString("idPreguntaCliente") + ") " + consulta.getString("DescripcionPregunta")%>

                                            <b class="error" id="numCel-error"></b>
                                        </div>
                                        <input type="radio"  name="radio<%=mas%>" value="si" id="si<%=mas%>" />
                                        <label class="lblPregunta" for="si<%=mas%>">Si</label>
                                        <input type="text" name="id_Pregunta<%=mas%>" value="<%=consulta.getString("idPreguntaCliente")%>" style="display:none;" >

                                        <input
                                            type="radio"
                                            name="radio<%=mas%>"
                                            id="no<%=mas%>"
                                            value="no"
                                            checked="checked"
                                            />
                                        <label class="lblPregunta" for="no<%=mas%>">No</label>
                                    </div>
                                    <%                                          }
                                            cn.closedConexion();
                                        } catch (SQLException ex) {
                                            System.out.println("Error en: " + ex);
                                        }
                                    %>

                                    <div class="titulo bloc3">¿Cómo considera su nivel de?</div>
                                    <!-- NIVELES DE ENERGIA -->
                                    <div class="campo-estrella">
                                        <div class="label">
                                            1) ¿Cómo considera su salud?
                                            <b class="error" id="numCel-error"></b>
                                        </div>
                                        <div class="star-rating">
                                            <!-- <p>Puntuación</p> -->
                                            <button type="button" id="star" class="star-salud">&#9734;</button>
                                            <button type="button" id="star" class="star-salud">&#9734;</button>
                                            <button type="button" id="star" class="star-salud">&#9734;</button>
                                            <button type="button" id="star" class="star-salud">&#9734;</button>
                                            <button type="button" id="star" class="star-salud">&#9734;</button>
                                            <br />
                                            <input type="text" name="star1" class="rating-salud" value="Elegir" readonly/>
                                        </div>
                                    </div>
                                    <!-- Condición fisica -->
                                    <div class="campo-estrella">
                                        <div class="label">
                                            2) ¿Cómo considera su condición física?
                                            <b class="error" id="numCel-error"></b>
                                        </div>
                                        <div class="star-rating">
                                            <!-- <p>Puntuación</p> -->
                                            <button type="button" id="star" class="star-fisico">&#9734;</button>
                                            <button type="button" id="star" class="star-fisico">&#9734;</button>
                                            <button type="button"  id="star" class="star-fisico">&#9734;</button>
                                            <button type="button" id="star" class="star-fisico">&#9734;</button>
                                            <button type="button" id="star" class="star-fisico">&#9734;</button>
                                            <br />
                                            <input type="text" name="star2" class="rating-fisico" value="Elegir" readonly />
                                        </div>
                                    </div>

                                    <!-- Condición nutricional -->
                                    <div class="campo-estrella">
                                        <div class="label">
                                            3) ¿Cómo considera su condición nutricional?
                                            <b class="error" id="numCel-error"></b>
                                        </div>
                                        <div class="star-rating">
                                            <!-- <p>Puntuación</p> -->
                                            <button type="button" id="star" class="star-nutricion">&#9734;</button>
                                            <button type="button" id="star" class="star-nutricion">&#9734;</button>
                                            <button type="button" id="star" class="star-nutricion">&#9734;</button>
                                            <button type="button" id="star" class="star-nutricion">&#9734;</button>
                                            <button type="button" id="star" class="star-nutricion">&#9734;</button>
                                            <br />
                                            <input
                                                type="text"
                                                name="star3"
                                                class="rating-nutricion"
                                                value="Elegir"
                                                readonly
                                                />
                                        </div>
                                    </div>

                                    <!-- Condición Estres -->
                                    <div class="campo-estrella">
                                        <div class="label">
                                            4) ¿Cómo considera su condición de estrés?
                                            <b class="error" id="numCel-error"></b>
                                        </div>
                                        <div class="star-rating">
                                            <!-- <p>Puntuación</p> -->
                                            <button type="button" id="star" class="star-estres">&#9734;</button>
                                            <button type="button" id="star" class="star-estres">&#9734;</button>
                                            <button type="button" id="star" class="star-estres">&#9734;</button>
                                            <button type="button" id="star" class="star-estres">&#9734;</button>
                                            <button type="button" id="star" class="star-estres">&#9734;</button>
                                            <br />
                                            <input type="text" name="star4" class="rating-estres" value="Elegir" readonly />
                                        </div>
                                    </div>

                                    <div class="campo btns bloc3">
                                        <button class="volver-pag3 volver">Atras</button>
                                        <button
                                            type="submit"
                                            value="insertar_cliente"
                                            name="accion"
                                            class="Fin"
                                            >
                                            Finalizar
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <!-- FIN FORM -->
                        </form>
                        <!-- </div> -->
                    </div>
                </div>

                <!-- imagen -->
                <img class="image-container" src="img/g4.jpg" alt="" />
            </div>
        </dialog>

        <!-- FORMULARIO CONTROL CLIENTE  -->
        <dialog id="modal_control_cliente">
            <div class="contenido1">
                <div class="container-controlc">
                    <div class="info-containercc" id="InfoCont">
                        <!-- <div class="container-form"> -->
                        <div id="btn_cancelarCCliente" class="btn_cancelar">
                            <i class="fa-solid fa-circle-xmark ex"> </i>
                        </div>

                        <header>Control Cliente</header>
                        <br />
                        <div class="container_detalle">
                            <div class="detalle_cliente">
                                <div class="detalle-input">
                                    <label for="txt_id">Id: </label>
                                    <input
                                        type="text"
                                        name="txt_id"
                                        id="txt_id"
                                        readonly

                                        />
                                    <label for="txt_nombresssD">Nombre: </label>
                                    <input
                                        type="text"
                                        id="txt_nombreD"

                                        readonly
                                        />
                                </div>

                                <div class="detalle-input">
                                    <label for="txt_correoD">Correo electrónico: </label>
                                    <input
                                        type="text"
                                        id="txt_correoD"
                                        readonly
                                        />
                                </div>
                                <div class="detalle-input">
                                    <label for="txt_edadD">Edad: </label>
                                    <input
                                        type="text"
                                        id="txt_edadD"
                                        readonly
                                        />
                                    <label for="txt_alturaD">Altura: </label>
                                    <input
                                        type="text"
                                        id="txt_alturaD"
                                        readonly
                                        />
                                </div>
                            </div>
                            <div class="imageD">
                                <img class="image-containerD" src="img/fitness.png" alt="" />
                            </div>
                        </div>

                        <div class="form-princi">
                            <form id="form_control_cliente" action="srv_control_cliente" method="post" >
                                <input
                                    type="text"
                                    name="txt_idRegControl"
                                    id="txt_idRegControl"
                                    readonly
                                    class="hide"
                                    />
                                <!-- PAGINA NO 1 DATOS GENERALES -->
                                <div class="pagina movPagCC">
                                    <div class="gridpagina">
                                        <div class="titulo bloc3">
                                            <i class="fa-sharp fa-solid fa-calendar-days"></i>
                                            Control Peso
                                        </div>
                                        <!-- Peso -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Peso <b class="error" id="peso-error"></b>
                                            </div>
                                            <span id="icoErrorCC" class="input-icon"
                                                  ><i class="fa fa-krw" aria-hidden="true"></i
                                                ></span>
                                            <input
                                                type="number"
                                                name="txtPeso"
                                                id="txtPeso"
                                                placeholder="Peso"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />

                                            <div class="medida">
                                                <select name="Mpeso" id="txtmedida">
                                                    <option value="kg">Kg</option>
                                                    <option value="lb">lb</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Grasa campo -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Grasa Corporal<b class="error" id="grasa-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa fa-percent" aria-hidden="true"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtGrasa"
                                                name="txtGrasac"
                                                placeholder="Grasa corporal"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select id="txtmedida">
                                                    <option value="0">%</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Agua -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Porcentaje Agua <b class="error" id="agua-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa fa-percent" aria-hidden="true"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txt_aguaPorc"
                                                name="aguaP"
                                                placeholder="Porcentaje Agua"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select id="txtmedida">
                                                    <option value="0">%</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Masa Muscular-->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Masa Muscular<b class="error" id="masa-error"></b>
                                            </div>

                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa fa-percent" aria-hidden="true"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtMasaM"
                                                name="masaM"
                                                placeholder="Masa Muscular"
                                                pattern="^[0-9]+"
                                                min="1"
                                                />
                                            <div class="medida">
                                                <select id="txtmedida">
                                                    <option value="0">%</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Valor Físico-->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Valor Físico <b class="error" id="fisicoV-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa fa-percent" aria-hidden="true"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtFisicoV"
                                                name="valorF"
                                                placeholder="Valor Físico"
                                                pattern="^[0-9]+"
                                                min="1"
                                                />
                                            <div class="medida">
                                                <select id="txtmedida">
                                                    <option value="0">%</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Metabolismo Basal -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Metabolismo Basal<b
                                                    class="error"
                                                    id="metabolismoB-error"
                                                    ></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa fa-percent" aria-hidden="true"></i>
                                            </div>
                                            <input
                                                type="text"
                                                name="metabolismoB"
                                                id="txtMetabolismoB"
                                                placeholder="Metabolismo Basal"
                                                />
                                            <div class="medida">
                                                <select id="txtmedida">
                                                    <option value="0">%</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Edad Metabolica-->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Edad Metabolica <b class="error" id="edadMet-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa fa-percent" aria-hidden="true"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtEdadMet"
                                                name="edadM"
                                                placeholder="Edad Metabolica"
                                                pattern="^[0-9]+"
                                                min="1"
                                                />
                                            <div class="medida">
                                                <select id="txtmedida"></select>
                                            </div>
                                        </div>

                                        <!-- Masa Ósea-->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Masa Ósea<b class="error" id="masaOs-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa fa-percent" aria-hidden="true"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtMasaOsea"
                                                name="masaO"
                                                placeholder="Masa Ósea"
                                                pattern="^[0-9]+"
                                                min="1"
                                                />
                                            <div class="medida">
                                                <select name="Mmasao" id="txtmedida">
                                                    <option value="kg">kg</option>
                                                    <option value="lb">lb</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Grasa Visceral-->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Grasa Visceral <b class="error" id="grasaVi-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa fa-percent" aria-hidden="true"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtGrasaVi"
                                                name="grasaV"
                                                placeholder="Grasa Visceral"
                                                pattern="^[0-9]+"
                                                min="1"
                                                />
                                            <div class="medida">
                                                <select id="txtmedida">
                                                    <option value="0">%</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="campo-medida bloc3">
                                        <button
                                            class="sigPagCC"
                                            name="btn_clientecc"
                                            id="btn_clientecc"
                                       disabled
                                            >
                                            Siguiente
                                        </button>
                                    </div>
                                </div>

                                <!-- PAGINA NO 2 MEDIDAS CORPORALES -->
                                <div class="pagina">
                                    <div class="gridpagina">
                                        <div class="titulo bloc3">
                                            <i class="fa-sharp fa-solid fa-calendar-days"></i>
                                            Control Medidas Corporales
                                        </div>

                                        <!-- Pectoral -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Pectoral <b class="error" id="pectoral-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa-solid fa-ruler"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtPectoral"
                                                name="txtPextoral"
                                                placeholder="Pectoral"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select name="pectoralM" id="txtmedida">
                                                    <option value="cm">cm</option>
                                                    <option value="mm">mm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Dorsal -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Dorsal <b class="error" id="dorsal-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa-solid fa-ruler"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtDorsal"
                                                name="txtDorsal"
                                                placeholder="Dorsal"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select name="dorsalM" id="txtmedida">
                                                    <option value="cm">cm</option>
                                                    <option value="mm">mm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Biceps-->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Biceps
                                                <b class="error" id="biceps-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa-solid fa-ruler"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtBiceps"
                                                name="txtBiceps"
                                                placeholder="Biceps"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select name="bicepsM" id="txtmedida">
                                                    <option value="cm">cm</option>
                                                    <option value="mm">mm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Antebrazos-->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Antebrazos
                                                <b class="error" id="antebrazos-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa-solid fa-ruler"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtAntebrazos"
                                                name="txtAntebrazos"
                                                placeholder="Antebrazos"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select name="antebrazosM" id="txtmedida">
                                                    <option value="cm">cm</option>
                                                    <option value="mm">mm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Cintura -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Cintura <b class="error" id="cintura-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa-solid fa-ruler"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtCintura"
                                                name="txrCintura"
                                                placeholder="Cintura"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select name="cinturaM" id="txtmedida">
                                                    <option value="cm">cm</option>
                                                    <option value="mm">mm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Cadera-->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Cadera<b class="error" id="cadera-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa-solid fa-ruler"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtCadera"
                                                name="txtCadera"
                                                placeholder="Cintura"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select name="caderaM" id="txtmedida">
                                                    <option value="cm">cm</option>
                                                    <option value="mm">mm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Muslo -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Muslo<b class="error" id="muslo-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa-solid fa-ruler"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtMuslo"
                                                name="txtMuslo"
                                                placeholder="Muslo"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select name="musloM" id="txtmedida">
                                                    <option value="cm">cm</option>
                                                    <option value="mm">mm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- Pantorrilla -->
                                        <div class="campo-medida">
                                            <div class="label">
                                                Pantorrilla <b class="error" id="pantorrilla-error"></b>
                                            </div>
                                            <div id="icoErrorCC" class="input-icon">
                                                <i class="fa-solid fa-ruler"></i>
                                            </div>
                                            <input
                                                type="number"
                                                id="txtPantorrila"
                                                name="txtPantorrila"
                                                placeholder="Pantorrilla"
                                                pattern="^[0-9]+"
                                                min="1"
                                                step="0.01"
                                                />
                                            <div class="medida">
                                                <select name="pantM" id="txtmedida">
                                                    <option value="cm">cm</option>
                                                    <option value="mm">mm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="campo-medida btns bloc3">
                                            <button type="button" class="volver-pag1CC volver">
                                                Atras
                                            </button>
                                            <button type="submit" name="accion" id="accion" value="insertar_controlCliente" class="finCC">Aceptar</button>
                                        </div>
                                    </div>
                                </div>

                                <!-- FIN FORM -->
                            </form>
                            <!-- </div> -->
                        </div>
                    </div>

                    <img class="image-container" src="img/gym_dos.svg" alt="" />
                </div>
            </div>
        </dialog>


        <!--        FORMULARIO MODIFICAR CLIENTE-->
        <dialog id="ModalModificarCliente">

            <div class="containerModCliente">
                <div class="info-containerModCliente" id="InfoCont">
                    <div id="btn_cancelarModCliente" class="btn_cancelarModCliente">
                        <i class="fa-solid fa-circle-xmark ex"> </i>
                    </div>
                    <header>Modificar Cliente</header>
                    <div class="form-principalMC">
                        <form id="operacionesCliente" action="srv_operaciones_cliente" method="post">
                            <div class="paginaMC movPag">
                                <div class="gridpaginaMC">
                                    <div class="titulo bloc3">
                                        <i class="fa fa-user-circle"></i> Datos Generales
                                    </div>
                                    <!-- Nombres -->
                                    <div class="campo">
                                        <div class="label">
                                            Nombres <b class="error" id="txtnombresM-error"></b>
                                        </div>
                                        <input type="text" id="txtidCliente" name="txtidCliente" class="hideg" />

                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            id="txtnombresClienteM"
                                            name="txtnombresClienteM"
                                            placeholder="Nombres"
                                            />
                                    </div>

                                    <!-- Apellidos -->
                                    <div class="campo">
                                        <div class="label">
                                            Apellidos
                                            <b class="error" id="txtxapellidosM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtApellidosClienteM"
                                            id="txtxapellidosClienteM"
                                            placeholder="Apellidos"
                                            />
                                    </div>

                                    <!-- Correo Electrónico -->
                                    <div class="campo">
                                        <div class="label">
                                            Correo Electrónico
                                            <b class="error" id="txtcorreoM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-envelope" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtcorreoClienteM"
                                            id="txtcorreoClienteM"
                                            placeholder="Correo Electrónico"
                                            />
                                    </div>
                                    <!-- Fecha Nacimiento -->
                                    <div class="campo">
                                        <div class="label">
                                            Fecha Nacimiento
                                            <b class="error" id="txtfechaNacimientoM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-calendar" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="date"
                                            name="txtfechaNacimientoM"
                                            id="txtfechaNacimientoM"
                                            style="height: calc(100% + 4px)"
                                            />
                                    </div>

                                    <!-- NO DPI -->
                                    <div class="campo">
                                        <div class="label">
                                            CUI
                                            <b class="error" id="txtxcuicM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtxCUICM"
                                            id="txtxCUICM"
                                            placeholder="CUI"
                                            />
                                    </div>

                                    <!-- Sexo -->
                                    <div class="campo">
                                        <div class="label">
                                            Sexo<b class="error" id="sexoM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-angle-double-down" aria-hidden="true"></i
                                            ></span>
                                        <select name="txtsexoM" id="txtsexoM">
                                            <option value="-1" disabled selected>Seleccionar</option>
                                            <option value="0">Femenino</option>
                                            <option value="1">Masculino</option>
                                            <option value="0">Otro</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="grid3">
                                    <!-- Teléfono -->
                                    <div class="campo">
                                        <div class="label">
                                            No. Teléfono
                                            <b class="error" id="txtTelefonoM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            style="padding-left: 42px; height: calc(100% + 6px)"
                                            type="tel"
                                            name="txtTelefonoM"
                                            id="txtTelefonoM"
                                            />
                                        <input
                                            type="text"
                                            id="telC_mod"
                                            name="telC_mod"
                                            class="hide"
                                            />
                                    </div>
                                    <!-- Edad -->
                                    <div class="campo">
                                        <div class="label">
                                            Edad
                                            <b class="error" id="txtEdadM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtEdadM"
                                            id="txtEdadM"
                                            placeholder="Edad"
                                            readonly
                                            />
                                    </div>

                                    <!-- Estatura -->
                                    <div class="campo">
                                        <div class="label">
                                            Estatura
                                            <b class="error" id="txtEstaturaM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-calendar" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="number"
                                            name="txtEstaturaM"
                                            id="txtEstaturaM"
                                            placeholder="Estatura"
                                            pattern="^[0-9]+"
                                            min="1"
                                            step="0.01"
                                            />
                                        <div class="iconEstaturaM">
                                            <label for="cm">cm</label>
                                        </div>
                                    </div>
                                    <!-- </div>
                    
                          <div class="grid3"> -->
                                    <div class="titulo bloc3">Datos Emergencia</div>

                                    <!-- Nombres -->
                                    <div class="campo">
                                        <div class="label">
                                            Nombres
                                            <b class="error" id="txtnombresEM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtnombresEmergenciaM"
                                            id="txtnombresEmergenciaM"
                                            placeholder="Nombres"
                                            />
                                    </div>

                                    <!-- Apellidos -->
                                    <div class="campo">
                                        <div class="label">
                                            Apellidos
                                            <b class="error" id="txtapellidosEM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            type="text"
                                            name="txtapellidosEmergenciaM"
                                            id="txtapellidosEmergenciaM"
                                            placeholder="Apellidos"
                                            />
                                    </div>

                                    <!-- Teléfono -->
                                    <div class="campo">
                                        <div class="label">
                                            No. Teléfono<b class="error" id="txttelefonoEM-error"></b>
                                        </div>
                                        <span id="icoErrorMC" class="input-icon"
                                              ><i class="fa fa-text-height" aria-hidden="true"></i
                                            ></span>
                                        <input
                                            style="padding-left: 42px; height: calc(100% + 6px)"
                                            type="tel"
                                            name="txttelefonoEM"
                                            id="txttelefonoEM"
                                            />
                                        <input
                                            type="text"
                                            name="telEm_mod"
                                            id="telEm_mod"
                                            class="hide"
                                            />
                                    </div>
                                </div>
                                <div class="campo bloc3">
                                    <button
                                        class="finModificar"
                                        name="btn_modificarCliente"
                                        id="btn_modificarCliente"
                                        value="modificar_cliente"
                                        type="submit"
                                        >
                                        Aceptar
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </dialog>

        <!-- TABLE CLIENTES  -->
        <div class="table-cliente"> 
            <table id="datatable" >
                <thead>
                    <tr>
                        <th></th>
                        <th>No</th>
                        <th>idCliente</th>
                        <th>idRegistro</th>
                        <th>Nombres</th>
                        <th>Apellidos</th>
                        <th>CUI</th>
                        <th>Teléfono</th>
                        <th>Correo</th>
                        <th>Edad</th>
                        <th>Estatura</th>
                        <th>Fecha Inicio</th>

                    </tr>
                </thead>

                <tbody>
                    <%
                        Tablas tabla = new Tablas();
                        DefaultTableModel tbl_cliente = new DefaultTableModel();
                        tbl_cliente = tabla.table_cliente();
                        int NumRow = 0;
                        for (int i = 0; i < tbl_cliente.getRowCount(); i++) {
                            NumRow++;
                            out.println("<tr data-id=" + tbl_cliente.getValueAt(i, 0) + " >");
                            out.println("<td>" + NumRow + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 0) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 1) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 2) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 3) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 4) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 5) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 6) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 7) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 8) + "</td>");
                            out.println("<td>" + tbl_cliente.getValueAt(i, 9) + "</td>");
                            out.println("</tr>");
                        }

                    %>

                </tbody>
            </table>
        </div>
        <script src="js/main_table.js"></script>
        <!-- BOTONES PRINCIPALES DE LA TABLA -->
        <script>
            const dt = new DataTable("#datatable", [
                {
                    id: "button-agregar-cliente",
                    text: "Agregar Cliente",
                    icon: "fa-solid fa-user-plus",
                    action: function () {

                        const modalDatosGenerales = document.querySelector(
                                "#modal_datos_generales"
                                );
                        modalDatosGenerales.showModal();

                    },
                },

                {
                    id: "button-agregar-reporte",
                    text: "Agregar Reporte",
                    icon: "fa-solid fa-folder-plus",
                    action: function () {
                        const modal_detalle = document.querySelector(
                                "#modal_control_cliente"
                                );

                        modal_detalle.showModal();

                    },
                },

                {
                    id: "button-actualizar-cliente",
                    text: "Actualizar Cliente",
                    icon: "fa-solid fa-pen-to-square",
                    action: function () {
                        const modal_ModCliente = document.querySelector(
                                "#ModalModificarCliente"
                                );

                        modal_ModCliente.showModal();
                    },
                },

                {
                    id: "button-eliminar-cliente",
                    text: "Eliminar Cliente",
                    icon: "fa-solid fa-user-xmark",
                    action: function () {},
                },

                {
                    id: "button-ver-reporte",
                    text: "Ver Reporte",
                    icon: "fa-sharp fa-solid fa-folder-open",
                    action: function () {



                    },
                },
                {
                    id: "select-number-row",
                    text: "Mas de dos datos seleccionados",
                    icon: "fa-solid fa-xmark",
                    action: function () {},
                },
            ]);

            dt.parse();
        </script>
        <!--        <div class="container-prueba">
                    <div class="input-prueba">
                        <input type="text" id="idp" />
                    </div>
        
                    <div class="input-prueba">
                        <input type="text" id="nombrep" />
                    </div>
                    <div class="input-prueba">
                        <input type="text" id="apellidop" />
                    </div>
                    <div class="input-prueba">
                        <input type="text" id="cuip" />
                    </div>
                </div>-->

        <script src="library/momentLocale.min.js"></script>
        <script src="js/datos_generales_main.js"></script>
        <script src="js/control_cliente_main.js"></script>
        <script src="js/condicion_cliente_main.js"></script>
        <script src="js/enviar_datos.js"></script>
        <script src="js/modificar_cliente.js"></script>    
        <script src="js/operaciones_cliente.js"></script>

    </body>
</html>
