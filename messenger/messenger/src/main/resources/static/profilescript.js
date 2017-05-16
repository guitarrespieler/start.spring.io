function fillProfile(data){
	var list = document.getElementById("list");
	list.innerHTML = "";
	if(data.hasOwnProperty("error")){
		list.innerHTML += ("<a href=\"/login\" class=\"list-group-item\"><b>" + data.error + "</b></a>");
		return;
	}
	if(data.hasOwnProperty("firstname") && data.hasOwnProperty("lastname")){
		list.innerHTML += ("<li class=\"list-group-item\">Name<span class=\"badge\">" + data.firstname +" "+ data.lastname + "</span></li>");
	}
	if(data.hasOwnProperty("city")){
		list.innerHTML += ("<li class=\"list-group-item\">City<span class=\"badge\">" + data.city + "</span></li>");
	}
	if(data.hasOwnProperty("birthdate")){
		list.innerHTML += ("<li class=\"list-group-item\">Birth date<span class=\"badge\">" + data.birthdate + "</span></li>");
	}
	if(data.hasOwnProperty("contactstate")){
		if(data.contactstate == "SAME_USER")
			return;
		
		var innerstring = "";
		
		if(data.contactstate == "STRANGERS"){
			innerstring += "<button type=\"button\" class=\"btn btn-primary \" onclick=\"addFriendClicked(" + data.userid + ")\">Add to friendlist</button>";
			innerstring += "<button type=\"button\" class=\"btn btn-warning disabled \">New conversation</button>";
		}
		if(data.contactstate == "FRIENDS"){
			innerstring += "<button type=\"button\" class=\"btn btn-success disabled \">Friend</button>";
			innerstring += "<button type=\"button\" class=\"btn btn-success \" onclick=\"startNewConversation(" + data.userid + ")\">New conversation</button>";
		}
		list.innerHTML += "<li class=\"list-group-item text-right\">" + innerstring +"</li>";
	}
}

function addFriendClicked(userid){
	var obj = {"id": userid};
	var jssonniffied = JSON.stringify(obj);
	
	$.ajax({
        url:"/addfriend",
        type: "POST",
        contentType: "application/json",
        data:jssonniffied,
        success :location.reload()});
}

function startNewConversation(userid){
	var jssonniffied = JSON.stringify({"id": userid});
	
	$.ajax({
        url:"/conversation",
        type: "POST",
        contentType: "application/json",
        data:jssonniffied,
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
			if(response.hasOwnProperty("convid")){
				openConversation(response.convid);
			}            	
		}});
}