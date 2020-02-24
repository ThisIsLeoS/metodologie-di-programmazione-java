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
 * Rappresenta la pagina in cui, data una parola e una frase in cui appare, l’utente deve verificare
 * se il senso fornito dal sistema è appropriato
 */
public class SenseValidation extends BasicAnnotValidat implements BuildablePage
{   
    public static void main(String[] args) 
    {   
        new SenseValidation().buildPage();
    }
    
    /**
     * elemento form
     */
    private HTMLFormElement form;
    
    /**
     * contenitore della parola scaricata dal server
     */
    private HTMLSpanElement wordContainer;
    
    /**
     * contenitore della frase "Select the correct meaning of the word <parola scaricata>"
     */
    private HTMLSpanElement selectSentenceContainer;
    
    /**
     * contenitore della frase scaricata dal server
     */
    private HTMLSpanElement serverSentenceContainer;
    
    /**
     * contenitore della frase "is the following sense..."
     */
    private HTMLSpanElement isSentenceContainer;
    
    /**
     * contenitore del senso scaricato dal server
     */
    private HTMLSpanElement senseContainer;
    
    /**
     * contenitore di tutte le frasi e del senso
     */
    private HTMLParagraphElement sentencesContainer;
    
    /**
     * radio button "yes"
     */
    private HTMLInputElement yesRadio;
    
    /**
     * etichetta del radio button "yes"
     */
    private HTMLLabelElement yesRadioLabel;
    
    /**
     * radio button "no"
     */
    private HTMLInputElement noRadio;
    
    /**
     * etichetta del radio button "no"
     */
    private HTMLLabelElement noRadioLabel;
    
    /**
     * contenitore dei radio button e delle loro etichette
     */
    private HTMLDivElement radioBtnsContainer;
    
    /**
     * contenitore dei messaggi d'errore 
     */
    private HTMLDivElement errMsgContainer;
    
    /**
     * Costruttore di SenseValidation. Inizializza gli elementi e i loro attributi e aggiunge gli
     * event listener
     */
    public SenseValidation()
    {  
        // elemento form
        form = Utilities.createPOSTForm("senseValidation.jsp");
        form.addEventListener("submit", (event) -> {
            // se nessun pulsante radio è stato selezionato
            if ($("input[type=radio]:checked").length == 0)
            {
                errMsgContainer.textContent = "Select \"yes\" or \"no\"";
                event.preventDefault();
            }          
        });
        
        // contenitore della parola
        this.wordContainer = document.createElement(StringTypes.span);
        wordContainer.className = "word-container";
        
        // contenitore della frase "Select the correct meaning of the word <parola scaricata>"
        this.selectSentenceContainer = document.createElement(StringTypes.span);
              
        // contenitore della frase scaricata dal server
        this.serverSentenceContainer = document.createElement(StringTypes.span);
        serverSentenceContainer.className = "sentence-container";
        
        // contenitore della frase "is the following sense..."
        this.isSentenceContainer = document.createElement(StringTypes.span);
        isSentenceContainer.className = "is-sentence-container";
        isSentenceContainer.textContent = "Is the following sense the correct one for the word?";
        
        // contenitore del senso
        this.senseContainer = document.createElement(StringTypes.span);
        senseContainer.className = "sense-container";
        
        // contenitore di tutte le frasi e del senso
        this.sentencesContainer = document.createElement(StringTypes.p);
        sentencesContainer.className = "sentences-container";
        
        // radio button "yes"
        this.yesRadio = Utilities.createRadioBtn("radioBtn", "yes");
        
        // etichetta del radio button "yes"
        this.yesRadioLabel = Utilities.createLabel("yes");
        
        // radio button "no"
        this.noRadio = Utilities.createRadioBtn("radioBtn", "no");
        noRadio.className = "no-radio-btn";
        
        // etichetta del radio button "no"
        this.noRadioLabel = Utilities.createLabel("no");
        
        // contenitore dei radio button e delle loro etichette
        this.radioBtnsContainer = document.createElement(StringTypes.div);
        radioBtnsContainer.className = "radio-btns-container";
        
        /* parola, frase e senso vengono scaricati dal server e salvati nei campi degli appositi
        elementi */
        $.getJSON("nextExample.jsp", "task=SENSE_VALIDATION", (Object result, String a, JQueryXHR ctx) -> {
            JSON json = (JSON) result;
            String word = json.$get("word");
            String sentence = json.$get("example");
            String sense = json.$get("sense");
            wordContainer.textContent = word;
            serverSentenceContainer.textContent = "\"" + sentence + "\"";
            senseContainer.textContent = "\"" + sense + "\"";
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
                    $(sentencesContainer).append(
                        $(selectSentenceContainer).append(
                            "Given the word ",
                            wordContainer,
                            " in the sentence"
                        ),
                        serverSentenceContainer,
                        isSentenceContainer,
                        senseContainer
                    ),
                    $(radioBtnsContainer).append(
                        yesRadio,
                        yesRadioLabel,
                        noRadio,
                        noRadioLabel
                    ),
                    skipAndNextBtnsContainer,
                    errMsgContainer
                )
            )
        );
        return $("body");
    }
}

