<%--
  Created by IntelliJ IDEA.
  User: rojee
  Date: 7/05/2022
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verwijderen</title>
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

    <jsp:include page="header.jsp"/>

    <div class="content">
        <div class="kiekBloem">
            <h2 class="schuiven">Bevestiging</h2>
    <p>Ben je zeker dat je deze bloem met de naam ${bloem.naam} wil verwijderen?</p>
    <form method="post" action="BloemServlet?command=verwijderConfirmation&naam=${bloem.naam}" novalidate>
        <button type="submit" name="kiezen" value="ja">ja</button><br>
        <button type="submit" name="kiezen" value="annuleren">annuleren</button>


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
