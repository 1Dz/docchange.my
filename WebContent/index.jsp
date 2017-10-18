<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" name="viewport" content="width=device-width, initial-scale=1">
<title>Doc Change</title>
<!--JQUERY-->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<!--BOOTSTRAP-->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/index.css">
<script src="js/bootstrap.js"></script>
<title>Doc change</title>
</head>
<body>
	<div class="form text-center">
		<h1>Чтобы начать войдите в личный кабинет</h1>
		<form action="main.jsp" method="POST">
			<p>
			<h3>Логин:</h3>
			<input type="text" name="login">
			<p>
			<h3>Пароль:</h3>
			<input type="password" name="key">
			<p>
			<input type="submit" value="Войти" class="btn btn-danger btn-md">
		</form>
	</div>
	<h6 class="footer text-right">by 1Dz</h6>
</body>
</html>