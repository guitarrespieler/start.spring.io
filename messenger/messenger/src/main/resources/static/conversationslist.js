function fillConversationList(parseddata){
	var dataobj = parseddata;
	document.getElementById("list").innerHTML = "";
	if(dataobj.hasOwnProperty("error")){
		$("list").prepend("<a href=\"/login\" class=\"list-group-item\"><b>" + dataobj.error + "</b></a>");
		return;
	}
	for (var i = 0; i < dataobj.conversations.length; i++) { 
		var link;
		var title;
		var subtitle;
		
		if(dataobj.hasOwnProperty("link")){
			link = dataobj.conversations[i].link;			
		}
		if(dataobj.hasOwnProperty("title")){
			title = dataobj.conversations[i].title;
		}
		if(dataobj.hasOwnProperty("subtitle")){
			subtitle = dataobj.conversations[i].subtitle;
		}			
		$("list").append("<a href=\"" + link + "\" class=\"list-group-item\"><b>" + title + "</b><br><i>" + subtitle + "</i></a>");
	}
}	