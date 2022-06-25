<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="post" action="ShoppingList">
        <label>Username:</label>
        <input type="text" name="user_name" value="">
        <input type="submit" value="Register name" action="register">
        <input type ="hidden" name="action" value="register">
        </form>
    </body>
</html>
