package ui.controller;

import domain.db.BloemDB;
import domain.model.Bloem;
import execptions.DomainException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "BloemServlet", value = "/BloemServlet")
public class BloemServlet extends HttpServlet {
    private BloemDB bloemDB = new BloemDB();
    private Bloem bloem = new Bloem();

    public BloemServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**   request.setAttribute("Bloemen", bloemDB.getBloemen());
         RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
         view.forward(request, response);**/
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

        /**String naam = request.getParameter("fname");
         String kleur = request.getParameter("fcolor");
         int aantal = Integer.parseInt(request.getParameter("flower"));

         Bloem bloem = new Bloem(naam, kleur, aantal);
         request.setAttribute("Bloemen", bloemDB.getBloemen());
         bloemDB.voegBloemToe(bloem);
         RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
         view.forward(request, response);**/
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "home";
        if (request.getParameter("command") != null) {
            command = request.getParameter("command");
        }
        String destination;
        switch (command){
            case "cookieYes":
                destination = cookieYes(request,response);
                break;
            case "cookieNo":
                destination = cookieNo(request,response);
                break;
            case "home":
                destination = home(request,response);
                break;
            case "addElement":
                destination = addElement(request,response);
                break;
            case "navigeerVoegToe":
                destination = navigeerVoegToe(request,response);
                break;
            case "overview":
                destination = overview(request, response);
                break;
            case "switchCookie":
                destination = switchCookie(request,response);
                break;
            case "edit":
                destination = edit(request,response);
                break;
            case "editConfirmation":
                destination = editConfirmation(request,response);
                break;
            case "verwijder":
                destination = verwijder(request,response);
                break;
            case "verwijderConfirmation":
                destination = verwijderConfirmation(request,response);
                break;
            case "zoekFormulier":
                destination = zoek(request,response);
                break;
            case "zoek":
                destination = getZoekForm(request, response);
                break;
            default:
                destination = home(request,response);
        }
        request.getRequestDispatcher(destination).forward(request,response);//geneert jsp pagina
    }

    private String verwijder(HttpServletRequest request, HttpServletResponse response) {
       String naam =  request.getParameter("naam");
       Bloem bloem = bloemDB.zoek(naam);
       request.setAttribute("bloem",bloem);
        return "verwijderConfirmation.jsp";

    }

    private String verwijderConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naam = request.getParameter("naam");
        String keuze = request.getParameter("kiezen");
        if(keuze.equals("annuleren")){
             return overview(request,response);
        }else {
            Bloem bloem = bloemDB.zoek(naam);
            try {
                bloemDB.verwijderBlomen(bloem);
                return overview(request,response);
            } catch (DomainException exc) {
                request.setAttribute("error", exc.getMessage());
                return overview(request,response);
            }
        }

    }



    private String switchCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("bannerCookie","delete");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "index.jsp";
    }

    private String navigeerVoegToe(HttpServletRequest request, HttpServletResponse response) {
        setCookie(request,response);
        return "voegtoe.jsp";
    }
    private String addElement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCookie(request,response);

        ArrayList<String> errors = new ArrayList<String>();
        Bloem bloem = new Bloem();
        setNaam(bloem, request, errors);
        setKleur(bloem, request, errors);
        setAantal(bloem, request, errors);
        if (errors.size() == 0) {
            try {
                bloemDB.voegBloemToe(bloem);
                return overview(request, response);
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "voegtoe.jsp";

    }

    private String edit(HttpServletRequest request, HttpServletResponse response) {
        String naam = request.getParameter("naam");
        Bloem bloem = bloemDB.zoek(naam);
        request.setAttribute("bloem", bloem);
        return "edit.jsp";
    }

    private String editConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<String>();// lijst van errors
        String naam = request.getParameter("naam");
        String keuze = request.getParameter("kiezen");
        Bloem bloem = new Bloem();
            // new object toevoegen
            setNaam(bloem, request, errors);//
            setKleur(bloem, request, errors);
            setAantal(bloem, request, errors);
            request.setAttribute("bloem",bloem);
            if (errors.size() == 0) {// parameter zijn foutloos toevoegen aan bloem objeect
                try {
                    bloemDB.zoek(naam).vervangBloem(bloem);
                    return overview(request, response);
                } catch (DomainException exc) {
                    errors.add(exc.getMessage());
                }
            }

            request.setAttribute("errors",errors);//
            return "edit.jsp";
    }

    private String getZoekForm(HttpServletRequest request, HttpServletResponse response) {
        return "zoekFormulier.jsp";
    }

    private String zoek(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        Bloem bloem = new Bloem();
        setNaam(bloem, request, errors);

        if (errors.size() == 0) {
            try {
                if (bloemDB.vindNaam(bloem.getNaam())) {
                    request.setAttribute("bloem", bloemDB.zoek(bloem.getNaam()));
                    return "zoekGevonden.jsp";
                } else {
                    return "zoekNietGevonden.jsp";
                }
            } catch (DomainException exc) {
                errors.add(exc.getMessage());

            }

        }
        request.setAttribute("errors", errors);
        return getZoekForm(request, response);
    }





    private void setAantal(Bloem bloem, HttpServletRequest request, ArrayList<String> errors) {
        String aantal = request.getParameter("flower");
        boolean aantalHasErrors = false;
        try {
            request.setAttribute("aantalPreviousValue", aantal);// attribuute aanmaken
            request.setAttribute("aantalHasErrors","success");
            bloem.setAantal(Integer.parseInt(aantal));
        } catch (NumberFormatException exc) {
            errors.add("Geef aantal bloemen in");
            aantalHasErrors = true;
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            aantalHasErrors = true;
        } finally {
            request.setAttribute("aantalHasErrors", aantalHasErrors);
        }
    }



    private void setKleur(Bloem bloem, HttpServletRequest request, ArrayList<String> errors) {
        String kleur = request.getParameter("color");
        boolean colorHasErrors = false;
        try {
            request.setAttribute("colorPreviousValue", kleur);
            request.setAttribute("colorHasErrors", "success");
            bloem.setKleur(kleur);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            colorHasErrors = true;
        } finally {
            request.setAttribute("colorHasErrors", colorHasErrors);
        }
    }

    private void setNaam(Bloem bloem, HttpServletRequest request, ArrayList<String> errors) {

        String naam = request.getParameter("naam");
        boolean naamHasErrors = false;
        try {
            request.setAttribute("naamPreviousValue", naam);
            request.setAttribute("naamHasErrors","success");
            bloem.setNaam(naam);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            naamHasErrors = true;
        } finally {
            request.setAttribute("naamHasErrors", naamHasErrors);
        }
    }



    private String home(HttpServletRequest request, HttpServletResponse response) {
        setCookie(request,response);
        request.setAttribute("meesteVerkochtBloem",bloemDB.getMeesteVerkochtBloem());
        return "index.jsp";
    }

    private String overview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCookie(request,response);
        request.setAttribute("Bloemen", bloemDB.getBloemen());
        request.setAttribute("meesteVerkochtBloem",bloemDB.getMeesteVerkochtBloem());
        return "overzicht.jsp";
    }

    private String cookieYes(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = new Cookie("bannerCookie","yes");
        response.addCookie(cookie);
        request.setAttribute("bannerAttribute","yes");
        return "index.jsp";
    }

    private String cookieNo(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = new Cookie("bannerCookie","no");
        response.addCookie(cookie);
        request.setAttribute("bannerAttribute","no");
        return "index.jsp";
    }



    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }

    private void setCookie(HttpServletRequest request , HttpServletResponse response){
        Cookie c = getCookieWithKey(request,"bannerCookie");
        if(c == null){
            request.setAttribute("bannerAttribute", "noCookies");
        }else {
            if(c.getValue().equals("no")){
                request.setAttribute("bannerAttribute","no");
            }else{
                if(c.getValue().equals("yes")){
                    request.setAttribute("bannerAttribute", "yes");
                }
            }
        }
    }


}

