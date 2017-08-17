$(document).ready(function() {
    order.init();
});

var script = {
    orderTab: false,
    customerTab: false,
    productTab: false,
    orderTabSwich: function() {
        script.orderTab = true;
        script.customerTab = false;
        script.productTab = false;
        script.tabSwich();
    },
    customerTabSwich: function() {
        script.orderTab = false;
        script.customerTab = true;
        script.productTab = false;
        script.tabSwich();
    },
    productTabSwich: function() {
        script.orderTab = false;
        script.customerTab = false;
        script.productTab = true;
        script.tabSwich();
    },
    tabSwich: function() {
        if (script.orderTab) {
            $("#btnLoadAllOrder").removeClass("btn-primary").addClass("btn-default");
        } else {
            $("#btnLoadAllOrder").removeClass("btn-default").addClass("btn-primary");
        };
        if (script.customerTab) {
            $("#btnLoadAllCus").removeClass("btn-primary").addClass("btn-default");
        } else {
            $("#btnLoadAllCus").removeClass("btn-default").addClass("btn-primary");
        };
        if (script.productTab) {
            $("#btnLoadAllPro").removeClass("btn-primary").addClass("btn-default");
        } else {
            $("#btnLoadAllPro").removeClass("btn-default").addClass("btn-primary");
        }
    }
}