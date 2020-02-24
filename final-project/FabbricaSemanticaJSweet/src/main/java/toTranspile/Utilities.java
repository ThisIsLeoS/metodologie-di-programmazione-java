package toTranspile;

import def.dom.HTMLButtonElement;
import def.dom.HTMLDivElement;
import def.dom.HTMLFormElement;
import def.dom.HTMLInputElement;
import def.dom.HTMLLabelElement;
import def.dom.HTMLOptionElement;
import def.dom.HTMLSelectElement;
import def.dom.HTMLTextAreaElement;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;

/**
 * Contiene utilities per la parte front end del progetto
 */
public class Utilities
{   
    /**
     * Array contenente i file name di tutte le pagine html del progetto
     */
    public static final String[] HTMLFilesNames = {
        "translationAnnotation.html",
        "wordAnnotation.html",
        "definitionAnnotation.html",
        "senseAnnotation.html",
        "translationValidation.html",
        "senseValidation.html",
        "myAnnotation.html"     
    };
    
    /**
     * Restituisce un HTMLButtonElement con i campi type e textContent settati alla stringa passata
     * in input
     * @param type la stringa a cui verrà settato il campo type dell'HTMLButtonElement restituito
     * @param textContent la stringa a cui verrà settato il campo textContent dell'HTMLButtonElement 
     * restituito
     * @return un HTMLButtonElement con i campi type e textContent settati alla stringa passata
     * in input
     */
    public static HTMLButtonElement createButton(String type, String textContent)
    {
        HTMLButtonElement button = document.createElement(StringTypes.button);
        button.type = type;
        button.textContent = textContent;
        return button;
    }
    
    /**
     * Restituisce un HTMLInputElement col campo type settato a "checkbox" e i campi name e value
     * settati alle stringhe passate in input
     * @param name la stringa a cui verrà settato il campo name dell'HTMLInputElement restituito
     * @param value la stringa a cui verrà settato il campo value dell'HTMLInputElement restituito
     * @return un HTMLInputElement col campo type settato a "checkbox" e i campi name e value
     * settati alle stringhe passate in input
     */
    public static HTMLInputElement createCheckBox(String name, String value)
    {
        HTMLInputElement box = document.createElement(StringTypes.input);
        box.type = "checkbox";
        box.name = name;
        box.value = value;
        return box;
    }
    
    /**
     * Restituisce un HTMLInputElement con le coppie campo/valore:
     * <ul>
     *   <li><code>type = "email"</code></li>
     *   <li><code>name = "email"</code></li>
     *   <li><code>maxLength = 100</code></li>
     *   <li><code>required = true</code></li>
     * </ul>
     * @return un HTMLInputElement con le coppie campo/valore:
     * <ul>
     *   <li><code>type = "email"</code></li>
     *   <li><code>name = "email"</code></li>
     *   <li><code>maxLength = 100</code></li>
     *   <li><code>required = true</code></li>
     * </ul>
     */
    public static HTMLInputElement createEmailField()
    {
        HTMLInputElement emailField = document.createElement(StringTypes.input);
        emailField.type = "email";
        emailField.name = "email";
        emailField.maxLength = 100;
        emailField.required = true; 
        return emailField;
    }
        
    /**
     * Restituisce un HTMLLabelElement col campo textContent settato alla stringa passata in input
     * @param labelText la stringa a cui verrà settato il campo textContent dell'HTMLLabelElement 
     * restituito
     * @return un HTMLLabelElement col campo textContent settato alla stringa passata in input
     */
    public static HTMLLabelElement createLabel(String labelText)
    {
        HTMLLabelElement label = document.createElement(StringTypes.label);
        label.textContent = labelText;
        return label;
    }
    
    /**
     * Restituisce un HTMLDivElement col campo id settato a "main"
     * @return un HTMLDivElement col campo id settato a "main"
     */
    public static HTMLDivElement createMain()
    {
        HTMLDivElement main = document.createElement(StringTypes.div);
        main.id = "main";
        return main;
    }
    
    /**
     * Restituisce un HTMLOptionElement coi campi textContent e value settati alle stringhe passate
     * in input
     * @param textContent la stringa a cui verrà settato il campo textContent dell'HTMLOptionElement 
     * restituito
     * @param value la stringa a cui verrà settato il campo value dell'HTMLOptionElement restituito
     * @return un HTMLOptionElement coi campi textContent e value settati alle stringhe passate
     * in input
     */
    public static HTMLOptionElement createOption(String textContent, String value)
    {
        HTMLOptionElement option = document.createElement(StringTypes.option);
        option.textContent = textContent;
        option.value = value;
        return option;
    }
    
    /**
     * Restituisce un HTMLInputElement con le coppie campo/valore <code>type = "password"</code>,
     * <code>maxLength = 100</code>, <code>required = true</code> e col campo name settato alla 
     * stringa passata in input
     * @param name la stringa a cui verrà settato il campo name dell'HTMLInputElement restituito
     * @return un HTMLInputElement un HTMLInputElement con le coppie campo/valore 
     * <code>type = "password"</code>, <code>maxLength = 100</code>, <code>required = true</code> e
     * col campo name settato alla stringa passata in input
     */
    public static HTMLInputElement createPassField(String name)
    {
        HTMLInputElement passField = document.createElement(StringTypes.input);
        passField.type = "password";
        passField.name = name;
        passField.maxLength = 30;
        passField.required = true;
        return passField;
    }
    
    /**
     * Restituisce un HTMLFormElement col campo method settato a "POST" e il campo action settato 
     * all'URL passato in input
     * @param URL l'URL a cui verrà settato il campo action dell'HTMLFormElement restituito
     * @return un HTMLFormElement col campo method settato a "POST" e il campo action settato 
     * all'URL passato in input
     */
    public static HTMLFormElement createPOSTForm(String URL)
    {
        HTMLFormElement form = document.createElement(StringTypes.form);
        form.method = "POST";
        form.action = URL;
        return form;
    }
    
    /**
     * Restituisce un HTMLInputElement col campo type settato a "radio" e i campi name e value
     * settati alle stringhe passate in input
     * @param name la stringa a cui verrà settato il campo name dell'HTMLInputElement restituito
     * @param value la stringa a cui verrà settato il campo value dell'HTMLInputElement restituito
     * @return un HTMLInputElement col campo type settato a "radio" e i campi name e value
     * settati alle stringhe passate in input
     */
    public static HTMLInputElement createRadioBtn(String name, String value)
    {
        HTMLInputElement radioBtn = document.createElement(StringTypes.input);
        radioBtn.type = "radio";
        radioBtn.name = name;
        radioBtn.value = value;
        return radioBtn;
    }
    
    /**
     * Restituisce un HTMLSelectElement col campo name settato alla stringa passata in input
     * @param name la stringa a cui verrà settato il campo name dell'HTMLSelectElement restituito
     * @return un HTMLSelectElement col campo name settato alla stringa passata in input
     */
    public static HTMLSelectElement createSelect(String name)
    {
        HTMLSelectElement select = document.createElement(StringTypes.select);
        select.name = name;
        return select;
    }
    
    /**
     * Restituisce un HTMLTextAreaElement con il campo name settato alla stringa passata in input
     * @param name la stringa a cui verrà settato il campo name dell'HTMLTextAreaElement restituito
     * @return un HTMLTextAreaElement con il campo name settato alla stringa passata in input
     */
    public static HTMLTextAreaElement createTextAreaField(String name) 
    {
        HTMLTextAreaElement textAreaField = document.createElement(StringTypes.textarea);
        textAreaField.name = name;
        return textAreaField;
    }
    
    /**
     * Restituisce un HTMLInputElement con le coppie campo/valore <code>type="text"</code>, 
     * <code>maxLenght=100</code>, <code>required=true</code> e il campo name settato alla stringa 
     * passata in input
     * @param name la stringa a cui verrà settato il campo name dell'HTMLInputElement restituito
     * @return un HTMLInputElement con le coppie campo/valore <code>type="text"</code>, 
     * <code>maxLenght=100</code>, <code>required=true</code> e il campo name settato alla stringa 
     * passata in input
     */
    public static HTMLInputElement createTextField(String name)
    {
        HTMLInputElement textField = document.createElement(StringTypes.input);
        textField.type = "text";
        textField.name = name;
        textField.maxLength = 100;
        textField.required = true;
        return textField;
    }
    
    /**
     * Restituisce un file name a caso tra quelli presenti in HTMLFilesNames che non è uguale al
     * file name passato in input
     * @param HTMLFileNameToDiscard file name presente in HTMLFilesNames che non deve essere
     * restituito
     * @return un file name a caso tra quelli presenti in HTMLFilesNames che non è uguale al file
     * name passato in input
     */
    public static String getRandomHTMLFilename(String HTMLFileNameToDiscard)
    {
        String HTMLFileName;
        int randomInt;
        do
        {
            // viene generato un intero random compreso tra 0 e HTMLFilesNames.length (escluso)
            randomInt = (int)(Math.random() * Utilities.HTMLFilesNames.length);
            HTMLFileName = Utilities.HTMLFilesNames[randomInt];
        }
        while(HTMLFileName.equals(HTMLFileNameToDiscard));
        return HTMLFileName;
    }
}
