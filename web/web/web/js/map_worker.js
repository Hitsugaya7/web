var map;
var first;
var second;
var third;

function initMap() {
    map = new ymaps.Map("map", {
        center: [59.997572 ,30.329496],
        zoom: 17,
        controls: ['smallMapDefaultSet']
    });

    first = new ymaps.Placemark([59.991625 ,30.319887],
        {balloonContent: "Первая юрта"},
        {
            iconLayout: 'default#image',
            iconImageHref: '../image/map.png',
            iconImageSize: [35, 45],
            iconImageOffset: [-28, -39]
        });

    second = new ymaps.Placemark([59.997572 ,30.329496],
        {balloonContent: "Вторая юрта"},
        {
            iconLayout: 'default#image',
            iconImageHref: '../image/map.png',
            iconImageSize: [35, 45],
            iconImageOffset: [-28, -39]
        });
    third = new ymaps.Placemark([48.062633 ,37.813728],
        {balloonContent: "Третья юрта"},
        {
            iconLayout: 'default#image',
            iconImageHref: '../image/map.png',
            iconImageSize: [35, 45],
            iconImageOffset: [-28, -39]
        });

    map.geoObjects.add(first);
    map.geoObjects.add(second);
    map.geoObjects.add(third);
}

function changeShop() {
    var shop = document.getElementById("shop-choice").value;

    map.setZoom(17);
    switch (shop){
        case '1':
            map.setCenter([59.991625 ,30.319887 ]);
            break;
        case '2':
            map.setCenter([ 59.997572 ,30.329496 ]);
            break;
        case '3':
            map.setCenter([48.062633 ,37.813728 ]);
            break;
    }
}

function changeType() {
    if(document.getElementById("type-choice").checked){
        document.getElementById("shop").style.display = "none";
        document.getElementById("courier").style.display = "block";
    }
    else {
        document.getElementById("shop").style.display = "block";
        document.getElementById("courier").style.display = "none";
    }
}