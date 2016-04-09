<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Report</title>

<style type="text/css">
</style>

</head>

<body>
	<h1>
		<center>SUBSCRIPTION</center>
	</h1>

	<form id="queryBox" action="api/subscribe"
		method="POST">
		<b><character name/b><br> <input type="text" name="character_name"><br>

		<b>Character Id</b><br> <input type="text" name="character_id"><br>
		
		<b>subscribed_character_id</b><br> <input type="text" name="subscribed_character_id"><br>

		<b>subscribed_character Name</b><br> <input type="text" name="subscribed_character"><br>
		

		<b>Field subscribed on</b><br> <input type="text" name="field"><br>
		
		<input type="submit" value="GET DATA" />



	</form>





</body>
</html>