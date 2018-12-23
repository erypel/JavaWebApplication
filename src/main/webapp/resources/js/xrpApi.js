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

/**
 * Returns ledger information
 * Response looks something like:
 * {
  "id": 1,
  "status": "success",
  "type": "response",
  "result": {
    "closed": {
      "ledger": {
        "accepted": true,
        "account_hash": "42F02BA4712835C430281A5897F0DF9660823018EE55D5E4992BF1C3F1DB1536",
        "close_flags": 0,
        "close_time": 598918721,
        "close_time_human": "2018-Dec-23 22:18:41.000000000",
        "close_time_resolution": 10,
        "closed": true,
        "hash": "65A7103A9A6D924730FFCE3486E20511E15B1C17FD522CCE0B10CBCAC8EB0C23",
        "ledger_hash": "65A7103A9A6D924730FFCE3486E20511E15B1C17FD522CCE0B10CBCAC8EB0C23",
        "ledger_index": "43910513",
        "parent_close_time": 598918720,
        "parent_hash": "B318638DCA6646077792CE9F9C095EC655FA5F7657578D62BE515DBE8352E72C",
        "seqNum": "43910513",
        "totalCoins": "99991731485834280",
        "total_coins": "99991731485834280",
        "transaction_hash": "3D75A52D92496476C3440193BB77E8463B2BE85BFB56A9BBBEE506A90BEE4D3E"
      }
    },
    "open": {
      "ledger": {
        "closed": false,
        "ledger_index": "43910514",
        "parent_hash": "65A7103A9A6D924730FFCE3486E20511E15B1C17FD522CCE0B10CBCAC8EB0C23",
        "seqNum": "43910514"
      }
    }
  }
}
 */
function ledger(){
	let json = '{"id": 3,"command": "ledger","full": false,"expand": false,"transactions": true,"accounts": true}';
	webSocket.send(json);
}

/**
 * Get the most recent closed ledger index
 * Response looks something like:
 * {
  "id": 2,
  "status": "success",
  "type": "response",
  "result": {
    "ledger_hash": "133646CCA65F84687F98D4E9E913FFB55501E9E0DC1DE3E87B23EA99A5DC23F6",
    "ledger_index": 43910526
  }
}
 */
function ledger_closed(){
	let json = '{"id": 2,"command": "ledger_closed"}';
	webSocket.send(json);
}

/**
 * Get the current in-progress ledger index
 * Response looks something like:
 * {
  "id": 3,
  "status": "success",
  "type": "response",
  "result": {
    "ledger_current_index": 43910549
  }
}
 */
function ledger_current(){
	let json = '{"id": 3,"command": "ledger_current"}';
	webSocket.send(json);	
}

/**
 * Get a single node from the ledger
 * @param account_root: String(address) (Optional) Specify an AccountRoot object to retrieve.
 * Response looks something like:
 * {
  "id": 1,
  "status": "success",
  "type": "response",
  "result": {
    "index": "4F83A2CF7E70F77F79A307E6A472BFC2585B806A70833CCD1C26105BAE0D6E05",
    "ledger_hash": "D31EECA7195EC3DF51C4EC0A7F268F2AAAD3951AC99B5809104BD5F6DDEC7238",
    "ledger_index": 43910613,
    "node": {
      "Account": "r9cZA1mLK5R5Am25ArfXFmqgNwjZgnfk59",
      "Balance": "13314753787",
      "Flags": 0,
      "LedgerEntryType": "AccountRoot",
      "OwnerCount": 17,
      "PreviousTxnID": "5A18ACA848D7786E2AC27A2FFB54483F2B6367B4375A4AE00D6DCC60B668D0BD",
      "PreviousTxnLgrSeq": 42135765,
      "Sequence": 1406,
      "index": "4F83A2CF7E70F77F79A307E6A472BFC2585B806A70833CCD1C26105BAE0D6E05"
    },
    "validated": true
  }
}
 */
function ledger_entry(account_root){
	if(!account_root)
		account_root = "";
	let json = '{"id": 1,"command": "ledger_entry","type": "account_root","account_root": "'+ account_root +'","ledger_index": "validated"}';
	webSocket.send(json);
}