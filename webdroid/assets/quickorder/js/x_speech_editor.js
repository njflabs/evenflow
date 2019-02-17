var words = [];
var counts = [];
var choices = [];
var intChoices = 0;


function oldcalculate(result) {
    for (var i=0; i<result.length; i++) {
        if (array_contains(words, result[i])) {
            counts[result[i]]++;
        } else {
            words.push(result[i]);
            counts.push(1);
        }
    }
}


function dodidi(perc, result) {
 alert(result);
}


function calculate(inputs) {
    var outStr = "";
    for (var i = 0; i < inputs.length; i++) {
        var isExist = false;
        for (var j = 0; j < words.length; j++) {
            if (inputs[i] == words[j]) {
                isExist = true;
                counts[i] = counts[i] + 1;
		    outStr += inputs[i] + " ";		
            }
        }
        if (!isExist) {
            words.push(inputs[i]);
            counts.push(1);
		outStr += "<b>" + inputs[i] + "</b> ";			
        }
        isExist = false;
    }
	choices[intChoices] = outStr;
	intChoices++;
}




function array_contains(array, value) {
    for (var i=0; i<array.length; i++) {
        if (array[i] == value) {
            return true;
	  } else {
    return false;
		}
	}
}



function runCalcs() {
var allw = document.getElementById('txtSpeech').value;
var pair = [];
  var vars = allw.split("|:|");
  for (var i=0;i<vars.length;i++) {
     pair[i] = vars[i].split(" ");
	calculate(pair[i]);
    }
 
mDiv = document.getElementById("dvMain");

 


document.getElementById('dvSpeech').innerHTML = JSON.stringify(words);
document.getElementById('dvSpeech').innerHTML = document.getElementById('dvSpeech').innerHTML + "<br>" + JSON.stringify(counts);


  for (var iaa=0;iaa<choices.length;iaa++) {
	newel = document.createElement('div');
	newel.className = "searchBStoryDiv";

	newelCD = document.createElement('div'); 
	newelCD.contentEditable='true';
	newelCD.innerHTML = choices[iaa];
	newelCD.id = "dvSpcEdt" + iaa;

	newelBD = document.createElement('div'); 
	astrt =  JSSHOP.ui.doDefBtn("S", "app_dlg.getEpMDcom('125',dvSpcEdt" + iaa + ".innerText)");
	bstrt =  JSSHOP.ui.doDefBtn("C", "app_dlg.getEpMDcom('130',dvSpcEdt" + iaa + ".innerText)");
	newelBD.innerHTML = astrt + bstrt;
	newel.appendChild(newelCD);
	newel.appendChild(newelBD);


	mDiv.appendChild(newel);
    }



}

function doAppTest() {
try {
// document.getElementById('dvSpeech').innerHTML = "App test works";
runCalcs();
}catch(e) {
document.getElementById('dvSpeech').innerHTML = e;

}
}

 


