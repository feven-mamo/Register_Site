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


	<div class="container text-center">
		<h3>Add Course Preference</h3>
		<hr>

		<form:form class="form-horizontal" modelAttribute="course"
			method="POST" action="" enctype="multipart/form-data">

			<div class="form-group">
				<label class="control-label col-md-3">Course Name</label>
				<div class="col-md-3">
					<form:select onchange="preCourse()" path="courseNum"
						id="selectCourse" class="form-control inputstl">
						<form:option value="NONE" label="Select" />
						<form:options itemValue="courseNum" itemLabel="name"
							items="${courses}" />
					</form:select>

				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Prerequisite Course</label>
				<div class="col-md-3">
					<form:select onchange="showBlock()" path="courseNum"
						id="selectPreCourse" class="form-control inputstl">
						<form:option value="NONE" label="Select Prerequiste"></form:option>
					</form:select>

				</div>
			</div>


			<div class="container text-center" id="tasksDiv">
				<h3>Course Details</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead >
							<tr>
								<th>Check</th>
								<th>Block</th>
								<th>Professor</th>
								<th>Capacity</th>
								<th>Enrolled</th>
								<th>Seats</th>

							</tr>
						</thead>
						<tbody id="blockDetails">
						</tbody>
					</table>

					<div class="form-group ">
						<input type="button" class="btn btn-primary" onclick="register()" value="Register" />
					</div>
				</div>
			</div>
		</form:form>
	</div>
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/main2.js"></script>
</body>
</html>