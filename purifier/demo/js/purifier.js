jQuery(function() {
	
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