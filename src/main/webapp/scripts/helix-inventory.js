const urlParams = new URLSearchParams(window.location.search);

const helix_name = urlParams.get('helix_name')

fetch("/api/helixsystem/" + helix_name + "/inventory", { method: "GET",
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
        let helixName = document.getElementById("helix-name");
        helixName.innerText = helix_name;

        data.sort((firstEl, secondEl) => {
            if (firstEl.product.productID < secondEl.product.productID) {
                return -1
            }
            else if (firstEl.product.productID > secondEl.product.productID) {
                return 1
            }
            else {
                return 0
            }
        } )

        for (let i = 0; i < data.length; i++) {
            let productAmount = data[i].amount;
            let productName = data[i].product.productName;
            let productID = data[i].product.productID;
            let productPrice = data[i].product.productPrice;
            let productImg = data[i].product.imgSource;
            console.log(productAmount + "\n" + productName + "\n" + productID + "\n" + productPrice + "\n" + productImg);

            let gridContainer = document.getElementsByClassName("grid-container")[0];

            let gridItemElement = document.createElement("a");
            gridItemElement.className = "grid-item";
            gridItemElement.href = "/pages/product.html" + "?helix_name=" + helix_name + "&product_id=" + productID;

            let productImageElement = document.createElement("img");
            productImageElement.className = "image-content"
            productImageElement.src = "/resources/images/products/" + productImg;
            productImageElement.alt = "Product Image";
            productImageElement.width = 300;
            productImageElement.height = 150;

            let productNameElement = document.createElement("p");
            productNameElement.className = "text-content";
            productNameElement.textContent = productName;

            // Adding and organizing the nodes as children
            gridItemElement.appendChild(productImageElement);
            gridItemElement.appendChild(productNameElement);

            gridContainer.appendChild(gridItemElement)
        }
    })