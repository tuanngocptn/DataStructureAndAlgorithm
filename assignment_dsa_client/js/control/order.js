$(document).ready(function() {
    $('#btnLoadAllOrder').click(function() {
        order.init();
    })

    $("#sortOrderByCCode").click(function() {
        order.sort("ccode");
    });

    $("#sortOrderByPCode").click(function() {
        order.sort("pcode");
    });
})

var order = {
    name: "order",
    init: function() {
        order.getAll();
        script.orderTabSwich();
        $("#proSearch").hide();
        $("#cusSearch").hide();
        $("#tbl-search").empty();
    },
    getAll: function() {
        var api = constants.host + constants.order;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "getAll" },
            success: function(data) {
                orderControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
    add: function() {
        $("#tbl-search").empty();
        var api = constants.host + constants.order;
        var ccodeParam = $('#ccode').val();
        var pcodeParam = $('#pcode').val();
        var quantityParam = $('#quantity').val();
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "add", ccode: ccodeParam, pcode: pcodeParam, quantity: quantityParam },
            success: function(data) {
                orderControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                if (request.status == 403) {
                    $("#id-modal-title").text("Add Error");
                    $("#id-modal-content").text("Product code Or Customer code does not exist.")
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
    sort: function(searchType) {
        var isLowToHigh = 0;
        if (searchType == "pcode") {
            if (orderControl.isLowToHighPCode === 1) {
                orderControl.isLowToHighPCode = 0;
            } else {
                orderControl.isLowToHighPCode = 1;
            }
            isLowToHigh = orderControl.isLowToHighPCode;
        } else {
            if (orderControl.isLowToHighCCode === 1) {
                orderControl.isLowToHighCCode = 0;
            } else {
                orderControl.isLowToHighCCode = 1;
            }
            isLowToHigh = orderControl.isLowToHighCCode;
        }
        var api = constants.host + constants.order;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "sort", type: searchType, isLowToHigh: isLowToHigh },
            success: function(data) {
                orderControl.loadToTblMain(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
    searchOrder: function(ccodeParam, pcodeParam) {
        var api = constants.host + constants.order;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "search", ccode: ccodeParam, pcode: pcodeParam },
            success: function(data) {
                orderControl.loadModalSearchResult(data);
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
    searchCustomer: function() {
        var api = constants.host + constants.customer;
        var cSearch = $("#ccode").val();
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "search", ccode: cSearch },
            success: function(data) {
                orderControl.loadCusToTblSearch(data);
            },
            error: function(request, status, error) {
                $("#tbl-search").empty();
            }
        });
    },
    editCcode: function(ccode) {
        console.log(ccode);
        $("#tbl-search").empty();
        $("#ccode").val(ccode);
    },
    searchProduct: function() {
        var api = constants.host + constants.product;
        var pSearch = $("#pcode").val();
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "search", pcode: pSearch },
            success: function(data) {
                orderControl.loadProToTblSearch(data);
            },
            error: function(request, status, error) {
                $("#tbl-search").empty();
            }
        });
    },
    editPcode: function(pcode) {
        $("#tbl-search").empty();
        $("#pcode").val(pcode);
    }
}

var orderControl = {
    isLowToHighCCode: 1,
    isLowToHighPCode: 1,
    loadToTblMain: function(data) {
        var action = "";
        var tblMain = $("#tblMain");
        tblMain.empty();
        var ccodeSearch = 'order.sort("ccode")'
        var pcodeSearch = 'order.sort("pcode")'
        tblMain.append("<thead><tr><th onclick='" + ccodeSearch + "' class='text-center'>Customer Code <span class='glyphicon glyphicon-sort' aria-hidden='true'/></span></th><th onclick='" + pcodeSearch + "' class='text-center'>Product Code <span class='glyphicon glyphicon-sort' aria-hidden='true'/></span></th><th class='text-center'>Quantity(es)</th><th class='text-center'></th></tr></thead><tbody>");
        if (typeof tblMain !== 'undefined' && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                action = 'order.searchOrder("' + data[i].ccode + '","' + data[i].pcode + '")';
                tblMain.append("<tr onclick='" + action + "'><td class='text-center'>" + data[i].ccode + "</td><td class='text-center'>" + data[i].pcode + "</td><td class='text-center'>" + data[i].quantity + "</td><td class='text-center'></td></tr>");
            }
        }
        action = "order.add()"
        tblMain.append('</tbody><tfoot><tr><td class="text-center"><input onkeyup="order.searchCustomer()" class="form-control" type="text" name="ccode" id="ccode" placeholder="Customer code"></td><td class="text-center"><input onkeyup="order.searchProduct()" class="form-control" type="text" name="pcode" id="pcode" placeholder="Product code"></td><td class="text-center"><input class="form-control" type="number" name="quantity" id="quantity" placeholder="Quantity(es)"></td><td><div class="btn btn-success text-center" onclick="' + action + '">Add</div></td></tr></tfoot>');

    },
    loadCusToTblSearch: function(data) {
        var tblSearch = $("#tbl-search");
        tblSearch.empty();
        if (data.length > 0) {
            tblSearch.append("<thead><tr><th class='text-center'>Customer Code</th><th class='text-center'>Customer Name</th><th class='text-center'>Customer Phone</th><th class='text-center'></th></tr></thead><tbody>");
            if (typeof tblSearch !== 'undefined' && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    tblSearch.append("<tr onclick='order.editCcode(" + '"' + data[i].ccode + '"' + ")' ><td class='text-center'>" + data[i].ccode + "</td><td class='text-center'>" + data[i].cusName + "</td><td class='text-center'>" + data[i].phone + "</td></tr>");
                }
            }
            tblSearch.append('</tbody>');
        }
    },
    loadProToTblSearch: function(data) {
        var tblSearch = $("#tbl-search");
        tblSearch.empty();
        if (data.length > 0) {
            tblSearch.append("<thead><tr><th class='text-center'><div onclick='product.sort()'>Product Code <span class='glyphicon glyphicon-sort' aria-hidden='true'/></div></th><th class='text-center'>Product Name</th><th class='text-center'>Product Quantity(es)</th><th class='text-center'>Product Saled</th><th class='text-center'>Product price</th><th class='text-center'></th></tr></thead><tbody>");
            if (typeof tblSearch !== 'undefined' && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    tblSearch.append("<tr onclick='order.editPcode(" + '"' + data[i].pcode + '"' + ")' ><td class='text-center'>" + data[i].pcode + "</td><td class='text-center'>" + data[i].proName + "</td><td class='text-center'>" + data[i].quantity + "</td><td class='text-center'>" + data[i].saled + "</td><td class='text-center'>" + data[i].price + "</td></tr>");
                }
            }
            tblSearch.append('</tbody>');
        }
    },
    loadModalSearchResult: function(data) {
        console.log(data);
        $("#id-modal-title").text("Order Detail");
        var result = '<p class="col-md-12">Information Customer and Product:</p><div class="col-md-5"><ul><li>Code: ' + data.customer.ccode + '</li><li>Name: ' + data.customer.cusName + '</li><li>Phone: ' + data.customer.phone + '</li></ul></div><div class="col-md-7"><ul><li>Code: ' + data.product.pcode + '</li><li>Name: ' + data.product.proName + '</li><li>Quantity: ' + data.product.quantity + '</li><li>Saled: ' + data.product.saled + '</li><li>Price: ' + data.product.price + '</li></ul></div><br><br><br><br><br><br>';
        $("#id-modal-content").html(result);
        $('#myModal').modal('show');
    }
}