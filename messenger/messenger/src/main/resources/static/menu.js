function messages_menuClicked(){
	$.ajax({
        url:"/getConversations",
        type: "GET",
        contentType: "application/json",
        success :function(resp){
	            	var response = JSON.parse(resp);    			
	    			if(response.hasOwnProperty("url")){
	    				window.location.href = response.url;
	    				return;
	    			}            	
	    			fillConversationList(response);
				}
        });
}

function myprofile_menuClicked(){
	function getMyProfile(){
		$.ajax({
	        url:"/myprofile",
	        type: "GET",
	        success :function(resp){
	        	var response = JSON.parse(resp);    			
				if(response.hasOwnProperty("url")){
					window.location.href = response.url;
					return;
				}            	
				fillProfile(response);
	        	}
	        });
	}
	
}

function friends_menuClicked(){
	
}

function logout_menuClicked(){
	
}

function submitClicked(){
	var searchinput = document.getElementById("searchInput");
	
	
}