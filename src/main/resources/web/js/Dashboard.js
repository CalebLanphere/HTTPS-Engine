fetch("https://localhost:8080/api/v1/dashboard/entries", {
    method: "GET",
    credentials: "include",
    headers: {
        "Accept": "application/json, text/html",
        "Authorization": "Basic " + btoa(username + ":" + password)
    }
}).then(response => {
    if(response.status === 200) {
        document.createElement("ul")
    }
}).catch(error => {console.error(error)});