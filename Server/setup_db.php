<?php
session_start();
if (isset($_SESSION['db_admin_pwd'])) {
        $file = "data.db";
        $db = new SQLite3($file);
        $db->exec("CREATE TABLE data (up INTEGER, players INTEGER)");
        $db->exec("CREATE TABLE world (name TEXT, players INTEGER)");
        $db->exec("INSERT INTO data (up,players) VALUES (0, 0)");
        echo "Finished creating database! Click <a href='index.php'/>here</a> to go back to home!";
} else {
    echo "Please enter the password to continue:
    <form action='redirect_setup_db.php' method='post'>
    <input type='text' name='pwd'><br>
    <input type='submit'>
    </c
    ";
}
?>