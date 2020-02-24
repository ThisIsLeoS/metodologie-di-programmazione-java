package toTranspile;

import def.dom.HTMLAnchorElement;
import def.dom.HTMLButtonElement;
import def.dom.HTMLDivElement;
import def.dom.HTMLFormElement;
import def.dom.HTMLInputElement;
import def.dom.HTMLLabelElement;
import def.jquery.JQuery;
import jsweet.util.StringTypes;
import static def.dom.Globals.document;
import static def.jquery.Globals.$;

/**
 * Rappresenta la pagina di login
 */
public class Login implements BuildablePage
{
    public static void main(String[] args)
    {  
        new Login().buildPage();
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
    private HTMLLabelElement pwdLabel;
    
    /**
     * campo password
     */
    private HTMLInputElement pwdField;
    
    /**
     * pulsante per l'invio dei dati
     */
    private HTMLButtonElement loginButton;
    
    /**
     * contenitore per il link alla pagina di signup
     */
    private HTMLDivElement signupBox;
    
    /**
     * link alla pagina di signup
     */
    private HTMLAnchorElement signupLink;
    
    /**
     * Costruttore di Login. Inizializza gli elementi e i loro attributi
     */
    public Login()
    {       
        // elemento <main>
        main = Utilities.createMain();
        
        // elemento <form>
        form = Utilities.createPOSTForm("login.jsp");
        
        // etichetta del campo email
        emailLabel = Utilities.createLabel("Email*");
        
        // campo email
        emailField = Utilities.createEmailField();
        
        // etichetta del campo password
        pwdLabel = Utilities.createLabel("Password*");
        
        // campo password
        pwdField = Utilities.createPassField("pwd");
        
        // pulsante per l'invio dei dati
        loginButton = Utilities.createButton("submit", "Log in");
        loginButton.className = "btn btn-primary login-btn"; // "btn" e "btn-primary" sono classi di bootstrap
        
        // contenitore per il link alla pagina di signup
        signupBox = document.createElement(StringTypes.div);
        signupBox.className = "signup-box";
        signupBox.textContent = "Not a member yet? ";
        
        // link alla pagina di signup
        signupLink = document.createElement(StringTypes.a);
        signupLink.href = "signup.html";
        signupLink.textContent = "Sign up";
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
                    pwdLabel,
                    pwdField,
                    loginButton,
                    $(signupBox).append(
                        signupLink
                    )
                )
            )
        );
        return $("body");
    }
}
