function fillConversationList(parseddata){
	var dataobj = parseddata;
	document.getElementById("list").innerHTML = "";
	if(dataobj.hasOwnProperty("error")){
		$("list").prepend("<a href=\"/login\" class=\"list-group-item\"><b>" + dataobj.error + "</b></a>");
		return;
	}
	for (var i = 0; i < dataobj.conversations.length; i++) { 
		var convid;
		var title;
		var subtitle;
		
		if(dataobj.conversations[i].hasOwnProperty("convid")){
			convid = dataobj.conversations[i].convid;			
		}
		if(dataobj.conversations[i].hasOwnProperty("title")){
			title = dataobj.conversations[i].title;
		}
		if(dataobj.conversations[i].hasOwnProperty("subtitle")){
			subtitle = dataobj.conversations[i].subtitle;
		}			
		list.innerHTML += "<a class=\"list-group-item\" onclick=\"openConversation(\'" + convid + "\')\"><b>" + title + "</b><br><i>" + subtitle + "</i></a>";
	}
}	