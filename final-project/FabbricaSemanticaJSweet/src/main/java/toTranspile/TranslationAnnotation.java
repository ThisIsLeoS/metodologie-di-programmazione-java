package toTranspile;

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
 * fornire una traduzione nella sua lingua madre
 */
public class TranslationAnnotation extends BasicAnnotValidat implements BuildablePage
{	
    public static void main(String[] args) 
    {   
        new TranslationAnnotation().buildPage();
    }
    
    /**
     * contenitore della parola da tradurre scaricata dal server
     */
    private HTMLSpanElement wordContainer;
    
    /**
     * contenitore della definizione della parola da tradurre
     */
    private HTMLSpanElement definitionContainer;
    
    /**
     * contenitore sia della parola che della definizione
     */
    private HTMLParagraphElement wordAndDefinitionContainer;
    
    /**
     * etichetta del campo di testo in cui inserire la traduzione
     */
    private HTMLLabelElement translationLabel;
    
    /**
     * campo di testo in cui inserire la traduzione
     */
    private HTMLInputElement translationTextField;
    
    /**
     * Costruttore di TranslationAnnotation. Inizializza gli elementi e i loro attributi e aggiunge
     * gli event listener 
     */
    public TranslationAnnotation()
    {
        // elemento form
        form = Utilities.createPOSTForm("translationAnnotation.jsp");
        
        // contenitore della parola da tradurre
        this.wordContainer = document.createElement(StringTypes.span);
        wordContainer.className = "word-container";
        
        // contenitore della definizione della parola da tradurre
        this.definitionContainer = document.createElement(StringTypes.span);
        definitionContainer.className = "definition-container";
        
        // contenitore sia della parola che della definizione
        this.wordAndDefinitionContainer = document.createElement(StringTypes.p);
        wordAndDefinitionContainer.className = "word-and-def-container";        
        
        // etichetta del campo di testo in cui inserire la traduzione
        this.translationLabel = 
                Utilities.createLabel("Translate the word into your first language");
        
        // campo di testo in cui inserire la traduzione
        this.translationTextField = Utilities.createTextField("translation");
              
        /* la parola da tradurre e la sua definizione vengono scaricate dal server e salvate nei 
        campi degli appositi elementi */
        $.getJSON("nextExample.jsp", "task=TRANSLATION_ANNOTATION", (Object result, String a, JQueryXHR ctx) -> {
            JSON json = (JSON) result;
            String word = json.$get("word");
            String definition = json.$get("description");
            wordContainer.textContent = word;
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
        // all'elemento <body> vengono appesi tutti i discendenti
        $("body").append(
            header,
            $(mainEl).append(
                $(form).append(
                    $(wordAndDefinitionContainer).append(
                        wordContainer,
                        definitionContainer
                    ),
                    translationLabel,
                    translationTextField,
                    skipAndNextBtnsContainer
                )
            )
        );
        return $("body");
    }
}
