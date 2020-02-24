package toTranspile;

import static def.dom.Globals.document;
import static def.jquery.Globals.$;

import def.dom.HTMLAnchorElement;
import def.dom.HTMLButtonElement;
import def.dom.HTMLDivElement;
import def.dom.HTMLFormElement;
import jsweet.util.StringTypes;

/**
 * Contiene gli elementi comuni a tutte le pagine di annotazione e validazione
 */
public abstract class BasicAnnotValidat
{
    /**
     * link alla home page
     */
    private HTMLAnchorElement homeLink;
    
    /**
     * link alla pagina di logout
     */
    private HTMLAnchorElement logoutLink;
    
    /**
     * elemento header che contiene link alla home page e link alla pagina di logout
     */
    protected HTMLDivElement header;
    
    /**
     * elemento main
     */
    protected HTMLDivElement mainEl;
    
    /**
     * elemento form
     */
    protected HTMLFormElement form;
    
    /**
     * pulsante skip
     */
    protected HTMLButtonElement skipBtn;
    
    /**
     * pulsante next
     */
    protected HTMLButtonElement nextBtn;
    
    /**
     * contenitore dei pulsanti skip e next
     */
    protected HTMLDivElement skipAndNextBtnsContainer;
    
    /**
     * Costruttore di BasicAnnotValidat.
     * <ul> 
     *   <li>Inizializza gli elementi e i loro attributi.</li>
     *   <li>Appende il link alla home page e il link alla pagina di logout all'elemento header.</li>
     *   <li>Appende il pulsante skip e next al loro contenitore.</li>
     * </ul>
     */
    public BasicAnnotValidat()
    {
        // link alla home page
        homeLink = document.createElement(StringTypes.a);
        homeLink.href = "home.html";
        homeLink.textContent = "Home page";
        
        // link alla pagina di logout
        logoutLink = document.createElement(StringTypes.a);
        logoutLink.href = "logout.jsp";
        logoutLink.textContent = "Log out";
        
        // elemento header che contiene link alla home page e link alla pagina di logout
        header = document.createElement(StringTypes.div);
        header.id = "header";
        $(header).append(homeLink, logoutLink);
        
        // elemento main
        mainEl = Utilities.createMain();
        
        // pulsante skip
        skipBtn = Utilities.createButton("button", "Skip");
        // name e value verranno inviati al server se il pulsante è stato premuto
        skipBtn.name = "skipBtn";
        skipBtn.value = "clicked";
        skipBtn.className = "btn btn-primary"; // "btn" e "btn-primary" sono classi di bootstrap
        
        // pulsante next
        nextBtn = Utilities.createButton("submit","Next");
        // name e value verranno inviati al server se il pulsante è stato premuto
        nextBtn.name = "nextBtn";
        nextBtn.value = "clicked";
        nextBtn.className = "btn btn-primary"; // "btn" e "btn-primary" sono classi di bootstrap
        
        // contenitore dei pulsanti skip e next
        skipAndNextBtnsContainer = document.createElement(StringTypes.div);
        skipAndNextBtnsContainer.className = "skip-and-next-btns-container";
        $(skipAndNextBtnsContainer).append(skipBtn, nextBtn);
    }
}