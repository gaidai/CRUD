var NAME_REGEX = /^[A-ZА-ЯЇЄІЁ]{1}[A-Za-zА-Яа-яїЇєЄіІёЁ]{2}[A-Za-zА-Яа-яїЇєЄіІёЁ 0-9]{0,17}$/;

$('.btn-delete').click(function(event) {
    var id = $('.active-line').data('id');
    if (id === undefined){
        return false;
    };
    $.ajax({
        type: "GET",
        url:  "deleteDepartment",
        data: 'id='+id,
        success: function(data){
            $('.active-line').remove();
            $('.crud-nav').removeClass('active-nav');
            $('.link-edit').removeAttr( "href" );
            $('.link-list').removeAttr( "href" );
        },
        error: function(xhr, status, error) {
            alert("Server-side exception",error);
        }
    });
});

$('input[name=name]').on('blur input',function(){
    nameValidate();

});
function nameValidate(){
    var input = $('input[name=name]');
    var firstUp =  input.val().charAt(0).toUpperCase() + input.val().slice(1);
    input.val(firstUp);
    var btn = $('.command');
    btn.prop( "disabled", true );
    btn.removeClass('active-command');

    var message = input.parent().next();
    if(NAME_REGEX.test(input.val()) ){
        //Check if current value is used for another department
       isExists(input).done(function(data) {
           if(!(data === "true")){
               input.removeClass('not-valid');
               message.fadeOut(200);
               btn.prop( "disabled", false );
               btn.addClass('active-command');
               return true;
           }else{
                input.addClass('not-valid');
                message.fadeIn(200);
                // Compare value with old name , for Update page.
                if( $('.main-container').data('name') === input.val() ){
                    message.children('.notification-message').text("  This is the Current name ! Write new !");
                }else {
                   message.children('.notification-message').text("  This name is already in use !");
                }
                return false;
           }
       }).fail(function(error){
           input.addClass('not-valid');
           message.fadeIn(200);
           alert("Server exception ",error.statusText);
           message.children('.notification-message').text("  Connection error !");
           return false;
       });
    }else{
        input.addClass('not-valid');
        message.fadeIn(200);
        message.children('.notification-message').text("  Department name: first 3 symbols - only letters allowed, next 0-17 symbols - letters, digits and a space . ");
        return false;
    };
};
function isExists(input){
    return $.ajax({
        type: "GET",
        url: 'checkDepartment?name='+input.val(),
        success: function(data){

        },
        error: function(xhr, status, error) {
            alert( "Server-side exception, error ", error);
            return false;
        }
    });
};
$("#add").submit(function(e) {
    var url = "addDepartment";
    var name = $('input[name=name]').val();
    console.log("URL addDepartment");
    $.ajax({
        type: "POST",
        url: url,
        data: $("#add").serialize(),
        success: function(id){
            location.href = 'employees?id=' + id + '&name=' + name ;
        },
        error: function(xhr, status, error) {
            alert( "Server-side exception, error ", error);
        }
    });
    e.preventDefault();
});
$("#update").submit(function(e) {
    var name = $('input[name=name]').val();
    var id = $('.main-container').data('id');
    var url = "updateDepartment";
    $.ajax({
        type: "POST",
        url: url,
        data: $(this).serialize() + '&id='+id,
        success: function(data){
            location.href = 'departments' ;
        },
        error: function(xhr, status, error) {
            alert( "Server-side exception, error ", error);
        }
    });
    e.preventDefault();
});


