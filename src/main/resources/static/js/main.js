$(document).ready(function () {

    $("#regform").submit(function (event) {

        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

	//var data = $("#regform").serializeArray();
	var data = {
			"name" : $("#name").val(), 
			"company" : $("#company").val()
	};
	console.log(data);
	
	var json = JSON.stringify(data);
	console.log(json);
	//var formArray = {};
    
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/create",
        data: json,
        dataType: 'text', // json -> text
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            window.location.href = "/welcome"
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

}