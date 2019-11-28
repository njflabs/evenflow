<script language="php">
@header("Status: 200 OK");
global $receiver_email, $business, $item_name, $item_name1, $item_number, $item_number1, $affreward;
global $quantity, $invoice, $custom, $option_name1, $amount3;
global $option_selection1, $option_name2, $option_selection2, $num_cart_items;
global $payment_status, $pending_reason, $payment_date, $settle_amount;
global $settle_currency, $exchange_rate, $payment_gross, $payment_fee;
global $mc_gross, $mc_fee, $mc_currency, $tax, $txn_id, $subscr_id, $txn_type, $for_auction;
global $memo, $first_name, $last_name, $address_street, $address_city;
global $address_state, $address_zip, $address_country, $address_status;
global $payer_email, $payer_id, $payer_status;
global $payment_type, $notify_version, $verify_sign;
global $dumpString, $usePubs, $reply, $report, $pubString, $ipnTesting, $ttlDumpString, $docroot;
include "dourlparams.php"; /// must be included
$igateway = "paypal";
$isharedValueA = "0";
$isharedValueB = "0";
$isharedValueC = "0";
$pubString = "";
$reply = "";
$report = "";
$ipnreportString = "";
$postipn = "cmd=_notify-validate";
$noteipn = "<b>$igateway posted variables in order of appearance:</b><br><br>";
$postnum = 0;
$ttlDumpString = "";
$ttime = intval(time());
while(list($postkey, $postval) = each($HTTP_POST_VARS)) {
$postnum++; 
$postval = stripslashes($postval);
$postval = urlencode($postval);
$postipn .= "&" . $postkey . "=" . $postval;
$noteipn .= "<b>#" . $postnum . "</b> Key: " . $postkey . " <b>=</b>     " . $postval . "<br>";
} 
$pstheader = "POST /cgi-bin/webscr HTTP/1.0\r\n";
$pstheader.= "Host: paypal.com\r\n";
$pstheader.= "Content-Type: application/x-www-form-urlencoded\r\n";
$pstheader.= "Content-Length: " . strlen($postipn) . "\r\n\r\n";
if($ipnTesting == "OFF") {
$socket = fsockopen ("www.paypal.com", 80, $errno, $errstr, 30);
} else {
$socket = fsockopen ("www.sandbox.paypal.com", 80, $errno, $errstr, 30);
}
if (!$socket) {
$report .=  "Problem: Error Number: " . $errno . " Error String: " . $errstr . "<br>\n";
}
else {
fputs ($socket, $pstheader . $postipn);
while (!feof($socket)) {
$reply .= fgets($socket, 1024);
}
}
fclose($socket);
$report .= "$noteipn<br>$reply";
if($custom) { // check for the custom string to work with cart_info
$icinfoString = "$custom";
} else {
$icinfoString = "X0";
$ipnreportString .= "Could not get [custom] on IPN callback<br>";
// fatal error, add dump and end functions
}
if($mc_gross) { // check the total amount
$itotalvalue = "$mc_gross";
} else 
if($amount3) {
$itotalvalue = "$amount3";
} else {
$itotalvalue = "X0";
$ipnreportString .= "Could not get [mc_gross] on IPN callback<br>";
// fatal error, add dump and end functions
}
if($payer_email) { // get the payers gateway email address
$payer_email = str_replace("%40", "@", $payer_email);
$iuseremail = "$payer_email";
} else {
$iuseremail = "X0";
$ipnreportString .= "Could not get [payer_email] on IPN callback<br>";
}
if($mc_currency) { // get the currency used to pay
$icurrencycode = "$mc_currency";
} else {
$icurrencycode = "X0";
$ipnreportString .= "Could not get [mc_currency] on IPN callback<br>";
}
if($address_status) { // get if the payers address is verified or not by gateway
$iaddressconf = "$address_status";
} else {
$iaddressconf = "X0";
$ipnreportString .= "Could not get [address_status] on IPN callback<br>";
}
if($payer_status) { // get if the payer is verified or not by gateway
$igwayverified = "$payer_status";
} else {
$igwayverified = "X0";
$ipnreportString .= "Could not get [payer_status] on IPN callback<br>";
}
if($payment_status) { // get the payment status for this order only on cart, etc. or subscr_payment
$itransstatus = "$payment_status";
} else {
$itransstatus = "X0";
$ipnreportString .= "Could not get [payment_status] on IPN callback<br>";
}
if($txn_type) { // get the transaction type subscr_, cart
$itranstype = "$txn_type";
if(stristr($txn_type, "payment")) { // check if its a subscription signup
$itransstatus = "complete"; // set the trans status to complete, its a paymeny, there is no $payer_status
}
} else {
$itranstype = "X0";
$ipnreportString .= "Could not get [txn_type] on IPN callback<br>";
}
if($txn_id) {  // for TransID, if cart, use the txn_id,  else if subscr use the subscr_id
$itransid = "$txn_id";
} else 
if($subscr_id) {
$itransid = "$subscr_id";
} else {
$itransid = "X0";
$ipnreportString .= "Could not get [txn_id] or [subscr_id] on IPN callback<br>";
}
if($item_number1) { // get the payment status for this order only on car, etc. or subscr_payment
$iprodID = "$item_number1";
} else
if($item_number) {
$iprodID = "$item_number";
} else {
$iprodID = "X0";
$ipnreportString .= "Could not get [item_number]  on IPN callback<br>";
}
if($mc_fee) { // the fee associated with transactions, only for payed transactions.
$itransfee = "$mc_fee";
} else {
$itransfee = "X0";
$ipnreportString .= "Could not get [mc_fee] on IPN callback<br>";
}
if($item_name) {
// pass the subscription title as the custom var
// that way, we dont have to look it up in the notify tasks
// Format and get rid of + as blank spaces
$isharedValueA = urldecode($item_name);
}
$report .= "<br><br>$igateway IPN Report:<br>$ipnreportString";

 

 
$report .= "<br><br><b>Cart Info strings: <a href=\"index.php?proMod=admintools/db_record-edit&t=cart_info&f=CartNumber&v=$icinfoString\">cart_info</a>&nbsp;&nbsp";
$report .= "<a href=\"index.php?proMod=admintools/db_record-edit&t=ipn_posts&f=IpnCinfoStrng&v=$icinfoString\">IPN Post Vars</a></b><br><br><hr>";

   $shpfile = "qcomms/pp-" . $custom . ".html";  // the dumped file name
   $cleanFileContnt = stripslashes($report);
   $handle = fopen($shpfile, "a+");
   fputs($handle, $cleanFileContnt);     
   fclose($handle);
echo $cleanFileContnt;
</script>
