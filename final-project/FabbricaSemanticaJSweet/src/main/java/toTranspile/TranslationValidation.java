package toTranspile;

import def.dom.HTMLDivElement;
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
 * Rappresenta la pagina in cui, data una parola e una sua definizione in inglese, l’utente deve 
 * scegliere la miglior traduzione tra quelle fornite o specificare "none of the above"
 */
public class TranslationValidation extends BasicAnnotValidat implements BuildablePage
{   
    public static void main(String[] args) 
    {   
        new TranslationValidation().buildPage();
    }
    
    /**
     * contenitore della parola scaricata dal server
     */
    HTMLSpanElement wordContainer;
    
    /**
     * contenitore della frase "Select the best translation of the word <parola scaricata>"
     */
    HTMLParagraphElement selectSentenceContainer;
    
    /**
     * contenitore delle checkbox delle traduzioni
     */
    HTMLDivElement transBoxesContainer;
    
    /**
     * contenitore dei messaggi d'errore 
     */
    private HTMLDivElement errMsgContainer;
    
    /**
     * Costruttore di TranslationValidation. Inizializza gli elementi e i loro attributi e aggiunge
     * gli event listener
     */
    public TranslationValidation()
    {
        // elemento form
        form = Utilities.createPOSTForm("translationValidation.jsp");
        form.addEventListener("submit", (event) -> {
            // se nessuna checkbox è checked
            if ($(".checkboxes-container input:checked").length == 0)
            {
                errMsgContainer.textContent = "At least one checkbox must be checked!";
                event.preventDefault();
            }
            HTMLInputElement noneBox = (HTMLInputElement)document.querySelector("[value=\"none of the above\"]");
            // se sia la checkbox "none of the above" che un'altra checkbox sono state selezionate
            if (noneBox.checked && $(".checkboxes-container input:checked").length > 1)
            {
                errMsgContainer.textContent = "You can't check \"none of the above\" and other boxes too";
                event.preventDefault();
            }
        });
        
        // contenitore della parola
        this.wordContainer = document.createElement(StringTypes.span);
        wordContainer.className = "word-container";
        
        // contenitore della frase "Select the best translation of the word <parola scaricata>"
        this.selectSentenceContainer = document.createElement(StringTypes.p);
       
        // contenitore delle checkbox delle traduzioni
        this.transBoxesContainer = document.createElement(StringTypes.div);
        transBoxesContainer.className = "checkboxes-container";

        /* parola, definizione e traduzioni vengono scaricati dal server e salvati nei campi degli 
        appositi elementi. Per ogni traduzione viene inoltre creata una checkbox */
        $.getJSON("nextExample.jsp", "task=TRANSLATION_VALIDATION", (Object result, String a, JQueryXHR ctx) -> {
            JSON json = (JSON) result;
            String word = json.$get("word");
            String definition = json.$get("description");
            String[] translations = json.$get("translations");
            wordContainer.textContent = word;
            $(selectSentenceContainer).append(" (\"" + definition + "\")");
            // per ogni traduzione viene creata una checkbox con la sua etichetta
            for (String translation : translations) 
            {                                                    
                // checkbox
                HTMLInputElement transBox = Utilities.createCheckBox("translation", translation);
                
                // etichetta della checkbox
                HTMLLabelElement transBoxLabel = Utilities.createLabel(translation);
                
                // contenitore di checkbox ed etichetta
                HTMLDivElement boxContainer = document.createElement(StringTypes.div);
                $(boxContainer).append(transBox, transBoxLabel);
                        
                $(transBoxesContainer).append(boxContainer);
            }
            // viene creata anche la checkbox con etichetta "none of the above"
            HTMLInputElement transBox = Utilities.createCheckBox("translation", "none of the above");
            
            // etichetta della checkbox
            HTMLLabelElement transBoxLabel = Utilities.createLabel("none of the above");
            
            // contenitore di checkbox ed etichetta
            HTMLDivElement boxContainer = document.createElement(StringTypes.div);
            $(boxContainer).append(transBox, transBoxLabel);
            
            $(transBoxesContainer).append(boxContainer);
            return null;
        });
        
        // pulsante skip
        skipBtn.addEventListener("click", (event) -> {
            // l'input dell'utente viene resettato
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
                    $(selectSentenceContainer).append(
                        "Select the best translation of the word ",
                        wordContainer
                    ),
                    transBoxesContainer,
                    skipAndNextBtnsContainer,
                    errMsgContainer
                )
            )
        );
        return $("body");
    }
}