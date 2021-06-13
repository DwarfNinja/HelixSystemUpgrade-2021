
fetch("/api/account/notifications", { method: "GET",
    headers : {
        'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
    }})
    .then(response => response.json())
    .then(data => {
        let listContainer = document.getElementById("notification-list");

        for (let i = 0; i < data.length; i++) {
            let liElement = document.createElement("li");
            liElement.className = "notification";
            liElement.innerText = data[i].notificationMessage;

            let imgElement = document.createElement("img");
            imgElement.className = "image-content";
            imgElement.src = "/resources/images/products/" + data[i].notificationProduct.imgSource;
            imgElement.alt = "Product Image";
            imgElement.width = 150;
            imgElement.height = 75;

            let aElement = document.createElement("a");
            aElement.href = "/pages/product.html" + "?product_id=" + data[i].notificationProduct.productID;
            aElement.innerText = data[i].notificationProduct.productName;

            liElement.appendChild(imgElement);
            liElement.appendChild(aElement);

            let notificationList = document.getElementById("notification-list");
            notificationList.appendChild(liElement);
        }

    })

//gridItemElement.href = "/pages/product.html" + "?product_id=" + productID;


//        <ul id="notification-list">
//             <li class="notification">
//                 <img class="image-content" src="https://placekitten.com/50/50" alt="Product Image"/>
//                 <a href="helix-inventory.html?helix_name=LUMC">TestProduct</a>
//             </li>
//         </ul>