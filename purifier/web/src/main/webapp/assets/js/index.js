jQuery(function() {
    var w_h = jQuery(window).height();
    var t_h = jQuery("#J_nav").height();
    var c_h = jQuery("#J_con").height();
    var f_h = jQuery("#J_foot").height();
    var padding = (w_h-t_h-f_h-c_h)/2;
    $("#J_con").css("padding-top", padding);
    $("#J_con").css("padding-bottom", padding);

    $("#words").on("focus blur", function () {
        $(this).parent().toggleClass("ipt-selected");
    });
    $("#J_purBtn").click(function(){
        $("#J_mainForm").submit();
        return false;
    });

});