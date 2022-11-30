/* global Swal */

var frmoperaciones = $('#operacionesCliente');
var frm_controlCliente = $('#form_control_cliente');

const Abrir_modaL_modificarc = document.querySelector("#ModalModificarCliente");
const Abrir_modal_controlc = document.querySelector("#modal_control_cliente");

//ENVIA LOS DATOS AL SERVLET PARA MODIFICAR AL CLIENTE
frmoperaciones.submit(function (e) {
    e.preventDefault();

    $.ajax({
        type: frmoperaciones.attr('method'),
        url: frmoperaciones.attr('action') + "?accion=modificar_cliente",
        data: frmoperaciones.serialize(),

        success: function (data) {
            console.log('Submission was successful.');
            //   alert(data);

            let error = data;
            if (error == 0 || error === null) {
                Abrir_modaL_modificarc.close();
                Swal.fire({
                    title: "Error",
                    text: "Vaya, algo ha ocurrido mal",
                    icon: "error",
                    confirmButtonColor: "#ff004c",
                }).then(function () {
                    //location.reload();
                });
            } else {

                Abrir_modaL_modificarc.close();
                Swal.fire({
                    title: "Excelente!!",
                    text: "Nuevas medidas agregadas",
                    icon: "success",
                    confirmButtonColor: "#008d49",
                }).then(function () {
                    location.reload();
                });
                //        

            }
        },
        error: function (data) {
            alert('An error occurred.');
            alert(data);
        },
    });

});
let cod_Cliente;

const getvalueIdcliente = () => {
    cod_Cliente = document.querySelector("#txtidCliente").value;
};

$(document).ready(function () {

    $("#button-eliminar-cliente").click(function (e) {
        e.preventDefault();
        getvalueIdcliente();
        Swal.fire({
            title: "¿Desea eliminar al cliente?",
            text: "Se eliminara el registro completo del cliente",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#0072ff",
            cancelButtonColor: "#D2122E",
            cancelButtonText: "Cancelar",
            confirmButtonText: "Sí, deseo eliminar"
        }).then((result) => {
            if (result.value) {
                eliminarRegistro();
            }
        });
    });


    function eliminarRegistro() {
        $.ajax({
            type: frmoperaciones.attr('method'),
            url: frmoperaciones.attr('action') + "?accion=eliminar_registro",
            data: frmoperaciones.serialize(),

            success: function (data) {
                console.log(data);
                let error = data;
                if (error == 0 || error === null) {

                    Swal.fire({
                        title: "Error",
                        text: "Vaya, algo ha ocurrido mal",
                        icon: "error",
                        confirmButtonColor: "#ff004c",
                    }).then(function () {
                        //  location.reload();
                    });
                } else {

                    Swal.fire({
                        title: "Excelente!!",
                        text: "Cliente eliminado correctamente",
                        icon: "success",
                        confirmButtonColor: "#008d49",
                    }).then(function () {
                        location.reload();
                    });
                }
            }
        });
    }
});

//ENVIA LOS DATOS AL SERVLET srv_control_cliente 
frm_controlCliente.submit(function (e) {
    e.preventDefault();

    $.ajax({
        type: frm_controlCliente.attr('method'),
        url: frm_controlCliente.attr('action') + "?accion=insertar_controlCliente",
        data: frm_controlCliente.serialize(),

        success: function (data) {
            console.log('Submission was successful.');
            // alert(data);

            let error = data;
            if (error == 0 || error === null) {
                Abrir_modal_controlc.close();
                Swal.fire({
                    title: "Error",
                    text: "Vaya, algo ha ocurrido mal",
                    icon: "error",
                    confirmButtonColor: "#ff004c",
                }).then(function () {
                    //location.reload();
                });
            } else {

                Abrir_modal_controlc.close();
                Swal.fire({
                    title: "Excelente!!",
                    text: "Cliente modificado correctamente",
                    icon: "success",
                    confirmButtonColor: "#008d49",
                }).then(function () {
                    location.reload();
                });
                //        

            }
        },
        error: function (data) {
            alert('An error occurred.');
            alert(data);
        },
    });

});