
const txtnombresClienteM = document.querySelector("#txtnombresClienteM");
const txtxapellidosClienteM = document.querySelector("#txtxapellidosClienteM");
const txtcorreoClienteM = document.querySelector("#txtcorreoClienteM");
const txtfechaNacimientoM = document.querySelector("#txtfechaNacimientoM");
const txtxCUICM = document.querySelector("#txtxCUICM");
const txtsexoM = document.querySelector("#txtsexoM");
const txtTelefonoM = document.querySelector("#txtTelefonoM");
const txtEdadM = document.querySelector("#txtEdadM");
const txtEstaturaM = document.querySelector("#txtEstaturaM");
const txtnombresEmergenciaM = document.querySelector("#txtnombresEmergenciaM");
const txtapellidosEmergenciaM = document.querySelector(
        "#txtapellidosEmergenciaM"
        );

const txttelefonoEM = document.querySelector("#txttelefonoEM");
const btn_act_cliente = document.querySelector("#button-actualizar-cliente");


const btnModCliente = document.querySelector("#btn_modificarCliente");
//ERRORES ICONOS
const error_icoM = document.querySelectorAll("#icoErrorMC");
const txtnombresM_error = document.querySelector("#txtnombresM-error");
const txtxapellidosM_error = document.querySelector("#txtxapellidosM-error");
const txtcorreoM_error = document.querySelector("#txtcorreoM-error");
const txtfechaNacimientoM_error = document.querySelector(
        "#txtfechaNacimientoM-error"
        );
const txtxcuicM_error = document.querySelector("#txtxcuicM-error");
const sexoM_error = document.querySelector("#sexoM-error");
const txtTelefonoM_error = document.querySelector("#txtTelefonoM-error");
const txtEdadM_error = document.querySelector("#txtEdadM-error");
const txtEstaturaM_error = document.querySelector("#txtEstaturaM-error");
const txtnombresEM_error = document.querySelector("#txtnombresEM-error");
const txtapellidosEM_error = document.querySelector("#txtapellidosEM-error");
const txttelefonoEM_error = document.querySelector("#txttelefonoEM-error");

//TELEFONO SE TRASLADA LA EXTENSION AL INPUT
const telC_mod = document.querySelector("#telC_mod");
const telEm_mod = document.querySelector("#telEm_mod");

const btn_cancelarModCliente = document.querySelector(
        "#btn_cancelarModCliente"
        );

const phoneInputM = window.intlTelInput(txtTelefonoM, {
    preferredCountries: ["gt", "mx", "sv", "hn", "us"],
    utilsScript:
            "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
});

const phoneInputEmergencyM = window.intlTelInput(txttelefonoEM, {
    preferredCountries: ["gt", "mx", "sv", "hn", "us"],
    utilsScript:
            "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
});

const abrir_form_ModCliente = document.querySelector("#ModalModificarCliente");


function calcularEdad(txtfechaNacimientoM) {
    var hoy = new Date();
    var cumpleanos = new Date(txtfechaNacimientoM);
    let edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }
    return edad;
}

let errores_ModificarCliente = {
    txtnombresClienteM: false,
    txtxapellidosClienteM: false,
    txtcorreoClienteM: false,
    txtfechaNacimientoM: false,
    txtxCUICM: false,
    txtsexoM: false,
    txtTelefonoM: false,
    txtEstaturaM:false,
    txtnombresEmergenciaM: false,
    txtapellidosEmergenciaM: false,
    txttelefonoEM:false
};



window.addEventListener("load", function () {
    txtfechaNacimientoM.addEventListener("change", function () {
        if (this.value) {
            txtEdadM.value = `${calcularEdad(this.value)}`;
            txtEdadM.style.fontWeight = "bold";
            txtEdadM.style.borderColor = "#00ff00";
        }
    });
});


const validatefieldM = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp(
            "^([A-ZÀ-ÅÇ-ÖÙ-Ý][a-zà-åç-öù-ÿ]+(?:[-' ][A-ZÀ-ÅÇ-ÖÙ-Ý][a-zà-åç-öù-ÿ]+)*)$"
            );

    if (fieldValue.trim().length === 0) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_ModificarCliente[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_ModificarCliente[field_id] = true;
        error.innerHTML = "*Inicial Mayúscula, solo se permiten letras";
    } else {
        error_icoM[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        errores_ModificarCliente[field_id] = false;
        error.innerText = "";
    }
    validarModificarCliente();
};

const validateEmailBM = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp(
            "^([A-Z|a-z|0-9](.|_){0,1})+[A-Z|a-z|0-9]@([A-Z|a-z|0-9])+((.){0,1}[A-Z|a-z|0-9]){2}.[a-z]{2,3}$"
            );

    if (fieldValue.trim().length === 0) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#FF0000";
        errores_ModificarCliente[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#FF0000";
        errores_ModificarCliente[field_id] = true;
        error.innerHTML = "*No cumple como un correo válido";
    } else {
        error_icoM[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        errores_ModificarCliente[field_id] = false;
        error.innerText = "";
    }
    if (fieldValue === "Ni") {
        error_icoM[contador].classList.remove("invalid-background-ico");
        errores_ModificarCliente[field_id] = false;
        field.style.borderColor = "#00ff00";
        error.innerText = "";
    }
    validarModificarCliente();
};

const validatefieldWhiteM = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    if (fieldValue.trim().length === 0) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        error.innerHTML = "*Por favor llenar el campo";
        errores_ModificarCliente[field_id] = true;
    } else {
        error_icoM[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        error.innerText = "";
        errores_ModificarCliente[field_id] = false;
    }

    validarModificarCliente();
};

const validatefieldCUIM = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp("^[1-9]{1}[0-9]{3}\\s?[0-9]{5}\\s?[0-9]{4}$");

    if (fieldValue.trim().length === 0) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_ModificarCliente[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_ModificarCliente[field_id] = true;
        error.innerHTML = "*Inicial Mayúscula, solo se permiten letras";
    } else {
        error_icoM[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        errores_ModificarCliente[field_id] = false;
        error.innerText = "";
    }
    validarModificarCliente();
};

const validateSelectM = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    if (fieldValue === -1) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        errores_ModificarCliente[field_id] = true;
        error.innerHTML = "*Por favor seleccionar sexo";
    } else {
        error_icoM[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        errores_ModificarCliente[field_id] = false;
        error.innerText = "";
    }
    validarModificarCliente();
};

const validatefieldNumberFloatM = (contador, error, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const regex = new RegExp("[0-9]+([.|,][0-9]+)?");

    if (fieldValue.trim().length === 0) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#FF0000";
        errores_ModificarCliente[field_id] = true;
        error.innerHTML = "*Por favor llenar el campo";
    } else if (!regex.test(fieldValue)) {
        error_icoM[contador].classList.add("invalid-background-ico");

        errores_ModificarCliente[field_id] = true;
        field.style.borderColor = "#FF0000";
        error.innerHTML = "Números enteros y decimales";
    } else {
        error_icoM[contador].classList.remove("invalid-background-ico");
        errores_ModificarCliente[field_id] = false;
        field.style.borderColor = "#00ff00";
        error.innerText = "";
    }
    validarModificarCliente();
};

const validatePhoneNumberIM = (contador, TelI, error, phone, e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const field_id = e.target.id;
    const phone2 = phone.getNumber();
    if (phone.isValidNumber()) {
        error_icoM[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        error.innerHTML = "";
        errores_ModificarCliente[field_id] = false;
    } else if (fieldValue.trim().length === 0) {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        error.innerHTML = "*Por favor llenar el campo";
        errores_ModificarCliente[field_id] = true;
    } else {
        error_icoM[contador].classList.add("invalid-background-ico");
        field.style.borderColor = "#ff004c";
        error.innerText = "Número Invalido";
        errores_ModificarCliente[field_id] = true;
    }

    if (fieldValue === "Ni") {
        error_icoM[contador].classList.remove("invalid-background-ico");
        field.style.borderColor = "#00ff00";
        error.innerHTML = "";
        errores_ModificarCliente[field_id] = false;
    }

    validarModificarCliente();
    TelI.value = phone2;
};

txtnombresClienteM.addEventListener("blur", (e) =>
    validatefieldM(0, txtnombresM_error, e)
);

txtnombresClienteM.addEventListener("input", (e) =>
    validatefieldM(0, txtnombresM_error, e)
);

txtxapellidosClienteM.addEventListener("blur", (e) =>
    validatefieldM(1, txtxapellidosM_error, e)
);

txtxapellidosClienteM.addEventListener("input", (e) =>
    validatefieldM(1, txtxapellidosM_error, e)
);

txtcorreoClienteM.addEventListener("blur", (e) =>
    validateEmailBM(2, txtcorreoM_error, e)
);

txtcorreoClienteM.addEventListener("input", (e) =>
    validateEmailBM(2, txtcorreoM_error, e)
);

txtfechaNacimientoM.addEventListener("click", (e) =>
    validatefieldWhiteM(3, txtfechaNacimientoM_error, e)
);
txtfechaNacimientoM.addEventListener("change", (e) =>
    validatefieldWhiteM(3, txtfechaNacimientoM_error, e)
);

txtxCUICM.addEventListener("input", (e) =>
    validatefieldCUIM(4, txtxcuicM_error, e)
);
txtxCUICM.addEventListener("blur", (e) =>
    validatefieldCUIM(4, txtxcuicM_error, e)
);
txtsexoM.addEventListener("click", (e) => validateSelectM(5, sexoM_error, e));
txtsexoM.addEventListener("change", (e) => validateSelectM(5, sexoM_error, e));

txtTelefonoM.addEventListener("blur", (e) =>
    validatePhoneNumberIM(6, telC_mod, txtTelefonoM_error, phoneInputM, e)
);

txtTelefonoM.addEventListener("input", (e) =>
    validatePhoneNumberIM(6, telC_mod, txtTelefonoM_error, phoneInputM, e)
);

txtEstaturaM.addEventListener("blur", (e) =>
    validatefieldNumberFloatM(8, txtEstaturaM_error, e)
);
txtEstaturaM.addEventListener("input", (e) =>
    validatefieldNumberFloatM(8, txtEstaturaM_error, e)
);

txtnombresEmergenciaM.addEventListener("blur", (e) =>
    validatefieldM(9, txtnombresEM_error, e)
);
txtnombresEmergenciaM.addEventListener("input", (e) =>
    validatefieldM(9, txtnombresEM_error, e)
);

txtapellidosEmergenciaM.addEventListener("blur", (e) =>
    validatefieldM(10, txtapellidosEM_error, e)
);

txtapellidosEmergenciaM.addEventListener("input", (e) =>
    validatefieldM(10, txtapellidosEM_error, e)
);

txttelefonoEM.addEventListener("blur", (e) =>
    validatePhoneNumberIM(
            11,
            telEm_mod,
            txttelefonoEM_error,
            phoneInputEmergencyM,
            e
            )
);
txttelefonoEM.addEventListener("input", (e) =>
    validatePhoneNumberIM(
            11,
            telEm_mod,
            txttelefonoEM_error,
            phoneInputEmergencyM,
            e
            )
);

validarModificarCliente = () => {
    if (
            errores_ModificarCliente.txtnombresClienteM ||
            errores_ModificarCliente.txtxapellidosClienteM ||
            errores_ModificarCliente.txtcorreoClienteM ||
            errores_ModificarCliente.txtfechaNacimientoM ||
            errores_ModificarCliente.txtxCUICM ||
            errores_ModificarCliente.txtsexoM ||
            errores_ModificarCliente.txtTelefonoM ||
            errores_ModificarCliente.txtEstaturaM ||
            errores_ModificarCliente.txtnombresEmergenciaM ||
            errores_ModificarCliente.txtapellidosEmergenciaM ||
            errores_ModificarCliente.txttelefonoEM
            ) {
        btnModCliente.toggleAttribute("disabled", true);
    } else {
        btnModCliente.toggleAttribute("disabled", false);
    }
};


btn_cancelarModCliente.addEventListener("click", (e) => {
    e.preventDefault();
    Swal.fire({
        target: document.querySelector("#ModalModificarCliente"),
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
            LimpiarTodoModCliente();
            abrir_form_ModCliente.close();
        }
    });
});

function LimpiarTodoModCliente() {
    LimpiarInputModCliente();
    LimpiarColorModCliente();
    LimpiarColorIconM();
}

function LimpiarInputModCliente() {
    txtnombresClienteM.value = "";
    txtxapellidosClienteM.value = "";
    txtcorreoClienteM.value = "";
    txtfechaNacimientoM.value = "";
    txtxCUICM.value = "";
    txtsexoM.value = -1;
    txtTelefonoM.value = "";
    txtEdadM.value = "";
    txtEstaturaM.value = "";
    txtnombresEmergenciaM.value = "";
    txtapellidosEmergenciaM.value = "";
    txttelefonoEM.value = "";

    txtnombresM_error.innerHTML = "";
    txtxapellidosM_error.innerHTML = "";
    txtcorreoM_error.innerHTML = "";
    txtfechaNacimientoM_error.innerHTML = "";
    txtxcuicM_error.innerHTML = "";
    sexoM_error.innerHTML = "";
    txtTelefonoM_error.innerHTML = "";
    txtEdadM_error.innerHTML = "";
    txtEstaturaM_error.innerHTML = "";
    txtnombresEM_error.innerHTML = "";
    txtapellidosEM_error.innerHTML = "";
    txttelefonoEM_error.innerHTML = "";
}

function LimpiarColorModCliente() {
    txtnombresClienteM.style.borderColor = "#515151";
    txtxapellidosClienteM.style.borderColor = "#515151";
    txtcorreoClienteM.style.borderColor = "#515151";
    txtfechaNacimientoM.style.borderColor = "#515151";
    txtxCUICM.style.borderColor = "#515151";
    txtsexoM.style.borderColor = "#515151";
    txtTelefonoM.style.borderColor = "#515151";
    txtEdadM.style.borderColor = "#515151";
    txtEstaturaM.style.borderColor = "#515151";
    txtnombresEmergenciaM.style.borderColor = "#515151";
    txtapellidosEmergenciaM.style.borderColor = "#515151";
    txttelefonoEM.style.borderColor = "#515151";
}

function LimpiarColorIconM() {
    for (let i = 0; i < error_icoM.length; i++) {
        error_icoM[i].classList.replace(
                "invalid-background-ico",
                "border-colorIcon"
                );
    }
}

var frm_modificar = $('#operacionesCliente');



btn_act_cliente.addEventListener("click", (e) => llenarCampo());
 let idClienteM;
 
const getvalueId_cliente = () => {
 idClienteM = document.querySelector("#txtidCliente").value;
    alert(idClienteM);
};


function llenarCampo() {
    getvalueId_cliente();

    const nombresClienteM = document.querySelector("#txtnombresClienteM");
    const apellidosClienteM = document.querySelector("#txtxapellidosClienteM");
    const correoClienteM = document.querySelector("#txtcorreoClienteM");
    const fechaNacimientoM = document.querySelector("#txtfechaNacimientoM");
    const CUICM = document.querySelector("#txtxCUICM");
    const sexoM = document.querySelector("#txtsexoM");
    const TelefonoM = document.querySelector("#txtTelefonoM");
    const EdadM = document.querySelector("#txtEdadM");
    const EstaturaM = document.querySelector("#txtEstaturaM");
    const nombresEmergenciaM = document.querySelector("#txtnombresEmergenciaM");
    const apellidosEmergenciaM = document.querySelector(
            "#txtapellidosEmergenciaM");

    const telefonoEM = document.querySelector("#txttelefonoEM");

    $.ajax({
        url: frm_modificar.attr('action') + "?accion=datos_cliente&idCliente=" + idClienteM,
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            nombresClienteM.value = data.nombres_c;
            apellidosClienteM.value = data.apellidos_c;
            correoClienteM.value = data.correo_c;
            fechaNacimientoM.value = data.fn_cliente;
            CUICM.value = data.cui_c;
            sexoM.value = data.genero_c;
            TelefonoM.value = data.telefonon_c;
            EdadM.value = data.edad_c;
            EstaturaM.value = data.estatura_c;
            nombresEmergenciaM.value = data.nombres_emergencia;
            apellidosEmergenciaM.value = data.apellidos_emergencia;
            telefonoEM.value = data.telefono_emergencia;

        }

    });
}