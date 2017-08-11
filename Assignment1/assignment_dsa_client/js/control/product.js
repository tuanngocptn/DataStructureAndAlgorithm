$(document).ready(function() {
    $('#btnLoadAllPro').click(function() {    	
        product.getAll();
        script.productTabSwich();
    });
    $('#addProduct').click(function(){
    	product.add();
    });
    $('#sortProduct').click(function(){
    	product.sort();
    });
    $("#proSearch").on("keyup",function(){
    	// console.log($("#cusSearch").val());
    	product.search();
    });
});

var product = {
	name : "product",
	getAll:function(){
		var api = constants.host + constants.product;
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"getAll"},
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	},
	add:function(){
		var api = constants.host + constants.product;
		var pcodeParam = $('#pcode').val();
		var proNameParam = $('#proName').val();
		var quantityParam = $('#quantity').val();		
		var saledParam = $('#saled').val();
		var priceParam = $('#price').val();
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"add",pcode:pcodeParam,proName:proNameParam,quantity:quantityParam,saled:saledParam,price:priceParam},
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	},
	delete:function(code){
		var api = constants.host + constants.product;
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"delete",pcode:code},
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	},
	sort:function(){
		if(productControl.isLowToHigh === 1){
			productControl.isLowToHigh = 0;
		}else{
			productControl.isLowToHigh = 1;
		}
		
		var api = constants.host + constants.product;
		$.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"sort",isLowToHigh:productControl.isLowToHigh},
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
	},	  
    search:function(){        
        var api = constants.host + constants.product;
        var pSearch = $("#proSearch").val();
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: {action:"search",pcode:pSearch},
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    } 
}

var productControl = {
	isLowToHigh: 1,
	loadToTblMain: function(data){
		var action = "";
		var tblMain = $("#tblMain");
		tblMain.empty();
		tblMain.append("<thead><tr><th class='text-center'><div onclick='product.sort()'>Product Code</div></th><th class='text-center'>Product Name</th><th class='text-center'>Product Quantity(es)</th><th class='text-center'>Product Saled</th><th class='text-center'>Product price</th><th class='text-center'></th></tr></thead><tbody>");
		if(typeof tblMain !== 'undefined' && data.length > 0){
			for (var i = 0 ; i < data.length; i++) {
				action = 'product.delete("'+data[i].pcode+'")';
				tblMain.append("<tr><td class='text-center'>"+ data[i].pcode +"</td><td class='text-center'>"+ data[i].proName +"</td><td class='text-center'>"+ data[i].quantity +"</td><td class='text-center'>"+ data[i].saled +"</td><td class='text-center'>"+ data[i].price +"</td><td class='text-center'><div class='btn btn-danger' onclick='"+ action +"'>Delete</div></td></tr>");
			}
		}
		action = "product.add()"
		tblMain.append('</tbody><tfoot><tr><td class="text-center"><input class="form-control" type="text" name="pcode" id="pcode" required></td><td class="text-center"><input class="form-control" type="text" name="proName" id="proName" required></td><td class="text-center"><input class="form-control" type="text" name="quantity" id="quantity" required></td><td class="text-center"><input class="form-control" type="text" name="saled" id="saled" required></td><td class="text-center"><input class="form-control" type="text" name="price" id="price" required></td><td class="text-center"><div class="btn btn-success" onclick="'+ action +'">Add</div></td></tr></tfoot>');
		
	}
}