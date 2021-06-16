fetch("/api/account", { method: "GET",
    headers : {
        'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
    }})
    .then(response => {
        if (response.ok) {
            return response.json();
        }
    })

