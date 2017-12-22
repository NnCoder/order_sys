/**
 * Created by Tao on 2017/12/15.
 */
var websocket = null;

function sendMessage(obj){
    websocket.send(JSON.stringify(obj));
}

