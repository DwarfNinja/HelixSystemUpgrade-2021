const urlParams = new URLSearchParams(window.location.search);

const product_id = urlParams.get('product_id')

fetch("/api/helixsystem/LUMC/inventory/" + product_id, { method: "GET",
    headers : {
        'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
    }})

.then(response => response.json())
.then(data => {
    console.log(data)
    let productAmount = data.amount;
    let productName = data.product.productName;
    let productID = data.product.productID;
    let productPrice = data.product.productPrice;
    let productImg = data.product.imgSource;

    let productImageElement = document.getElementById("product-image");
    productImageElement.src = "/images/products/" + productImg;

    let productAmountElement = document.getElementById("product-amount");
    productAmountElement.innerText = "Stock: " + productAmount;

    let productIDElement = document.getElementById("product-id");
    productIDElement.innerText = "ID: " + productID;

    let productPriceElement = document.getElementById("product-price");
    productPriceElement.innerText = "€ " + productPrice;

    let productNameElement = document.getElementById("product-name");
    productNameElement.innerText = productName;

})
