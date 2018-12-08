<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
<title>MUM| home</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<body>
<div role="navigation">
	<div class="navbar navbar-inverse">
			<a href="/welcome" class="navbar-brand">MUM</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<c:if test="${pageContext.request.userPrincipal.name == null}">
						<li><a href="/login">Login</a></li>
					</c:if>
					<c:if test="${role == 'ADMIN'}">
						<li><a href="/register">New User Registration</a></li>
						<li><a href="/show-users">All Users List</a></li>
						<li><a href="/add-course">Register Course</a></li>
						<li><a href="/approvepre">Approve Preference</a></li>
						<li><a href="/approve-waivers">Approve Waivers</a></li>
					</c:if>
					
					<c:if test="${role == 'STUDENT'}">
						<li><a href="/addcourse">Apply Preference</a></li>
						<li><a href="/showstatus">Preference Status</a></li>
						<li><a href="/add-block">Register Block</a></li>
						<li><a href="/view">View Waiver Request Status</a></li>
						<li><a href="/waive">Request Waivers</a></li>
					</c:if>
					
					<c:if test="${pageContext.request.userPrincipal.name != null}">
				        <form style="display:none;" id="logoutForm" method="POST" action="${contextPath}/logout">
				            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        </form>
						<li><a href="/show-courses">Course List</a></li>
						<li><a href="/show-blocks">Block List</a></li>
						<li><a href="#" onclick="document.forms['logoutForm'].submit()">Logout(${pageContext.request.userPrincipal.name})</a></li>
				        
				    </c:if>
				</ul>
			</div>
			<div><a href="/welcome?lang=en">English</a> | <a href="/welcome?lang=am">Amharic</a></div>
		</div>
	</div>
	
	<div>${role}</div>
	<div class="container text-center" id="tasksDiv">
		<h3>Course List</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Course Number</th>
						<th>Course Name</th>
						<th>Prerequiste</th>
						<th>Delete</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="course" items="${courses }">
					<tr>
							<td>${course.courseNum}</td>
							<td>${course.name}</td>
							<td>${course.prerequisite.name}</td>

							<td><a href="/delete-course?coursenumber=${course.courseNum }"><span
									class="glyphicon glyphicon-trash"></span></a></td>
							<td><a href="/edit-course?coursenumber=${course.courseNum}"><span
									class="glyphicon glyphicon-pencil"></span></a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>