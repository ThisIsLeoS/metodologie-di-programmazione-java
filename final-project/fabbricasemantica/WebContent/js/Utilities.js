/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Contiene utilities per la parte front end del progetto
     * @class
     */
    class Utilities {
        static HTMLFilesNames_$LI$() { if (Utilities.HTMLFilesNames == null)
            Utilities.HTMLFilesNames = ["translationAnnotation.html", "wordAnnotation.html", "definitionAnnotation.html", "senseAnnotation.html", "translationValidation.html", "senseValidation.html", "myAnnotation.html"]; return Utilities.HTMLFilesNames; }
        ;
        /**
         * Restituisce un HTMLButtonElement con i campi type e textContent settati alla stringa passata
         * in input
         * @param {string} type la stringa a cui verrà settato il campo type dell'HTMLButtonElement restituito
         * @param {string} textContent la stringa a cui verrà settato il campo textContent dell'HTMLButtonElement
         * restituito
         * @return {HTMLButtonElement} un HTMLButtonElement con i campi type e textContent settati alla stringa passata
         * in input
         */
        static createButton(type, textContent) {
            let button = document.createElement("button");
            button.type = type;
            button.textContent = textContent;
            return button;
        }
        /**
         * Restituisce un HTMLInputElement col campo type settato a "checkbox" e i campi name e value
         * settati alle stringhe passate in input
         * @param {string} name la stringa a cui verrà settato il campo name dell'HTMLInputElement restituito
         * @param {string} value la stringa a cui verrà settato il campo value dell'HTMLInputElement restituito
         * @return {HTMLInputElement} un HTMLInputElement col campo type settato a "checkbox" e i campi name e value
         * settati alle stringhe passate in input
         */
        static createCheckBox(name, value) {
            let box = document.createElement("input");
            box.type = "checkbox";
            box.name = name;
            box.value = value;
            return box;
        }
        /**
         * Restituisce un HTMLInputElement con le coppie campo/valore:
         * <ul>
         * <li><code>type = "email"</code></li>
         * <li><code>name = "email"</code></li>
         * <li><code>maxLength = 100</code></li>
         * <li><code>required = true</code></li>
         * </ul>
         * @return {HTMLInputElement} un HTMLInputElement con le coppie campo/valore:
         * <ul>
         * <li><code>type = "email"</code></li>
         * <li><code>name = "email"</code></li>
         * <li><code>maxLength = 100</code></li>
         * <li><code>required = true</code></li>
         * </ul>
         */
        static createEmailField() {
            let emailField = document.createElement("input");
            emailField.type = "email";
            emailField.name = "email";
            emailField.maxLength = 100;
            emailField.required = true;
            return emailField;
        }
        /**
         * Restituisce un HTMLLabelElement col campo textContent settato alla stringa passata in input
         * @param {string} labelText la stringa a cui verrà settato il campo textContent dell'HTMLLabelElement
         * restituito
         * @return {HTMLLabelElement} un HTMLLabelElement col campo textContent settato alla stringa passata in input
         */
        static createLabel(labelText) {
            let label = document.createElement("label");
            label.textContent = labelText;
            return label;
        }
        /**
         * Restituisce un HTMLDivElement col campo id settato a "main"
         * @return {HTMLDivElement} un HTMLDivElement col campo id settato a "main"
         */
        static createMain() {
            let main = document.createElement("div");
            main.id = "main";
            return main;
        }
        /**
         * Restituisce un HTMLOptionElement coi campi textContent e value settati alle stringhe passate
         * in input
         * @param {string} textContent la stringa a cui verrà settato il campo textContent dell'HTMLOptionElement
         * restituito
         * @param {string} value la stringa a cui verrà settato il campo value dell'HTMLOptionElement restituito
         * @return {HTMLOptionElement} un HTMLOptionElement coi campi textContent e value settati alle stringhe passate
         * in input
         */
        static createOption(textContent, value) {
            let option = document.createElement("option");
            option.textContent = textContent;
            option.value = value;
            return option;
        }
        /**
         * Restituisce un HTMLInputElement con le coppie campo/valore <code>type = "password"</code>,
         * <code>maxLength = 100</code>, <code>required = true</code> e col campo name settato alla
         * stringa passata in input
         * @param {string} name la stringa a cui verrà settato il campo name dell'HTMLInputElement restituito
         * @return {HTMLInputElement} un HTMLInputElement un HTMLInputElement con le coppie campo/valore
         * <code>type = "password"</code>, <code>maxLength = 100</code>, <code>required = true</code> e
         * col campo name settato alla stringa passata in input
         */
        static createPassField(name) {
            let passField = document.createElement("input");
            passField.type = "password";
            passField.name = name;
            passField.maxLength = 30;
            passField.required = true;
            return passField;
        }
        /**
         * Restituisce un HTMLFormElement col campo method settato a "POST" e il campo action settato
         * all'URL passato in input
         * @param {string} URL l'URL a cui verrà settato il campo action dell'HTMLFormElement restituito
         * @return {HTMLFormElement} un HTMLFormElement col campo method settato a "POST" e il campo action settato
         * all'URL passato in input
         */
        static createPOSTForm(URL) {
            let form = document.createElement("form");
            form.method = "POST";
            form.action = URL;
            return form;
        }
        /**
         * Restituisce un HTMLInputElement col campo type settato a "radio" e i campi name e value
         * settati alle stringhe passate in input
         * @param {string} name la stringa a cui verrà settato il campo name dell'HTMLInputElement restituito
         * @param {string} value la stringa a cui verrà settato il campo value dell'HTMLInputElement restituito
         * @return {HTMLInputElement} un HTMLInputElement col campo type settato a "radio" e i campi name e value
         * settati alle stringhe passate in input
         */
        static createRadioBtn(name, value) {
            let radioBtn = document.createElement("input");
            radioBtn.type = "radio";
            radioBtn.name = name;
            radioBtn.value = value;
            return radioBtn;
        }
        /**
         * Restituisce un HTMLSelectElement col campo name settato alla stringa passata in input
         * @param {string} name la stringa a cui verrà settato il campo name dell'HTMLSelectElement restituito
         * @return {HTMLSelectElement} un HTMLSelectElement col campo name settato alla stringa passata in input
         */
        static createSelect(name) {
            let select = document.createElement("select");
            select.name = name;
            return select;
        }
        /**
         * Restituisce un HTMLTextAreaElement con il campo name settato alla stringa passata in input
         * @param {string} name la stringa a cui verrà settato il campo name dell'HTMLTextAreaElement restituito
         * @return {HTMLTextAreaElement} un HTMLTextAreaElement con il campo name settato alla stringa passata in input
         */
        static createTextAreaField(name) {
            let textAreaField = document.createElement("textarea");
            textAreaField.name = name;
            return textAreaField;
        }
        /**
         * Restituisce un HTMLInputElement con le coppie campo/valore <code>type="text"</code>,
         * <code>maxLenght=100</code>, <code>required=true</code> e il campo name settato alla stringa
         * passata in input
         * @param {string} name la stringa a cui verrà settato il campo name dell'HTMLInputElement restituito
         * @return {HTMLInputElement} un HTMLInputElement con le coppie campo/valore <code>type="text"</code>,
         * <code>maxLenght=100</code>, <code>required=true</code> e il campo name settato alla stringa
         * passata in input
         */
        static createTextField(name) {
            let textField = document.createElement("input");
            textField.type = "text";
            textField.name = name;
            textField.maxLength = 100;
            textField.required = true;
            return textField;
        }
        /**
         * Restituisce un file name a caso tra quelli presenti in HTMLFilesNames che non è uguale al
         * file name passato in input
         * @param {string} HTMLFileNameToDiscard file name presente in HTMLFilesNames che non deve essere
         * restituito
         * @return {string} un file name a caso tra quelli presenti in HTMLFilesNames che non è uguale al file
         * name passato in input
         */
        static getRandomHTMLFilename(HTMLFileNameToDiscard) {
            let HTMLFileName;
            let randomInt;
            do {
                {
                    randomInt = ((Math.random() * Utilities.HTMLFilesNames_$LI$().length) | 0);
                    HTMLFileName = Utilities.HTMLFilesNames_$LI$()[randomInt];
                }
            } while ((((o1, o2) => { if (o1 && o1.equals) {
                return o1.equals(o2);
            }
            else {
                return o1 === o2;
            } })(HTMLFileName, HTMLFileNameToDiscard)));
            return HTMLFileName;
        }
    }
    toTranspile.Utilities = Utilities;
    Utilities["__class"] = "toTranspile.Utilities";
})(toTranspile || (toTranspile = {}));
toTranspile.Utilities.HTMLFilesNames_$LI$();
