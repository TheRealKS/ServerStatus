<?php
session_start();
if (isset($_POST["pwd"])) {
    $pwd = $_POST["pwd"];
    unset($_POST["pwd"]);
    if ($pwd == "admindb") {
        $_SESSION["db_admin_pwd"] = true;
        header("setup_db.php");
    }
} else {
    header("index.php");
}
?>