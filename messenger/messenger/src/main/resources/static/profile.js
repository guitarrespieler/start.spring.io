function fillProfile(data){
	var list = document.getElementById("list");
	list.innerHTML = "";
	if(data.hasOwnProperty("error")){
		list.innerHTML += ("<a href=\"/login\" class=\"list-group-item\"><b>" + dataobj.error + "</b></a>");
		return;
	}
	if(data.hasOwnProperty("userid")){
		document.getElementById("listdiv").setAttribute("name",data.userid);
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
		
		if(data.contactstate == "STRANGER"){
			$("buttondiv").innerHTML += ("<button type=\"button\" class=\"btn btn-primary\" onclick=\"addFriendClicked()\">Add to friendlist</button>");
			$("buttondiv").innerHTML += ("<button type=\"button\" class=\"btn btn-warning disabled\">Message</button>");
		}
		if(data.contactstate == "FRIEND"){
			$("buttondiv").innerHTML += ("<button type=\"button\" class=\"btn btn-success disabled\">Friend</button>");
			$("buttondiv").innerHTML += ("<button type=\"button\" class=\"btn btn-success\">Message</button>");
		}
	}
}

function addFriendClicked(){
	var userid = {"userid": $("listdiv").name};
	
	var jssonniffied = JSON.stringify(userid);
	
	$.ajax({
        url:"/addfriend",
        type: "POST",
        contentType: "application/json",
        data:jssonniffied,
        success :location.reload()});
}