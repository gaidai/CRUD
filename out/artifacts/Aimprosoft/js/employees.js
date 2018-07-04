var EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,4}))$/;
var NAME_REGEX = /^[A-ZА-ЯЇЄІЁ]{1}[a-zа-яїєіё]{1,16}$/;

$('.btn-delete').click(function(event) {
    var id = $('.active-line').data('id');
    if (id === undefined){      
        return false;
    }; 
    $.ajax({
        type: "POST",
        url: "deleteEmployee?id="+id,
        success: function(data){
            $('.active-line').remove();
            $('.crud-nav').removeClass('active-nav');
            $('.link-edit').removeAttr( "href" );
        },
        error: function(xhr, status, error) {
            alert("Server-side exception",error);
        }
    });
});
function emailValidate(){
    var input = $('input[name=email]');

    var message = input.parent().next();
    if(EMAIL_REGEX.test(input.val()) ){
        // Compare value with old name , for Update page.
        if( $('.main-container').data('email') != input.val() ) {
            //Check if current value is used for another department
          isEmailExists(input).done(function (data) {
                console.log(data);//print
                if (!(data === "true")) {
                    input.removeClass('not-valid');
                    message.fadeOut(200);
                    isValid();
                    return true;

                } else {
                    input.addClass('not-valid');
                    message.fadeIn(200);
                    // Compare value with old name , for Update page.
                    message.children('.notification-message').text("  This email is already in use !");
                    isValid();

                    return false;
                }
            }).fail(function (error) {
                input.addClass('not-valid');
                message.fadeIn(200);
                message.children('.notification-message').text("  Connection error !");
                isValid();
                alert("Server exception ",error.statusText);
                return false;
            });
        }else{
            input.removeClass('not-valid');
            message.fadeOut(200);
            isValid();
            return true;

        };
    }else{
        input.addClass('not-valid');
        message.fadeIn(200);
        message.children('.notification-message').text(" Invalid email! Check it! ");
        isValid();
        return false;
    };
};
function isEmailExists(input){
    console.log($('input[name=email]').val());

    return $.ajax({
        type: "GET",
        url: 'checkEmail?email='+input.val(),
        success: function (data) {
        },
        error: function(xhr, status, error) {
            alert( "Server-side exception, error ", error);
        }
    });
};
$('input[name=email]').on('focusin blur input',function(){
    emailValidate();
});
function isDate(ExpiryDate) {
    var objDate,  // date object initialized from the ExpiryDate string
        mSeconds, // ExpiryDate in milliseconds
        day,      // day
        month,    // month
        year;     // year
    // date length should be 10 characters (no more no less)
    if (ExpiryDate.length !== 10) {
        return false;
    }
    // third and sixth character should be '-'
    if (ExpiryDate.substring(2, 3) !== '-' || ExpiryDate.substring(5, 6) !== '-') {
        return false;
    }
    // extract month, day and year from the ExpiryDate (expected format is "dd-mm-yyyy")
    // subtraction will cast variables to integer implicitly (needed
    // for !== comparing)
    month = ExpiryDate.substring(3, 5) - 1; // because months in JS start from 0
    day = ExpiryDate.substring(0, 2) - 0;
    year = ExpiryDate.substring(6, 10) - 0;
    // test year range: differens with current year
    var today = new Date();
    // convert ExpiryDate to milliseconds
    mSeconds = (new Date(year, month, day)).getTime();
    // initialize Date() object from calculated milliseconds
    objDate = new Date();
    objDate.setTime(mSeconds);
    // compare input date and parts from Date() object
    // if difference exists then date isn't valid
    if (objDate.getFullYear() !== year ||
        objDate.getMonth() !== month ||
        objDate.getDate() !== day ||
        today.getFullYear()-16 < objDate.getFullYear() ||
        today.getFullYear()-100 > objDate.getFullYear()
    ){
        return false;
    }
    // otherwise return true
    return true;
};
$('input[name=birthdate]').on('focusin input blur',function(e){
    var input = $(this);
    var message = input.parent().next();
    if (isDate(input.val())) {
        input.removeClass('not-valid');
        message.fadeOut(200);
    }else {
        input.addClass('not-valid');
        message.fadeIn(200);
    };
    isValid();
});
$('input[name=firstname] ').on('focusin blur input',function(){
    var input = $(this);
    var firstUp =  input.val().charAt(0).toUpperCase() + input.val().slice(1);
    input.val(firstUp);
    var message = input.parent().next();

    if (NAME_REGEX.test(input.val())) {
        input.removeClass('not-valid');
        message.fadeOut(200);
    } else {
        input.addClass('not-valid');
        message.fadeIn(200);
    };
    isValid();
});
$('input[name=lastname] ').on('focusin blur input',function(){
    var input = $(this);
    var firstUp =  input.val().charAt(0).toUpperCase() + input.val().slice(1);
    input.val(firstUp);
    var message = input.parent().next();

    if (NAME_REGEX.test(input.val())) {
        input.removeClass('not-valid');
        message.fadeOut(200);
    } else {
        input.addClass('not-valid');
        message.fadeIn(200);
    };
    isValid();
});
function departmentValidate(){
    var input = $('input[name=department]');
    var message = input.parent().next();
    // Compare value with old name , for Update page.
     //Check if current value is used for another department
    isDepartment(input).done(function (data) {
        console.log(data);
        if (data === "true") {
            input.removeClass('not-valid');
            message.fadeOut(200);
            isValid();
        } else {
            input.addClass('not-valid');
            message.fadeIn(200);

            message.children('.notification-message').text(" This Department ID is not exists !");
            isValid();
        }
    }).fail(function (error) {
        input.addClass('not-valid');
        message.fadeIn(200);
        message.children('.notification-message').text("Can't check this ID !")
        alert("Can't check this department ID !", error.statusText);
        isValid();
    });
};
function isDepartment(input){
  return $.ajax({
    type: "POST",
    url: 'checkDepartment?id='+input.val(),
    success: function(data){
    },
    error: function(xhr, status, error) {
      alert("Can't check this department ID from isDepartment!", error);
    }
  });
};
$('input[name=department] ').on('focusin blur input',function(){
    var input = $(this);
    var departmentId = parseInt(input.val());

    if(isNaN(departmentId)){
        departmentId = "";
        input.val(departmentId);
        var message = input.parent().next();
        input.addClass('not-valid');
        message.fadeIn(200);
        message.children('.notification-message').text("Department ID must be an Integer number!");
    }else{
        input.val(departmentId);
        departmentValidate();
        isValid();
    };
});
$('input[name=salary] ').on('focusin blur input',function(){
  var input = $(this);
  var salary = parseInt(input.val());
  var message = input.parent().next();
  if( isNaN(salary) ){
    input.val('');
    input.addClass('not-valid');
    message.fadeIn(200);
  }else if( salary < 100){
    input.val(salary);
    input.addClass('not-valid');
    message.fadeIn(200);
    isValid();
  }else{
    input.val(salary);
    input.removeClass('not-valid');
    message.fadeOut(200);
    isValid();
  };
});
// Disable submit button , if one of values is invalid
function isValid() {
    var btn = $('button.command');
    if( $('input.not-valid').length>0 ){
        btn.prop( "disabled", true );
        btn.removeClass('active-command');
    }else {
        btn.prop( "disabled", false );
        btn.addClass('active-command');
    }
};
$("#add").submit(function(e) {
    var url = "addEmployee";
    var departmentId = $('.main-container').data('departmentid');
    console.log("URL addEmployee " + departmentId);
    $.ajax({
        type: "POST",
        url: url,
        data: $("#add").serialize()+"&departmentid="+departmentId,
        success: function(){
            location.href = 'employees?id=' + departmentId ;
        },
        error: function(xhr, status, error) {
            alert("Server-side exception", error);
        }
    });
    e.preventDefault();
});
$("#update").submit(function(e) {
    var id = $('.main-container').data('id');
    var departmentId = $('input[name=department] ').val();
    var url = "updateEmployee";
    $.ajax({
        type: "POST",
        url: url,
        data: $('#update').serialize() + '&id='+id,
        success: function(data){
            location.href = 'employees?id=' + departmentId ;
        },
        error: function(xhr, status, error) {
            alert( "Servlet-side Error",error );
        }
    });
    e.preventDefault();
});

