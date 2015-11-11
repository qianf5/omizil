jQuery(function() {
    var w_h = jQuery(window).height();
    var t_h = jQuery("#J_nav").height();
    var c_h = jQuery("#J_con").height();
    var f_h = jQuery("#J_foot").height();
    if ((t_h + c_h) < (w_h - f_h)) {
        $("#J_foot").addClass("foot-fix");
    } else {
        $("#J_foot").addClass("m4t");
    }

    $("#words").on("focus blur", function () {
        $(this).parent().toggleClass("ipt-selected");
    });

    $(".y-rule, .g-rule").on("mouseover", function() {
        //alert(1)
        //alert($(this).html());
    });
    $(".rule-nav li a").on("click", function() {
        var liParent = $(this).parent();
        var olParent = liParent.parent();

        var selecteDataId = liParent.attr("data-id");
        $(olParent.children()).each(function(i, e) {
            $(e).removeClass("active");
            var dataId = $(e).attr("data-id");
            $("#" + dataId).hide();
        });

        liParent.addClass("active");
        $("#" + selecteDataId).show();
    });
    $(".bar").click(function() {
        $("#purifierForm").submit();
    });
});