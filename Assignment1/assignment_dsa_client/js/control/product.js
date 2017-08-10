$(document).ready(function() {
    $('#btnLoadAllPro').click(function() {
        product.getAll();
    });
    $('#addProduct').click(function(){
    	product.add();
    });
    $('#sortProduct').click(function(){
    	product.sort();
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
	} 
}

var productControl = {
	isLowToHigh: 1,
	loadToTblMain: function(data){
		var action = "";
		var tblMain = $("#tblMain");
		tblMain.empty();
		tblMain.append("<thead><tr><th>Product Code</th><th>Product Name</th><th>Product Quantity</th><th>Product Saled</th><th>Product price</th><th></th></tr></thead><tbody>");
		if(typeof tblMain !== 'undefined' && data.length > 0){
			for (var i = 0 ; i < data.length; i++) {
				action = 'product.delete("'+data[i].pcode+'")';
				tblMain.append("<tr><td>"+ data[i].pcode +"</td><td>"+ data[i].proName +"</td><td>"+ data[i].quantity +"</td><td>"+ data[i].saled +"</td><td>"+ data[i].price +"</td><td onclick='"+ action +"'>Delete</td></tr>");
			}
		}
		action = "product.add()"
		tblMain.append('</tbody><tfoot><tr><td><input type="text" name="pcode" id="pcode" required></td><td><input type="text" name="proName" id="proName" required></td><td><input type="text" name="quantity" id="quantity" required></td><td><input type="text" name="saled" id="saled" required></td><td><input type="text" name="price" id="price" required></td><td onclick="'+ action +'">Add</td></tr></tfoot>');
		
	}
}