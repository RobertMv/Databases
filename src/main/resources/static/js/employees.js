let lastFound = null;
document.addEventListener("DOMContentLoaded", loadPositions);

function loadPositions() {
    let request = new XMLHttpRequest();
    const url = "/positions/all";
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            fillOptions(request.response, "position");
            fillOptions(request.response, "position_update");
        }
    });
    request.send();
}

function fillOptions(jsonData, id) {
    let positionsSelector = document.getElementById(id);
    positionsSelector.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Выберите должность';

    positionsSelector.add(defaultOption);

    let option;

    for (let i = 0; i < jsonData.length; i++){
        option = document.createElement('option');
        option.text = jsonData[i].name;
        option.value = jsonData[i].name;
        positionsSelector.add(option);
    }
}
function add() {
    let request = new XMLHttpRequest();
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let patronymic = document.getElementById("patronymic").value;
    let birthDate = document.getElementById("birthDate").value;
    let sex = document.getElementById("sex").value;
    let passport = document.getElementById("passport").value;
    let employmentDate = document.getElementById("employmentDate").value;
    let phone = document.getElementById("phone").value;
    let position = document.getElementById("position").value;
    let hours = document.getElementById("hours").value;

    const url = "/employees/save";
    let employee = JSON.stringify({
        "name": name,
        "surname": surname,
        "patronymic": patronymic,
        "birthDate": birthDate,
        "sex": sex,
        "passport": passport,
        "employmentDate": employmentDate,
        "phone": phone,
        "position": position,
        "hours": hours
    });

    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200){
            alert("Работник успешно сохранен");
        }
    });
    console.log(employee);
    request.send(employee);
}

function getAll() {
    let request = new XMLHttpRequest();
    const url = "/employees/all";
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
            document.getElementById("find-fio-table").style.display='none';
            document.getElementById("find-by-id").style.display='none';
            document.getElementById("find-by-passport").style.display='none';
            break;
        case "1":
            document.getElementById("find-fio-table").style.display='none';
            document.getElementById("find-by-id").style.display='block';
            document.getElementById("find-by-passport").style.display='none';
            break;
        case "2":
            document.getElementById("find-fio-table").style.display='block';
            document.getElementById("find-by-id").style.display='none';
            document.getElementById("find-by-passport").style.display='none';
            break;
        case "3":
            document.getElementById("find-fio-table").style.display='none';
            document.getElementById("find-by-id").style.display='none';
            document.getElementById("find-by-passport").style.display='block';
    }
}

function find() {
    let url;
    let selected = document.getElementById("selector").value;
    switch (selected) {
        case "1":
            let id = document.getElementById("find-by-id").value;
            url = "/employees/id/" + id;
            break;
        case "2":
            let name = document.getElementById("find-by-name").value;
            let surname = document.getElementById("find-by-surname").value;
            let patronymic = document.getElementById("find-by-patronymic").value;
            url = "/employees/fio/" + name + "-" + surname + "-" + patronymic;
            break;
        case "3":
            let passport = document.getElementById("find-by-passport").value;
            url = "/employees/passport/" + passport;
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
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let patronymic = document.getElementById("patronymic").value;
    let birthDate = document.getElementById("birthDate").value;
    let sex = document.getElementById("sex").value;
    let passport = document.getElementById("passport").value;
    let employmentDate = document.getElementById("employmentDate").value;
    let phone = document.getElementById("phone").value;
    let position = document.getElementById("position").value;
    let hours = document.getElementById("hours").value;

    const url = "/employees/save";
    let employee = JSON.stringify({
        "name": name,
        "surname": surname,
        "patronymic": patronymic,
        "birthDate": birthDate,
        "sex": sex,
        "passport": passport,
        "employmentDate": employmentDate,
        "phone": phone,
        "position": position,
        "hours": hours
    });
    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200){
            alert("Блюдо успешно обновлёно");
        }
    });
    request.send(employee);
}
function readyForUpdate() {
    if (lastFound == null){
        alert("Сначала найдите работника!");
    } else {
        document.getElementById("update_form").style.display="block";

        document.getElementById("name_update").value = lastFound.name;
        document.getElementById("surname_update").value = lastFound.surname;
        document.getElementById("patronymic_update").value = lastFound.patronymic;
        document.getElementById("birthDate_update").value = lastFound.birthDate;
        document.getElementById("sex_update").value = lastFound.sex;
        document.getElementById("passport_update").value = lastFound.passport;
        document.getElementById("employmentDate_update").value = lastFound.employmentDate;
        document.getElementById("phone_update").value = lastFound.phone;
        document.getElementById("position_update").value = lastFound.position;
        document.getElementById("hours_update").value = lastFound.hours;
    }
}

function deleteById () {
    let request = new XMLHttpRequest();
    const url = "/employees/delete-id/" + document.getElementById("delete_by_id").value;
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Работник успешно удалён");
            document.getElementById("delete_by_id").value = "";
        }
    });
    request.send();
}

function deleteAll () {
    let request = new XMLHttpRequest();
    const url = "/employees/delete-all";
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Все работники успешно удалены");
        }
    });
    request.send();
}