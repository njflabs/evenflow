<script language="php">
/*****************************************************************************/
/* ppalCart: profitCode.com                                                  */
/* Software Version: ppalCart 2.5 EE                                         */
/* ========================================================================= */
/* Software Distributed by:    http://www.profitcode.com                     */
/* ========================================================================= */
/* Copyright (c) 2001-2004 profitcode.com                                    */
/* Software by: profitCode.com                                               */
/* This software is bound by the enclosed license agreement (license.txt)    */
/*****************************************************************************/
global $data, $auser, $apass, $rownum ,$numrows, $dyndbase;
global $action, $tablename, $fnamestring, $scriteria, $fieldname;
global $header, $znamestring, $qstring, $filename, $dbqi;
global $dbase, $db, $host, $dbuser, $dbpass;

include "qcommsheader.php";
// include "../../shared/loadenv.php";
// do not remove!
if($dyndbase) {
$dbase = $dyndbase;
} else {
sendAlert("No databae name provided");
}

if((!$auser) || (!$apass)) {
sendAlert("Error: No Username or Password Given.");
} else {
 
$dbuser = Fb860c96e($auser);
$dbpass = Fb860c96e($apass);
}
 
function Fb860c96e($Vb45cffe0) {
 return strrev(base64_decode($Vb45cffe0));
}

$data = "";
$dbpropsString = "";
$numlines = 0;

$numrows = 0;
if($action) {
global $result;


if($action == "uploadFileQuery") {
if (($_FILES['filename']) && ($dbqi)) {

$prodImgname = $_FILES['filename']['name'];
$prodImgnamet = $_FILES['filename']['tmp_name'];
$thefilename = "dbqs/$dbqi" . ".pcdbq";
if (file_exists($thefilename)){
unlink($thefilename);
}
sleep(1);
if(!copy($prodImgnamet, "$thefilename")){
echo "Error: Could not copy file";
} else {
loadQfile($dbqi);
} 
} else {
echo "Error: Filename var missing  :: $filename :: $dbqi :: $prodImgnamet :: $thefilename";
}
}

if($action == "refreshDB") {
$db = ($GLOBALS["___mysqli_ston"] = mysqli_connect($host,  $dbuser,  $dbpass)) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
((bool)mysqli_query($db, "USE " . $dbase));
$result1 = mysqli_query( $db, "SHOW TABLES FROM $dbase") or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
while($tname = mysqli_fetch_row($result1)) {
$thetable = $tname[0];
$dbpropsString .= "<tbl>$thetable" . "\n";
addfields($thetable);
}
// echo $dbpropsString;
$gz_data = gzencode($dbpropsString);
echo "$gz_data";
}
// 
if($action == "runQuery") {
$qui = stripSlashes($qstring);
$qui = str_replace(";", "", $qui); 
$data = "";
$numrows = 0;
$rowi = 0;
$db = ($GLOBALS["___mysqli_ston"] = mysqli_connect($host,  $dbuser,  $dbpass)) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
((bool)mysqli_query($db, "USE " . $dbase)) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));

$query = "select * from $tablename";
$result = mysqli_query($GLOBALS["___mysqli_ston"], $query) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
// if(mysql_num_rows($result) > 0) {
// if(mysql_num_rows($result)) {
if($result) {
$colqty = (($___mysqli_tmp = mysqli_num_fields($result)) ? $___mysqli_tmp : false) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
// echo "gdgdgdgd $colqty";
// $count = mysql_num_rows($result) or trigger_error(sendGZAlert(mysql_error()));
for ($i = 0; $i < $colqty; $i++) {
$thefield = ((($___mysqli_tmp = mysqli_fetch_field_direct($result,  $i)->name) && (!is_null($___mysqli_tmp))) ? $___mysqli_tmp : false);
// echo "field name:: $thefield";
if($i == $colqty - 1) {
$data .= "$thefield\n";
} else {
$data .= "$thefield\t";
}
}

$result = mysqli_query( $db, $qui) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
$count = mysqli_num_rows($result);	
$numrows = 0;
while($row = mysqli_fetch_row($result)){	

    $numrows++;     
    $line = ""; 
    foreach($row as $value){
            $rowi++;
            $value = str_replace("\t"," ",$value);
            $value = str_replace("\n"," ",$value);
            $value = str_replace("\r"," ",$value);   
            if($rowi == $colqty) {
            $line .= $value; 
            } else {
            $line .= $value . "\t"; 
        } 

        
    }    
         
    if($numrows == $count) {
    $data .= $line;        
    } else {
    $data .= $line . "\n";
    }    
$rowi = 0;
}
if($numrows == 0) {
// $data = "Error: No records found...";
// $data = "Error: No records found...";

$data .= "$data";


}
} else {
// $data = "Error: No records found error...";

$data .= "$data";

}
$gz_data = gzencode($data);
echo "$gz_data";
// echo "$data";
}
}
function RTESafe($strText) {
	//returns safe code for preloading in the RTE
	$tmpString = trim($strText);
	$tmpString = str_replace(chr(147), chr(34), $tmpString);
	$tmpString = str_replace(chr(148), chr(34), $tmpString);
	return $tmpString;
}


function sendAlert($thealert) {
global $action;
if(($action) && ($action == "runQuery")) { 
sendGZAlert($thealert);
exit;
} else {
echo "Error: $thealert";
exit;
}
}


function sendGZMsg($thealert) {
$thegzalert = gzencode($thealert);
// echo "$thegzalert";
echo "$thegzalert";
exit;
}


function sendGZAlert($thealert) {
$thegzaalert = "Error: "  . $thealert;
$thegzalert = gzencode($thegzaalert);
// echo "$thegzalert";
echo "$thegzalert";
exit;
}

function addfields($thetable){
global $dbpropsString, $dbase, $db, $host, $dbuser, $dbpass, $numlines;
$db = ($GLOBALS["___mysqli_ston"] = mysqli_connect($host,  $dbuser,  $dbpass)) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
((bool)mysqli_query($db, "USE " . $dbase)) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
$query = "select * from $thetable limit 1";
$result = mysqli_query($GLOBALS["___mysqli_ston"], $query) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
$colqty = (($___mysqli_tmp = mysqli_num_fields($result)) ? $___mysqli_tmp : false) or trigger_error(sendGZAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
$count = mysqli_num_rows($result);
for ($i = 0; $i < $colqty; $i++) {
$thefield = ((($___mysqli_tmp = mysqli_fetch_field_direct($result,  $i)->name) && (!is_null($___mysqli_tmp))) ? $___mysqli_tmp : false);
$dbpropsString .= "$thefield" . "\n";
}
$dbpropsString .= "<ttok>\n";
$colqty = 0;
$numlines++;
}

function loadQfile($theqfile) {
// echo "loading qfile";
global $host, $dbuser, $dbpass, $dbase;
$filename = "dbqs/$theqfile" . ".pcdbq";
$zd = gzopen($filename, "r");
$thefcontents = gzread($zd, 1500000);
gzclose($zd);
$fcontents = $thefcontents;
// $fcontents = RTESafe($thefcontents);
// echo $fcontents;
$db = ($GLOBALS["___mysqli_ston"] = mysqli_connect($host,  $dbuser,  $dbpass)) or trigger_error(sendAlert(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
((bool)mysqli_query($db, "USE " . $dbase));
$query = explode("\n",$fcontents);
$i=0;
$idone = 0;
while($i < count($query)) {
// $qui = addslashes($query[$i]);
$qui = $query[$i];
$eri = $i + 1;
if($idone >= 100) {
$result = mysqli_query($db, $qui, MYSQLI_USE_RESULT) or trigger_error(sendAlert("Record: $eri \n\n: $qui \n\n" . ((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
sleep(1);
$idone = 0;
} else {
$result = mysqli_query($db, $qui, MYSQLI_USE_RESULT) or trigger_error(sendAlert("Record: $eri \n\n: $qui \n\n" . ((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false))));
}
$i++;
$idone++;
} 

if(($idone == 0) && ($i == 0)) {
$result =  mysqli_query($db, $fcontents);
sendGZMsg("Records Inserted");
} else {
sendGZMsg("Total Records Inserted: $i");
}
}
// phpinfo();
</script>