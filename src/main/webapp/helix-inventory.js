fetch("/api/helixsystem/LUMC/inventory")
    .then(response => response.json())
    .then(data => {
        console.log(data);
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
            gridItemElement.href = "product.html" + "?product_id=" + productID;

            let productImageElement = document.createElement("img");
            productImageElement.className = "image-content"
            productImageElement.src = "/images/products/" + productImg;
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