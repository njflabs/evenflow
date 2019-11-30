<?php
global $in;
ini_set("display_errors", 1);
error_reporting(E_ALL ^ E_STRICT ^ E_DEPRECATED);

function makeThumbnails($updir, $img, $id)
{
    $thumbnail_width = 84;
    $thumbnail_height = 84;
    $thumb_beforeword = "thumb";
    $arr_image_details = getimagesize("$updir" . '/' . $img); // pass id to thumb name
    $original_width = $arr_image_details[0];
    $original_height = $arr_image_details[1];
    if ($original_width > $original_height) {
        $new_width = $thumbnail_width;
        $new_height = intval($original_height * $new_width / $original_width);
    } else {
        $new_height = $thumbnail_height;
        $new_width = intval($original_width * $new_height / $original_height);
    }
    $dest_x = intval(($thumbnail_width - $new_width) / 2);
    $dest_y = intval(($thumbnail_height - $new_height) / 2);
    if ($arr_image_details[2] == IMAGETYPE_GIF) {
        $imgt = "ImageGIF";
        $imgcreatefrom = "ImageCreateFromGIF";
    }
    if ($arr_image_details[2] == IMAGETYPE_JPEG) {
        $imgt = "ImageJPEG";
        $imgcreatefrom = "ImageCreateFromJPEG";
    }
    if ($arr_image_details[2] == IMAGETYPE_PNG) {
        $imgt = "ImagePNG";
        $imgcreatefrom = "ImageCreateFromPNG";
    }
    if ($imgt) {
        $old_image = $imgcreatefrom("$updir" . '/' . "$img");
        $new_image = imagecreatetruecolor($thumbnail_width, $thumbnail_height);


switch ($imgt) {

    case 'ImageGIF':
    case 'ImagePNG':
        // integer representation of the color black (rgb: 0,0,0)
        $background = imagecolorallocate($new_image, 0, 0, 0);
        // removing the black from the placeholder
        imagecolortransparent($new_image, $background);

    case 'ImagePNG':
        // turning off alpha blending (to ensure alpha channel information
        // is preserved, rather than removed (blending with the rest of the
        // image in the form of black))
        imagealphablending($new_image, false);

        // turning on alpha channel information saving (to ensure the full range
        // of transparency is preserved)
        imagesavealpha($new_image, true);
        break;

    default:
        break;
}


        imagecopyresized($new_image, $old_image, $dest_x, $dest_y, 0, 0, $new_width, $new_height, $original_width, $original_height);
        $imgt($new_image, "$updir" . '/s_' . "$thumb_beforeword" . "$img");
    }
 echo json_encode(array('in' => $img, 'success' => true, 'msg' => 'done', 'id' => $id));
 exit;
}
require(dirname(__FILE__) . '/Uploader.php');


// Directory where we're storing uploaded images
// Remember to set correct permissions or it won't work
$upload_dir = '../images/pimgs';
$iid = "123";
if(isset($_REQUEST['in'])) {
$in = $_REQUEST['in'];
}
if(isset($_POST['in'])) {
$in = $_POST['in'];
}
if(isset($_REQUEST['iid'])) {
$iid = $_REQUEST['iid'];
}
if(isset($_POST['iid'])) {
$iid = $_POST['iid'];
}

$uploader = new FileUpload('uploadfile');
$nf = $in;
$uploader->setFileName($nf);
// $uploader->setFileName($nf);
// Handle the upload
$result = $uploader->handleUpload($upload_dir);

if (!$result) {
makeThumbnails($upload_dir, $nf, $iid);
// echo json_encode(array('in' => $nf, 'success' => false, 'msg' => $uploader->getErrorMsg()));  
} else {
makeThumbnails($upload_dir, $nf, $iid);
// echo json_encode(array('in' => $nf, 'success' => true, 'msg' => $uploader->getErrorMsg()));
 }



?>