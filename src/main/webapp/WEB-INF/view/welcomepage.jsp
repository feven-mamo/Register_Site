<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
<title>tecno-tab | home</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
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
						<li><a href="/add-block">Register Block</a></li>
						<li><a href="/approvepre">Approve Preference</a></li>
						<li><a href="/approve-waivers">Approve Waivers</a></li>
					</c:if>
					
					<c:if test="${role == 'STUDENT'}">
						<li><a href="/addcourse">Apply Preference</a></li>
						<li><a href="/showstatus">Preference Status</a></li>
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
	<c:choose>
		<c:when test="${mode=='MODE_HOME' }">
			<div class="container" id="homediv">
				<div class="jumbotron text-center">
				<spring:message    code="label.welcome" />
					<label name="welcome"></label>
					<h1>Welcome to MUM Registrar</h1>
					<h3>Register for courses</h3>
				</div>
			</div>
		</c:when>

		<c:when test="${mode=='MODE_REGISTER' }">
			<div class="container text-center">
				<h3>Register New User</h3>
				<hr>
				<form:form class="form-horizontal" modelAttribute="user" method="POST" action="save-user" enctype="multipart/form-data">
					<form:input type="hidden" name="user_id" path="user_id" />

					<div class="form-group">
						<label class="control-label col-md-3">First Name</label>
						<div class="col-md-7">
							<form:input type="text" class="form-control" name="firstname"
								path="firstname" />
								<form:errors path="firstname" class="valerror"/>
								
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Last Name</label>
						<div class="col-md-7">
							<form:input type="text" class="form-control" name="lastname"
								path="lastname" />
								<form:errors path="lastname" class="valerror"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Email </label>
						<div class="col-md-3">
							<form:input type="text" class="form-control" name="email"
								path="email" />
								<form:errors path="email" class="valerror"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Date of Birth </label>
						<div class="col-md-3">
							<form:input type="date" class="form-control" name="dob"
								path="dob" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-3">
							<form:input type="text" class="form-control" name="username"
								path="username" />
								<form:errors path="username" class="valerror" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Password</label>
						<div class="col-md-3">
							<form:password class="form-control" name="password"
								path="password" />
								<form:errors path="password" class="valerror" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Role</label>
						
						<div class="col-md-3">
							<select name="role" class="form-control inputstl">
								<option>STUDENT</option>
								<option>ADMIN</option>
							</select>
						</div>
						
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Profile Picture</label>
						<div class="col-md-3">
							<form:input type="file" class="form-control" name="image"
								path="image" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Register" />
					</div>
				</form:form>
			</div>
		</c:when>

		<c:when test="${mode=='ALL_USERS' }">
			<h3>${msg}</h3>
			<div class="container text-center" id="tasksDiv">
		
				<h3>All Registered Users</h3>
				<hr>
				<div class="table-responsive">
				
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>UserId</th>
								<th>UserName</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th>Date Of Birth</th>
								<th>Role</th>
								<th>Photo</th>
								<th>Delete</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users }">
								<tr>
									<td>${user.user_id}</td>
									<td>${user.username}</td>
									<td>${user.firstname}</td>
									<td>${user.lastname}</td>
									<td>${user.email}</td>
									<td>${user.dob}</td>
									<td>${user.role}</td>
									<td>
									<img
						src="<c:url value="/resources/images/${user.user_id}.png"/>"
						alt="${user.username}" height="42" width="42">
									</td>
									<td><a href="/delete-user?id=${user.user_id }"><span
											class="glyphicon glyphicon-trash"></span></a></td>
									<td><a href="/edit-user?id=${user.user_id }"><span
											class="glyphicon glyphicon-pencil"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>

		<c:when test="${mode=='MODE_UPDATE' }">
			<div class="container text-center">
				<h3>Update User</h3>
				<hr>
				<form:form class="form-horizontal" modelAttribute="user" method="POST" action="save-user">
					<form:input type="hidden" name="user_id" path="user_id" />

					<div class="form-group">
						<label class="control-label col-md-3">First Name</label>
						<div class="col-md-7">
							<form:input type="text" class="form-control" name="firstname"
								path="firstname" />
								
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Last Name</label>
						<div class="col-md-7">
							<form:input type="text" class="form-control" name="lastname"
								path="lastname" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Email </label>
						<div class="col-md-3">
							<form:input type="text" class="form-control" name="email"
								path="email" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Date of Birth </label>
						<div class="col-md-3">
							<form:input type="date" class="form-control" name="dob"
								path="dob" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-3">
							<form:input type="text" class="form-control" name="username"
								path="username" />
								<form:errors path="username" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Password</label>
						<div class="col-md-3">
							<form:password class="form-control" name="password"
								path="password" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Role</label>
						<div class="col-md-3">
							
								<form:select name="role" path="role" class="form-control inputstl">
								<option>STUDENT</option>
								<option>ADMIN</option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Profile Picture</label>
						<div class="col-md-3">
							<form:input type="file" class="form-control" name="image"
								path="image" />
						</div>
					</div>

					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Register" />
					</div>
				</form:form>
			</div>
		</c:when>


		<c:when test="${mode=='MODE_LOGIN' }">
			<div class="container text-center">
				
				<hr>
				<form method="POST" action="${contextPath}/login" class="form-signin">
				
			        <h2 class="form-heading">Log in</h2>
			       
			        <div class="form-group ${error != null ? 'has-error' : ''}">
			            <span>${error}</span>
			            <input name="username" type="text" class="form-control" placeholder="Username"
			                   />
			            <input name="password" type="password" class="form-control" placeholder="Password"/>
			            <span>${error}</span>
			            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
			            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
			           
			        </div>
			
			    </form>
			    <!--
				<form class="form-horizontal" method="POST" action="${contextPath}/login">
					<c:if test="${not empty error }">
						<div class="alert alert-danger">
							<c:out value="${error }"></c:out>
						</div>
					</c:if>
					<div class="form-group">
					<div class="control-label col-md-3">
					<spring:message    code="label.userName" /></div>
						
						<div class="col-md-7">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label col-md-3">
					<spring:message    code="label.password" />
					</div>
						
						<div class="col-md-7">
							<input type="password" class="form-control" name="password"
								value="${user.password }" />
						</div>
					</div>
					<div class="form-group ">
					<spring:message code="label.submit" var="labelSubmit"></spring:message>
					 <input type="submit"  class="btn btn-primary" value="${labelSubmit}" />
					
					</div>
				</form>-->
			</div>
		</c:when>
	</c:choose>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>