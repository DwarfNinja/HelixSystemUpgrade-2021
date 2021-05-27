fetch ("/api/system/accounts")
    .then(response => response.json())
    .then(data => {
        let accountName = data[0].accountName
        document.querySelector("h1").innerHTML = accountName
    })