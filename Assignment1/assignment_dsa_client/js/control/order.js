$(document).ready(function() {
	$('#btnLoadAllOrder').click(function() {
        order.getAll();
    })
})

var order = {
	name:"order",
	getAll:function(){
		var api = constants.host + constants.order;
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"getAll"},
            success: function(data) {
                orderControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	},
	add:function(){
		var api = constants.host + constants.order;
		var ccodeParam = $('#ccode').val();
		var pcodeParam = $('#pcode').val();
		var quantityParam = $('#quantity').val();
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"add",ccode:ccodeParam,pcode:pcodeParam,quantity:quantityParam},
            success: function(data) {
                orderControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	}
}

var orderControl = {
    isLowToHigh: 1,
	loadToTblMain: function(data){
		var action = "";
		var tblMain = $("#tblMain");
		tblMain.empty();
		tblMain.append("<thead><tr><th>Customer Code</th><th>Product Code</th><th>Quantity(es)</th><th></th></tr></thead><tbody>");
		if(typeof tblMain !== 'undefined' && data.length > 0){
			for (var i = 0 ; i < data.length; i++) {
				tblMain.append("<tr><td>"+ data[i].ccode +"</td><td>"+ data[i].pcode +"</td><td>"+ data[i].quantity +"</td><td></td></tr>");
			}
		}
		action = "order.add()"
		tblMain.append('</tbody><tfoot><tr><td><input type="text" name="ccode" id="ccode" required></td><td><input type="text" name="pcode" id="pcode" required></td><td><input type="text" name="quantity" id="quantity" required></td><td onclick="'+ action +'">Add</td></tr></tfoot>');
		
	}
}