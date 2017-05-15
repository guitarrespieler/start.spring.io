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
					fillConversation(response);
				}
			});
	
}

function fillConversation(data){
	var list = document.getElementById("list");
	list.innerHTML = "";
	if(data.hasOwnProperty("error")){
		list.innerHTML += ("<a href=\"/login\" class=\"list-group-item\"><b>" + data.error + "</b></a>");
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
		
		list.innerHTML += ("<li class=\"list-group-item\"><b>"+ author +"</b><span class=\"badge\"></span></li>");
		list.innerHTML += ("<li class=\"list-group-item\"><i>"+ content +"</i><span class=\"badge\"></span></li>");
	}
	list.innerHTML += "<textarea id=\"inputbox\" rows=\"4\" cols=\"50\" placeholder=\"Write your message here...\"></textarea>";
	list.innerHTML += "<button type=\"button\" class=\"btn btn-primary \" onclick=\"sendMessage()\">Add to friendlist</button>");
}

function sendMessage(){
	var message = JSON.stringify("{content: " + document.getElementById("inputbox").value + "}");
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
					if(data.hasOwnProperty("error")){
						var list = document.getElementById("list");
						list.innerHTML = "";
						list.innerHTML += ("<a href=\"/login\" class=\"list-group-item\"><b>" + data.error + "</b></a>");
						return;
					}
				}
			});	
	
}