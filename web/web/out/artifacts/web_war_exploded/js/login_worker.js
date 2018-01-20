function showform() {
    document.getElementById("overlay").style.display = "block";
    document.getElementById("login-form").style.display = "block";
}

function closeform() {
    document.getElementById("overlay").style.display = "none";
    document.getElementById("login-form").style.display = "none";
}

function enter() {
    var user = document.getElementById("name").value;
    if(user != undefined && user != ""){
        document.cookie = "user=" + user;
        closeform();
        location.reload();
    }
}