let logout_button;

fetch("/pages/header.html")
    .then(response => {
        return response.text()
    })
    .then(data => {
        document.querySelector("header").innerHTML = data;

        logout_button = document.querySelector("logout-button")
    });

function logout() {
    window.sessionStorage.removeItem("myJWT");
    window.location.href = "../index.html"
}

