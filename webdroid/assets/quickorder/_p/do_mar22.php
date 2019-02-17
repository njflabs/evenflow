<?php
ini_set("display_errors", 1);
error_reporting(E_ALL ^ E_STRICT ^ E_DEPRECATED);
global $fJSonFldrQstring;
include 'LZCompressor/LZString.php';
include 'LZCompressor/LZContext.php';
include 'LZCompressor/LZData.php';
include 'LZCompressor/LZUtil.php';
include 'LZCompressor/LZUtil16.php';
define("DB_DSN","mysql:host=127.12.163.130;dbname=qbits"); // works with rhcloud and xampp
define("DB_USERNAME","qbits");
define("DB_PASSWORD","qbits");
define("DB_CHARACSET","utf8");
$filename = "qcache/";
$ftime = intval(time());
$dbftime = intval(time()); 
$fJSonFldrQstring = "";
$respstat = "n";
use LZCompressor\LZString as LZString;
 

// simple function that takes a filename and the content
// as variables, then saves the file. Alerts you if $andAlert set to true
function saveFeedfile($shpfile, $shpfilehtml) {
 
  //  $cleanFileContnt = stripslashes($shpfilehtml);


   $handle = fopen($shpfile, 'w');
   fputs($handle, $shpfilehtml);  
   // chmod("$docroot" . $shpfile, 0777);   
   fclose($handle);
   // chmod("$docroot" . $shpfile, 1777);  
 
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

if(!empty($_GET["ts"])) {
$dbftime = $_GET["ts"];
}
if(!empty($_GET["fn"])) {
if($_GET["fn"] == "noQvalue") {
} else {
$filename = "qcache/" . $_GET["fn"];
if(file_exists($filename)) {
$ftime = filemtime($filename);
$respstat = "y";
include $filename;
exit;
}
}
} 

if(!empty($_GET['q'])) {
$qq = $_GET['q'];

include "Database.php";
$cdbf = new Database();
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
$fJSonFldrQstring = json_encode($retval);
// echo "[" . $fJSonFldrQstring . "]";
// echo  $fJSonFldrQstring;

 
// print_r($retval);
// exit;

} else {
try {
$clist = $cdbf->fetch_custom($qq);

foreach ($clist as $myrow) {
	$i = 0;

            array_push($values,$myrow);    
		$i++;
 
}
$fJSonFldrQstring = json_encode($values);
// echo $fJSonFldrQstring;

// exit;
} catch(Exception $ex) {
print_r($ex);
}


}

 

} else {
echo "noQ";
}



// echo LZString::compress($fJSonFldrQstring);
// $fstr = LZString::compressToEncodedURIComponent($fJSonFldrQstring);
// saveFeedfile($filename . "n-na.txt", $fJSonFldrQstring);
// saveFeedfile($filename . "n-ca.txt", $fstr);
if(!empty($_GET['lz'])) {
if($_GET['lz'] == "y") {
$tstr = LZString::compressToEncodedURIComponent($fJSonFldrQstring);
$fJSonFldrQstring = $tstr;
// echo $fstr;
}
}

echo $fJSonFldrQstring;

if(!empty($_GET["fn"])) {
if($_GET["fn"] == "noQvalue") {
} else {
 try {
saveFeedfile($filename, $fJSonFldrQstring);
} catch(Exception $ex) {
print_r($ex);
}
}
}
// echo $fJSonFldrQstring;
exit;
?>