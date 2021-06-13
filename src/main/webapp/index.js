
function login() {
    let formData = new FormData(document.querySelector("#loginForm"));
    let encData = new URLSearchParams(formData.entries());

    fetch("/api/authentication", {method: "POST", body: encData})
        .then(function (response) {
            if (response.ok) {
                return response.json(); //body will be json
            }
            else {
                throw "Wrong username / password"; //there is no body, just throw the error
            }
        })
        .then(myJson => {
            window.sessionStorage.setItem("myJWT", myJson.JWT)
            window.location.href = "/pages/account.html";
        }) //present in the JsonBody
        .catch(error => console.log(error)) //will log Wrong username/password if !response.ok

    return false;
}

