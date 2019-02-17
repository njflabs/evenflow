<?php
global $gvals;
function RemoveXSS($val) { 
   $val = preg_replace('/([\x00-\x08][\x0b-\x0c][\x0e-\x20])/', '', $val); 
   $search = 'abcdefghijklmnopqrstuvwxyz'; 
   $search .= 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'; 
   $search .= '1234567890!@#$%^&*()'; 
   $search .= '~`";:?+/={}[]-_|\'\\'; 
   for ($i = 0; $i < strlen($search); $i++) { 
      $val = preg_replace('/(&#[x|X]0{0,8}'.dechex(ord($search[$i])).';?)/i', $search[$i], $val); // with a ; 
      $val = preg_replace('/(&#0{0,8}'.ord($search[$i]).';?)/', $search[$i], $val); // with a ; 
   } 
   // now the only remaining whitespace attacks are \t, \n, and \r 
   $ra1 = Array('javascript', 'vbscript', 'expression', 'applet', 'meta', 'xml', 'blink', 'link', 'style', 'script', 'embed', 'object', 'iframe', 'frame', 'frameset', 'ilayer', 'layer', 'bgsound','base'); 

   // $ra1 = Array('javascript', 'vbscript', 'expression', 'applet', 'meta', 'xml', 'blink', 'link', 'style', 'script', 'embed', 'object', 'iframe', 'frame', 'frameset', 'ilayer', 'layer', 'bgsound', 'title', 'base'); 
   $ra2 = Array('onabort', 'onactivate', 'onafterprint', 'onafterupdate', 'onbeforeactivate', 'onbeforecopy', 'onbeforecut', 'onbeforedeactivate', 'onbeforeeditfocus', 'onbeforepaste', 'onbeforeprint', 'onbeforeunload', 'onbeforeupdate', 'onblur', 'onbounce', 'oncellchange', 'onchange', 'onclick', 'oncontextmenu', 'oncontrolselect', 'oncopy', 'oncut', 'ondataavailable', 'ondatasetchanged', 'ondatasetcomplete', 'ondblclick', 'ondeactivate', 'ondrag', 'ondragend', 'ondragenter', 'ondragleave', 'ondragover', 'ondragstart', 'ondrop', 'onerror', 'onerrorupdate', 'onfilterchange', 'onfinish', 'onfocus', 'onfocusin', 'onfocusout', 'onhelp', 'onkeydown', 'onkeypress', 'onkeyup', 'onlayoutcomplete', 'onload', 'onlosecapture', 'onmousedown', 'onmouseenter', 'onmouseleave', 'onmousemove', 'onmouseout', 'onmouseover', 'onmouseup', 'onmousewheel', 'onmove', 'onmoveend', 'onmovestart', 'onpaste', 'onpropertychange', 'onreadystatechange', 'onreset', 'onresize', 'onresizeend', 'onresizestart', 'onrowenter', 'onrowexit', 'onrowsdelete', 'onrowsinserted', 'onscroll', 'onselect', 'onselectionchange', 'onselectstart', 'onstart', 'onstop', 'onsubmit', 'onunload'); 
   $ra = array_merge($ra1, $ra2); 
   $found = true; // keep replacing as long as the previous round replaced something 
   while ($found == true) { 
      $val_before = $val; 
      for ($i = 0; $i < sizeof($ra); $i++) { 
         $pattern = '/'; 
         for ($j = 0; $j < strlen($ra[$i]); $j++) { 
            if ($j > 0) { 
               $pattern .= '('; 
               $pattern .= '(&#[x|X]0{0,8}([9][a][b]);?)?'; 
               $pattern .= '|(&#0{0,8}([9][10][13]);?)?'; 
               $pattern .= ')?'; 
            } 
            $pattern .= $ra[$i][$j]; 
         } 
         $pattern .= '/i'; 
         $replacement = substr($ra[$i], 0, 2).'<x>'.substr($ra[$i], 2); // add in <> to nerf the tag 
         $val = preg_replace($pattern, $replacement, $val); // filter out the hex tags 
         if ($val_before == $val) { 
            // no replacements were made, so exit the loop 
            $found = false; 
         } 
      } 
   } 
   return $val;
} 

function getGvalKeyVal($tGVK, $tDefGVKVal) {
global $gvals;
if(array_key_exists($tGVK, $gvals))  {
if(strlen($gvals[$tGVK]) > 0) {
return $gvals[$tGVK];
} else {
return $tDefGVKVal;
}
} else {
return $tDefGVKVal;
}
}

function setGvalKeyVal($tGVK, $tDefGVKVal) {
global $gvals;
$gvals = $gvals + array("$tGVK"=>"$tDefGVKVal");
}

function getGvals() {
$tmpGvals = array();
foreach($_REQUEST as $key=>$value) { 
    $tmpRkey = $_REQUEST[$key];
    $_REQUEST[$key] = $value; 
    if (get_magic_quotes_gpc()==0) { $value = addslashes($value); } 
    if (isset($_POST[$key])) { $_POST[$key] = $value; $tmpRkey = $_POST[$key];} 
    if (isset($_COOKIE[$key])) { $_COOKIE[$key] = $value; $tmpRkey = $_COOKIE[$key];} 
    if (isset($_FILES[$key])) { $_FILES[$key] = $value; $tmpRkey = $_FILES[$key];} 
    if (isset($_GET[$key])) { $_GET[$key] = $value; $tmpRkey = $_GET[$key];} 
    if (isset($HTTP_POST_VARS[$key])) { $HTTP_POST_VARS[$key] = $value; $tmpRkey = $HTTP_POST_VARS[$key];} 
    if (isset($HTTP_COOKIE_VARS[$key])) { $HTTP_COOKIE_VARS[$key] = $value; $tmpRkey = $HTTP_COOKIE_VARS[$key];} 
    if (isset($HTTP_FILE_VARS[$key])) { $HTTP_FILE_VARS[$key] = $value; $tmpRkey = $HTTP_FILE_VARS[$key];} 
    if (isset($HTTP_GET_VARS[$key])) { $HTTP_GET_VARS[$key] = $value; $tmpRkey = $HTTP_GET_VARS[$key];}  
    $value = RemoveXSS($value);
    ${$key} = $value; 
  //  echo $key . "=> $value      ::      ";
	$tmpRkey = (string)$key;
    $tmpGvals = $tmpGvals + array("$tmpRkey"=>"$value");
}
return $tmpGvals;
}
foreach($_REQUEST as $key=>$value) { 
    $_REQUEST[$key] = $value; 
    if (get_magic_quotes_gpc()==0) { $value = addslashes($value); } 
    if (isset($_POST[$key])) { $_POST[$key] = $value; $tmpRkey = $_POST[$key];} 
    if (isset($_COOKIE[$key])) { $_COOKIE[$key] = $value; $tmpRkey = $_COOKIE[$key];} 
    if (isset($_FILES[$key])) { $_FILES[$key] = $value; $tmpRkey = $_FILES[$key];} 
    if (isset($_GET[$key])) { $_GET[$key] = $value; $tmpRkey = $_GET[$key];} 
    if (isset($HTTP_POST_VARS[$key])) { $HTTP_POST_VARS[$key] = $value; $tmpRkey = $HTTP_POST_VARS[$key];} 
    if (isset($HTTP_COOKIE_VARS[$key])) { $HTTP_COOKIE_VARS[$key] = $value; $tmpRkey = $HTTP_COOKIE_VARS[$key];} 
    if (isset($HTTP_FILE_VARS[$key])) { $HTTP_FILE_VARS[$key] = $value; $tmpRkey = $HTTP_FILE_VARS[$key];} 
    if (isset($HTTP_GET_VARS[$key])) { $HTTP_GET_VARS[$key] = $value; $tmpRkey = $HTTP_GET_VARS[$key];}  
    $value = RemoveXSS($value);
    ${$key} = $value; 
}
$gvals = getGvals();
?>