<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- Core CSS Files --%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unpkg.com/shards-ui@latest/dist/css/shards.min.css">
    <link rel="stylesheet" href="/resources/css/style.css"/>
    <%-- Icon and Fonts--%>
    <link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,400,700" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900&amp;subset=latin-ext" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500&display=swap&subset=korean" rel="stylesheet"/>
    <%-- Core JS Files --%>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/shards-ui@latest/dist/js/shards.min.js"></script>
    <script src="/resources/js/style.js"></script>
</head>

<body>
<%-- Wrapper 영역 --%>
<div id="layoutWrapper">
    <%-- Header 영역 --%>
    <div id="layoutHeader"><tiles:insertAttribute name="header" ignore="true"/></div>
    <%-- Menu 영역 --%>
    <div id="layoutMenu"><tiles:insertAttribute name="menu" ignore="true"/></div>
    <%-- Content 영역 --%>
    <div id="layoutContent"><tiles:insertAttribute name="content" ignore="true"/></div>
    <%-- Footer 영역 --%>
    <div id="layoutFooter"><tiles:insertAttribute name="footer" ignore="true"/></div>
</div>
</body>
</html>