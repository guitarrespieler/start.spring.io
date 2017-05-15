function fillFriendsList(dataobj){
	var list = document.getElementById("list");
	list.innerHTML = "";
	if(dataobj.hasOwnProperty("error")){
		list.innerHTML += "<a href=\"/login\" class=\"list-group-item\"><b>" + dataobj.error + "</b></a>";
		return;
	}
	
	if(!dataobj.hasOwnProperty("friends")){
		return;
	}
	
	for (var i = 0; i < dataobj.friends.length; i++) { 
		var link;
		var title;
		var subtitle;
		
		
		
		if(dataobj.friends[i].hasOwnProperty("link")){
			link = dataobj.friends[i].link;			
		}
		if(dataobj.friends[i].hasOwnProperty("title")){
			title = dataobj.friends[i].title;
		}
		if(dataobj.friends[i].hasOwnProperty("subtitle")){
			subtitle = dataobj.friends[i].subtitle;
		}			
		list.innerHTML += "<a class=\"list-group-item\" onclick=\"loadThisProfile(\'" + link + "\')\"><b>" + title + "</b><br><i>" + subtitle + "</i></a>";
	}
}

function loadThisProfile(linktoprofile){
	$.ajax({
		url: linktoprofile,
		type: "GET",
		success: function(resp){
			var response = JSON.parse(resp);		
			if(response.hasOwnProperty("url")){
				window.location.href = response.url;
				return;
			}   	
			fillProfile(response);
    	}
	});
	
}