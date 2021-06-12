
fetch("/api/account/notifications", { method: "GET",
    headers : {
        'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
    }})
    .then(response => response.json())
    .then(data => {
        let listContainer = document.getElementById("notification-list");

        for (let i = 0; i < data.length; i++) {

        }

    })
