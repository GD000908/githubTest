<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>입력 페이지</title>
</head>
<body>
	<h2>사용자 입력</h2>
	<form action="insert" method="post">
		이름: <input type="text" name="name" required><br> 나이: <input
			type="number" name="age" required><br>
		<button type="submit">저장</button>
	</form>
</body>
</html>
