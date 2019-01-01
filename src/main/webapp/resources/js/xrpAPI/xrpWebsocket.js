/**
 *  Contains the web socket to interact with XRP ledger API found here:
 *  https://developers.ripple.com/websocket-api-tool.html
 */

// Production Web Socket
//let webSocket = new WebSocket("wss://s2.ripple.com:443 ");
// TestNet web socket
/**
 * Test Credentials:
 * 
 * Public Address:
 * rwYQjHp9HZiKKpZB4i4fvc8eQvAtA7vdY6
 * 
 * Private Key:
 * snKixQChzs9KcBxxrYWpm97sxnA1e
 */

/**
 * Second set of test credentials
 * 
 * Address 
 * rntmtrrtSGS9dJD84krKvutJLeQ6mADgQp 
 * Secret
 * snzzPQHtaCt2oj6YEx5CmCLD3p9Qv 
 * Balance 10,000 XRP
 */
let webSocket = new WebSocket("wss://s.altnet.rippletest.net:51233");
