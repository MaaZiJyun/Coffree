<%-- 
    Document   : CoffeeList
    Created on : 30 Jun 2022, 11:22:28
    Author     : 37409
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Coffree</title>
        <title>Coffree Application</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand">Coffree</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                      <li class="nav-item">
                        <a class="nav-link" href="new">Add New Order</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="list">List All Orders</a>
                      </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-5">
        <h2>List of Order</h2>
        <table class="table">
            <thead>
                <tr>
                <th scope="col">ID</th>
                <th scope="col">Username</th>
                <th scope="col">Coffee Name</th>
                <th scope="col">Sugar</th>
                <th scope="col">Temperature</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="coffee" items="${coffeeList}">
                <tr>
                    <th scope="row"><c:out value="${coffee.id}" /></td>
                    <td><c:out value="${coffee.u_name}" /></td>
                    <td><c:out value="${coffee.c_name}" /></td>
                    <td><c:out value="${coffee.sugar}" /></td>
                    <td><c:out value="${coffee.temperature}" /></td>
                    <td>
                        <a class="btn btn-outline-primary" href="edit?id=<c:out value='${coffee.id}' />">Edit</a>
                        <a class="btn btn-outline-danger" href="delete?id=<c:out value='${coffee.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </body>
</html>
