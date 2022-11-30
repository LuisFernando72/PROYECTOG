/* global moment, Swal */
//CLIENTE INPUT
const D_nombre = document.querySelector("#txt_nombreD");
const D_email = document.querySelector("#txt_correoD");
const D_edad = document.querySelector("#txt_edadD");
const D_altura = document.querySelector("#txt_alturaD");
const D_id = document.querySelector("#txt_id");
const icon_errorCC = document.querySelectorAll("#icoErrorCC");
//CONTROL PESO
const txtPeso = document.querySelector("#txtPeso");
const erro_peso = document.querySelector("#peso-error");
const txtGrasa = document.querySelector("#txtGrasa");
const error_grasaC = document.querySelector("#grasa-error");
const txt_aguaPorc = document.querySelector("#txt_aguaPorc");
const error_agua = document.querySelector("#agua-error");
const txtMasaM = document.querySelector("#txtMasaM");
const error_masa = document.querySelector("#masa-error");
const txtFisicoV = document.querySelector("#txtFisicoV");
const error_Vfisico = document.querySelector("#fisicoV-error");
const txtMetabolismoB = document.querySelector("#txtMetabolismoB");
const errorMetabolismoB = document.querySelector("#metabolismoB-error");
const txtEdadMet = document.querySelector("#txtEdadMet");
const error_edadM = document.querySelector("#edadMet-error");
const txtMasaOsea = document.querySelector("#txtMasaOsea");
const error_masaOs = document.querySelector("#masaOs-error");
const txtGrasaVi = document.querySelector("#txtGrasaVi");
const error_visceral = document.querySelector("#grasaVi-error");

//CONTROL MEDIDAS CORPORALES
const pectoral = document.querySelector("#txtPectoral");
const erro_pectoral = document.querySelector("#pectoral-error");
const dorsal = document.querySelector("#txtDorsal");
const error_dorsal = document.querySelector("#dorsal-error");
const biceps = document.querySelector("#txtBiceps");
const error_biceps = document.querySelector("#biceps-error");
const antebrazos = document.querySelector("#txtAntebrazos");
const error_ant = document.querySelector("#antebrazos-error");
const cintura = document.querySelector("#txtCintura");
const erro_cintura = document.querySelector("#cintura-error");
const cadera = document.querySelector("#txtCadera");
const error_cadera = document.querySelector("#cadera-error");
const muslo = document.querySelector("#txtMuslo");
const error_muslo = document.querySelector("#muslo-error");
const pantorrilla = document.querySelector("#txtPantorrila");
const error_pant = document.querySelector("#pantorrilla-error");
//alert("iconos: "+ icon_error.length);
//ABRIENDO EL MODAL

const medidaB = document.querySelectorAll("#txtmedida");

//BOTONES
const movPagCC = document.querySelector(".movPagCC");
const btn_adelante2CC = document.querySelector(".sigPagCC");

const btn_atras1cc = document.querySelector(".volver-pag1CC");
const bt_modal_detalle = document.querySelector("#modal_control_cliente");

const siguiente = document.querySelector("#btn_clientecc");
const btn_cancelarCC = document.querySelector("#btn_cancelarCCliente");

let errores = {
    txtPeso: false,
    txtGrasa: false,
    txt_aguaPorc: false,
    txtMasaM: false,
    txtFisicoV: false,
    txtMetabolismoB: false,
    txtEdadMet: false,
    txtMasaOsea: false,
    txtGrasaVi: false
};

const validatefieldNumberFloatCC = (contador, med, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp("[0-9]+([.|,][0-9]+)?");

    if (fieldValue.trim().length === 0) {
        icon_errorCC[contador].classList.add("invalid-backgroundcc");
        field.style.borderColor = "#ff004c";
        medidaB[med].classList.remove("color-borde-aceptcc");
        medidaB[med].classList.add("color-borde-errorcc");
        errores[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        icon_errorCC[contador].classList.add("invalid-backgroundcc");
        medidaB[med].classList.remove("#ff004c");
        medidaB[med].classList.add("color-borde-errorcc");
        errores[field_id] = true;
        field.style.borderColor = "#ff004c";
        error.innerHTML = "Números enteros y decimales";
    } else {
        icon_errorCC[contador].classList.remove("invalid-backgroundcc");
        medidaB[med].classList.remove("color-borde-errorcc");
        medidaB[med].classList.add("color-borde-aceptcc");
        errores[field_id] = false;
        field.style.borderColor = "#00ff00";
        error.innerText = "";
    }
    validarControl();
};

btn_adelante2CC.addEventListener("click", function (e) {
    e.preventDefault();
    movPagCC.style.marginLeft = "-16.67%";
});


// BOTONES HACIA ATRAS
btn_atras1cc.addEventListener("click", function (e) {
    e.preventDefault();
    movPagCC.style.marginLeft = "0%";
});



// function limpiar() {
//   txtnombresCliente.value = "";
//   txtxapellidoCliente.value = "";
//   txtcorreoCliente.value = "";
//   txtfechaNacimiento.value = "";
//   txtxCUIC.value = "";
//   txtTelefono.value = "";
//   txtsexo.value = -1;
//   edad.value = "";
//   fechaInicio.value = "";
//   txtnombresEmergencia.value = "";
//   txtapellidosEmergencia.value = "";
//   txttelefonoEmergencia.value = "";
// }



//window.addEventListener("load", function () {
//  size_input(D_nombre);
//  size_input(D_email);
//  size_input(D_edad);
//  size_input(D_altura);
//  size_input(D_id);
//
//});
//// QUINTO FORMULARIO NUTRICION
//

txtPeso.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(0, 0, erro_peso, e)
);

txtPeso.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(0, 0, erro_peso, e)
);

txtGrasa.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(1, 1, error_grasaC, e)
);
txtGrasa.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(1, 1, error_grasaC, e)
);
txt_aguaPorc.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(2, 2, error_agua, e)
);
txt_aguaPorc.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(2, 2, error_agua, e)
);
txtMasaM.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(3, 3, error_masa, e)
);
txtMasaM.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(3, 3, error_masa, e)
);
txtFisicoV.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(4, 4, error_Vfisico, e)
);
txtFisicoV.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(4, 4, error_Vfisico, e)
);
txtMetabolismoB.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(5, 5, errorMetabolismoB, e)
);
txtMetabolismoB.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(5, 5, errorMetabolismoB, e)
);
txtEdadMet.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(6, 6, error_edadM, e)
);
txtEdadMet.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(6, 6, error_edadM, e)
);
txtMasaOsea.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(7, 7, error_masaOs, e)
);
txtMasaOsea.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(7, 7, error_masaOs, e)
);
txtGrasaVi.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(8, 8, error_visceral, e)
);
txtGrasaVi.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(8, 8, error_visceral, e)
);

pectoral.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(9, 9, erro_pectoral, e)
);
pectoral.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(9, 9, erro_pectoral, e)
);
dorsal.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(10, 10, error_dorsal, e)
);
dorsal.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(10, 10, error_dorsal, e)
);
biceps.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(11, 11, error_biceps, e)
);
biceps.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(11, 11, error_biceps, e)
);

antebrazos.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(12, 12, error_ant, e)
);
antebrazos.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(12, 12, error_ant, e)
);

cintura.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(13, 13, erro_cintura, e)
);
cintura.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(13, 13, erro_cintura, e)
);
cadera.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(14, 14, error_cadera, e)
);
cadera.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(14, 14, error_cadera, e)
);
muslo.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(15, 15, error_muslo, e)
);
muslo.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(15, 15, error_muslo, e)
);

pantorrilla.addEventListener("blur", (e) =>
    validatefieldNumberFloatCC(16, 16, error_pant, e)
);
pantorrilla.addEventListener("input", (e) =>
    validatefieldNumberFloatCC(16, 16, error_pant, e)
);

validarControl = () => {
    if (
            errores.txtPeso ||
            errores.txtGrasa ||
            errores.txt_aguaPorc ||
            errores.txtMasaM ||
            errores.txtFisicoV ||
            errores.txtMetabolismoB ||
            errores.txtEdadMet ||
            errores.txtMasaOsea ||
            errores.txtGrasaVi
            ) {
        siguiente.toggleAttribute("disabled", true);
    } else {
        siguiente.toggleAttribute("disabled", false);
    }
};


btn_cancelarCC.addEventListener("click", () => {
    //  e.preventDefault();
    console.log("hacieron click en cancelar");
    Swal.fire({
        target: document.querySelector("#modal_control_cliente"),
        title: "¿Desea cancelar el registro?",
        text: "Se perderán los datos, si es que ya llenó algunos.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#0072ff",
        cancelButtonColor: "#D2122E",
        cancelButtonText: "Cancelar",
        confirmButtonText: "Sí, deseo salir",
    }).then((result) => {
        if (result.value) {
            // location.reload();
            //   limpiar();
            LimpiarFormCC();
            bt_modal_detalle.close();
        }
    });
});
function LimpiarFormCC() {
    LimpiarInputD();
    LimpiarColorD();
    coloriconD();
    LimpiarBordeMed();
}


function LimpiarInputD() {
    txtPeso.value = "";
    erro_peso.innerHTML = "";
    txtGrasa.value = "";
    error_grasaC.innerHTML = "";
    txt_aguaPorc.value = "";
    error_agua.innerHTML = "";
    txtMasaM.value = "";
    error_masa.innerHTML = "";
    txtFisicoV.value = "";
    error_Vfisico.innerHTML = "";
    txtMetabolismoB.value = "";
    errorMetabolismoB.innerHTML = "";
    txtEdadMet.value = "";
    error_edadM.innerHTML = "";
    txtMasaOsea.value = "";
    error_masaOs.innerHTML = "";
    txtGrasaVi.value = "";
    error_visceral.innerHTML = "";

    pectoral.value = "";
    erro_pectoral.innerHTML = "";
    dorsal.value = "";
    error_dorsal.innerHTML = "";
    biceps.value = "";
    error_biceps.innerHTML = "";
    antebrazos.value = "";
    error_ant.innerHTML = "";
    cintura.value = "";
    erro_cintura.innerHTML = "";
    cadera.value = "";
    error_cadera.innerHTML = "";
    muslo.value = "";
    error_muslo.innerHTML = "";
    pantorrilla.value = "";
    error_pant.innerHTML = "";

    D_nombre.value = "";
    D_email.value = "";
    D_edad.value = "";
    D_altura.value = "";
    D_id.value = "";
    icon_errorCC.value = "";
}

function LimpiarColorD() {
    txtPeso.style.borderColor = "#515151";
    txtGrasa.style.borderColor = "#515151";
    txt_aguaPorc.style.borderColor = "#515151";
    txtMasaM.style.borderColor = "#515151";
    txtFisicoV.style.borderColor = "#515151";
    txtMetabolismoB.style.borderColor = "#515151";
    txtEdadMet.style.borderColor = "#515151";
    txtMasaOsea.style.borderColor = "#515151";
    txtGrasaVi.style.borderColor = "#515151";
    pectoral.style.borderColor = "#515151";
    dorsal.style.borderColor = "#515151";
    biceps.style.borderColor = "#515151";
    antebrazos.style.borderColor = "#515151";
    cintura.style.borderColor = "#515151";
    cadera.style.borderColor = "#515151";
    muslo.style.borderColor = "#515151";
    pantorrilla.style.borderColor = "#515151";
}

function coloriconD() {
    for (let i = 0; i < icon_errorCC.length; i++) {
        icon_errorCC[i].classList.replace(
                "invalid-backgroundcc",
                "colorIconNormal"
                );
    }
}

function LimpiarBordeMed() {
    for (let a = 0; a < medidaB.length; a++) {
        medidaB[a].classList.replace("color-borde-aceptcc", "colorBorderAcept");
        medidaB[a].classList.replace("color-borde-errorcc", "colorBorderAcept");
    }
}
