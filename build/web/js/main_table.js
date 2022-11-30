class DataTable {
    element;
    headers;
    items;
    CopyItems;
    selected;
    pagination;
    numberOfEntries;
    headerButtons;
    constructor(selector, headerButtons) {
        this.element = document.querySelector(selector);
        this.headers = [];
        this.items = [];
        this.pagination = {
            total: 0,
            noItemsPerPage: 0,
            noPages: 0,
            actua: 0,
            pointer: 0,
            diff: 0,
            lastPageBeforeDots: 0,
            noButtonsBeforeDots: 4,
        };
        this.selected = [];
        this.numberOfEntries = 5;
        this.headerButtons = headerButtons;
    }

    parse() {
        const headers = [...this.element.querySelector("thead tr").children];
        const trs = [...this.element.querySelector("tbody").children];
        headers.forEach((element) => {
            this.headers.push(element.textContent);
        });
        // console.log(this.headers);  SELECCIONAOD TODOS LOS DATOS

        trs.forEach((tr) => {
            const cells = [...tr.children];
            const item = {
                id: this.generateUUUID(),
                values: [],
            };
            cells.forEach((cell) => {
                if (cell.children.length > 0) {
                    const statusElement = [...cell.children][0];
                    const status = statusElement.getAttribute("class");
                    if (status != null) {
                        item.values.push(`<span class='${status}'></span>`);
                    }
                } else {
                    item.values.push(cell.textContent);
                }
            });
            this.items.push(item);
        });
        // console.log(this.items) Items;
        this.makeTable();
    }

    makeTable() {
        this.CopyItems = [...this.items];
        this.initPagination(this.items.length, this.numberOfEntries);
        const container = document.createElement("div");
        container.id = this.element.id;
        this.element.innerHTML = "";
        this.element.replaceWith(container);
        this.element = container;
        this.createHTML();
        this.renderHeaders();
        this.renderRows();
        this.renderPagesButtons();
        this.renderHeaderButtons();
        this.renderSearch();
        this.renderSelectEntries();
        // this.sizeInput();
    }

    initPagination(total, entries) {
        this.pagination.total = total;
        this.pagination.noItemsPerPage = entries;
        this.pagination.noPages = Math.ceil(
                this.pagination.total / this.pagination.noItemsPerPage
                );
        this.pagination.actua = 1;
        this.pagination.pointer = 0;
        this.pagination.diff =
                this.pagination.noItemsPerPage -
                (this.pagination.total % this.pagination.noItemsPerPage);
    }

    generateUUUID() {
        return (Date.now() * Math.floor(Math.random() * 100000)).toString();
    }
//
    createHTML() {
        this.element.innerHTML = `
  <div class="datatable-container">
  <h1 class="heading">Nuestros Clientes</h1>
      
  <div class="header-tools">
  
  <div class="tools">
  <ul class="header-buttons-container">  </ul>
  </div>
  
  <div class="search">
  <span id="icon-search"><i class="fa-brands fa-searchengin"></i></span>
  <input type="text" placeholder="Buscar Cliente"  class="search-input">
  </div>
  </div>
  <div class="table-section">
  <table class="datatable">
  <thead>
  <tr>
  </tr>
  </thead>
  <tbody>
  </tbody>
  
  </table>
  </div>
  <div class="footer-tools" >
  <div class="list-items">
  Ver
  <select name="n-entries" id="n-enties" class="n-entries">
  </select>
  datos
  </div>
  
  <div class="pages"></div>
  
  
  </div>
  
  
  </div>
  `;
    }

    renderHeaders() {
        this.element.querySelector("thead tr").innerHTML = "";
        this.headers.forEach((header) => {
            this.element.querySelector("thead tr").innerHTML += `<th>${header}</th>`;
        });
    }

    renderRows() {
        this.element.querySelector("tbody").innerHTML = "";
        let i = 0;
        const {pointer, total} = this.pagination;
        const limit = this.pagination.actua * this.pagination.noItemsPerPage;
        for (i = pointer; i < limit; i++) {
            if (i == total) {
                break;
            }

            const {id, values} = this.CopyItems[i];
            const checked = this.isChecked(id);
            let data = "";
            data += `
      <td class="table-checkbox">
  <input type="checkbox" class="datatable-checkbox" data-id = "${id}" ${
                    checked ? "checked" : ""
                    } />
  
      </td> `;
            values.forEach((cell) => {
                data += `<td>${cell}</td>`;
            });
            this.element.querySelector("tbody").innerHTML += `<tr>${data}</tr>`;
            //LISTENER PARA EL CHECKBOX
            document.querySelectorAll(".datatable-checkbox").forEach((checkbox) => {
                checkbox.addEventListener("click", (e) => {
                    const element = e.target;
//                    console.log(element);
                    const id = element.getAttribute("data-id");
                    if (element.checked) {
                        const item = this.getItem(id);
                        this.selected.push(item);
                        if (this.selected.length > 1) {
                            element.checked = false;
                            this.removeSelected(id);
                        }

                    } else {
                        this.removeSelected(id);
                   //     this.cleaning_input();
                    }

                    const sizefila = document.querySelector("#select-number-row");
                    const btn_agregar_cliente = document.querySelector(
                            "#button-agregar-cliente"
                            );
                    const btn_agregar_reporte = document.querySelector(
                            "#button-agregar-reporte"
                            );
                    const btn_actualizar_cliente = document.querySelector(
                            "#button-actualizar-cliente"
                            );
                    const btn_eliminar_cliente = document.querySelector(
                            "#button-eliminar-cliente"
                            );
                    const btn_ver_reporte = document.querySelector("#button-ver-reporte");
                    if (this.selected.length == 0) {
                        btn_agregar_cliente.disabled = false;
                        btn_agregar_reporte.disabled = true;
                        btn_actualizar_cliente.disabled = true;
                        btn_ver_reporte.disabled = true;
                        btn_eliminar_cliente.disabled = true;
                        sizefila.style.display = "none";
                    } else {
                        if (this.selected.length == 1) {

                            sizefila.style.display = "none";
                            btn_agregar_cliente.disabled = true;
                            btn_agregar_reporte.disabled = false;
                            btn_actualizar_cliente.disabled = false;
                            btn_ver_reporte.disabled = false;
                            btn_eliminar_cliente.disabled = false;
                            this.viewData();
                        } else {
                            sizefila.style.display = "block";
                            sizefila.style.background = "#f5043c";
                            sizefila.style.color = "#ffffff";
                            btn_agregar_cliente.disabled = true;
                            btn_agregar_reporte.disabled = true;
                            btn_actualizar_cliente.disabled = true;
                            btn_ver_reporte.disabled = true;
                        }
                    }


                    console.log(this.selected);
                });
            });
        }
    }

    isChecked(id) {
        const items = this.selected;
        let res = false;
        if (items.length == 0) {
            return false;
        }

        items.forEach((item) => {
            if (item.id == id) {
                res = true;
            }
        });
        return res;
    }

    getItem(id) {
        const res = this.items.filter((item) => item.id == id);
        if (res.length == 0) {
            return null; //verificar aqui
        }

        return res[0];
    }

    removeSelected(id) {
        const res = this.selected.filter((item) => item.id !== id);
        this.selected = [...res];
    }

    renderPagesButtons() {
        const pagesContainer = this.element.querySelector(".pages");
        let pages = "";
        const buttonsToShow = this.pagination.noButtonsBeforeDots;
        const actualIndex = this.pagination.actua;
        let limI = Math.max(actualIndex - 2, 1);
        let limS = Math.min(actualIndex + 2, this.pagination.noPages);
        const missinButtons = buttonsToShow - (limS - limI);
        if (Math.max(limI - missinButtons, 0)) {
            limI = limI - missinButtons;
        } else if (
                Math.min(limS + missinButtons, this.pagination.noPages) !=
                this.pagination.noPages
                ) {
            limS = limS + missinButtons;
        }

        if (limS < this.pagination.noPages - 2) {
            pages += this.getIteratedButtons(limI, limS);
            pages += "<li>...</li>";
            pages += this.getIteratedButtons(
                    this.pagination.noPages - 1,
                    this.pagination.noPages
                    );
        } else {
            pages += this.getIteratedButtons(limI, this.pagination.noPages);
        }
        pagesContainer.innerHTML = `<ul>${pages}</ul>`;
        this.element.querySelectorAll(".pages li button").forEach((button) => {
            button.addEventListener("click", (e) => {
                this.pagination.actua = parseInt(e.target.getAttribute("data-page"));
                this.pagination.pointer =
                        this.pagination.actua * this.pagination.noItemsPerPage -
                        this.pagination.noItemsPerPage;
                this.renderRows();
                this.renderPagesButtons();
            });
        });
    }

    getIteratedButtons(start, end) {
        let res = "";
        for (let i = start; i <= end; i++) {
            if (i == this.pagination.actua) {
                res += `<li><span class="active">${i}</span></li>`;
            } else {
                res += `<li><button data-page="${i}"> ${i}</button></li>`;
            }
        }
        return res;
    }

    renderHeaderButtons() {
        let html = "";
        const buttonsContainer = this.element.querySelector(
                ".header-buttons-container"
                );
        const headerButtons = this.headerButtons;
        headerButtons.forEach((button) => {
            html += `<li><button id="${button.id}" title="${button.text}" > <i class="${button.icon} material-icons"></i></button></li>`;
            // console.log(button.id);
        });
        buttonsContainer.innerHTML = html;
        headerButtons.forEach((button) => {
            document
                    .querySelector("#" + button.id)
                    .addEventListener("click", button.action);
        });
    }
    renderSearch() {
        this.element
                .querySelector(".search-input")
                .addEventListener("input", (e) => {
                    const query = e.target.value.trim().toLowerCase();
                    if (query == "") {
                        this.CopyItems = [...this.items];
                        this.initPagination(this.CopyItems.length, this.numberOfEntries);
                        this.renderRows();
                        this.renderPagesButtons();
                        return;
                    }

                    this.search(query);
                    this.initPagination(this.CopyItems.length, this.numberOfEntries);
                    this.renderRows();
                    this.renderPagesButtons();
                });
    }

    search(query) {
        let res = [];
        this.CopyItems = [...this.items];
        for (let i = 0; i < this.CopyItems.length; i++) {
            const {id, values} = this.CopyItems[i];
            const row = values;
            for (let j = 0; j < row.length; j++) {
                const cell = row[j];
                if (cell.toLowerCase().indexOf(query) >= 0) {
                    res.push(this.CopyItems[i]);
                    break;
                }
            }
        }
        this.CopyItems = [...res];
    }

    renderSelectEntries() {
        const select = this.element.querySelector(".n-entries");
        const html = [10, 15].reduce((acc, item) => {
            return (acc += `<option value="${item}" ${
                    this.numberOfEntries === item ? "selected" : ""
                    }>${item}</option>`);
        }, "");
        select.innerHTML = html;
        this.element.querySelector(".n-entries").addEventListener("change", (e) => {
            const numberOfEntries = parseInt(e.target.value);
            this.numberOfEntries = numberOfEntries;
            this.initPagination(this.CopyItems.length, this.numberOfEntries);
            this.renderRows();
            this.renderPagesButtons();
            this.renderSearch();
        });
    }

    getSelected() {
        return this.selected;
    }

    add(item) {
        const row = {
            id: this.generateUUUID,
            values: [],
        };
        const status = `<span  class="${item[0]}"></span>`;
        item.shift();
        row.values = [status, ...items];
        this.items = [row, ...this.items];
        this.makeTable();
    }

//ver12
    viewData() {
        let idRegistro1 = document.querySelector("#txt_id");
        let NombreCliente1 = document.querySelector("#txt_nombreD");
        let correoCliente1 = document.querySelector("#txt_correoD");
        let edadCliente1 = document.querySelector("#txt_edadD");
        let alturaC1 = document.querySelector("#txt_alturaD");
        const IdRegistro = document.querySelector("#txtidCliente");
        const buttonControlC = document.querySelector("#button-agregar-reporte");
        const btn_act_cliente = document.querySelector("#button-actualizar-cliente");
        const txt_idRegControl = document.querySelector("#txt_idRegControl");
        if (this.selected.length == 1) {
            let idCliente = this.selected[0].values[1];
            IdRegistro.value = idCliente;

            buttonControlC.addEventListener("click", () => {
                txt_idRegControl.value = this.selected[0].values[2];
                idRegistro1.value = this.selected[0].values[1];
                NombreCliente1.value = this.selected[0].values[3] + " " + this.selected[0].values[4];
                correoCliente1.value = this.selected[0].values[7];
                edadCliente1.value = this.selected[0].values[8];
                alturaC1.value = this.selected[0].values[9] + " cm";
                size_input(idRegistro1);
                size_input(NombreCliente1);
                size_input(correoCliente1);
                size_input(edadCliente1);
                size_input(alturaC1);

            });
        }
    }

//    cleaning_input() {
//        const idp = document.querySelector("#idp");
//        const nombrep = document.querySelector("#nombrep");
//        const apellidop = document.querySelector("#apellidop");
//        const cuip = document.querySelector("#cuip");
//        idp.value = "";
//        nombrep.value = "";
//        apellidop.value = "";
//        cuip.value = "";
//    }
//    

    sizeInput(input_name) {
        //    const size_input = (input_name) => {
        var texto = input_name;
        var txt = texto.value;
        var tamano = txt.length;
        tamano *= 8.1; //el valor multiplicativo debe cambiarse dependiendo del tamaño de la fuente
        texto.style.width = tamano + "px";
    }

}


window.addEventListener("load", function () {
    const sizefila = document.querySelector("#select-number-row");
    const btn_agregar_cliente = document.querySelector(
            "#button-agregar-cliente"
            );
    const btn_agregar_reporte = document.querySelector(
            "#button-agregar-reporte"
            );
    const btn_actualizar_cliente = document.querySelector(
            "#button-actualizar-cliente"
            );
    const btn_eliminar_cliente = document.querySelector(
            "#button-eliminar-cliente"
            );
    const btn_ver_reporte = document.querySelector("#button-ver-reporte");
    btn_agregar_cliente.disabled = false;
    btn_agregar_reporte.disabled = true;
    btn_actualizar_cliente.disabled = true;
    btn_ver_reporte.disabled = true;
    btn_eliminar_cliente.disabled = true;
    sizefila.style.display = "none";
});


