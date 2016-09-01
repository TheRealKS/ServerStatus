<?php
if (isset($_POST['up'])) {
    $up = $_POST['up'];
    $playersonline = $_POST['players'];
    $worldsandplayers = $_POST['worlds'];
    unset($_POST['up'], $_POST['players'], $_POST['worlds']);
    $file = "data.db";
    if (file_exists($file)) {
        $db = new SQLite3($file);
        $time = time();
        $db->exec("UPDATE data SET up = {$up}");
        $db->exec("UPDATE data SET players = {$playersonline}");
        $db->exec("UPDATE data SET lastupdate = {$time}");
        $db->exec("DELETE FROM world");
        $worlds = explode("||", $worldsandplayers);
        foreach ($worlds as $world) {
            $parts = explode(":", $world);
            $wname = $parts[0];
            $wplayers = $parts[1];
            $db->exec("INSERT INTO world (name,players) VALUES ('{$wname}', {$wplayers})");
        }
        echo "oke";
    }
}            
?>