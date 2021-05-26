fetch ("/api/helixsystem/LUMC/inventory")
    .then(response => response.json())
    .then(data => {
        console.log(data)
        for (i = 0; i < data.length; i++) {
            let productAmount = data[i].amount
            let productName = data[i].product.productName
            let productID = data[i].product.productID
            let productPrice = data[i].product.productPrice
            let productImg =  data[i].product.imgSource
            console.log(productAmount +"\n" + productName + "\n" + productID + "\n" + productPrice + "\n" + productImg )

            let gridContainer = document.getElementsByClassName("grid-container")[0]

            let divElement = document.createElement("div")
            divElement.setAttribute("class", "grid-item")

            let imgElement = document.createElement("img")
            imgElement.setAttribute("src", "../resources/" + productImg)
            imgElement.setAttribute("alt", "Product Image")
            // ../resources/images/HelixLogo.png

            let h3Element = document.createElement("h5")
            h3Element.setAttribute("class", "text-content")
            h3Element.textContent = productName

            divElement.appendChild(imgElement)
            divElement.appendChild(h3Element)

            gridContainer.appendChild(divElement)
        }
    })