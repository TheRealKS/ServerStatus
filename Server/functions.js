function getServerData() {
    var http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            var response = http.responseText;
            var responses = response.split(",");
            var up = responses[0];
            var playerstotal = responses[1];
            var worlds = getWorldData(responses[2]);
        }
    }
    http.open("GET", "get_db.php");
    http.send();
}

function getWorldData(worldstring) {
    var worldmap = {};
    var parts = worldstring.split("||");
    for (var world in parts) {
        var partss = world.split(":");
        worldmap[partss[0]] = partss[1];
    }
    return worldmap;
}