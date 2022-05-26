<%@ page import="domain.model.Bloem" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
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
        <jsp:param name="title" value="Home"/>
    </jsp:include>

    <div class="content">
        <article class="beschrijving">
            <h2>Home</h2>
            <span>Ontdek onze bloemen. Hoe noemen ze. Wat zijn hun kleuren?
        We schrijven hier alles over onze bloemen.</span><br>
            <span>De bestverkochte bloem is <b>De bestverkochte bloem is${meesteVerkochtBloem.naam}</b> </span>
            <span>Samen met onze kwekers kan u de beste bloemen uit het seizoen selecteren.</span>
            <span>Bloemen waar mens én natuur blij van worden: daar zetten we ons voor in.</span>
            <p>Wij kopen onze producten bij onze lokale Belgische partners om  topkwaliteit te verzekeren.
                Alle bloemen en planten worden met zorg gekweekt en zorgvuldig gearrangeerd door gepassioneerde stylisten en bloemisten.
            </p>
        </article>
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


        $(document).ready(function(){
            $('.knop').click(function(){
                $('.cookie-container').hide();


            });

        });

    </script>
</div>

</body>
</html>