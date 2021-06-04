
fetch("/api/account", { method: "GET",
    headers : {
        'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
    }})
    .then(response => response.json())
    .then(data => {
        console.log(data)
        let accountID = data.accountID;
        let accountName = data.accountName;
        let accountRole = data.accountRole;
        let accountHelixAccessList = data.helixAccessList

        let listContainer = document.getElementById("helixsystem-list");

        for (let i = 0; i < accountHelixAccessList.length; i++) {

            let aElement = document.createElement("a");
            aElement.className = "helixsystem-name"
            aElement.href = "helix-inventory.html" + "?helix_name=" + accountHelixAccessList[i];

            let pElement = document.createElement("p");
            pElement.innerText = " - " + accountHelixAccessList[i];


            aElement.appendChild(pElement);
            listContainer.appendChild(aElement)
        }

        let accountIDElement = document.getElementById("account-id");
        accountIDElement.innerText = "Account ID: " + accountID
        let accountNameElement = document.getElementById("account-name");
        accountNameElement.innerText = "Name: " + accountName;

    })
