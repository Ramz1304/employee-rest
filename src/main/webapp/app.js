function addEmployee(event){
	
	var $form = $("#employee-form");
	var json = toJson($form);
	
	alert("adding-employee");
	
	
	
	$.ajax({
		url: './api',
		type: 'POST',
		data: json,
		success: function(response) {
			console.log("employee created");
			getEmployeelist();
		},
		error: function(){
			console.log("an error has occured");
	}
	});
	
	return false;
}

function getEmployeelist(){
	$.ajax({
		url: './api',
		type: 'GET',
		success: function(data) {
			console.log("employee data fetched");
			console.log(data);
			displayEmployeelist(data);
		},
		error: function(){
			alert("an error has occured");
	}
	});
	
}

function displayEmployeelist(data){
	console.log("printing employee data");
	var $tbody = $('#employee-table').find('tbody');
	$tbody.empty();
	for(var i in data){
		var e = data[i];
		var buttonhtml = '<button onclick="deleteEmployee(' + e.id + ')">delete</button>'
		var row = "<tr>"
		+ "<td>" + e.id + "</td>" 
		+ "<td>" + e.name + "</td>" 
		+ "<td>" + e.age + "</td>" 
		+ "<td>" + buttonhtml + "</td>"
		+ "</tr>";
		$tbody.append(row);
		
	}
	
	
}

function deleteEmployee(id){
		$.ajax({
		url: './api?id='+id,
		type: 'DELETE',
		success: function(data) {
			console.log("employee deleted");
			getEmployeelist();
		},
		error: function(){
			alert("an error has occured");
	}
	});
	
	
}
	

function toJson($form){
	var serialized = $form.serializeArray();
	console.log(serialized);
	var s = '';
	var data = {};
    for(s in serialized){
           data[serialized[s]['name']] = serialized[s]['value']
	}
     var json = JSON.stringify(data);
     console.log(json);
     return json;
	

}

function init(){
	$('#add-employee').click(addEmployee);
	$('#refresh-data').click(getEmployeelist);
}




$(document).ready(init);
$(document).ready(getEmployeelist);