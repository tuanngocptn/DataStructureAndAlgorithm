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
    $('#do-edit-product').click(function(){
        product.doEdit();
    });
    $("#proSearch").on("keyup", function() {
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
                    $("#id-modal-content").text(JSON.parse(request.responseText).message)
                    $('#myModal').modal('show');
                }
                if (request.status == 400) {
                    $("#id-modal-title").text("Warning");
                    $("#id-modal-content").text(JSON.parse(request.responseText).message);
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
    edit: function(code) {
        var api = constants.host + constants.product;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "searchByCode", pcode: code},
            success: function(data) {
                var form = '<table align="center"><tr><td class="control-label">Product Code:</td><td><input class="form-control" type="text" name="editPcode" id="editPcode" value="'+ data.pcode +'" placeholder="Product Code" disabled></td></tr><tr><td class="control-label">Product Name:</td><td><input class="form-control" type="text" name="editProName" id="editProName" value="'+ data.proName +'" placeholder="Product Name"></td></tr><tr><td class="control-label">Product Quantity:</td><td><input class="form-control" type="text" name="editQuantity" id="editQuantity" type="number" value="'+ data.quantity +'" placeholder="Product Quantity"></td></tr><tr><td class="control-label">Product Saled:</td><td><input class="form-control" type="text" name="editSaled" id="editSaled" type="number" value="'+ data.saled +'" placeholder="Product Saled" disabled></td></tr><tr><td class="control-label">Product Price:</td><td><input class="form-control" type="text" name="editPrice" id="editPrice" type="number" value="'+ data.price +'" placeholder="Product Price"></td></tr></table>'
                $("#id-edit-modal-title").text("Edit Product");
                $("#id-edit-modal-content").html(form);
                $("#do-edit-product").show();
                $("#do-edit-customer").hide();
                $('#edit-model').modal('show');
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
    doEdit: function() {
        var api = constants.host + constants.product;
        var pcodeParam = $('#editPcode').val();
        var proNameParam = $('#editProName').val();
        var quantityParam = $('#editQuantity').val();
        var saledParam = $('#editSaled').val();
        var priceParam = $('#editPrice').val();
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "edit", pcode: pcodeParam, proName: proNameParam, quantity: quantityParam, saled: saledParam, price: priceParam },
            success: function(data) {
                $('#edit-model').modal('hide');
                productControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                $('#edit-model').modal('hide');
                if (request.status == 403) {
                    $("#id-modal-title").text("Add Error");
                    $("#id-modal-content").text(JSON.parse(request.responseText).message)
                    $('#myModal').modal('show');
                }
                if (request.status == 400) {
                    $("#id-modal-title").text("Warning");
                    $("#id-modal-content").text(JSON.parse(request.responseText).message);
                    $('#myModal').modal('show');
                }
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
        tblMain.append("<thead><tr><th class='text-center'><div onclick='product.sort()'>Product Code <span class='glyphicon glyphicon-sort' aria-hidden='true'/></div></th><th class='text-center'>Product Name</th><th class='text-center'>Product Quantity(es)</th><th class='text-center'>Product Saled</th><th class='text-center'>Product price</th><th class='text-center' style='width: 140px'></th></tr></thead><tbody>");
        if (typeof tblMain !== 'undefined' && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                action = 'product.delete("' + data[i].pcode + '")';
                var editAction = 'product.edit("' + data[i].pcode + '")';
                tblMain.append("<tr><td class='text-center'>" + data[i].pcode + "</td><td class='text-center'>" + data[i].proName + "</td><td class='text-center'>" + data[i].quantity + "</td><td class='text-center'>" + data[i].saled + "</td><td class='text-center'>" + data[i].price + "</td><td class='text-center'><div class='btn btn-danger' onclick='" + action + "'>Delete</div> <div class='btn btn-warning' onclick='" + editAction + "'>Edit</div></td> </tr>");
            }
        }
        action = "product.add()"
        tblMain.append('</tbody><tfoot><tr><td class="text-center"><input class="form-control" type="text" name="pcode" id="pcode" placeholder="Code"></td><td class="text-center"><input class="form-control" type="text" name="proName" id="proName" placeholder="Name"></td><td class="text-center"><input class="form-control" type="number"1 name="quantity" id="quantity" placeholder="Quantity"></td><td class="text-center"><input class="form-control" type="number" name="saled" id="saled" placeholder="Saled" value="0" disabled></td><td class="text-center"><input class="form-control" type="number" name="price" id="price" placeholder="Price"></td><td class="text-center"><div class="btn btn-success" onclick="' + action + '">Add</div></td></tr></tfoot>');

    }
}