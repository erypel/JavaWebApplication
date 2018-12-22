// Web Socket
let webSocket = new WebSocket("ws://localhost:8080/JavaWebApplication/xrpWebWocketEndpoint");

// Interact with HTML elements
let echoText = document.getElementById("echoText");
echoText.value = "";
let message = document.getElementById("wsMessage");

// assign functions to the websocket
webSocket.onopen = function(message){ wsOpen(message); };
webSocket.onmessage = function(message){ wsGetMessage(message); };
webSocket.onclose = function(message){ wsClose(message); };
webSocket.onerror = function(message){ wsError(message); };

/**
 * Send Message
 * 
 * when the button echo is clicked, this function is called and 
 * invokes the send method of the web socket class with the value of 
 * the textbox as a parameter. Writes to the textbox.
 */
function wsSendMessage(){
	webSocket.send(message.value);
	echoText.value += "Message sent to the server: " + message.value + "\n";
	message.value = "";
}

/**
 * Close the websocket connection
 */
function wsCloseConnection(){
	webSocket.close();
}

/**
 * Get the WebSocket message from the server and send it to the text
 * area.
 */
function wsGetMessage(message){
	echoText.value += "Message received from the server: " + message.date + "\n";
}

/**
 * Capture and close the event and write a message to the text area
 * @param message
 * @returns
 */
function wsClose(message){
	echoText.value += "Disconnect ... \n";
}

/**
 * If there are any errors, write them to text area
 */
function weError(message){
	echoText.value += "Error ... \n";
}