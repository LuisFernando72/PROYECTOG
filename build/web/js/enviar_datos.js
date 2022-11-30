/* global Swal */
const modalDatosGenerales = document.querySelector("#modal_datos_generales");
const modal_detalleC = document.querySelector("#modal_control_cliente");

let idRegistro = document.querySelector("#txt_id");
let NombreCliente = document.querySelector("#txt_nombreD");
let correoCliente = document.querySelector("#txt_correoD");
let edadCliente = document.querySelector("#txt_edadD");
let alturaC = document.querySelector("#txt_alturaD");

var frm = $('#form_datosg');


frm.submit(function (e) {
    e.preventDefault();

//    alert(frm.attr('method'));
//    alert(frm.attr('action'));
    alert(frm.serialize());

    $.ajax({
        type: frm.attr('method'),
        url: frm.attr('action') + "?accion=insertar_cliente",
        data: frm.serialize(),

        success: function (data) {
            console.log('Submission was successful.');
            alert(data);

            let error = data;
            if (error == 0 || error === null) {
                modalDatosGenerales.close();
                Swal.fire({
                    title: "Error",
                    text: "Vaya, algo ha ocurrido mal",
                    icon: "error",
                    confirmButtonColor: "#ff004c",
                }).then(function () {
                    location.reload();
                });
            } else {

                modalDatosGenerales.close();
                Swal.fire({
                    title: "Excelente!!",
                    text: "Cliente creado correctamente",
                    icon: "success",
                    confirmButtonColor: "#008d49",
                }).then(function () {
                    traerDatos(error);
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

//modal_detalleC.addEventListener("click", TraerDatosControl()());
//function TraerDatosControl() {
//    $.ajax({
//        url: frm.attr('action') + "?accion=traer_registro&id_cliente=" + idCliente,
//        type: "GET",
//        dataType: "JSON",
//        success: function (data) {
//
//            idRegistro.value = data.cod_registro;
//            NombreCliente.value = data.nomC;
//            correoCliente.value = data.email;
//            edadCliente.value = data.edad;
//            alturaC.value = data.altura;
//            size_input(idRegistro);
//            size_input(NombreCliente);
//            size_input(correoCliente);
//            size_input(edadCliente);
//            size_input(alturaC);
//            modal_detalleC.showModal();
//        }
//
//    });
//}

function traerDatos(idCliente) {

    $.ajax({
        url: frm.attr('action') + "?accion=traer_registro&id_cliente=" + idCliente,
        type: "GET",
        dataType: "JSON",
        success: function (data) {

            idRegistro.value = data.cod_registro;
            NombreCliente.value = data.nomC;
            correoCliente.value = data.email;
            edadCliente.value = data.edad;
            alturaC.value = data.altura;
            size_input(idRegistro);
            size_input(NombreCliente);
            size_input(correoCliente);
            size_input(edadCliente);
            size_input(alturaC);
            modal_detalleC.showModal();
        }

    });
}

const size_input = (input_name) => {
    var texto = input_name;
    var txt = texto.value;
    var tamano = txt.length;
    tamano *= 8.1; //el valor multiplicativo debe cambiarse dependiendo del tamaño de la fuente
    texto.style.width = tamano + "px";
};

