<%@ page import="domain.model.Bloem" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <title>Pass aan</title>
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
            <c:if test="${not empty errors}">
                <div id="error" class="alert alert-danger">
                    <ul>
                        <c:forEach items="${errors}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <h1>Voeg je nieuw bloem toe</h1>
            <form method="post" action="BloemServlet?command=editConfirmation&naam=${bloem.naam}">
                <div class="row">
                    <div class="col-25">
                        <input type="hidden" name="command" value="edit">
                        <input type="hidden" name="naam" value="${bloem.naam}">
                        <label for="naam">Naam:</label>

                    </div>
                    <div class="col-75">
                        <input type="text" id="naam" name="naam" placeholder="Naam van een bloem.."
                               value="${bloem.naam}" class="${naamHasErrors}"required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="color">Kleur:</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="color" name="color" placeholder="Kies een kleur.."
                               value="${bloem.kleur}" class="${colorHasErrors}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="flower">Aantal</label>
                    </div>
                    <div class="col-75">
                        <input type="number" id="flower" name="flower"
                               value="${bloem.aantal}"  class="${aantalHasErrors}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <input type="submit" name="kiezen" value="PAS AAN"><br>
                        <input type="submit" name="kiezen" value="annuleren">
                    </div>
                </div>
            </form>

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
