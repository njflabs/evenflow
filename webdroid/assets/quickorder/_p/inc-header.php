<?php
global $pgTitle, $pgDesc, $siteTitle, $doPHPUse, $fArr;
global $siteWebDir, $siteRootDir;
$doPHPUse = "yes";
$siteTitle = "Our Site";
$pgTitle = "Page Title";
$pgDesc = "Page Desc";
$pgKeywords = "Page Keywords"; 
ini_set("display_errors", 1);
error_reporting(E_ALL ^ E_STRICT);
$fArr = array("tplates/index_header.html", "tplates/index_nav.html", "tplates/produtos_main.html", "tplates/index_footer.html");
?>