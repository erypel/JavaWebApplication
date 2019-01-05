/**
 * boilerplate code to wrap custom code in RippleAPI
 * 
 * https://developers.ripple.com/rippleapi-reference.html#main_content_wrapper
 */ 
//Can't use ECMS6 because the ScriptEngine in Java doesn't support it cleanly
var RippleAPI = require('ripple-lib').RippleAPI;
var api = new RippleAPI({
	server: "wss://s.altnet.rippletest.net:51233" // test server
});

api.on('error', (errorCode, errorMessage) => {
	  console.log(errorCode + ': ' + errorMessage);
	});
api.on('connected', () => {
	  console.log('connected');
	});

api.on('disconnected', (code) => {
	// code - [close
	// code](https://developer.mozilla.org/en-US/docs/Web/API/CloseEvent) sent
	// by the server
	// will be 1000 if this was normal closure
	console.log('disconnected, code:', code);
	});
	
api.connect().then(() => {
	  /* insert code here */
	}).then(() => {
	  return api.disconnect();
	}).catch(console.error);

function sign(txJSON, secret) {
	return api.sign(txJSON, secret);
}

function submit() {
	
}

function verify() {
	
}