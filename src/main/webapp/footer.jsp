<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer>

    <div class="socialMedia">
        <p>BlomenWinkel &copy;</p>
        <p id="followparagraaf">Volg ons</p>
        <a href="https://www.facebook.com/facebookapp" class="socialMedia"><i class="fab fa-facebook-square"></i></a>
        <a href="https://www.instagram.com/" class="socialMedia"><i class="fab fa-instagram"></i></a>
        <a href="https://twitter.com/" class="socialMedia"><i class="fab fa-twitter"></i></a>
        <a href="https://www.snapchat.com/l/nl-nl/" class="socialMedia"><i class="fab fa-snapchat-ghost"></i></a>
    </div>
        <div id="cookie">
        <c:choose>
            <c:when test="${bannerAttribute == 'yes'}">
            <p>Cookies worden wel verzameld. <a href="BloemServlet?command=switchCookie">Wijzig</a></p>
        </c:when>
            <c:when test="${bannerAttribute == 'no'}">
                <p>Cookies worden niet verzameld. <a href="BloemServlet?command=switchCookie">Wijzig</a></p>
            </c:when>
         <c:otherwise >
        <p>Deze website slaat gegevens op zoals cookies om sitefunctionaliteit mogelijk te maken, inclusief analyse en personalisatie. Door deze website te gebruiken, accepteert u automatisch dat wij cookies gebruiken.s<a href="BloemServlet?command=cookieYes"> Ja </a><a href="BloemServlet?command=cookieNo"> Nee</a></p>
         </c:otherwise>
        </c:choose>
        </div>

</footer>
