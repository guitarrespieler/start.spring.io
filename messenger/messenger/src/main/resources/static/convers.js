function openConversation(convid){
	$.ajax({
        url:"/conversation?convid=" + convid,
        type: "GET",
        contentType: "application/json",
        success :function(resp){
		        	var response = JSON.parse(resp);    			
					if(response.hasOwnProperty("url")){
						window.location.href = response.url;
						return;
					}
					if(response.hasOwnProperty("error")){
						var list = document.getElementById("list");
						list.innerHTML = "";
						list.innerHTML += "<a href=\"/login\" class=\"list-group-item\"><b>" + response.error + "</b></a>";
						return;
					}
					fillConversation(response);
				}
			});
	
}

function fillConversation(data){
	var list = document.getElementById("list");
	list.innerHTML = "";
	if(data.hasOwnProperty("error")){
		list.innerHTML += "<a href=\"/login\" class=\"list-group-item\"><b>" + data.error + "</b></a>";
		return;
	}
	
	var convid;
	
	if(data.hasOwnProperty("convid")){
		convid = data.convid;
	}
	
	var messages;
	
	if(data.hasOwnProperty("messages")){
		messages = data.messages;
	}
	
	for(var i = 0; i < messages.length; i++){
		var author = messages[i].author;
		var content = messages[i].content;
		
		list.innerHTML += "<li class=\"list-group-item\"><b>"+ author +"</b><span class=\"badge\"></span></li>";
		list.innerHTML += "<li class=\"list-group-item\"><i>"+ content +"</i><span class=\"badge\"></span></li>";
	}
	var innerstring = "<textarea id=\"inputbox\" rows=\"3\" cols=\"80\" placeholder=\"Write your message here...\"></textarea>";
	innerstring += "<button type=\"button\" class=\"btn btn-primary pull-right\" onclick=\"sendMessage("+ convid +")\">Send</button>";
	
	list.innerHTML += "<li class=\"list-group-item\">"+ innerstring +"</li>";
}

function sendMessage(convid){
	var obj = {conversation: convid, content: document.getElementById("inputbox").value};
	var message = JSON.stringify(obj);
	$.ajax({
        url:"/sendmessage",
        type: "POST",
        contentType: "application/json",
        data:message,
        success :function(resp){
		        	var response = JSON.parse(resp);    			
					if(response.hasOwnProperty("url")){
						window.location.href = response.url;
						return;
					}            	
					if(response.hasOwnProperty("error")){
						var list = document.getElementById("list");
						list.innerHTML = "";
						list.innerHTML += "<a href=\"/login\" class=\"list-group-item\"><b>" + response.error + "</b></a>";
						return;
					}
					openConversation(convid);
				}
			});	
	
}