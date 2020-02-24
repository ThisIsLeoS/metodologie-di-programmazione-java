package toTranspile;

import def.dom.HTMLAnchorElement;
import def.dom.HTMLButtonElement;
import def.dom.HTMLDivElement;
import def.jquery.JQuery;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;
import static def.dom.Globals.window;
import static def.jquery.Globals.$;

/**
 * Rappresenta la home page
 */
public class Home implements BuildablePage
{
    public static void main(String[] args)
    {
        new Home().buildPage();
    }
    
    /**
     * elemento main
     */
    private HTMLDivElement main;
    
    /**
     * pulsante start
     */
    private HTMLButtonElement startBtn;
    
    /**
     * link alla pagina di logout
     */
    private HTMLAnchorElement logoutLink;
    
    /**
     * Costruttore di Home. Inizializza gli elementi e i loro attributi
     */
    public Home()
    {
        // elemento <main>
        this.main = Utilities.createMain();
        
        // pulsante start
        this.startBtn = Utilities.createButton("button", "Start");
        startBtn.className = "btn btn-primary btn-lg start-btn"; // "btn", "btn-primary" e "btn-lg" sono classi di bootstrap
        startBtn.addEventListener("click", (event) -> {       
            window.location.href = Utilities.getRandomHTMLFilename("");
        });
        
        // link alla pagina di logout
        this.logoutLink = document.createElement(StringTypes.a);
        logoutLink.href = "logout.jsp";
        logoutLink.textContent = "Log out";
    }
    
    @Override
    public JQuery buildPage()
    {
        // all'elemento <body> vengono appesi tutti i discendenti
        $("body").append(
            $(main).append(
                startBtn,
                logoutLink
            )
        );   
        return $("body");
    }
}
