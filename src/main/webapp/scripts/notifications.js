fetch("/api/account/notifications", { method: "GET",
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
        let listContainer = document.getElementById("notification-list");

        for (let i = 0; i < data.length; i++) {
            let liElement = document.createElement("li");
            liElement.className = "notification";
            liElement.id = "notification-id-" + data[i].notificationID;

            let aElement = document.createElement("a");
            aElement.href = "/pages/product.html" + "?product_id=" + data[i].notificationProduct.productID;

            let imgElement = document.createElement("img");
            imgElement.className = "image-content";
            imgElement.src = "/resources/images/products/" + data[i].notificationProduct.imgSource;
            imgElement.alt = "Product Image";
            imgElement.width = 175;
            imgElement.height = 90;

            let h3ElementProductName = document.createElement("h3");
            h3ElementProductName.className = "text";
            h3ElementProductName.innerText = data[i].notificationProduct.productName;

            let iElementNotification = document.createElement("i");
            let iconDict = {
                "New Product!": "fa fa-exclamation-circle fa-2x",
                "Product Restocked!": "fa fa-box-open fa-2x",
                "Interested?":  "fa fa-question-circle fa-2x",
            }
            iElementNotification.className = "icon-content" + " " + iconDict[data[i].notificationMessage];

            let h3ElementNotificationMessage = document.createElement("h3");
            h3ElementNotificationMessage.className = "text";
            h3ElementNotificationMessage.innerText = data[i].notificationMessage;

            let buttonElementInterested = document.createElement("button");
            buttonElementInterested.className = "button";
            let iElementBell = document.createElement("i");
            iElementBell.className = "fa fa-bell fa-2x";

            let buttonElementDeleted = document.createElement("button");
            buttonElementDeleted.className = "button";
            let iElementTrash = document.createElement("i");
            iElementTrash.className = "fa fa-trash fa-2x";

            buttonElementInterested.appendChild(iElementBell);
            buttonElementDeleted.appendChild(iElementTrash);
            buttonElementDeleted.setAttribute("onclick", "deleteNotification(" + data[i].notificationID + ")");

            aElement.appendChild(imgElement);
            aElement.appendChild(h3ElementProductName);
            aElement.appendChild(iElementNotification);
            aElement.appendChild(h3ElementNotificationMessage);

            liElement.appendChild(aElement);
            liElement.appendChild(buttonElementInterested);
            liElement.appendChild(buttonElementDeleted);

            let notificationList = document.getElementById("notification-list");
            notificationList.appendChild(liElement);
        }
    })

function deleteNotification(notificationID) {
    fetch("/api/account/notifications/delete/" + notificationID, { method: "POST",
        headers : {
            'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }})
        .then(response => {
            if (response.ok) {
                let notification = document.getElementById("notification-id-" + notificationID);
                notification.className = "notification animate-remove";
                notification.addEventListener("transitionend", () => {
                    notification.remove();
                })
            }
            else {
                throw "Error: Notification could not be deleted"
            }
        })

}