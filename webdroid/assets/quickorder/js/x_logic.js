var doAddCat = function() {
};
var doEditCat = function() {
if(ppid == 0) {
} else {
doQComm('select * from prod_categories where _id = ' + ppid + ' order by _id desc limit 1', null, pfDRet)
}
}; 