/**
 *  Methods to interact with XRP ledger API found here:
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
let webSocket = new WebSocket("wss://s.altnet.rippletest.net:51233");

/**
 * Get server info. Response should look something like: 
 * {
  "id": 1,
  "status": "success",
  "type": "response",
  "result": {
    "info": {
      "build_version": "1.1.2",
      "complete_ledgers": "32570-43910145",
      "hostid": "ROWE",
      "io_latency_ms": 1,
      "jq_trans_overflow": "0",
      "last_close": {
        "converge_time_s": 2.633,
        "proposers": 26
      },
      "load_factor": 1,
      "peer_disconnects": "89062",
      "peer_disconnects_resources": "171",
      "peers": 123,
      "pubkey_node": "n9LbkoB9ReSbaA9SGL317fm6CvjLcFG8hGoierLYfwiCDsEXHcP3",
      "server_state": "full",
      "state_accounting": {
        "connected": {
          "duration_us": "367169421",
          "transitions": 1
        },
        "disconnected": {
          "duration_us": "1191717",
          "transitions": 1
        },
        "full": {
          "duration_us": "918197886643",
          "transitions": 1
        },
        "syncing": {
          "duration_us": "7318309",
          "transitions": 1
        },
        "tracking": {
          "duration_us": "62",
          "transitions": 1
        }
      },
      "time": "2018-Dec-23 21:55:18.260270",
      "uptime": 918573,
      "validated_ledger": {
        "age": 6,
        "base_fee_xrp": 0.00001,
        "hash": "6C0642F4253EADFBB851930A1A80AA3453D7ADFC96E6CB8046A527B17C29E895",
        "reserve_base_xrp": 20,
        "reserve_inc_xrp": 5,
        "seq": 43910145
      },
      "validation_quorum": 21
    }
  }
}
 */
function server_info() {
	let json = '{"id": 1, "command": "server_info"}';
	webSocket.send(json);
}

/**
 * get server state. Response should look something like this:
 * {
  "id": 2,
  "status": "success",
  "type": "response",
  "result": {
    "state": {
      "build_version": "1.1.2",
      "complete_ledgers": "32570-43910283",
      "io_latency_ms": 1,
      "jq_trans_overflow": "0",
      "last_close": {
        "converge_time": 3010,
        "proposers": 26
      },
      "load_base": 256,
      "load_factor": 256,
      "load_factor_fee_escalation": 256,
      "load_factor_fee_queue": 256,
      "load_factor_fee_reference": 256,
      "load_factor_server": 256,
      "peer_disconnects": "89090",
      "peer_disconnects_resources": "171",
      "peers": 124,
      "pubkey_node": "n9LbkoB9ReSbaA9SGL317fm6CvjLcFG8hGoierLYfwiCDsEXHcP3",
      "server_state": "full",
      "state_accounting": {
        "connected": {
          "duration_us": "367169421",
          "transitions": 1
        },
        "disconnected": {
          "duration_us": "1191717",
          "transitions": 1
        },
        "full": {
          "duration_us": "918732962546",
          "transitions": 1
        },
        "syncing": {
          "duration_us": "7318309",
          "transitions": 1
        },
        "tracking": {
          "duration_us": "62",
          "transitions": 1
        }
      },
      "time": "2018-Dec-23 22:04:13.336164",
      "uptime": 919108,
      "validated_ledger": {
        "base_fee": 10,
        "close_time": 598917850,
        "hash": "25DBD01B242F57957996AA977A5D8E137093EB10F7483E25B74909FF73E4D1E0",
        "reserve_base": 20000000,
        "reserve_inc": 5000000,
        "seq": 43910283
      },
      "validation_quorum": 21
    }
  }
}
 */
function server_state(){
	let json = '{"id": 2, "command": "server_state"}';
	webSocket.send(json);
}

/**
 * Checks connectivity to the server
 * Response should look something like:
 * {
  	"id": 3,
  	"status": "success",
  	"type": "response",
  	"result": {}
	}
 */
function ping(){
	let json = '{"id": 3, "command": "ping"}';
	webSocket.send(json);
}

/**
 * Start receiving selected streams from the server
 * @param accounts: (optional) Array with the unique base58 addresses of accounts to monitor for 
 * validated transactions. The server sends a notification for any transaction that affects at 
 * least one of these accounts.
 * 
 * Response looks like:
 * {
  "id": 1,
  "status": "success",
  "type": "response",
  "result": {
    "fee_base": 10,
    "fee_ref": 10,
    "hostid": "ROWE",
    "ledger_hash": "839643494C0019FCC882C0ED371F42478F65B5E69AABA44400C9AE2D026BC5CF",
    "ledger_index": 43910395,
    "ledger_time": 598918271,
    "load_base": 256,
    "load_factor": 256,
    "pubkey_node": "n9LbkoB9ReSbaA9SGL317fm6CvjLcFG8hGoierLYfwiCDsEXHcP3",
    "random": "1D13098D5CFF237F907D3E69D1F87DD6AB4D754868AB4E7400710C6AEBB92857",
    "reserve_base": 20000000,
    "reserve_inc": 5000000,
    "server_status": "full",
    "validated_ledgers": "32570-43910395"
  }
}
 */
function subscribe(accounts){
	if(!accounts)
		accounts = [];
	
	let json = '{"id": 1, "command": "subscribe", "accounts":'+ accounts +',"streams": ["server", "ledger"]}'
	webSocket.send(json);
}

/**
 * Stop receiving selected streams from the server
 * @param accounts: (optional) Array with the unique base58 addresses of accounts to monitor for 
 * validated transactions. The server sends a notification for any transaction that affects at 
 * least one of these accounts.
 * 
 * Response looks like:
 * {
  "id": 2,
  "status": "success",
  "type": "response",
  "result": {}
}
 */
function unsubscribe(accounts){
	if(!accounts)
		accounts = [];
	
	let json = '{"id": 1, "command": "unsubscribe", "accounts":'+ accounts +',"streams": ["server", "ledger"]}'
	webSocket.send(json);
}
