function getWorldData() {
    var http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            var response = http.responseText;

        }
    }
    http.open("GET", "");
    http.send();
}