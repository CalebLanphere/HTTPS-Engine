const loginButton = document.getElementById("loginButton");

loginButton.addEventListener("click", function() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch("https://localhost:8080/api/v1/login", {
        method: "POST",
        credentials: "include",
        headers: {
            "Accept": "application/json, text/html",
            "Authorization": "Basic " + btoa(username + ":" + password)
        }
    }).then(response => {
        if(response.status === 204) {
            window.location.replace("https://localhost:8080/test");
        }
    }).catch(error => {console.error(error)});
})