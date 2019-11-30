<script language="php">
global $dyndbase, $adminuser, $adminpass, $filename;
global $dbase, $db, $host, $dbuser, $dbpass;


// used for register_globals ON
foreach ($_REQUEST as $key=>$value) { 
    if (get_magic_quotes_gpc()==0) { 
        $value = addslashes($value); 
    } 
    $value = str_replace(array(')','|'),array(')','|'),$value); 
    ${$key} = $value; 
    $_REQUEST[$key] = $value; 
    if (isset($_POST[$key])) { $_POST[$key] = $value; } 
    if (isset($_COOKIE[$key])) { $_COOKIE[$key] = $value; } 
    if (isset($_FILES[$key])) { $_FILES[$key] = $value; } 
    if (isset($_GET[$key])) { $_GET[$key] = $value; } 
    if (isset($HTTP_POST_VARS[$key])) { $HTTP_POST_VARS[$key] = $value; } 
    if (isset($HTTP_COOKIE_VARS[$key])) { $HTTP_COOKIE_VARS[$key] = $value; } 
    if (isset($HTTP_FILE_VARS[$key])) { $HTTP_FILE_VARS[$key] = $value; } 
    if (isset($HTTP_GET_VARS[$key])) { $HTTP_GET_VARS[$key] = $value; } 
}
if (isset($_FILES[$key])) { $_FILES[$key] = $value; }

// your database host address. Ususally leave as is.
$host = "127.12.163.130";



</script>