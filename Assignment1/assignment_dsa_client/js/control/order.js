$(document).ready(function() {
	$('#btnLoadAllOrder').click(function() {		
        order.getAll();
        script.orderTabSwich();
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
		var ccodeSearch = 'order.sort("ccode")'
		var pcodeSearch = 'order.sort("pcode")'
		tblMain.append("<thead><tr><th onclick='"+ ccodeSearch +"' class='text-center'>Customer Code</th><th onclick='"+ pcodeSearch +"' class='text-center'>Product Code</th><th class='text-center'>Quantity(es)</th><th class='text-center'></th></tr></thead><tbody>");
		if(typeof tblMain !== 'undefined' && data.length > 0){
			for (var i = 0 ; i < data.length; i++) {
				tblMain.append("<tr><td class='text-center'>"+ data[i].ccode +"</td><td class='text-center'>"+ data[i].pcode +"</td><td class='text-center'>"+ data[i].quantity +"</td><td class='text-center'></td></tr>");
			}
		}
		action = "order.add()"
		tblMain.append('</tbody><tfoot><tr><td class="text-center"><input class="form-control" type="text" name="ccode" id="ccode" required></td><td class="text-center"><input class="form-control" type="text" name="pcode" id="pcode" required></td><td class="text-center"><input class="form-control" type="text" name="quantity" id="quantity" required></td><td><div class="btn btn-success text-center" onclick="'+ action +'">Add</div></td></tr></tfoot>');
		
	}
}