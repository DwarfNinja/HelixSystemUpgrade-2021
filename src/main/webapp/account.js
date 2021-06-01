
document.getElementById("loginformsubmit").addEventListener("click", login);

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
        .then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT)) //present in the JsonBody
        .catch(error => console.log(error)) //will log Wrong username/password if !response.ok
}

// document.querySelector("#showMeAllCountries").addEventListener("click", function (){
//     var fetchOptions = { method: "GET",
//         headers : {
//             'Authorization' : 'Bearer ' + window.sessionStorage.getItem("myJWT")
//         }}
//     fetch("/restservices/countries", fetchOptions).then(function(response){
//         if (response.ok) return response.json();
//     }).then(myJson => console.log(myJson)).catch(error => console.log(error))
// })
