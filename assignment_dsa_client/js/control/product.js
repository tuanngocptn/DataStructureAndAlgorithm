$(document).ready(function() {
    $('#btnLoadAllPro').click(function() {
        product.init();
    });
    $('#addProduct').click(function() {
        product.add();
    });
    $('#sortProduct').click(function() {
        product.sort();
    });
    $("#proSearch").on("keyup", function() {
        // console.log($("#cusSearch").val());
        product.search();
    });
});

var product = {
    name: "product",
    init: function() {
        product.getAll();
        script.productTabSwich();
        $("#cusSearch").hide();
        $("#proSearch").show();
        $("#tbl-search").empty();
    },
    getAll: function() {
        var api = constants.host + constants.product;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "getAll" },
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
    add: function() {
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
            data: { action: "add", pcode: pcodeParam, proName: proNameParam, quantity: quantityParam, saled: saledParam, price: priceParam },
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                if (request.status == 403) {
                    $("#id-modal-title").text("Add Error");
                    $("#id-modal-content").text("Product code has existed.")
                    $('#myModal').modal('show');
                }
                if (request.status == 400) {
                    $("#id-modal-title").text("Warning");
                    $("#id-modal-content").text("Please fill all the information.");
                    $('#myModal').modal('show');
                }
            }
        });
    },
    delete: function(code) {
        var api = constants.host + constants.product;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "delete", pcode: code },
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
    sort: function() {
        if (productControl.isLowToHigh === 1) {
            productControl.isLowToHigh = 0;
        } else {
            productControl.isLowToHigh = 1;
        }

        var api = constants.host + constants.product;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "sort", isLowToHigh: productControl.isLowToHigh },
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
    search: function() {
        var api = constants.host + constants.product;
        var pSearch = $("#proSearch").val();
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "search", pcode: pSearch },
            success: function(data) {
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                if (request.status == 400) {
                    product.init();
                }
            }
        });
    }
}

var productControl = {
    isLowToHigh: 1,
    loadToTblMain: function(data) {
        var action = "";
        var tblMain = $("#tblMain");
        tblMain.empty();
        tblMain.append("<thead><tr><th class='text-center'><div onclick='product.sort()'>Product Code <span class='glyphicon glyphicon-sort' aria-hidden='true'/></div></th><th class='text-center'>Product Name</th><th class='text-center'>Product Quantity(es)</th><th class='text-center'>Product Saled</th><th class='text-center'>Product price</th><th class='text-center'></th></tr></thead><tbody>");
        if (typeof tblMain !== 'undefined' && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                action = 'product.delete("' + data[i].pcode + '")';
                tblMain.append("<tr><td class='text-center'>" + data[i].pcode + "</td><td class='text-center'>" + data[i].proName + "</td><td class='text-center'>" + data[i].quantity + "</td><td class='text-center'>" + data[i].saled + "</td><td class='text-center'>" + data[i].price + "</td><td class='text-center'><div class='btn btn-danger' onclick='" + action + "'>Delete</div></td></tr>");
            }
        }
        action = "product.add()"
        tblMain.append('</tbody><tfoot><tr><td class="text-center"><input class="form-control" type="text" name="pcode" id="pcode" placeholder="Code"></td><td class="text-center"><input class="form-control" type="text" name="proName" id="proName" placeholder="Name"></td><td class="text-center"><input class="form-control" type="number" name="quantity" id="quantity" placeholder="Quantity"></td><td class="text-center"><input class="form-control" type="number" name="saled" id="saled" placeholder="Saled"></td><td class="text-center"><input class="form-control" type="number" name="price" id="price" placeholder="Price"></td><td class="text-center"><div class="btn btn-success" onclick="' + action + '">Add</div></td></tr></tfoot>');

    }
}