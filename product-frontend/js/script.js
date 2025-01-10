
$('#inputPreco').mask("000.000.000.000.000,00", {reverse: true});

function convertToNumber(priceFormat){
    return priceFormat.replace(/\./g, '').replace(',', '.');
}

var products = [
    {
        id: 1,
        name: "Computador M1-TX",
        description: "Intel i7, 16GB, SSD 256, HD 1TB",
        price: 4500,
        category: 1,
        promotion: true,
        new: true
    },
    {
        id: 2,
        name: "Computador M2-TX",
        description: "Intel i5, 8GB, HD 1TB",
        price: 2600,
        category: 2,
        promotion: false,
        new: false
    },
    {
        id: 3,
        name: "Computador M7",
        description: "Intel i7, 32GB, SSD 512, HD 1TB",
        price: 5950,
        category: 3,
        promotion: false,
        new: true
    },
];

var categories = [
    {id: 1, name: "Produção Própria"},
    {id: 2, name: "Nacional"},
    {id: 3, name: "Importado"}
];

loadProducts();


function loadProducts(){
    for(let prod of products){
        addNewRow(prod);
    }
}

function save(){

   var prod = {
        id: products.length + 1,
        name: document.getElementById("inputNome").value,
        description: document.getElementById("inputDescricao").value,
        price: convertToNumber(document.getElementById("inputPreco").value),
        category: document.getElementById("selectCategoria").value,
        promotion: document.getElementById("checkboxPromocao").checked,
        new: document.getElementById("checkboxLancamento").checked
    };

addNewRow(prod);
products.push(prod);

document.getElementById("formProduto").reset();

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

    var categoryNode = document.createTextNode(categories[prod.category - 1].name);
    newRow.insertCell().appendChild(categoryNode);

    var options = "";
    if (prod.promotion){
        options = "<span class='badge bg-success me-1'>P</span>";
    }

    if (prod.new){
        options += "<span class='badge bg-primary me-1'>L</span>";
    }

    cell = newRow.insertCell();
    cell.className="d-none d-md-table-cell";
    cell.innerHTML = options;

    
}

