const urlParams = new URLSearchParams(window.location.search);

const helix_name = urlParams.get('helix_name')
const product_id = urlParams.get('product_id')

fetch("/api/helixsystem/" + helix_name + "/inventory/" + product_id, { method: "GET",
    headers : {
        'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
    }})

    .then(response => {
        if (response.ok) {
            return response.json();
        }
        else {
            window.location.href = "/pages/error/" + response.status + ".html"
        }
    })
    .then(data => {
        let productAmount = data.amount;
        let productName = data.product.productName;
        let productID = data.product.productID;
        let productPrice = data.product.productPrice;
        let productImg = data.product.imgSource;

        let productImageElement = document.getElementById("product-image");
        productImageElement.src = "/resources/images/products/" + productImg;

        let productAmountElement = document.getElementById("product-amount");
        productAmountElement.innerText = "Stock: " + productAmount;

        let productIDElement = document.getElementById("product-id");
        productIDElement.innerText = "ID: " + productID;

        let productPriceElement = document.getElementById("product-price");
        productPriceElement.innerText = "€ " + productPrice;

        let productNameElement = document.getElementById("product-name");
        productNameElement.innerText = productName;
})
