fetch("/api/account", { method: "GET",
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
        let accountID = data.accountID;
        let accountName = data.accountName;
        let accountUsername = data.accountUsername;
        let accountEmail = data.accountEmail;
        let accountPhoneNumber = data.accountPhoneNumber;
        let accountOrganizationType = data.accountOrganizationType;
        let accountJobFunction = data.accountJobFunction;
        let accountHelixAccessList = data.helixAccessList

        let listContainer = document.getElementById("helixsystem-list");
        console.log(accountHelixAccessList)
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
        let accountUsernameElement = document.getElementById("account-username");
        accountUsernameElement.innerText = "Username: " + accountUsername;

        let accountNameElement = document.getElementById("account-name");
        accountNameElement.innerText = "Name: " + accountName;
        let accountEmailElement = document.getElementById("account-email");
        accountEmailElement.innerText = "Email: " + accountEmail;
        let accountPhoneNumberElement = document.getElementById("account-phonenumber");
        accountPhoneNumberElement.innerText = "Phone number: " + accountPhoneNumber;

        let accountOrganizationTypeElement = document.getElementById("account-organizationtype");
        accountOrganizationTypeElement.innerText = "Organization Type: " + accountOrganizationType;
        let accountJobFunctionElement = document.getElementById("account-jobfuntion");
        accountJobFunctionElement.innerText = "Job Function: " + accountJobFunction;

    })
