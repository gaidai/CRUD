<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Department Manager</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/form.css">
    <link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
</head>
<body>
    <div class="main-container" data-page="departments">
        <div class="row">
            <div class="col-4">
                <div class="row">
                    <div class="header-title"><h2 >Departments</h2></div>
                </div>
                <c:choose>
                    <c:when test="${empty requestScope.departments}">
                        <div class="empty-list"><div> List is Empty</div>
                            <div> Click Add to create a new deparment</div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="table-dark list-table">
                            <thead>
                               <tr >
                                   <th>ID</th>
                                   <th>Name</th>
                                   <th>Updated</th>
                                    <%--<th>Modify</th>--%>
                               </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dep" items="${requestScope.departments}" varStatus="loop">
                                    <tr data-index="td${loop.index}" data-name="${dep.name}" data-id="${dep.id}" class="list-line">
                                        <td><c:out value="${dep.id}" /></td>
                                        <td><c:out value="${dep.name}" /></td>
                                        <td data-toggle="tooltip" data-placement="right" title="Created: ${dep.created}"><c:out value="${dep.updated}" /></td>
                                        <%--<td><a class="edit-icon" ><i class="fa fa-edit" ></i> </a> </i>&nbsp;&nbsp;&nbsp;<a class="delete-icon"><i class="fa fa-times"></i></a></td>--%>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-1">
                <a href="createDepartment.html" class="crud-link"><div class="btn-add" data-toggle="tooltip" data-placement="right" title="Добавить">Add</div></a>

                <div class="crud-nav">
                    <a class="crud-link link-list"><div class="btn-list" data-toggle="tooltip" data-placement="right" title="Список сотрудников">List</div></a>
                    <a class="crud-link link-edit"><div  class="btn-edit" data-toggle="tooltip" data-placement="right" title="Редактировать">Edit</div></a>
                    <a class="crud-link link-delete"><div class="btn-delete" data-toggle="tooltip" data-placement="right" title="Удалить">Delete</div></a>

                </div>

            </div>
        </div>
    </div>

    <h1 class="up scrollMore header-title">&uarr; UP &uarr;</h1>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
    <script src="js/departments.js"></script>
</body>
</html>
