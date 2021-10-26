let lastFound = null;
document.addEventListener("DOMContentLoaded", loadProducts);

function loadProducts() {
    let request = new XMLHttpRequest();
    const url = "/products/all";
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            fillOptions(request.response, "products");
            fillOptions(request.response, "products_update");
        }
    });
    request.send();
}

function fillOptions(jsonData, id) {
    let productsSelector = document.getElementById(id);
    productsSelector.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Выберите продукты зажимая \'Ctrl\'';

    productsSelector.add(defaultOption);

    let option;

    for (let i = 0; i < jsonData.length; i++){
        option = document.createElement('option');
        option.text = jsonData[i].name;
        option.value = jsonData[i].name;
        productsSelector.add(option);
    }
}
function add() {
    let request = new XMLHttpRequest();
    let about = document.getElementById("about").value;
    let name = document.getElementById("name").value;
    let seasonal = document.getElementById("seasonal").checked;
    let products = [];
    for (let option of document.getElementById("products").options){
        if (option.selected){
            products.push(option.value);
        }
    }
    const url = "/dishes/save";
    let dish = JSON.stringify({
        "about": about,
        "name": name,
        "seasonal": seasonal,
        "requiredProducts": products
    });

    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200){
            alert("Блюдо успешно сохранено");
        }
    });
    console.log(dish);
    console.log(products);
    request.send(dish);
}

function getAll() {
    let request = new XMLHttpRequest();
    const url = "/dishes/all";
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
            url = "/dishes/id/" + id;
            break;
        case "2":
            let name = document.getElementById("find-by-name").value;
            url = "/dishes/name/" + name;
            break;
        case "3":
            url = "/dishes/seasonal";
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
            lastFound = request.response;
            fillTable(jsonData, "found-table");
        }
    });
    request.send();
}

function update() {
    let request = new XMLHttpRequest();
    let about = document.getElementById("about").value;
    let name = document.getElementById("name").value;
    let seasonal = document.getElementById("seasonal").checked;
    let products = [];
    for (let option of document.getElementById("products").options){
        if (option.selected){
            products.push(option.value);
        }
    }
    const url = "/dishes/save";
    let product = JSON.stringify({
        "id": lastFound.id,
        "about": about,
        "name": name,
        "seasonal": seasonal,
        "requiredProducts": products
    });
    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200){
            alert("Блюдо успешно обновлёно");
        }
    });
    request.send(product);
}
function readyForUpdate() {
    if (lastFound == null){
        alert("Сначала найдите блюдо!");
    } else {
        document.getElementById("about_update").style.display="block";
        document.getElementById("name_update").style.display="block";
        document.getElementById("seasonal_update").style.display="block";
        document.getElementById("products_update").style.display="block";
        document.getElementById("checkboxLabel").style.display="block";
        document.getElementById("update").style.display="block";
        document.getElementById("about_update").value = lastFound.about;
        document.getElementById("name_update").value = lastFound.name;
        document.getElementById("seasonal_update").checked = lastFound.seasonal;
    }
}

function deleteById () {
    let request = new XMLHttpRequest();
    const url = "/dishes/delete-id/" + document.getElementById("delete_by_id").value;
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Блюдо успешно удалено");
            document.getElementById("delete_by_id").value = "";
        }
    });
    request.send();
}

function deleteAll () {
    let request = new XMLHttpRequest();
    const url = "/dishes/delete-all";
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Все блюда успешно удалены");
        }
    });
    request.send();
}