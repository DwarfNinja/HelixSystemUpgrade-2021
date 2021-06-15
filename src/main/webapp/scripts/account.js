
fetch("/api/account", { method: "GET",
    headers : {
        'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
    }})
    .then(response => response.json())
    .then(data => {
        let accountID = data.accountID;
        let accountName = data.accountName;
        let accountRole = data.accountRole;
        let accountHelixAccessList = data.helixAccessList

        let listContainer = document.getElementById("helixsystem-list");

        for (let i = 0; i < accountHelixAccessList.length; i++) {

            let liElement = document.createElement("li");

            let aElement = document.createElement("a");
            aElement.className = "helixsystem-name"
            aElement.href = "/pages/helix-inventory.html" + "?helix_name=" + accountHelixAccessList[i];
            aElement.innerText = accountHelixAccessList[i];



            liElement.appendChild(aElement);
            listContainer.appendChild(liElement)
        }

        let accountIDElement = document.getElementById("account-id");
        accountIDElement.innerText = "Account ID: " + accountID
        let accountNameElement = document.getElementById("account-name");
        accountNameElement.innerText = "Name: " + accountName;

    })
