<?php
if (isset($_SESSION["db_admin_pwd"])) {

} else {
    echo "Please enter the password to continue:
    <form action='redirect_setup_db.php' method='post'>
    <input type='text' name='pwd'><br>
    <input type='submit'>
    </form>
    ";
}
?>