$(document).ready(function(){
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
    $(".up").click(function() {
        $('html, body').animate({
            scrollTop: 0
        }, 800);
    });
    $(document).scroll(function () {
        var y = $(this).scrollTop();
        if (y > 50) {
            $('.up').fadeIn();
        } else {
            $('.up').fadeOut();
        };
    });
});
// move navigation bar to selected deparment-line
$('.list-line').click(function(event) {
    $('.active-line').removeClass('active-line');
    $(this).addClass('active-line');
    $('.link-edit').attr("href", "http://www.google.com/");
    var nav = $('.crud-nav');
    var tableH = $('.list-table').innerHeight();
    var navH = nav.innerHeight();
    var position = $(this).position().top ;
    var headerH = $('.header-title').innerHeight();
    nav.addClass('active-nav');
    if(tableH>navH){
        if(position> navH/2){
            nav.animate({'margin-top': position - headerH }, "slow");
        }else{
            nav.animate({'margin-top': headerH/2 }, "slow");
        };
    };
    var id = $('.active-line').data('id');
    // for department or employee lists
    var depID = $('.main-container').data('id');
    if(depID !== undefined){
        $('.link-edit').attr("href", 'updateEmployee?id=' + id);
    }else {
        $('.link-edit').attr("href", 'updateDepartment?id=' + id);
        $('.link-list').attr("href", 'employees?id=' + id);
    }
});
function alert (title, content) {
    $('.alert-title').html(title);
    $('.alert-content').html(content);
    if( $(".alert").is(':visible') ){
        $(".alert").stop().stop().stop().stop()
            .fadeOut(100).fadeIn(400).delay(2000).fadeOut(900);
    }else{
        $(".alert").fadeIn(500).delay(4000).fadeOut(1000);
    };
};
$('.alert-close').click(function(event) {
    $(".alert").stop().stop().stop().fadeOut(200);
});
