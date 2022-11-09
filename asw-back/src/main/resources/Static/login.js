
async function getUserVariables(username){
    const response = await fetch("http://localhost:8081/user?username="+"oriol");
    return await response.json();
}
function dataCorrectForm(maxvisit, minaway, delay){
    return !isNaN(Number(maxvisit)) && !isNaN(Number(minaway)) && !isNaN(Number(delay))
}
function getInfoUser(){
    let username = localStorage.getItem("username")
    let userInformation = getUserVariables(username)
    maxvisit = document.getElementById("maxvisit").value
    minaway = document.getElementById("minaway").value
    delay = document.getElementById("delay").value
    about = document.getElementById("about").value
    showdead = document.getElementById("showdead").value
    noprocrast = document.getElementById("noprocrast").value
    if(dataCorrectForm(maxvisit, minaway, delay)){

    }
    else alert("Max visit, Min away and dealy must be numbers")

}
async function setUserInfo() {
    let username = localStorage.getItem("username")
    let data = await getUserVariables(username)
    document.getElementById("username").value = data.username
    document.getElementById("created").value = data.created
    document.getElementById("karma").value = data.karma
    document.getElementById("maxvisit").value = data.maxvisit
    document.getElementById("minaway").value = data.minaway
    document.getElementById("delay").value = data.delay
    document.getElementById("about").value = data.about
    if(data.showdead == 0) document.getElementById("showdead").checked = true
    if(data.noprocrast == 0) document.getElementById("noprocrast").checked = true

}

window.onload = async function WindowLoad(event) {
    setUserInfo()
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
    console.log('User signed out.');
    });
}
function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}
window.handleCredentialResponse = async (response) => {
    responsePayload = parseJwt(response.credential);
    await fetch("http://localhost:8081/login?username=" + responsePayload.given_name, {method:'POST'});
    localStorage.setItem("username", "username")
    console.log(response.credential)
    console.log("ID: " + responsePayload.sub);
    console.log('Full Name: ' + responsePayload.name);
    console.log('Given Name: ' + responsePayload.given_name);
    console.log('Family Name: ' + responsePayload.family_name);
    console.log("Image URL: " + responsePayload.picture);
    console.log("Email: " + responsePayload.email);
}
