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

    $("#id-do-pick-product").click(function() {
        order.doEditSearchField();
    })
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
                console.log(data);1
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
    },
    showPickProduct: function() {
        var api = constants.host + constants.product;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "getAll" },
            success: function(data) {
                orderControl.loadToTblPickProduct(data);
                $('#id-pick-product').modal('show');
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
    },
    checkValue: function(data) {
        if (parseInt(data.value) < data.min) {
            data.value = 0;
        }
        if (parseInt(data.value) > data.max) {
            data.value = data.max;
        }
        if (isNaN(parseInt(data.value))) {
            data.value = 0;
        }
    },
    doEditSearchField: function() {
        var api = constants.host + constants.product;
        $.ajax({
            type: 'POST',
            url: api,
            dataType: "JSON",
            data: { action: "getAll" },
            success: function(data) {
                orderControl.loadToProductField(data);
                $('#id-pick-product').modal('hide');
            },
            error: function(request, status, error) {
                console.log(error);
            }
        });
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
        tblMain.append("<thead><tr><th class='text-center'>Code</th><th onclick='" + ccodeSearch + "' class='text-center'>Customer Code <span class='glyphicon glyphicon-sort' aria-hidden='true'/></span></th><th onclick='" + pcodeSearch + "' class='text-center'>Product <span class='glyphicon glyphicon-sort' aria-hidden='true'/></span></th><th></th></tr></thead><tbody>");
        if (typeof tblMain !== 'undefined' && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                action = 'order.searchOrder("' + data[i].ccode + '","' + data[i].pcode + '")';
                tblMain.append("<tr onclick='" + action + "'><td class='text-center'>" + (i + 1) + "</td><td class='text-center'>" + data[i].ccode + "</td><td class='text-center'>" + data[i].pcode + "</td><td class='text-center'></td></tr>");
            }
        }
        action = "order.add()"
        tblMain.append('</tbody><tfoot><tr><th></th><td class="text-center"><input onkeyup="order.searchCustomer()" class="form-control" type="text" name="ccode" id="ccode" placeholder="Customer code"></td><td class="text-center" onclick="order.showPickProduct()"><input disabled class="form-control" type="text" name="pcode" id="pcode" placeholder="Product"></td><td><div class="btn btn-success text-center" onclick="' + action + '">Add</div></td></tr></tfoot>');

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
        $("#id-modal-title").text("Order Detail");
        var result = "<p class='text-center'> - <b>Code:</b> "+ data.customer.ccode +" - <b>Name:</b> "+ data.customer.cusName +" - <b>Phone:</b> "+ data.customer.phone +" - </p>";
        if (data.product.length > 0) {
            result = result + "<table class='table'>";
            var total = 0;
            for (var i = 0; i < data.product.length; i++) {
                result += "<tr><td class='text-center'>"+ data.product[i].pcode +"</td><td class='text-center'>"+ data.product[i].proName +"</td><td class='text-center'>"+ data.product[i].quantity +"</td><td class='text-center'>"+ data.product[i].price +" $</td></tr>"
                total += (data.product[i].quantity * data.product[i].price);
            }
            result += "<tr><td class='text-center'></td><td class='text-center'></td><td class='text-center'>Total:</td><td class='text-center'>"+ total +" $</td></tr>"
            console.log(total);
            result = result + "</table>";
        }
        $("#id-modal-content").html(result);
        $('#myModal').modal('show');
    },
    loadToTblPickProduct: function(data) {
        var tblPick = $("#tbl-pick-product");
        var action = "";
        tblPick.empty();
        tblPick.append("<thead><tr><th class='text-center'>Product Code</th><th class='text-center'>Product Name</th><th class='text-center'>Product Quantity(es)</th></tr></thead><tbody>");
        if (typeof tblPick !== 'undefined' && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                action = 'product.delete("' + data[i].pcode + '")';
                var editAction = 'product.edit("' + data[i].pcode + '")';
                tblPick.append("<tr><td class='text-center'>" + data[i].pcode + "</td><td class='text-center'>" + data[i].proName + "</td><td class='text-center'><input onkeyup='order.checkValue(this)' type='number' id='id-pick-" + data[i].pcode + "' data-code='" + data[i].pcode + "' min='0' max='" + data[i].quantity + "' class='form-control' type='text' placeholder='Product Quantity'></td></tr>");
            }
        }

    },
    loadToProductField: function(data) {
        if (data.length > 0) {
            var result = "";
            for (var i = 0; i < data.length; i++) {
                var attribute = $("#id-pick-" + data[i].pcode);
                if(parseInt(attribute.val()) > 0){
                    result = result + attribute.attr("data-code") + ":" + attribute.val() + ",";
                }
            }
            if(result.length>0){
                result = result.substring(0, result.length - 1);
            }
            $("#pcode").val(result);
        }
    }
}