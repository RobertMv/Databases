let lastFoundProduct = null;

function add() {
    let request = new XMLHttpRequest();
    let name = document.getElementById("product_name").value;
    let about = document.getElementById("product_about").value;
    const url = "/products/save";
    let product = JSON.stringify({
        "name": name,
        "about": about
    });
    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Продукт успешно добавлен");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send(product);
}

function getAll() {
    let request = new XMLHttpRequest();
    const url = "/products/all";
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Все данные успешно получены");
            document.getElementById("products-table").style.display = "block";
            fillTable(request.response, "products-table");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
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
    switch (selected.value) {
        case "0":
            document.getElementById("find-by-name").style.display = 'none';
            document.getElementById("find-by-id").style.display = 'none';
            break;
        case "1":
            document.getElementById("find-by-name").style.display = 'none';
            document.getElementById("find-by-id").style.display = 'block';
            break;
        case "2":
            document.getElementById("find-by-name").style.display = 'block';
            document.getElementById("find-by-id").style.display = 'none';
            break;
    }
}

function find() {
    let url;
    let selected = document.getElementById("product-selector").value;
    switch (selected) {
        case "1":
            let id = document.getElementById("find-by-id").value;
            url = "/products/id/" + id;
            break;
        case "2":
            let name = document.getElementById("find-by-name").value;
            url = "/products/name/" + name;
            break;
    }
    let request = new XMLHttpRequest();
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            document.getElementById("products-found-table").style.display = "block";
            let jsonData = [];
            jsonData.push(request.response);
            console.log(jsonData);
            lastFoundProduct = request.response;
            fillTable(jsonData, "products-found-table");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send();
}

function update() {
    let request = new XMLHttpRequest();
    let name = document.getElementById("product_name_update").value;
    let about = document.getElementById("product_about_update").value;
    const url = "/products/save";
    let product = JSON.stringify({
        "id": lastFoundProduct.id,
        "name": name,
        "about": about
    });
    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Продукт успешно обновлён");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send(product);
}

function readyForUpdate() {
    if (lastFoundProduct == null) {
        alert("Сначала найдите продукт!");
    } else {
        document.getElementById("product_name_update").style.display = "block";
        document.getElementById("product_about_update").style.display = "block";
        document.getElementById("product_update").style.display = "block";
        document.getElementById("product_name_update").value = lastFoundProduct.name;
        document.getElementById("product_about_update").value = lastFoundProduct.about;
    }
}

function deleteById() {
    let request = new XMLHttpRequest();
    const url = "/products/delete-id/" + document.getElementById("delete_product_by_id").value;
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Продукт успешно удалён");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send();
}

function deleteAll() {
    let request = new XMLHttpRequest();
    const url = "/products/delete-all";
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Все продукты успешно удалены");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send();
}