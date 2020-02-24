package toTranspile;

import def.dom.Element;
import def.dom.HTMLAnchorElement;
import def.dom.HTMLButtonElement;
import def.dom.HTMLDivElement;
import def.dom.HTMLFormElement;
import def.dom.HTMLInputElement;
import def.dom.HTMLLabelElement;
import def.dom.HTMLOptionElement;
import def.dom.HTMLSelectElement;
import def.dom.NodeListOf;
import def.jquery.JQuery;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;
import static def.jquery.Globals.$;

/**
 * Rappresenta la pagina di sign up del progetto FabbricaSemantica
 */
public class Signup implements BuildablePage
{
    public static void main(String[] args)
    {
        new Signup().buildPage();
    }
    
    /**
     * elemento main
     */
    private HTMLDivElement main;
    
    /**
     * elemento form
     */
    private HTMLFormElement form;
    
    /**
     * etichetta del campo email
     */
    private HTMLLabelElement emailLabel;
    
    /**
     * campo email
     */
    private HTMLInputElement emailField;
    
    /**
     * etichetta del campo password
     */
    private HTMLLabelElement passLabel;
    
    /**
     * campo password
     */
    private HTMLInputElement passField;
    
    /**
     * etichetta del campo di conferma della password
     */
    private HTMLLabelElement confirmPassLabel;
    
    /**
     * campo di conferma della password
     */
    private HTMLInputElement confirmPassField;
    
    /**
     * etichetta delle chekbox riguardanti la lingua madre
     */
    private HTMLLabelElement firstLanguageLabel;
    
    /**
     * chekbox "IT"
     */
    private HTMLInputElement ITBox;
    
    /**
     * etichetta della checkbox "IT"
     */
    private HTMLLabelElement ITBoxLabel;
    
    /**
     * chekbox "EN"
     */
    private HTMLInputElement ENBox;
    
    /**
     * etichetta della checkbox "EN"
     */
    private HTMLLabelElement ENBoxLabel;
    
    /**
     * contenitore delle checkbox riguardanti la lingua madre
     */
    private HTMLDivElement firstLangsContainer;
    
    /**
     * etichetta dei menù a tendina riguardanti le altre lingue parlate
     */
    private HTMLLabelElement secondLanguagesLabel;
    
    /**
     * contenitore dei menù a tendina riguardanti le altre lingue parlate
     */
    private HTMLDivElement secondLangsContainer;
        
    /**
     * pulsante per l'invio dei dati
     */
    private HTMLButtonElement signupButton;
    
    /**
     * link alla pagina di signin
     */
    private HTMLAnchorElement loginLink;
    
    /**
     * contenitore dei messaggi d'errore 
     */
    private HTMLDivElement errMsgContainer;
    
    /**
     * Costruttore di Signup. Inizializza gli elementi e i loro attributi e aggiunge gli event
     * listeners
     */
    public Signup()
    {
        // elemento <main>
        main = Utilities.createMain();
        
        // elemento <form>
        form = Utilities.createPOSTForm("signup.jsp");
        form.addEventListener("submit", (event) -> {
            /* se la password inserita nel campo password e quella inserita nel campo conferma
            password non conincidono */
            if (!passField.value.equals(confirmPassField.value))
            {
                errMsgContainer.textContent = "Passwords don't match!";
                event.preventDefault();
            }
            
            // se nessuna checkbox è checked
            if ($(".first-langs-container input:checked").length == 0)
            {
                errMsgContainer.textContent = "At least one checkbox must be checked!";
                event.preventDefault();
            }
        });
        
        // etichetta del campo email
        emailLabel = Utilities.createLabel("Email*");
        
        // campo email
        emailField = Utilities.createEmailField();
               
        // etichetta del campo password
        passLabel = Utilities.createLabel("Password*");
        
        // campo password
        passField = Utilities.createPassField("pwd");
        /* espressione regolare che identifica stringhe con minimo 8 caratteri, almeno una lettera e
        un numero */
        passField.pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        passField.title = "Minimum eight characters, at least one letter and one number";
        
        // etichetta del campo di conferma della password
        confirmPassLabel = Utilities.createLabel("Confirm password*");
        
        // campo di conferma della password
        confirmPassField = Utilities.createPassField("confirmPwd");
        
        // etichetta delle chekbox riguardanti la lingua madre
        firstLanguageLabel = Utilities.createLabel("First language*");
        
        // chekbox "IT"
        ITBox = Utilities.createCheckBox("itCheckbox", "on");
        
        // etichetta della checkbox "IT"
        ITBoxLabel = Utilities.createLabel("IT");
        
        // chekbox "EN"
        ENBox = Utilities.createCheckBox("enCheckbox", "on");
        
        // etichetta della checkbox "EN"
        ENBoxLabel = Utilities.createLabel("EN");
        
        // contenitore delle checkbox riguardanti la lingua madre
        firstLangsContainer = document.createElement(StringTypes.div);
        firstLangsContainer.className = "first-langs-container";
              
        // etichetta dei menù a tendina riguardanti le altre lingue parlate
        secondLanguagesLabel = Utilities.createLabel("Second languages");
        secondLanguagesLabel.className = "second-langs-label";
        
        // contenitore dei menù a tendina riguardanti le altre lingue parlate
        secondLangsContainer = document.createElement(StringTypes.div);
        secondLangsContainer.className = "second-langs-container";
        
        // vengono generati i menù a tendina con le altre lingue parlate e i livelli
        for (int i = 0; i < 2; ++i)
        {
            // select
            HTMLSelectElement secondLangs = Utilities.createSelect("secondLang");
            
            // options
            /* se quest'opzione è selezionata, quando il form è sottomesso al server viene inviata 
             * una stringa vuota */
            HTMLOptionElement chooseLangOption = Utilities.createOption("Language", "");
            // per rendere quest'opzione quella che è visualizzata quando il menù è chiuso
            chooseLangOption.selected = true;
            HTMLOptionElement itA1 = Utilities.createOption("IT-A1", "it-a1");
            itA1.className = "IT";
            HTMLOptionElement itA2 = Utilities.createOption("IT-A1", "it-a2");
            itA2.className = "IT";
            HTMLOptionElement itB1 = Utilities.createOption("IT-B1", "it-b1");
            itB1.className = "IT";
            HTMLOptionElement itB2 = Utilities.createOption("IT-B2", "it-b2");
            itB2.className = "IT";
            HTMLOptionElement itC1 = Utilities.createOption("IT-C1", "it-c1");
            itC1.className = "IT";
            HTMLOptionElement itC2 = Utilities.createOption("IT-C2", "it-c2");
            itC2.className = "IT";
            HTMLOptionElement enA1 = Utilities.createOption("EN-A1", "en-a1");
            enA1.className = "EN";
            HTMLOptionElement enA2 = Utilities.createOption("EN-A2", "en-a2");
            enA2.className = "EN";
            HTMLOptionElement enB1 = Utilities.createOption("EN-B1", "en-b1");
            enB1.className = "EN";
            HTMLOptionElement enB2 = Utilities.createOption("EN-B2", "en-b2");
            enB2.className = "EN";
            HTMLOptionElement enC1 = Utilities.createOption("EN-C1", "ec-c1");
            enC1.className = "EN";
            HTMLOptionElement enC2 = Utilities.createOption("EN-C2", "en-c2");
            enC2.className = "EN";
            
            $(secondLangsContainer).append (
                $(secondLangs).append(
                    chooseLangOption,
                    itA1,
                    itA2,
                    itB1,
                    itB2,
                    itC1,
                    itC2,
                    enA1,
                    enA2,
                    enB1,
                    enB2,
                    enC1,
                    enC2
                )
            );
            
            /* se l'opzione di una lingua è selezionata in un menù, non deve essere possibile
            selezionarla anche negli altri menù */
            secondLangs.addEventListener("change", (event) -> {
                JQuery optSelected = $(secondLangs).children("option:selected");
                String optSelectedClass = $(optSelected).attr("class");
                // seleziona tutti gli elementi select con attributo name che ha valore "secondLang" 
                NodeListOf<Element> selects = document.querySelectorAll("[name|='secondLang']");
                for (Element select : selects)
                {
                    // se il select non è quello a cui appartiene l'opzione cliccata
                    if (!select.equals(secondLangs))
                    {
                        // rimuovi l'attributo "disabled" da tutte le opzioni
                        $(select).children().removeAttr("disabled");
                        /* aggiungi l'attributo "disabled" a tutte le opzioni che hanno la stessa
                        classe dell'opzione cliccata */
                        $(select).children("." + optSelectedClass).attr("disabled", "true");
                    };
                }
            });
        }     
        
        // pulsante per l'invio dei dati
        signupButton = Utilities.createButton("submit", "Sign up");
        signupButton.className = "btn btn-primary signup-btn"; // "btn" e "btn-primary" sono classi di bootstrap
        
        // link alla pagina di signin
        loginLink = document.createElement(StringTypes.a);
        loginLink.className = "login-link";
        loginLink.href = "login.html";
        loginLink.textContent = "Log in instead";
        
        // contenitore dei messaggi d'errore
        errMsgContainer = document.createElement(StringTypes.div);
        errMsgContainer.className = "err-msg-container";
    }
    
    @Override
    public JQuery buildPage()
    {
        // all'elemento <body> vengono appesi tutti i discendenti
        $("body").append(
            $(main).append(
                $(form).append(
                    emailLabel,
                    emailField,
                    passLabel,
                    passField,
                    confirmPassLabel,
                    confirmPassField,
                    firstLanguageLabel,
                    $(firstLangsContainer).append(
                        ITBox,
                        ITBoxLabel,
                        ENBox,
                        ENBoxLabel
                    ),
                    secondLanguagesLabel,
                    secondLangsContainer,
                    signupButton,
                    loginLink,
                    errMsgContainer
                )
            )
        );   
        return $("body");
    }
}
