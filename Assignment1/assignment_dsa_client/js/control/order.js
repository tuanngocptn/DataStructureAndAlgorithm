$(document).ready(function() {
	$('#btnLoadAllOrder').click(function() {
        order.getAll();
    })

    $("#sortOrderByCCode").click(function(){
        order.sort("ccode");
    });   

    $("#sortOrderByPCode").click(function(){
        order.sort("pcode");
    });
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
	},
	sort:function(searchType){
		var isLowToHigh = 0;
		if(searchType == "pcode"){
	        if(orderControl.isLowToHighPCode === 1){
	            orderControl.isLowToHighPCode = 0;
	        }else{
	            orderControl.isLowToHighPCode = 1;
	        } 
	        isLowToHigh = orderControl.isLowToHighPCode;
		}else{
			if(orderControl.isLowToHighCCode === 1){
	            orderControl.isLowToHighCCode = 0;
	        }else{
	            orderControl.isLowToHighCCode = 1;
	        }
	        isLowToHigh = orderControl.isLowToHighCCode;
		}     
        var api = constants.host + constants.order;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"sort",type:searchType,isLowToHigh:isLowToHigh},
            success: function(data) {
                orderControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
}

var orderControl = {
    isLowToHighCCode: 1,
    isLowToHighPCode: 1,
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