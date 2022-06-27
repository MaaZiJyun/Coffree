<%-- 
    Document   : CoffeeList
    Created on : 27 Jun 2022, 10:19:26
    Author     : 37409
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="container mt-5" style="width: 30%; margin: 0 auto">
        
        <c:if test="${coffee != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${coffee == null}">
            <form action="insert" method="post">
        </c:if>
        <h2>
            <c:if test="${coffee != null}">
                Edit Order
            </c:if>
            <c:if test="${coffee == null}">
                Add New Order
            </c:if>
        </h2>
        <c:if test="${coffee != null}">
            <input type="hidden" name="id" value="<c:out value='${coffee.id}' />" />
        </c:if>
        <div class="mb-3">
            <label for="title" class="form-label">Username</label>
            <input type="text" class="form-control" name="title" size="45"
            value="<c:out value='${coffee.u_name}' />"
        />
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">Coffee Name</label>
            <input type="text" class="form-control" name="title" size="45"
            value="<c:out value='${coffee.c_name}' />"
        />
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">Author</label>
            <input type="text" class="form-control" name="title" size="45"
            value="<c:out value='${coffee.sugar}' />"
        />
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">Price</label>
            <input type="text" class="form-control" name="title" size="45"
            value="<c:out value='${coffee.temperature}' />"
        />
        </div>
        <div class="d-grid mt-5">
            <input type="submit" class="btn btn-dark" value="Save" />
        </div>
        </form>
    </div> 
    </body>
</html>
