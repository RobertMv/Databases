let lastFound = null;
document.addEventListener("DOMContentLoaded", loadEmployees);

function loadEmployees() {
    let request = new XMLHttpRequest();
    const url = "/employees/all";
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            console.log(request.response);
            fillOptions(request.response, "employees");
            fillOptions(request.response, "employees_update");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send();
}

function fillOptions(jsonData, id) {
    let employeesSelector = document.getElementById(id);
    employeesSelector.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Выберите работников удерживая зажатой клавишу \'Ctrl\'';

    employeesSelector.add(defaultOption);

    let option;

    for (let i = 0; i < jsonData.length; i++) {
        option = document.createElement('option');
        option.text = jsonData[i].id + "  " + jsonData[i].name + "  " + jsonData[i].surname + "  " + jsonData[i].position;
        option.value = jsonData[i].id + "  " + jsonData[i].name + "  " + jsonData[i].surname + "  " + jsonData[i].position;
        employeesSelector.add(option);
    }
}

function add() {
    let request = new XMLHttpRequest();
    let name = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let monthProfit = document.getElementById("monthProfit").value;
    let yearProfit = document.getElementById("yearProfit").value;
    let employees = [];
    for (let option of document.getElementById("employees").options) {
        if (option.selected) {
            const customEmployee = option.value.split('  ');
            console.log(customEmployee);
            employees.push(customEmployee);
        }
    }
    const url = "/restaurants/save";
    let employee = JSON.stringify({
        "name": name,
        "address": address,
        "monthProfit": monthProfit,
        "yearProfit": yearProfit,
        "employees": employees
    });

    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Ресторан успешно сохранен");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    console.log(employee);
    request.send(employee);
}

function getAll() {
    let request = new XMLHttpRequest();
    const url = "/restaurants/all";
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Все данные успешно получены");
            document.getElementById("table").style.display = "block";
            fillTable(request.response, "table");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send();
}

function fillTable(jsonData, tableId) {
    let table = document.getElementById(tableId);
    //clear table
    let rows = table.getElementsByTagName('tr')
    for (let x = rows.length - 1; x > 0; x--) {
        table.removeChild(rows[x]);
    }
    //fill table
    let i = 0;
    jsonData.forEach((row) => {
        const tr = document.createElement("tr");

        Object.values(row).forEach((cell) => {
            const td = document.createElement("td");
            if (Object.keys(row)[i] === "employees") {
                console.log("HERE");
                let select = document.createElement('select');

                let array = row.employees.toString().split(',');
                console.log(array.length);

                for (let i = 0; i <= array.length - 4; i += 4) {
                    let option = document.createElement('option');
                    option.text = array[i] + " " + array[i + 1] + " " + array[i + 2] + " " + array[i + 3];
                    select.add(option);
                }

                td.appendChild(select);
                tr.appendChild(td)
            } else {
                td.textContent = cell;
                tr.appendChild(td);
            }
            i++;
        });
        table.appendChild(tr);
        i = 0;
    });
}

function selectorChanged(selected) {
    switch (selected.value) {
        case "0":
            document.getElementById("find-by-name").style.display = 'none';
            document.getElementById("find-by-id").style.display = 'none';
            break;
        case "1":
            document.getElementById("find-by-id").style.display = 'block';
            document.getElementById("find-by-name").style.display = 'none';
            break;
        case "2":
            document.getElementById("find-by-id").style.display = 'none';
            document.getElementById("find-by-name").style.display = 'block';
            break;
    }
}

function find() {
    let url;
    let selected = document.getElementById("selector").value;
    switch (selected) {
        case "1":
            let id = document.getElementById("find-by-id").value;
            url = "/restaurants/id/" + id;
            break;
        case "2":
            let name = document.getElementById("find-by-name").value;
            url = "/restaurants/name/" + name;
            break;
        case "3":
            url = "/restaurants/get/month-profit";
            break;
        case "4":
            url = "/restaurants/get/year-profit";
            break;
    }
    let request = new XMLHttpRequest();
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            document.getElementById("found-table").style.display = "block";
            let jsonData = [];
            jsonData.push(request.response);
            console.log(jsonData);
            lastFound = request.response;
            fillTable(jsonData, "found-table");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send();
}

function update() {
    let request = new XMLHttpRequest();
    let name = document.getElementById("name_update").value;
    let address = document.getElementById("address_update").value;
    let monthProfit = document.getElementById("monthProfit_update").value;
    let yearProfit = document.getElementById("yearProfit_update").value;
    let employees = [];
    for (let option of document.getElementById("employees_update").options) {
        if (option.selected) {
            employees.push(option.value);
        }
    }
    const url = "/restaurants/save";
    let employee = JSON.stringify({
        "id": lastFound.id,
        "name": name,
        "address": address,
        "monthProfit": monthProfit,
        "yearProfit": yearProfit,
        "employees": employees
    });
    request.open("POST", url, true);
    request.setRequestHeader("Content-type", "application/json");
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Ресторан успешно обновлён");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    console.log(employee);
    request.send(employee);
}

function readyForUpdate() {
    if (lastFound == null) {
        alert("Сначала найдите ресторан!");
    } else {
        document.getElementById("update_form").style.display = "block";

        document.getElementById("name_update").value = lastFound.name;
        document.getElementById("address_update").value = lastFound.address;
        document.getElementById("monthProfit_update").value = lastFound.monthProfit;
        document.getElementById("yearProfit_update").value = lastFound.yearProfit;
    }
}

function deleteById() {
    let request = new XMLHttpRequest();
    const url = "/restaurants/delete-id/" + document.getElementById("delete_by_id").value;
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Ресторан успешно удалён");
            document.getElementById("delete_by_id").value = "";
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send();
}

function deleteAll() {
    let request = new XMLHttpRequest();
    const url = "/restaurants/delete-all";
    request.open("DELETE", url);
    request.setRequestHeader("Content-type", "application/json");
    request.responseType = "json";
    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Все рестораны успешно удалены");
        }
        if (request.readyState === 4 && request.status !== 200) {
            alert(request.response.message);
        }
    });
    request.send();
}