package toTranspile;

import def.dom.HTMLDivElement;
import def.dom.HTMLFormElement;
import def.dom.HTMLInputElement;
import def.dom.HTMLLabelElement;
import def.dom.HTMLParagraphElement;
import def.dom.HTMLSpanElement;
import def.jquery.JQuery;
import def.jquery.JQueryXHR;
import def.js.JSON;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;
import static def.jquery.Globals.$;

/**
 * Rappresenta la pagina in cui, data una parola e una frase in cui la parola appare, l’utente deve
 * selezionare il senso appropriato
 */
public class SenseAnnotation extends BasicAnnotValidat implements BuildablePage
{   
    public static void main(String[] args) 
    {               
       new SenseAnnotation().buildPage();
    }
    
    /**
     * elemento form
     */
    private HTMLFormElement form;
    
    /**
     * contenitore della frase "Select the correct meaning of the word <parola scaricata>"
     */
    private HTMLSpanElement selectSentenceContainer;
    
    /**
     * contenitore della parola scaricata dal server
     */
    private HTMLSpanElement wordContainer;
    
    /**
     * contenitore della frase scaricata dal server
     */
    private HTMLSpanElement serverSentenceContainer;
    
    /**
     * contenitore sia della frase "Select the corret" che della frase scaricata dal server
     */
    private HTMLParagraphElement wordAndSentenceContainer;

    /**
     * contenitore delle checkbox dei sensi scaricati dal server
     */
    private HTMLDivElement sensesBoxesContainer;
    
    /**
     * contenitore dei messaggi d'errore 
     */
    private HTMLDivElement errMsgContainer;
    
    /**
     * Costruttore di SenseAnnotation. Inizializza gli elementi e i loro attributi e aggiunge gli
     * event listener
     */
    public SenseAnnotation()
    {   
        // elemento form
        form = Utilities.createPOSTForm("senseAnnotation.jsp");
        form.addEventListener("click", (event) -> {
            // se nessuna checkbox è checked
            if ($(".checkboxes-container input:checked").length == 0)
            {
                errMsgContainer.textContent = "At least one checkbox must be checked!";
                event.preventDefault();
            }
        });
        
        // contenitore della frase "Select the correct meaning of the word <parola scaricata>"
        this.selectSentenceContainer = document.createElement(StringTypes.span);  
        
        // contenitore della parola scaricata dal server
        this.wordContainer = document.createElement(StringTypes.span);
        wordContainer.className = "word-container"; 
        
        // contenitore della frase scaricata dal server
        this.serverSentenceContainer = document.createElement(StringTypes.span);
        serverSentenceContainer.className = "sentence-container";
        
        // contenitore sia della frase "Select the corret" che della frase scaricata dal server
        this.wordAndSentenceContainer = document.createElement(StringTypes.p);
        wordAndSentenceContainer.className = "word-and-sentence-container";
        
        // contenitore delle checkbox dei sensi
        this.sensesBoxesContainer = document.createElement(StringTypes.div);
        sensesBoxesContainer.className = "checkboxes-container";
            
        /* parola, frase e sensi vengono scaricati dal server e salvati nei campi degli appositi
        elementi. Per ogni senso viene inoltre creata una checkbox */
        $.getJSON("nextExample.jsp", "task=SENSE_ANNOTATION", (Object result, String a, JQueryXHR ctx) -> {
            JSON json = (JSON) result;
            String word = json.$get("word");
            String sentence = json.$get("description");
            String[] synsetsIds = json.$get("synsetsIds");
            String[] senses = json.$get("senses");
            wordContainer.textContent = word;
            serverSentenceContainer.textContent = "\"" + sentence + "\"";
            // per ogni senso viene creata una checkbox con la sua etichetta
            for (int i = 0; i < senses.length; ++i) 
            {                                                    
                // checkbox
                HTMLInputElement senseBox = Utilities.createCheckBox("sense", synsetsIds[i]);
                
                // etichetta della checkbox
                HTMLLabelElement senseBoxLabel = Utilities.createLabel(senses[i]);
                
                // contenitore di checkbox ed etichetta
                HTMLDivElement boxContainer = document.createElement(StringTypes.div);
                $(boxContainer).append(senseBox, senseBoxLabel);
                        
                $(sensesBoxesContainer).append(boxContainer);
            }      
            return null;
        });
        
        // pulsante skip
        skipBtn.addEventListener("click", (event) -> {
            // viene resettato l'attributo value di tutti gli elementi del form
            form.reset();
            form.submit();
        });
        
        // contenitore dei messaggi d'errore
        errMsgContainer = document.createElement(StringTypes.div);
        errMsgContainer.className = "err-msg-container";
    }
    
    @Override
    public JQuery buildPage()
    {
        // all'elemento <body> vengono appesi tutti i discendenti
        $("body").append(
            header,
            $(mainEl).append(
                $(form).append(
                    $(wordAndSentenceContainer).append(
                        $(selectSentenceContainer).append(
                            "Select the correct sense of the word ",
                             wordContainer,
                            " in the sentence"
                        ),
                        serverSentenceContainer
                    ),
                    sensesBoxesContainer,
                    skipAndNextBtnsContainer,
                    errMsgContainer
                )
            )
        ); 
        return $("body");
    }
}
