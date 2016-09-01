<?php
$file = "data.db";
if (file_exists($file)) {
    $db = new SQLite3($file);
    $up = "up:" . $db->querySingle("SELECT up FROM data") . ",";
    $playersonline = "players:" . $db->querySingle("SELECT players FROM data") . ",";
    $world = $db->query("SELECT name,players FROM world");
    $worlds = 'worlds:{';
    while ($dat = $world->fetchArray()) {
        $str = $dat['name'] . ":" . $dat['players'] . "||";
        $worlds .= $str;
    }
    $worlds = substr($worlds, 0, -2);
    $worlds .= '}';
    echo $up . $playersonline . $worlds;
}
?>