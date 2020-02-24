/* Faralli: esempio di sviluppo di un form senza uso di jQuery
 * Fa vedere come si possono creare dei metodi in Java per poi passarli come callback agli elementi
 * del form. 
 * Il metodo onSubmit viene assegnato come riferimento al campo onSubmit di un oggetto
 * HTMLFormElement (riga 40 e qualcosa. Nota: immagini che il campo onSubmit sia un riferimento ad 
 * una interfaccia SAM). onSubmit è il metodo che dev'essere richiamato quando viene sottomesso il
 * form (viene fatta una richiesta HTTP al server). Nel metodo onSubmit definito in questa classe 
 * si compone la query da fare al server. Nel meotodo onSubmit si possono anche fare dei controlli
 * (vd. commento più avanti).
 * Il fatto di non utilizzare jQuery ci farà scrivere un po' più di codice, ma è un po' meno 
 * complicato a suo avviso
 */

package org.jsweet.examples.inputcontrol;

import static def.dom.Globals.console;
import static def.dom.Globals.document;
import static def.dom.Globals.window;

import def.dom.Element;
import def.dom.Event;
import def.dom.HTMLAnchorElement;
import def.dom.HTMLElement;
import def.dom.HTMLFormElement;
import def.dom.HTMLInputElement;
import def.dom.Node;
import def.js.Array;

public class InputControl {

    public static void main(String[] args) {
        new InputControl();
    }
    
    private HTMLFormElement form;

    public InputControl() {
        console.info("creating input control example");

        this.form = (HTMLFormElement) document.querySelector("form");
        this.form.onsubmit = this::onSubmit;
        
        Array<Element> inputs = Array.from(this.form.querySelectorAll(".form-control"));
        for (Node element : inputs) {
            addHitListener((HTMLElement) element);
        }

        this.form.querySelector("#reset").addEventListener("click", (event) -> {
            form.reset();
            for (Element element : inputs) {
                element.classList.remove("hit");
            }
        });
    }

    private Boolean onSubmit(Event event) {
        event.stopPropagation();
        console.log("form is valid");
        document.getElementById("form").classList.add("invisible");

        window.scroll(0, 0);
        
        /* con getInput("name").value sto richiedendo la stringa che è stata digitata nella input
         * box che si chiama name. Potrei per esempio fare anche un controllo sulla validità di
         * questa stringa e, se la stringa non fosse valida, si può, invece di chiamare la URL che
         * dev'essere chiamata per effettuare la richiesta, richiamare una pagina che visualizza lo 
         * stesso form ma con un messaggio d'errore oppure richiamare la stessa URL che gestirà 
         * l'errore e restituirà la pagina con un errore. Qui abbiamo noi il controllo del flusso, 
         * cosa che è difficile percepire nel Java d'esempio dato dal prof dove viene usata jQuery
         */
        document.getElementById("name").textContent = getInput("name").value;
        document.getElementById("age").textContent = getInput("age").value;
        document.getElementById("email").textContent = getInput("email").value;
        document.getElementById("tel").textContent = getInput("tel").value;

        String url = getInput("url").value;
        HTMLAnchorElement urlLink = (HTMLAnchorElement) document.getElementById("url");
        urlLink.href = url;
        urlLink.textContent = url;

        document.getElementById("range").textContent = getInput("range").value;
        document.getElementById("date").textContent = getInput("date").value;
        document.getElementById("continent").textContent = getInput("continent").value;
        document.getElementById("favcolor").style.backgroundColor = getInput("favcolor").value;

        document.getElementById("view").classList.remove("invisible");

        return false;
    }

    private HTMLInputElement getInput(String name) {
        return (HTMLInputElement) document.querySelector("[name='" + name + "']");
    }

    private void addHitListener(HTMLElement element) {
        element.addEventListener("keyup", (evt) -> {
            console.log("typing...");
            element.classList.add("hit");
        });
    }
}