<%@ page import="domain.model.Bloem" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bloem Niet Gevonden</title>
    <link href="style.css" rel="stylesheet" type="text/css"><script src="https://kit.fontawesome.com/eca43c78f4.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="favicon/favicon-16x16.png">
    <link rel="manifest" href="/site.webmanifest">
</head>
<body>
<div class="alles">

    <jsp:include page="header.jsp">
        <jsp:param name="title" value="voegToe"/>
    </jsp:include>

    <div class="content">
        <div class="kiekBloem">
        <main>
            <article>
                <h2>Bloem niet gevonden...</h2>
                <p>Helaas, de bloem waarnaar je zocht is niet gevonden. Je kan de bloem wel <a href="BloemServlet?command=navigeerVoegToe">toevoegen</a> als je wil.</p>
                <p>Of ga op <a href="BloemServlet?command=zoekFormulier">zoek</a> naar een andere bloem.</p>

            </article>
        </main>

        </div>
    </div>
    <jsp:include page="footer.jsp"/>

    <script>
        $(document).ready(function() {
            $('#hamburger-icon').click(function () {
                $(".main-menu ul").stop();
                $(".main-menu ul").slideToggle();
                $('#hamburger-icon').toggleClass("fa-times");
                $('#hamburger-icon').toggleClass("fa-bars");
            });
        });
    </script>
</div>
</body>
</html>