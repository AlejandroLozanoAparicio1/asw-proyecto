console.log("nonono")
async function getUserVariables(username){
    const response = await fetch("http://localhost:8081/user?username="+username);
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
    console.log("sisisi")
    let username = localStorage.getItem("username")
    let data = await getUserVariables(username)
    document.getElementById("username").value = data.username
    document.getElementById("created").value = data.created
    document.getElementById("karma").value = data.karma
    document.getElementById("maxvisit").value = data.maxvisit
    document.getElementById("minaway").value = data.minaway
    document.getElementById("delay").value = data.delay
    document.getElementById("about").value = data.about
    if(data.showdead == 1) document.getElementById("showdead").checked = true
    if(data.noprocrast == 1) document.getElementById("noprocrast").checked = true
}

setUserInfo()
