<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username}</p><a href="ShoppingList?action=logout">Logout</a>

        <form method="post" action="ShoppingList">
            <h2>List</h2>
            <label>Add item:</label>
            <input type="text" name="item_to_add" value="">
            <input type="submit" value="Add" >
            <input type="hidden" name="action" value="add">
        </form>
        <br>
        
         <!--display this form if there are items in the shopping list-->
<c:if test="${itemlist != null}">
       
<form action="ShoppingList" method="post">
            <!--loop through each item in the item list and add them as radio buttons-->
            <c:forEach var="item" items="${itemlist}">
                <input type="radio" name="single_item" value="${item}">
                <label for="${item}">${item}</label>
                <br>
            </c:forEach>

            <input type="submit" value="Delete" >
            <!--send delete value to the servlet when the delete button is clicked-->
            <input type="hidden" name="action" value="delete"> 
        </form>
     </c:if>

    </body>
</html>
