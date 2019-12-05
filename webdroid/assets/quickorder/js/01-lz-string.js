var LZString=(function(){var c=String.fromCharCode;var g="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";var b="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+-$";var e={};function a(j,h){if(!e[j]){e[j]={};for(var f=0;f<j.length;f++){e[j][j.charAt(f)]=f;}}return e[j][h];}var d={compressToBase64:function(f){if(f==null){return"";}var h=d._compress(f,6,function(i){return g.charAt(i);});switch(h.length%4){default:case 0:return h;case 1:return h+"===";case 2:return h+"==";case 3:return h+"=";}},decompressFromBase64:function(f){if(f==null){return"";}if(f==""){return null;}return d._decompress(f.length,32,function(h){return a(g,f.charAt(h));});},compressToUTF16:function(f){if(f==null){return"";}return d._compress(f,15,function(h){return c(h+32);})+" ";},decompressFromUTF16:function(f){if(f==null){return"";}if(f==""){return null;}return d._decompress(f.length,16384,function(h){return f.charCodeAt(h)-32;});},compressToUint8Array:function(m){var l=d.compress(m);var h=new Uint8Array(l.length*2);for(var k=0,j=l.length;k<j;k++){var f=l.charCodeAt(k);h[k*2]=f>>>8;h[k*2+1]=f%256;}return h;},decompressFromUint8Array:function(l){if(l===null||l===undefined){return d.decompress(l);}else{var h=new Array(l.length/2);for(var k=0,j=h.length;k<j;k++){h[k]=l[k*2]*256+l[k*2+1];}var f=[];h.forEach(function(i){f.push(c(i));});return d.decompress(f.join(""));}},compressToEncodedURIComponent:function(f){if(f==null){return"";}return d._compress(f,6,function(h){return b.charAt(h);});},decompressFromEncodedURIComponent:function(f){try{if(f==null){return"";}if(f==""){return null;}if(f.indexOf(" ")!=-1){f=f.replace(/ /g,"+");}return d._decompress(f.length,32,function(i){return a(b,f.charAt(i));});}catch(h){alert("decompressFromEncodedURIComponent: "+JSON.stringify(f));return f;}},compress:function(f){return d._compress(f,16,function(h){return c(h);});},_compress:function(l,w,q){if(l==null){return"";}var n,r,t={},s={},u="",j="",x="",k=2,m=3,h=2,p=[],f=0,o=0,v;for(v=0;v<l.length;v+=1){u=l.charAt(v);if(!Object.prototype.hasOwnProperty.call(t,u)){t[u]=m++;s[u]=true;}j=x+u;if(Object.prototype.hasOwnProperty.call(t,j)){x=j;}else{if(Object.prototype.hasOwnProperty.call(s,x)){if(x.charCodeAt(0)<256){for(n=0;n<h;n++){f=(f<<1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}}r=x.charCodeAt(0);for(n=0;n<8;n++){f=(f<<1)|(r&1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=r>>1;}}else{r=1;for(n=0;n<h;n++){f=(f<<1)|r;if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=0;}r=x.charCodeAt(0);for(n=0;n<16;n++){f=(f<<1)|(r&1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=r>>1;}}k--;if(k==0){k=Math.pow(2,h);h++;}delete s[x];}else{r=t[x];for(n=0;n<h;n++){f=(f<<1)|(r&1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=r>>1;}}k--;if(k==0){k=Math.pow(2,h);h++;}t[j]=m++;x=String(u);}}if(x!==""){if(Object.prototype.hasOwnProperty.call(s,x)){if(x.charCodeAt(0)<256){for(n=0;n<h;n++){f=(f<<1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}}r=x.charCodeAt(0);for(n=0;n<8;n++){f=(f<<1)|(r&1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=r>>1;}}else{r=1;for(n=0;n<h;n++){f=(f<<1)|r;if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=0;}r=x.charCodeAt(0);for(n=0;n<16;n++){f=(f<<1)|(r&1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=r>>1;}}k--;if(k==0){k=Math.pow(2,h);h++;}delete s[x];}else{r=t[x];for(n=0;n<h;n++){f=(f<<1)|(r&1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=r>>1;}}k--;if(k==0){k=Math.pow(2,h);h++;}}r=2;for(n=0;n<h;n++){f=(f<<1)|(r&1);if(o==w-1){o=0;p.push(q(f));f=0;}else{o++;}r=r>>1;}while(true){f=(f<<1);if(o==w-1){p.push(q(f));break;}else{o++;}}return p.join("");},decompress:function(f){if(f==null){return"";}if(f==""){return null;}return d._decompress(f.length,32768,function(h){return f.charCodeAt(h);});},_decompress:function(j,k,f){var q=[],s,m=4,t=4,r=3,h="",o=[],v,p,u,y,l,n,x,z={val:f(0),position:k,index:1};for(v=0;v<3;v+=1){q[v]=v;}u=0;l=Math.pow(2,2);n=1;while(n!=l){y=z.val&z.position;z.position>>=1;if(z.position==0){z.position=k;z.val=f(z.index++);}u|=(y>0?1:0)*n;n<<=1;}switch(s=u){case 0:u=0;l=Math.pow(2,8);n=1;while(n!=l){y=z.val&z.position;z.position>>=1;if(z.position==0){z.position=k;z.val=f(z.index++);}u|=(y>0?1:0)*n;n<<=1;}x=c(u);break;case 1:u=0;l=Math.pow(2,16);n=1;while(n!=l){y=z.val&z.position;z.position>>=1;if(z.position==0){z.position=k;z.val=f(z.index++);}u|=(y>0?1:0)*n;n<<=1;}x=c(u);break;case 2:return"";}q[3]=x;p=x;o.push(x);while(true){if(z.index>j){return"";}u=0;l=Math.pow(2,r);n=1;while(n!=l){y=z.val&z.position;z.position>>=1;if(z.position==0){z.position=k;z.val=f(z.index++);}u|=(y>0?1:0)*n;n<<=1;}switch(x=u){case 0:u=0;l=Math.pow(2,8);n=1;while(n!=l){y=z.val&z.position;z.position>>=1;if(z.position==0){z.position=k;z.val=f(z.index++);}u|=(y>0?1:0)*n;n<<=1;}q[t++]=c(u);x=t-1;m--;break;case 1:u=0;l=Math.pow(2,16);n=1;while(n!=l){y=z.val&z.position;z.position>>=1;if(z.position==0){z.position=k;z.val=f(z.index++);}u|=(y>0?1:0)*n;n<<=1;}q[t++]=c(u);x=t-1;m--;break;case 2:return o.join("");}if(m==0){m=Math.pow(2,r);r++;}if(q[x]){h=q[x];}else{if(x===t){h=p+p.charAt(0);}else{return null;}}o.push(h);q[t++]=p+h.charAt(0);m--;p=h;if(m==0){m=Math.pow(2,r);r++;}}}};return d;})();if(typeof define==="function"&&define.amd){define(function(){return LZString;});}else{if(typeof module!=="undefined"&&module!=null){module.exports=LZString;}else{if(typeof angular!=="undefined"&&angular!=null){angular.module("LZString",[]).factory("LZString",function(){return LZString;});}}}