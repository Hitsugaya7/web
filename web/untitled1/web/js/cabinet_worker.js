function checked(x) {
    switch(x){
        case 1:
            document.getElementById("c1").setAttribute("checked","checked");
            break;
        case 2:
            document.getElementById("c2").setAttribute("checked","checked");
            break;
        case 3:
            document.getElementById("c3").setAttribute("checked","checked");
            break;
        case 4:
            document.getElementById("c4").setAttribute("checked","checked");
            break;
        default:
            document.getElementById("c1").setAttribute("checked","checked");
            break;
    }
}