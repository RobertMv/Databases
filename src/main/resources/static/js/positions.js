let lastFoundProduct = null;
function add() {
    let request = new XMLHttpRequest();
    let code = document.getElementById("position_code").value;
    let name = document.getElementById("position_name").value;
    let salary = document.getElementById("position_salary").value;
    const url = "/positions/save";
    let product = JSON.stringify({
        "code": code,
        "name": name,
        "salary": salary
    });
    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200){
            alert("Позиция успешно сохранена");
        }
    });
    request.send(product);
}

function getAll() {
    let request = new XMLHttpRequest();
    const url = "/positions/all";
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Все данные успешно получены");
            document.getElementById("table").style.display="block";
            fillTable(request.response, "table");
        }
    });
    request.send();
}

function fillTable(jsonData, tableId) {
    let productsTable = document.getElementById(tableId);
    //clear table
    let rows = productsTable.getElementsByTagName('tr')
    for (let x = rows.length - 1; x > 0; x--) {
        productsTable.removeChild(rows[x]);
    }
    //fill table
    jsonData.forEach((row) => {
        const tr = document.createElement("tr");

        Object.values(row).forEach((cell) => {
            const td = document.createElement("td");
            td.textContent = cell;
            tr.appendChild(td);
        });
        productsTable.appendChild(tr);
    });
}

function selectorChanged(selected) {
    switch (selected.value){
        case "0":
            document.getElementById("find-by-name").style.display='none';
            document.getElementById("find-by-id").style.display='none';
            break;
        case "1":
            document.getElementById("find-by-name").style.display='none';
            document.getElementById("find-by-id").style.display='block';
            break;
        case "2":
            document.getElementById("find-by-name").style.display='block';
            document.getElementById("find-by-id").style.display='none';
            break;
    }
}

function find() {
    let url;
    let selected = document.getElementById("selector").value;
    switch (selected) {
        case "1":
            let id = document.getElementById("find-by-id").value;
            url = "/positions/id/" + id;
            break;
        case "2":
            let name = document.getElementById("find-by-name").value;
            url = "/positions/name/" + name;
            break;
    }
    let request = new XMLHttpRequest();
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            document.getElementById("found-table").style.display="block";
            let jsonData = [];
            jsonData.push(request.response);
            console.log(jsonData);
            lastFoundProduct = request.response;
            fillTable(jsonData, "found-table");
        }
    });
    request.send();
}

function update() {
    let request = new XMLHttpRequest();
    let code = document.getElementById("position_code_update").value;
    let name = document.getElementById("position_name_update").value;
    let salary = document.getElementById("position_salary_update").value;
    const url = "/positions/save";
    let product = JSON.stringify({
        "id": lastFoundProduct.id,
        "code": code,
        "name": name,
        "salary": salary
    });
    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200){
            alert("Позиция успешно обновлёна");
        }
    });
    request.send(product);
}
function readyForUpdate() {
    if (lastFoundProduct == null){
        alert("Сначала найдите позицию!");
    } else {
        document.getElementById("position_code_update").style.display="block";
        document.getElementById("position_name_update").style.display="block";
        document.getElementById("position_salary_update").style.display="block";
        document.getElementById("position_update").style.display="block";
        document.getElementById("position_code_update").value = lastFoundProduct.code;
        document.getElementById("position_name_update").value = lastFoundProduct.name;
        document.getElementById("position_salary_update").value = lastFoundProduct.salary;
    }
}

function deleteById () {
    let request = new XMLHttpRequest();
    const url = "/positions/delete-id/" + document.getElementById("delete_product_by_id").value;
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Позиция успешно удалена");
            document.getElementById("delete_product_by_id").value = "";
        }
    });
    request.send();
}

function deleteAll () {
    let request = new XMLHttpRequest();
    const url = "/products/all";
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Все позиции успешно удалены");
        }
    });
    request.send();
}