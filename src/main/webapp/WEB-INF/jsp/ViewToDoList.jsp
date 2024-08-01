<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title> View ToDo List</title>
	
	<link rel="stylesheet"
	       	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	       <link rel="stylesheet"
	           href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
	       <script
	       	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	       <script
	       	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	       <script
	           src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

	   <style>
	       a{
	           color: white;
	       }
	       a:hover {
	           color: white;
	           text-decoration: none;
	       }
	   </style>
	
</head>
<body>
    <div class="container">
        <h1 class="p-3">ToDo List</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Date</th>
                    <th>Status</th>
					<th>Mark Completed</th>
	           		<th>Edit</th>
	           		<th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="todo" items="${list}">
                    <tr>
                        <td>${todo.title}</td>
                        <td>${todo.date}</td>
                        <td>${todo.status}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/viewToDoList/updateToDoStatus/${todo.id}" class="btn btn-success">Mark Complete</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/viewToDoList/editToDoItem/${todo.id}" class="btn btn-primary">Edit</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/viewToDoList/deleteToDoItem/${todo.id}" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
		<button type="button" class="btn btn-primary btn-block">
		    	<a href="/viewToDoList/addToDoItem">Add New ToDo Item</a>
		    </button>
    </div>
	<script th:inline="javascript">
	               window.onload = function() {

	                   var msg = "${message}";
	                   
	                   if (msg == "Save Success") {
	       				Command: toastr["success"]("Item added successfully!!")
	       			} else if (msg == "Delete Success") {
	       				Command: toastr["success"]("Item deleted successfully!!")
	       			} else if (msg == "Delete Failure") {
	       			    Command: toastr["error"]("Some error occurred, couldn't delete item")
	       			} else if (msg == "Edit Success") {
	       				Command: toastr["success"]("Item updated successfully!!")
	       			}

	       			toastr.options = {
	                         "closeButton": true,
	                         "debug": false,
	                         "newestOnTop": false,
	                         "progressBar": true,
	                         "positionClass": "toast-top-right",
	                         "preventDuplicates": false,
	                         "showDuration": "300",
	                         "hideDuration": "1000",
	                         "timeOut": "5000",
	                         "extendedTimeOut": "1000",
	                         "showEasing": "swing",
	                         "hideEasing": "linear",
	                         "showMethod": "fadeIn",
	                         "hideMethod": "fadeOut"
	                       }
	       	    }
	           </script>
</body>
</html>
