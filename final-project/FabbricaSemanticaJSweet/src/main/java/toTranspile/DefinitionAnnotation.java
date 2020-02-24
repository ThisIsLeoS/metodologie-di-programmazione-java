package toTranspile;

import def.dom.HTMLLabelElement;
import def.dom.HTMLParagraphElement;
import def.dom.HTMLSpanElement;
import def.dom.HTMLTextAreaElement;
import def.jquery.JQuery;
import def.jquery.JQueryXHR;
import def.js.JSON;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;
import static def.jquery.Globals.$;

/**
 * Rappresenta la pagina in cui, data una parola e un suo iperonimo, l’utente deve fornire una 
 * definizione nella sua lingua madre
 */
public class DefinitionAnnotation extends BasicAnnotValidat implements BuildablePage
{   
    public static void main(String[] args) 
    {
        new DefinitionAnnotation().buildPage();
    }
    
    /**
     * contenitore delle parola scaricata dal server
     */
    private HTMLSpanElement wordContainer;
    
    /**
     * contenitore dell'iperonimo scaricato dal server
     */
    private HTMLSpanElement hyperonymContainer;
    
    /**
     * contenitore sia della parola che dell'iperonimo
     */
    private HTMLParagraphElement wordAndHyperonymContainer;
    
    /**
     * etichetta del campo di testo in cui inserire la definizione
     */
    private HTMLLabelElement definitionLabel;
    
    /**
     * campo di testo in cui inserire la definizione
     */
    private HTMLTextAreaElement definitionTextAreaField;
    
    /**
     * Costruttore di DefinitionAnnotation. Inizializza gli elementi e i loro attributi e aggiunge
     * gli event listener
     */
    public DefinitionAnnotation()
    {        
        // elemento form
        form = Utilities.createPOSTForm("definitionAnnotation.jsp");
        
        // contenitore delle parola scaricata dal server
        this.wordContainer = document.createElement(StringTypes.span);
        wordContainer.className = "word-container";
        
        // contenitore dell'iperonimo scaricato dal server
        this.hyperonymContainer = document.createElement(StringTypes.span);
        hyperonymContainer.className = "hyperonym-container";
        
        // contenitore sia della parola che dell'iperonimo
        this.wordAndHyperonymContainer = document.createElement(StringTypes.p);
        wordAndHyperonymContainer.className = "word-and-hyp-container";
        $(wordAndHyperonymContainer).append(wordContainer, hyperonymContainer);
        
        // etichetta del campo di testo in cui inserire la definizione
        this.definitionLabel =
                Utilities.createLabel("Give a definition of the word using your first language");
        
        // campo di testo in cui inserire la definizione
        this.definitionTextAreaField = Utilities.createTextAreaField("definition");
        definitionTextAreaField.required = true;
        definitionTextAreaField.maxLength = 500;
        
        /* la parola e l'iperonimo vengono scaricati dal server e salvati nei campi degli appositi
        elementi */
        $.getJSON("nextExample.jsp", "task=DEFINITION_ANNOTATION", (Object result, String a, JQueryXHR ctx) -> {
            JSON json = (JSON) result;
            String word = json.$get("word");
            String hyperonym = json.$get("hypernym");
            wordContainer.textContent = word;
            hyperonymContainer.textContent = hyperonym;
            return null;
        });
        
        // pulsante skip
        skipBtn.addEventListener("click", (event) -> {
            // viene resettato l'attributo value di tutti gli elementi del form
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
                    wordAndHyperonymContainer,
                    definitionLabel,
                    definitionTextAreaField,
                    skipAndNextBtnsContainer
                )
            )
        );
        return $("body");
    }
}
