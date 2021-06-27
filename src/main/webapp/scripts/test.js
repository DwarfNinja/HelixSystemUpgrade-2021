function getFetch(apiUrl, jwtBool, divID) {
    let divElement = document.getElementById("container").getElementsByTagName("div")[divID]
    let jwtDic = {true: "myJWT", false: "noJWT"}
    fetch(apiUrl, {
        method: "GET",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem(jwtDic[jwtBool])
        }
    })
        .then(response => {
            divElement.getElementsByTagName("p")[1].innerText = "Response : " + response.status;
            if (response.ok) {
                divElement.getElementsByTagName("i")[0].className = "far fa-check-circle fa-2x";
            } else {
                return response.json()
                    .then(data => {
                        divElement.getElementsByTagName("i")[0].className = "far fa-times-circle fa-2x";
                        divElement.getElementsByTagName("p")[1].innerText += " " + JSON.stringify(data)
                            .replace(/[{}-]/g, '').replace(/"/g, " ");
                    })
                    .catch(error => {
                        divElement.getElementsByTagName("i")[0].className = "far fa-times-circle fa-2x";
                        divElement.getElementsByTagName("p")[1].innerText += " " + "error :" + error;
                    })

            }
        })
}

function postFetch(apiUrl, jwtBool, divID) {
    let divElement = document.getElementById("container").getElementsByTagName("div")[divID]
    let jwtDic = {true: "myJWT", false: "noJWT"}
    fetch(apiUrl, {
        method: "POST",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem(jwtDic[jwtBool])
        }
    })
        .then(response => {
            divElement.getElementsByTagName("p")[1].innerText = "Response : " + response.status;
            if (response.ok) {
                divElement.getElementsByTagName("i")[0].className = "far fa-check-circle fa-2x";
            } else {
                return response.json()
                    .then(data => {
                        divElement.getElementsByTagName("i")[0].className = "far fa-times-circle fa-2x";
                        divElement.getElementsByTagName("p")[1].innerText += " " + JSON.stringify(data)
                            .replace(/[{}-]/g, '').replace(/"/g, " ");
                    })
                    .catch(error => {
                        divElement.getElementsByTagName("i")[0].className = "far fa-times-circle fa-2x";
                        divElement.getElementsByTagName("p")[1].innerText += " " + "error :" + error;
                    })
            }
        })
}
