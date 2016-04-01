<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Report</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript">

	function startRefresh() {
		var now = new Date;
		var today = now.toISOString().substring(0, 10);
		var start_time = "00:00";
		var end_time = now.toTimeString().substring(0, 5);
	//	boolean t = ($("#isTimeout").is(':checked')) ? 1 : 0;		            
		$("#date").val(today);                            
		$("#time1").val(start_time);                         
		$("#time2").val(end_time);  
		//$('#isTimeout').val($("#isTimeout").is(':checked'));   
		//$("#t").val(t);
		$("#queryBox").submit();                                  
	}                                                
/*	//boolean t = ($("#isTimeout").is(':checked')) ? 1 : 0;   
	if($("#isTimeout").is(':checked')) {
		setTimeout(startRefresh, 30000);
	}
	
	function autoRefresh(){
		//boolean t = ($("#isTimeout").is(':checked')) ? 1 : 0;
		if($("#isTimeout").is(':checked')) {
			setTimeout(startRefresh, 30000);
		}*/
	
		setTimeout(startRefresh, 180000);
	
	
	
</script>
<link
	href="https://ajax.googleapis.com/ajax/static/modules/gviz/1.1/orgchart/orgchart.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<h1>
		<center>FASHION AND YOU-REPORT</center>
	</h1>
	<form id="queryBox" action="/reports/api/countHome" name="UserDto"
		method="POST">
		<b>Enter Date</b><br /> 
		<input type="date" name="date" id="date"><br />
		<b>Enter start time</b> <br /> 
		 <input type="time" name="time1"id="time1" /> <br />
		<b>Enter End time</b> <br /> 
	    <input type="time" name="time2" id="time2" />  	<br />
		<input type="submit" value="GET DATA" /><br /> 
		<!--  AutoRefresh: <input type="checkbox" id="isTimeout" onclick="autoRefresh()" value="${t}"/> --> 
	</form>

	<script
		src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1.1','packages':['orgchart']}]}"
		type="text/javascript"></script>
	<link rel="stylesheet" type="text/css"
		href="https://www.google.com/uds/api/visualization/1.1/b9acaf9ce948f3550e84416288f03821/ui+en.css">
	<script type="text/javascript"
		src="https://www.google.com/uds/api/visualization/1.1/b9acaf9ce948f3550e84416288f03821/format+en,default+en,ui+en,orgchart+en.I.js"></script>
	<div id="chart_div"></div>
	<script type="text/javascript">
		//&lt;![CDATA[

		google.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Name');
			data.addColumn('string', 'Manager');
			data.addColumn('string', 'ToolTip');                     
                                                      
			data.addRows([                                                  
							<c:forEach items="${ReportsDoList}" var="list">[
									{
										v : '${list.name}',
										f : '${list.name}<div style="color:red; font-style:italic"><a href="${list.href}">${list.count}</a></div>'
									}, '${list.parentName}', ''], </c:forEach> ]);

			var chart = new google.visualization.OrgChart(document
					.getElementById('chart_div'));
			chart.draw(data, {
				allowHtml : true
			});
		}
	</script>
	


</body>
</html>