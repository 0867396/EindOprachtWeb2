<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="main-menu">
        <span class="bar-hamburger"><i id="hamburger-icon" class="fas fa-bars"></i></span>
        <div id="logo"><img src="img/flower_icon.png" alt="navIcon"></div>
        <ul>
            <li ${param.title eq 'Home' ? 'id="actual"':""}>
                     <a href="BloemServlet">Home</a></li>
            <li ${param.title eq 'voegToe' ? 'id="actual"':""}>
                     <a href="BloemServlet?command=navigeerVoegToe">Voeg Toe</a></li>
            <li ${param.title eq 'overzicht' ? 'id="actual"':""}>
                     <a href="BloemServlet?command=overview">Overzicht</a></li>
            <li ${param.title eq 'overzicht' ? 'id="actual"':""}>
                <a href="BloemServlet?command=zoekFormulier">Zoek</a></li>

        </ul>
    </nav>
</header>
