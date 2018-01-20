function to_cart(x) {
    const one = "one";
    const two = "two";
    const three = "three";
    const four = "four";

    var matches;

    matches = document.cookie.match(new RegExp("(?:^|; )" + one.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    var one_count = matches ? decodeURIComponent(matches[1]) : 0;

    matches = document.cookie.match(new RegExp("(?:^|; )" + two.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    var two_count = matches ? decodeURIComponent(matches[1]) : 0;

    matches = document.cookie.match(new RegExp("(?:^|; )" + three.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    var three_count = matches ? decodeURIComponent(matches[1]) : 0

    atches = document.cookie.match(new RegExp("(?:^|; )" + four.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    var four_count = matches ? decodeURIComponent(matches[1]) : 0;

    matches = document.cookie.match(new RegExp("(?:^|; )" + "user".replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
    var username = matches ? decodeURIComponent(matches[1]) : 0;

    if(username == 0){
        showform();
    }else {
        switch (x) {
            case 1:
                one_count++;
                document.cookie = one + "=" + one_count;
                break;
            case 2:
                two_count++;
                document.cookie = two + "=" + two_count;
                break;
            case 3:
                three_count++;
                document.cookie = three + "=" + three_count;
                break;
            case 4:
                four_count++;four
                document.cookie = four + "=" + four_count;
                break;
            default:
                break;
        }

        document.getElementById("popup1").style.display = "block";

        var f1000 = delay(f, 3000);
        f1000();
    }
}

function f() {
    document.getElementById("popup1").style.display = "none";
}

function delay(f, ms) {
    return function() {
        var savedThis = this;
        var savedArgs = arguments;

        setTimeout(function() {
            f.apply(savedThis, savedArgs);
        }, ms);
    };

}

function del(x) {
    const one = "one";
    const two = "two";
    const three = "three";
    const four = "four";

    switch(x){
        case 1:
            document.cookie = one + "=0"
            break;
        case 2:
            document.cookie = two + "=0"
            break;
        case 3:
            document.cookie = three + "=0"
            break;
        case 4:
            document.cookie = four + "=0"
            break;
        default:
            break;
    }
    location.reload();
}
