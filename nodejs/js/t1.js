//var encrypt = require('./encrypt');
var encrypt = require('./encrypt-0.1');

//var src = 'Hello,CryptWorld';
var src = 'Hello,CryptWorld|ok|1934';

var enc = encrypt.hashx('Hello,CryptWorld', 'ok', '1934');
var dec = encrypt.decx(enc);

//var enc = encrypt.encrypt(src);
//var dec = encrypt.decrypt(enc);
//
console.log(src);
console.log(enc);
console.dir(dec);
