$(document).ready(function() {
    $('#btnLoadAllCus').click(function() {
        customer.getAll();
    });
    $('#addCustomer').click(function(){
    	customer.add();
    });
    $('#sortCustomer').click(function(){
        customer.sort();
    });    
    $("#cusSearch").on("keyup",function(){
        console.log($("#cusSearch").val());
        customer.search();
    });
});

var customer = {
	name:"customer",
	getAll:function(){
		var api = constants.host + constants.customer;
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"getAll"},
            success: function(data) {
                customerControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	},

	add:function(){
		var api = constants.host + constants.customer;
		var ccodeParam = $('#ccode').val();
		var cusNameParam = $('#cusName').val();
		var phoneParam = $('#phone').val();
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"add",ccode:ccodeParam,cusName:cusNameParam,phone:phoneParam},
            success: function(data) {
                customerControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	},
	delete:function(code){
		var api = constants.host + constants.customer;
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"delete",ccode:code},
            success: function(data) {
                customerControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	},
    sort:function(){
        if(customerControl.isLowToHigh === 1){
            customerControl.isLowToHigh = 0;
        }else{
            customerControl.isLowToHigh = 1;
        }
        
        var api = constants.host + constants.customer;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"sort",isLowToHigh:customerControl.isLowToHigh},
            success: function(data) {
                customerControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },    
    search:function(){        
        var api = constants.host + constants.customer;
        var cSearch = $("#cusSearch").val();
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"search",ccode:cSearch},
            success: function(data) {
                customerControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    } 
}

var customerControl = {
    isLowToHigh: 1,
	loadToTblMain: function(data){
		var action = "";
		var tblMain = $("#tblMain");
		tblMain.empty();
		tblMain.append("<thead><tr><th>Customer Code</th><th>Customer Name</th><th>Customer Phone</th><th></th></tr></thead><tbody>");
		if(typeof tblMain !== 'undefined' && data.length > 0){
			for (var i = 0 ; i < data.length; i++) {
				action = 'customer.delete("'+data[i].ccode+'")';
				tblMain.append("<tr><td>"+ data[i].ccode +"</td><td>"+ data[i].cusName +"</td><td>"+ data[i].phone +"</td><td onclick='"+ action +"'>Delete</td></tr>");
			}
		}
		action = "customer.add()"
		tblMain.append('</tbody><tfoot><tr><td><input type="text" name="ccode" id="ccode" required></td><td><input type="text" name="cusName" id="cusName" required></td><td><input type="text" name="phone" id="phone" required></td><td onclick="'+ action +'">Add</td></tr></tfoot>');
		
	}
}