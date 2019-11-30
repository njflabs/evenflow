<?php
define("DB_DSN","mysql:host=localhost;dbname=webmobia");
define("DB_USERNAME","webmobia");
define("DB_PASSWORD","");
define("DB_CHARACSET","utf8");
if(!empty($_GET['q'])) {
$qq = $_GET['q'];

include "mainincs/Database.php";
$cdbf = new Database();
$clist = $cdbf->fetch_custom($qq);
$gArray = array();
$values = array();

try {
foreach ($clist as $myrow) {
	$i = 0;

            array_push($values,$myrow);    
		$i++;
		$gArray = $gArray +  $values;
            // array_push($gArray, $values);
		//	unset($values);$values = array(); 
}
$fJSonFldrQstring = json_encode($values);
if(!empty($_GET['cb'])) {
$cb = $_GET['cb'];
echo $cb . "('" . $fJSonFldrQstring . "');";
} else {
echo "alert('" . $fJSonFldrQstring . "');";
}
} catch(Exception $ex) {
print_r($clist);
}
 } else {
echo "noQ";
}
exit;
// echo json_encode($carr);
// print_r($clist);
?>