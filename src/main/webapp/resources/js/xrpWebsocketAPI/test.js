/**
 * methods for testing transactions
 */


/**
 * FOR TESTING ONLY. DO NOT SEND SECRET KEYS TO A SERVER YOU DO NOT CONTROL
 * Sends a transaction to be signed by the server.
 * @param account
 * @param destination
 * @param currency
 * @param value
 * @param secretKey
 * @returns
 */
function sign(account, destination, currency, value, secretKey){
	let json = '{"id": 9,"command": "sign","tx_json": {"TransactionType": "Payment","Account": "' + account + '","Destination": "' + destination + '","Amount": {"currency": "' + currency + '","value": "' + value + '","issuer": "' + account + '"}},"secret": "' + secretKey + '","offline": false,"fee_mult_max": 1000}';
	webSocket.send(json);
}

/**
 * FOR TESTING ONLY. DO NOT SEND SECRET KEYS TO A SERVER YOU DO NOT CONTROL
 * Submits a transaction to the network.
 * 
 * @param secretKey
 * @param account
 * @returns
 */
function submit(secretKey, account){
	let json = '{"id": 10,"command": "submit","secret": "' + secretKey + '","tx_json": {"Flags": 0,"TransactionType": "AccountSet","Account": "' + account + '","Fee": "10000"}}';
	webSocket.send(json);
}