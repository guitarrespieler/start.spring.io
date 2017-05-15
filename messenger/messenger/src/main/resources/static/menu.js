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

function friends_menuClicked(){
	$.ajax({
        url:"/friends",
        type: "GET",
        success :function(resp){
		        	var response = JSON.parse(resp);    			
					if(response.hasOwnProperty("url")){
						window.location.href = response.url;
						return;
					}            	
					fillFriendsList(response);
        		}
        });
}

function logout_menuClicked(){
	$.ajax({
        url:"/logout",
        type: "GET",
        success :function(resp){
		        	var response = JSON.parse(resp);    			
					if(response.hasOwnProperty("url")){
						window.location.href = response.url;
						return;
					}            	
        		}
        });
}
<!--not implemented yet-->
function submitClicked(){}