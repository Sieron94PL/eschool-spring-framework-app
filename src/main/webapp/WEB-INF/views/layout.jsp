<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>



<body style="width: 100%; height: 100%;">

	<table style="border-style:solid" width="100%" height="100%">
		<tr>
			<td height="110px"
				style="background: -moz-linear-gradient(right, gray, white); background-image: url(http://localhost:8080/eschool/resources/eschool.jpg); background-repeat: no-repeat;"
				colspan="2"><tiles:insertAttribute name="header" /></td>
		</tr>
		<tr>
			<td height="12px"><tiles:insertAttribute name="menu" /></td>
		</tr>
		<tr>
			<td style="vertical-align:top;"><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td colspan="2"><tiles:insertAttribute name="footer" /></td>
		</tr>
	</table>

</body>
</html>