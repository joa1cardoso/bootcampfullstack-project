
$('#inputPreco').mask("000.000.000.000.000,00", {reverse: true});

function convertToNumber(priceFormat){
    return priceFormat.replace(/\./g, '').replace(',', '.');
}

var products = [];

var categories = [];

loadCategories();
loadProducts();



function loadCategories(){

    $.ajax({
        url:"http://localhost:8080/categories",
        type: "GET",
        async: false,
        success: (response) => {
            categories = response;

            for(var cat of categories){
                document.getElementById("selectCategoria").innerHTML += `<option value="${cat.id}">${cat.name}</option>`;
            }

      }
  });
}

function loadProducts(){

    $.getJSON("http://localhost:8080/products", (response) => {

        products = response;
        for(let prod of products){
            addNewRow(prod);
        }

    });

}

function save(){

   var prod = {
        id: products.length + 1,
        name: document.getElementById("inputNome").value,
        description: document.getElementById("inputDescricao").value,
        price: convertToNumber(document.getElementById("inputPreco").value),
        idCategory: document.getElementById("selectCategoria").value,
        promotion: document.getElementById("checkboxPromocao").checked,
        newProduct: document.getElementById("checkboxLancamento").checked
    };

    $.ajax({
        url:"http://localhost:8080/products",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(prod),
        success: (product) => {
            addNewRow(product);
            products.push(product);
            document.getElementById("formProduto").reset();

      }
  });


}

function addNewRow(prod){
    var table = document.getElementById("productsTable");

    var newRow = table.insertRow();

    var idNode = document.createTextNode(prod.id);
    newRow.insertCell().appendChild(idNode);

    var nameNode = document.createTextNode(prod.name);
    newRow.insertCell().appendChild(nameNode);

    var descriptionNode = document.createTextNode(prod.description);
    var cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.appendChild(descriptionNode);

    var formatter = new Intl.NumberFormat("pt-BR", {
        style: "currency",
        currency: "BRL",
    });

    var priceNode = document.createTextNode(formatter.format(prod.price));
    newRow.insertCell().appendChild(priceNode);

    var categoryNode = document.createTextNode(categories[prod.idCategory - 1].name);
    newRow.insertCell().appendChild(categoryNode);

    var options = "";
    if (prod.promotion){
        options = "<span class='badge bg-success me-1'>P</span>";
    }

    if (prod.newProduct){
        options += "<span class='badge bg-primary me-1'>L</span>";
    }

    cell = newRow.insertCell();
    cell.className="d-none d-md-table-cell";
    cell.innerHTML = options;

    
}

