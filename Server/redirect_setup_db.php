<?php
session_start();
if (isset($_POST["pwd"])) {
    $pwd = $_POST["pwd"];
    unset($_POST["pwd"]);
    if ($pwd == "admindb") {
        $_SESSION['db_admin_pwd'] = "true";
        header("Location: setup_db.php");
    } else {
        echo "invalid pass";
    }
} else {
    header("Location: index.php");
}
?>