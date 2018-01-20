function sort(fl) {
    const name = "filter";
    var matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    if(fl == undefined) {
        fl = matches ? decodeURIComponent(matches[1]) : 0;
    }else{
        document.cookie = "filter=" + fl;
    }

    //console.log("fl = " + fl);

    if(fl == 0){
        for (i = 1; i < 4; i++){
            document.getElementById("i" + i).style.display = "block";

            document.getElementById("a" + i).style.textDecoration = "none";
            document.getElementById("a" + i).style.fontWeight = "normal";
        }

        document.getElementById("a0").style.textDecoration = "underline";
        document.getElementById("a0").style.fontWeight = "bolder";
    }else {
        document.getElementById("a0").style.textDecoration = "none";
        document.getElementById("a0").style.fontWeight = "normal";

        for (i = 1; i < 4; i++) {
            document.getElementById("i" + i).style.display = "none";

            document.getElementById("a" + i).style.textDecoration = "none";
            document.getElementById("a" + i).style.fontWeight = "normal";

            if (i == fl) {
                document.getElementById("i" + i).style.display = "block";

                document.getElementById("a" + i).style.textDecoration = "underline";
                document.getElementById("a" + i).style.fontWeight = "bolder";
            }
        }
    }
}

function showmsg(){
    document.getElementById("inf1").style.display = "none";
    document.getElementById("inf2").style.display = "block";

    document.getElementById("popup1").style.display = "block"

    var f1000 = zdelay(q, 2500);
    f1000();
}

function q() {
    document.getElementById("popup1").style.display = "none"

    document.getElementById("inf1").style.display = "block";
    document.getElementById("inf2").style.display = "none";
}

function zdelay(f, ms) {
    return function() {
        var savedThis = this;
        var savedArgs = arguments;

        setTimeout(function() {
            f.apply(savedThis, savedArgs);
        }, ms);
    };

}