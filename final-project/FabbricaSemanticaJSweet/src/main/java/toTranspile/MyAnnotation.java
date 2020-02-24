package toTranspile;

import def.dom.HTMLDivElement;
import def.dom.HTMLInputElement;
import def.jquery.JQuery;
import def.jquery.JQueryXHR;
import def.js.JSON;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;
import static def.jquery.Globals.$;

/**
 * Rappresenta la pagina in cui, data una lista di parole, l’utente deve fornire la traduzione nella
 * sua lingua madre per ogni parola
 */
public class MyAnnotation extends BasicAnnotValidat implements BuildablePage
{   
    public static void main(String[] args) 
    {               
       new MyAnnotation().buildPage();
    }
    
    /**
     * Elemento che contiene tutte le parole scaricate dal server e i relativi campi di testo
     */
    HTMLDivElement wordsAndInputsContainer;
    
    /**
     * Costruttore di MyAnnotation. Inizializza gli elementi e i loro attributi e aggiunge gli event
     * listener
     */
    public MyAnnotation()
    {   
        // elemento form
        form = Utilities.createPOSTForm("myAnnotation.jsp");
        
        // elemento che contiene tutte le parole scaricate dal server e i relativi campi di testo
        wordsAndInputsContainer = document.createElement(StringTypes.div);
        wordsAndInputsContainer.className = "words-inputs-container";
        
        /* le parole vengono scaricate dal server e per ogni parola viene creato il contenitore 
         * padre e il campo di testo fratello */
        $.getJSON("nextExample.jsp", "task=MY_ANNOTATION", (Object result, String a, JQueryXHR ctx) -> {
            JSON json = (JSON) result;           
            String[] words = json.$get("words");
            // per ogni parola viene creato il contenitore padre e il campo di testo fratello
            for (String word : words) 
            {                                                    
                // contenitore padre
                HTMLDivElement wordAndInputContainer = document.createElement(StringTypes.div);
                wordAndInputContainer.className = "word-input-container";
                // campo di testo fratello
                HTMLInputElement textField = Utilities.createTextField("word");

                $(wordsAndInputsContainer).append(
                    $(wordAndInputContainer).append(
                            word, 
                            textField
                    )
                );
            }
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
                    "Translate the following words into your first language",
                    wordsAndInputsContainer,
                    skipAndNextBtnsContainer
                )            
            )
        ); 
        return $("body");
    }
}
