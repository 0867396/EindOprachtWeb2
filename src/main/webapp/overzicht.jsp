<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.db.BloemDB" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.model.Bloem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Overzicht</title>
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
        <jsp:param name="title" value="overzicht"/>
    </jsp:include>

    <div class="content">
        <c:choose>
       <c:when test="${not empty Bloemen}">
        <h2>Overzicht</h2>

        <div class="overzichtTable">
            <table>
                <tr>
                    <th>Naam</th>
                    <th>Kleur</th>
                    <th>Aantal</th>
                    <th>Pas aan</th>
                    <th>Verwijder</th>
                </tr>

                <%--    Open for-loop--%>
             <%--   <%ArrayList<Bloem> bloem = (ArrayList<Bloem>) request.getAttribute("Bloemen");
                    for (Bloem b : bloem) {%> --%>
                <c:if test="$not empty Bloemen"></c:if>
                <c:forEach var="bloem" items="${Bloemen}">
                    
                <tr>
                    <td>${bloem.naam}</td>
                    <td>${bloem.kleur}</td>
                    <td>${bloem.aantal}</td>
                    <td><button><a href="BloemServlet?command=edit&naam=${bloem.naam}">pass aan</a></button></td>
                    <td><button><a href="BloemServlet?command=verwijder&naam=${bloem.naam}">Verwijder</a></button></td>

                </tr>
                </c:forEach>
            </table>


            <span>De bestverkochte bloem is <b>${meesteVerkochtBloem.naam}</b> </span><br>
        </div>
        </c:when>
            <c:otherwise>
                <p>Er zijn geen bloemen</p>
            </c:otherwise>
        </c:choose>
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