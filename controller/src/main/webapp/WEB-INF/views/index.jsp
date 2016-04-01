<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Org Chart Example - jsFiddle demo</title>

<style type="text/css">
</style>

<link
	href="https://ajax.googleapis.com/ajax/static/modules/gviz/1.1/orgchart/orgchart.css"
	rel="stylesheet" type="text/css">
</head>

<body>
	<h1>
		<center>FASHION AND YOU-REPORT VIEWER</center>
	</h1>                                               
	
	<form id="queryBox" action="api/countHome" method="POST">
	
		
		Enter Your Name<input type="text" name="character_name">
<br />
Enter Your Id <input type="text" name="character_id" />

		Enter Id of Your subscription <input type="text" name="subscribed_character_id">
<br />
Enter Name of Your Subscription <input type="text" name="subscribed_character" />
Enter Your Subscription Field
<select name="field">
  <option value="location">location</option>
  <option value="skill">skill</option>
  <option value="status">status</option>
</select>
			                                        	
		 	 <input type="submit" value="POST DATA" />
	</form>

</body>
</html>