package toTranspile;

import def.dom.HTMLInputElement;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;

/**
 * builder di HTMLInputElement
 */
public class HTMLInputBuilder 
{  
    /* 
    Use example:
    HTMLInputElement emailField = 
        new InputBuilder("email")
            .setName("email")
            .setRequired(true)
            .setMaxLength(100)
            .build();
    */
    
    /**
     * valore del campo "defaultValue" dell'HTMLInputElement che viene costruito
     */
    private String defaultValue;
    
    /**
     * valore del campo "name" dell'HTMLInputElement che viene costruito
     */
    private String name;
    
    /**
     * valore del campo "required" dell'HTMLInputElement che viene costruito
     */
    private boolean required;
    
    /**
     * valore del campo "type" dell'HTMLInputElement che viene costruito
     */
    private String type;
    
    /**
     * valore del campo "value" dell'HTMLInputElement che viene costruito
     */
    private String value;
    
    /**
     * costruttore di HTMLInputBuilder
     * @param type valore del campo "value" dell'HTMLInputElement che viene costruito
     */
    public HTMLInputBuilder(String type)
    {
        this.type = type;
    }
    
    /**
     * Restituisce l'HTMLInputBuilder corrente col campo value settato alla stringa passata in input
     * @param value la stringa a cui verrà settato il campo value dell'HTMLInputBuilder corrente
     * @return l'HTMLInputBuilder corrente col campo value settato alla stringa passata in input
     */
    public HTMLInputBuilder setDefaultValue(String value)
    {
        this.value = defaultValue;
        return this;
    }
    
    /**
     * Restituisce l'HTMLInputBuilder corrente col campo name settato alla stringa passata in input
     * @param name la stringa a cui verrà settato il campo name dell'HTMLInputBuilder corrente
     * @return l'HTMLInputBuilder corrente col campo name settato alla stringa passata in input
     */
    public HTMLInputBuilder setName(String name)
    {
        this.name = name;
        return this;
    }
    
    /**
    * Restituisce l'HTMLInputBuilder corrente col campo required settato al booleano passato in 
    * input
    * @param required il booleano a cui verrà settato il campo name dell'HTMLInputBuilder
    * corrente
    * @return l'HTMLInputBuilder corrente col campo required settato al booleano passato in input
    */
    public HTMLInputBuilder setRequired(boolean required)
    {
        this.required = required;
        return this;
    }
    
    /**
     * Restituisce l'HTMLInputBuilder corrente col campo type settato alla stringa passata in input
     * @param type la stringa a cui verrà settato il campo name dell'HTMLInputBuilder
     * corrente
     * @return l'HTMLInputBuilder corrente col campo name settato alla stringa passata in input
     */
    public HTMLInputBuilder setType(String type)
    {
        this.type = type;
        return this;
    }
    
    /**
     * Restituisce un HTMLInputElement che ha le stesse coppie campo/valore dell'HTMLInputBuilder
     * corrente
     * @return un HTMLInputElement che ha le stesse coppie campo/valore dell'HTMLInputBuilder
     * corrente
     */
    public HTMLInputElement build()
    {   
        HTMLInputElement inputEl = document.createElement(StringTypes.input);
        inputEl.type = this.type;
        inputEl.value = this.value;
        inputEl.name = this.name;
        inputEl.required = this.required;
        inputEl.type = this.type;
        inputEl.value = this.value;
        return inputEl;
    }
}

