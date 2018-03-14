var stompClient = null;

function setConnected(connected){
	$("#connect").prop("disabled",connected);
	$("#disconnect").prop("disabled",!connected);
	if (connected){
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}
// utiliza sockjs y stomp para abrir una conexión a /gs-guide-websocket
function connect() {
	var socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame){
		setConnected(true);
		console.log('Connected: '+frame);
		stompClient.subscribe('/topic/notifications',function(notifications){
			alert(notifications);
		});
		getNotifications();
		stompClient.subscribe('/topic/greetings',function (greeting){
			showGreeting(JSON.parse(greeting.body).content);
		});
	}, function(error){
		alert(error);
	});
}

function disconnect(){
	if (stompClient !== null){
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}
// coge el nombre que el usuario ha introducido y utiliza el cliente STOMP
// para enviarlo al destino /app/hello
function sendName(){
	stompClient.send("/app/hello",{},JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message){
	$("#greetings").append("<tr><td>"+message+"</td></tr>");
}

$(function (){
	$("form").on('submit', function (e) {
		e.preventDefault();
	});
	$("#connect").click(function() { connect(); });
	$("#disconnect").click(function() { disconnect(); });
	$("#send").click(function() { sendName(); });
});




