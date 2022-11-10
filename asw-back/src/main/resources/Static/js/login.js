async function changeButton(username){
    let user = await fetch("http://localhost:8081/user?username=" + username);
    let karma = await user.json().karma;
    let login = document.getElementById("login")
    login.innerHTML =
        `<ul class="nav-log">
            <li class="nav-item"><a onclick="getProfile()" id="me">${username}</a><span id="karma">(${karma})</span></li>
            <li class="nav-item"><a id="logout" onClick= "signOut()">logout</a></li>
        </ul>`
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
    username = responsePayload.name.replaceAll(/\s/g,'');
    console.log(username)
    await fetch("http://localhost:8081/login?username=" + username, {method:'POST'});
    localStorage.setItem("username", username)
    console.log(response.credential)
    changeButton(username);
}