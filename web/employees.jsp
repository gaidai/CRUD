<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${requestScope.name}</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
</head>
<body>
    <div class="main-container" data-id="${requestScope.id}" data-name="${requestScope.name}">
        <a  data-toggle="tooltip" data-placement="right" title="Back to the Departments List" href="departments" class="btn btn-outline-primary btn-sm"><i class="fas fa-arrow-left"></i></a>
        <div class="row">
            <div class="col-6">
                <div class="row">
                    <div class="header-title"><h2 >Department: ${requestScope.name}</h2></div>
                </div>
                <c:choose>
                    <c:when test="${ empty( requestScope.employees )}">
                        <div class="empty-list"><div> List is Empty</div>
                            <div> Click Add to create a new employee</div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="table-dark list-table">
                            <thead>
                                <tr >
                                    <th>ID</th>
                                    <th>FirstName</th>
                                    <th>LastName</th>
                                    <th>Birthdate</th>
                                    <th>Email</th>
                                    <th>Updated</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="emp" items="${requestScope.employees}" varStatus="loop">
                                    <tr data-index="td${loop.index}" data-id="${emp.id}" class="list-line">
                                        <td><c:out value="${emp.id}" /></td>
                                        <td><c:out value="${emp.firstname}" /></td>
                                        <td><c:out value="${emp.lastname}" /></td>
                                        <td><c:out value="${emp.birthdate}" /></td>
                                        <td><c:out value="${emp.email}" /></td>
                                        <td data-toggle="tooltip" data-placement="right" title="Created: ${emp.created}"><c:out value="${emp.updated}" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-1">
                <a class="crud-link link-add" href="addEmployee?id=${requestScope.id}"><div class="btn-add">Add</div></a>

                <div class="crud-nav">
                    <a class="crud-link link-edit"><div  class="btn-edit">Edit</div></a>
                    <a class="crud-link link-delete"><div class="btn-delete">Delete</div></a>
                </div>

            </div>
        </div>
    </div>
</div>
</hr>
<h1 class="up scrollMore header-title">&uarr; UP &uarr;</h1>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
<script src="js/main.js"></script>
<script src="js/employees.js"></script>
</body>
</html>
