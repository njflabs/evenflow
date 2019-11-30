<?php
ini_set("display_errors", 1);
error_reporting(E_ALL ^ E_STRICT ^ E_DEPRECATED);
global $fJSonFldrQstring, $respstat;

// change evenflow to your database name
define("DB_DSN","mysql:host=127.12.163.130;dbname=evenflow"); // works with rhcloud and xampp
define("DB_USERNAME","evenflow");
define("DB_PASSWORD","evenflow");
define("DB_CHARACSET","utf8");
$filename = "qcache/";
$ftime = intval(time());
$dbftime = intval(time()); 
$fJSonFldrQstring = "";
$retArr = array();
$respstat = "pending";

include "Database.php";
$cdbf = new Database();


function sendGZMsg($thealert) {
$thegzalert = gzencode($thealert);
echo "$thealert";
exit;
}


function loadQfile($theqfile) {
global $cdbf;
try {
$filename = "qcomms/dbqs/$theqfile" . ".pcdbq";
$zd = gzopen($filename, "r");
$thefcontents = gzread($zd, 1500000);
gzclose($zd);
$fcontents = $thefcontents;
$gArray = json_decode($fcontents);
$i=0;
$ia = 0;
$idone = 0;
while($i < count($gArray)) {
$re = $gArray[$i];
$clist = $cdbf->fetch_custom($re);
if($idone >= 100) {
sleep(1);
$idone = 0;
}  
$i++;
$idone++;
}

if(($idone == 0) && ($i == 0)) {
sendGZMsg("Records Inserted");
} else {
sendGZMsg("Total Records Inserted: $i");
}

} catch(Exception $ex) {
sendGZMsg("loadQfile.error: " . $ex);
}

exit;
 
}

 












 



// simple function that takes a filename and the content
// as variables, then saves the file. Alerts you if $andAlert set to true
function saveFeedfile($shpfile, $shpfilehtml) {
  $cleanFileContnt = stripslashes($shpfilehtml);
   $handle = @fopen($shpfile, 'w+');
   fputs($handle, $shpfilehtml);  
   fclose($handle);
   echo $cleanFileContnt;
   exit;
 
} 



function getFFileCache($theFeedfile, $theDelaySecs) {
global $siteRootDir;


$filename = $theFeedfile;
        if ( file_exists( $filename ) ) {
            // find how long ago the file was added to the cache
            // and whether that is longer then MAX_AGE
            $mtime = filemtime( $filename );
            $age = time() - $mtime;
            if ( $theDelaySecs > $age ) {
                // object exists and is current
                return 'HIT';
            }
            else {
                // object exists but is old
                return 'STALE';
            }
        }
        else {
            // object does not exist
            return 'MISS';
        }
}

if(!empty($_POST["action"])) {
 
if($_POST["action"] == "save") {
if(!empty($_POST["fn"])) {
if(!empty($_POST["rs"])) {
$filename = "qcache/" . $_POST["fn"];
$retArr = array("status" => "saved", "data" => $_POST["rs"]);
$fJSonFldrQstring = json_encode($retArr);
$respstat = saveFeedfile($filename, $fJSonFldrQstring);
// echo $_POST["rs"] . " :: " . $respstat;
// exit;
} // rs not empty
} // fn not empty
} // action = save
 

  


if($_POST["action"] == "synch") {
try {
// if (($_FILES['filename']) && ($_POST["dbqi"])) {
$dbqi = $_POST["dbqi"];
$prodImgname = $_FILES['filename']['name'];
$prodImgnamet = $_FILES['filename']['tmp_name'];
$thefilename = "qcomms/dbqs/$dbqi" . ".pcdbq";
if (file_exists($thefilename)){
unlink($thefilename);
}
sleep(1);
if(!copy($prodImgnamet, "$thefilename")){
sendGZMsg("Error: Could not copy file");
} else {
loadQfile($dbqi);
} 
// } else {
// sendGZMsg("Error: Filename var missing  :: $filename :: $dbqi :: $prodImgnamet :: $thefilename");
// }

 
} catch(Exception $ex) {
sendGZMsg("synch.error: " . $ex);

}
exit;
} // action = synch


} // action not empty

if(!empty($_GET["ts"])) {
$dbftime = $_GET["ts"];
}
if(!empty($_GET["fn"])) {

if($_GET["fn"] == "noQvalue") {

} else {

$filename = "qcache/" . $_GET["fn"];
if(file_exists($filename)) {
$ftime = filemtime($filename);
if(!empty($_GET["fc"])) {
if($_GET["fc"] == "y") {
$respstat = "nofile";
} else {
$respstat = "hasfile";
include $filename;
exit;
}
} else {
$respstat = "hasfile";
include $filename;
exit;
}

} else {
 
$respstat = "nofile";


} // file not exists
} // fn not noQvalue
} // fn not empty
 

if(!empty($_GET['q'])) {
$qq = $_GET['q'];


$gArray = array();
$values = array();
$retval = array();
$rType = substr($qq, 0, 6);
if(stristr($rType,"batch")) {
$gArray = json_decode(substr($qq, 5));
 
// $clist = $cdbf->fetch_custom($qui);

$i=0;
$ia = 0;
$idone = 0;
while($i < count($gArray)) {
$re = $gArray[$i];
$tmpA = null;
$tmpA = array();
$fArr = array();
$clist = $cdbf->fetch_custom($re->v);
 

foreach ($clist as $myrow) {

 // array_push($tmpA,$myrow);    
 $tmpA = $tmpA + array($myrow);
}

$tarr = array("f" => $re->f, "v" => $tmpA, "e" => $re->e);
$farr = array($re->e => $tarr); 

$retval = $retval + array($re->e => $tarr);
// array_push($retval,$farr);
// $retval = $retval + $tarr;
// $re = null;
// unset($tmpA);$tmpA = array(); 
// unset($tarr);$tarr = array(); 
$i++;
}
// $tval = null;
$retArr = array("status" => $respstat, "data" => $retval);
$fJSonFldrQstring = json_encode($retval);
// echo "[" . $fJSonFldrQstring . "]";
// echo  $fJSonFldrQstring;

 
// print_r($retval);
// exit;
 

} else if(stristr($rType,"synch")) {
 


$gArray = json_decode(substr($qq, 5, -1));
 
// $clist = $cdbf->fetch_custom($qui);

$i=0;
$ia = 0;
$idone = 0;
while($i < count($gArray)) {
$re = $gArray[$i];
$tmpA = null;
$tmpA = array();
$fArr = array();
 
$clist = $cdbf->fetch_custom($re->v);
$tmpR = "Updating item: " . $i;
$tarr = array("f" => $re->f, "v" => $tmpR, "e" => $re->e);
array_push($retval,$tarr);
$i++;
}
// $tval = null;
$retArr = array("status" => $respstat, "data" => $retval);
$fJSonFldrQstring = json_encode($retval);
// echo "[" . $fJSonFldrQstring . "]";
// echo  $fJSonFldrQstring;

 
// print_r($retval);
// exit;

} else {
try {
$clist = $cdbf->fetch_custom($qq);
$rType = substr($qq, 0, 7);
if(stristr($rType,"select")) {
foreach ($clist as $myrow) {
	$i = 0;

            array_push($values,$myrow);    
		$i++;
 
}
$retArr = array("status" => $respstat, "data" => $values);
$fJSonFldrQstring = json_encode($values);

} else { // not a select, return something else 
$retArr = array("status" => $rType, "data" => json_encode($clist));
$fJSonFldrQstring = json_encode($retArr);
// $fJSonFldrQstring = "not returning on $qq";
}
// echo $fJSonFldrQstring;

// exit;
} catch(Exception $ex) {
// print_r($qq . $ex);
$retArr = array("status" => "error", "data" => addslashes($qq . $ex));
$fJSonFldrQstring = json_encode($retArr);
}


}

if(!empty($_GET["ts"])) {
$fJSonFldrQstring = json_encode($retArr);
}

 
if($respstat == "nofile") {
$fJSonFldrQstring = json_encode($retArr);
if(!empty($_GET["lz"])) {
if($_GET["lz"] == "y") {
} else {
$retArr["status"] = "saved";
$tJSonFldrQstring = json_encode($retArr);
$savedStat = saveFeedfile($filename, $tJSonFldrQstring);
}
}
}
 

} else {
echo "noQ";
}



echo stripslashes($fJSonFldrQstring);

 
exit;
?>