package toTranspile;

import def.dom.HTMLInputElement;
import def.dom.HTMLLabelElement;
import def.dom.HTMLParagraphElement;
import def.jquery.JQuery;
import def.jquery.JQueryXHR;
import def.js.JSON;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;
import static def.jquery.Globals.$;

/**
 * Rappresenta la pagina in cui, data una definizione in inglese, l’utente deve provare a 
 * indovinare la parola definita 
 */
public class WordAnnotation extends BasicAnnotValidat implements BuildablePage
{   
    public static void main(String[] args) 
    {   
        new WordAnnotation().buildPage();
    }
    
    /**
     * contenitore della definizione scaricata dal server
     */
    private HTMLParagraphElement definitionContainer;
    
    /**
     * etichetta del campo di testo in cui inserire la parola
     */
    private HTMLLabelElement wordLabel;
    
    /**
     * campo di testo in cui inserire la parola
     */
    private HTMLInputElement wordTextField;
    
    /**
     * Costruttore di WordnAnnotation. Inizializza gli elementi e i loro attributi e aggiunge
     * gli event listener
     */
    public WordAnnotation()
    {
        // elemento form
        form = Utilities.createPOSTForm("wordAnnotation.jsp");
        
        // contenitore della definizione della parola
        this.definitionContainer = document.createElement(StringTypes.p);
        definitionContainer.className = "definition-container";
        
        // etichetta del campo di testo in cui inserire la parola
        this.wordLabel = Utilities.createLabel("What's the word?");
        
        // campo di testo in cui inserire la parola
        this.wordTextField = Utilities.createTextField("word");
        
        /* la definizione della parola viene scaricata dal server e salvata nel campo dell'apposito
        elemento */
        $.getJSON("nextExample.jsp", "task=WORD_ANNOTATION", (Object result, String a, JQueryXHR ctx) -> {
            JSON json = (JSON) result;
            String definition = json.$get("description");
            definitionContainer.textContent = definition;
            return null;
        });
        
        // pulsante skip
        skipBtn.addEventListener("click", (event) -> {
            // l'input dell'utente viene resettato
            form.reset();
            form.submit();
        });
    }
    
    @Override
    public JQuery buildPage()
    {                
        // all'elemento <body> vengono appesi gli elementi figli
        $("body").append(
            header,
            $(mainEl).append(
                $(form).append(
                    definitionContainer,
                    wordLabel,
                    wordTextField,
                    skipAndNextBtnsContainer
                )
            )
        );
        return $("body");
    }
}
