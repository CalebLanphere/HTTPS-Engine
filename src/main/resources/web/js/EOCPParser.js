/**
 *
 */

fetch("https://localhost:8080/test", {
    method: "GET",
    credentials: "include",
    headers: {
        "Accept": "application/json, text/html",
        "Authorization": "Basic " + btoa("test@test.test:test")
    }
}).then(response => response.json()).then((responseData) => {
    addListElements(responseData);
}).catch(error => {console.error(error)});

const addListElements = (response) => {
    const divToAddResponseInto = document.getElementById("responseDiv");

    for(let i = 0; i < response.length; i++) {
        let element = createHTMLElement(response[i]);

        divToAddResponseInto.appendChild(element);
    }
};

const createHTMLElement = (EOCPElement) => {
    let element = document.createElement(EOCPElement.Type);
    if(EOCPElement.Content != null) {
        element.innerHTML = EOCPElement.Content;
    }
    if(EOCPElement.Id != null) {
        element.setAttribute("Id", EOCPElement.Id);
    }
    if(EOCPElement.Class != null) {
        element.setAttribute("Class", EOCPElement.Class);
    }
    if(EOCPElement.Style != null) {
        element.setAttribute("Style", EOCPElement.Style);
    }
    if(EOCPElement.Children != null) {
        for(let i = 0; i < EOCPElement.Children.length; i++) {
            createChildHTMLElement(element, EOCPElement.Children[i]);
        }
    }

    return element;
};

const createChildHTMLElement = (parentElement, EOCPObject) => {
    let element = createHTMLElement(EOCPObject);
    parentElement.appendChild(element);
};