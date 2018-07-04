<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Employee</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/alert.css">
    <link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
</head>
<body>
<div class="main-container" data-id="${requestScope.id}"  data-email="${requestScope.email}" data-departmentid="${requestScope.departmentId}"  >
    <a  data-toggle="tooltip" data-placement="right" title="Back to the Departments List" href="employees?id=${requestScope.departmentId}" class="btn btn-outline-primary btn-sm"><i class="fas fa-arrow-left"></i></a>
    <div class="row" >
        <div class="offset-3 col-3">
            <div class="add-form">
                <form  id="update" class="addform" action='' method="post">
                    <div class="row input-line">
                        <div class="col-12" >
                            <input type="text" class="text" value="${requestScope.firstname}" name="firstname">
                            <span>firstname</span>
                        </div>
                        <div class="notification-input">
                            <div class="notification-message ">&nbsp; Firstname: first 2 symbols - only letters allowed, next 0-17 symbols - letters, digits and a space .</div>
                        </div>
                    </div>
                    <div class="row input-line">
                        <div class="col-12" >
                            <input type="text" class="text" value="${requestScope.lastname}" name="lastname">
                            <span>lastname</span>
                        </div>
                        <div class="notification-input">
                            <div class="notification-message ">&nbsp; Lastname: first 2 symbols - only letters allowed, next 0-17 symbols - letters, digits and a space .</div>
                        </div>
                    </div>
                    <div class="row input-line">
                        <div class="col-12" >
                            <input type="text" class="text" value="${requestScope.email}" name="email">
                            <span>email</span>
                        </div>
                        <div class="notification-input">
                            <div class="notification-message ">&nbsp; Invalid Email, check it!</div>
                        </div>
                    </div>
                    <div class="row input-line">
                        <div class="col-12" >
                            <input type="text" maxlength="12" value="${requestScope.salary}" minlength="3" class="text" name="salary">
                            <span>salary</span>
                        </div>
                        <div class="notification-input">
                            <div class="notification-message ">&nbsp; Salary should be larger then 100 .</div>
                        </div>
                    </div>
                    <div class="row input-line">
                        <div class="col-12" >
                            <input type="text" maxlength="10" value="${requestScope.birthdate}" minlength="10" class="text" name="birthdate">
                            <span>birthdate</span>
                        </div>
                        <div class="notification-input">
                            <div class="notification-message ">&nbsp; birthdate pattern: dd-mm-yyyy .</div>
                        </div>
                    </div>
                    <div class="row input-line">
                        <div class="col-12" >
                            <input type="text" class="text" value="${requestScope.departmentId}" name="department">
                            <span>department id</span>
                        </div>
                        <div class="notification-input">
                            <div class="notification-message ">&nbsp; department: print correct Department ID .</div>
                        </div>
                    </div>
                    <button type="submit" class="command">
                        Update Employee
                    </button>
                </form>
            </div>
        </div>
        <div class="col-3"></div>
    </div>
</div>
<div class="alert">
    <div class="alert-body">
        <div class="alert-title">Title</div>
        <hr>
        <div class="alert-content"></div>
    </div>
    <a class="alert-close"><i class="fas fa-times"></i></a>
</div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <script src="js/employees.js"></script>
    <script src="js/main.js" defer></script>
</body>
</html>
