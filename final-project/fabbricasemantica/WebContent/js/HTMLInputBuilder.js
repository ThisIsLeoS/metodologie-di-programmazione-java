/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * costruttore di HTMLInputBuilder
     * @param {string} type valore del campo "value" dell'HTMLInputElement che viene costruito
     * @class
     */
    class HTMLInputBuilder {
        constructor(type) {
            if (this.defaultValue === undefined)
                this.defaultValue = null;
            if (this.name === undefined)
                this.name = null;
            if (this.required === undefined)
                this.required = false;
            if (this.type === undefined)
                this.type = null;
            if (this.value === undefined)
                this.value = null;
            this.type = type;
        }
        /**
         * Restituisce l'HTMLInputBuilder corrente col campo value settato alla stringa passata in input
         * @param {string} value la stringa a cui verrà settato il campo value dell'HTMLInputBuilder corrente
         * @return {toTranspile.HTMLInputBuilder} l'HTMLInputBuilder corrente col campo value settato alla stringa passata in input
         */
        setDefaultValue(value) {
            this.value = this.defaultValue;
            return this;
        }
        /**
         * Restituisce l'HTMLInputBuilder corrente col campo name settato alla stringa passata in input
         * @param {string} name la stringa a cui verrà settato il campo name dell'HTMLInputBuilder corrente
         * @return {toTranspile.HTMLInputBuilder} l'HTMLInputBuilder corrente col campo name settato alla stringa passata in input
         */
        setName(name) {
            this.name = name;
            return this;
        }
        /**
         * Restituisce l'HTMLInputBuilder corrente col campo required settato al booleano passato in
         * input
         * @param {boolean} required il booleano a cui verrà settato il campo name dell'HTMLInputBuilder
         * corrente
         * @return {toTranspile.HTMLInputBuilder} l'HTMLInputBuilder corrente col campo required settato al booleano passato in input
         */
        setRequired(required) {
            this.required = required;
            return this;
        }
        /**
         * Restituisce l'HTMLInputBuilder corrente col campo type settato alla stringa passata in input
         * @param {string} type la stringa a cui verrà settato il campo name dell'HTMLInputBuilder
         * corrente
         * @return {toTranspile.HTMLInputBuilder} l'HTMLInputBuilder corrente col campo name settato alla stringa passata in input
         */
        setType(type) {
            this.type = type;
            return this;
        }
        /**
         * Restituisce un HTMLInputElement che ha le stesse coppie campo/valore dell'HTMLInputBuilder
         * corrente
         * @return {HTMLInputElement} un HTMLInputElement che ha le stesse coppie campo/valore dell'HTMLInputBuilder
         * corrente
         */
        build() {
            let inputEl = document.createElement("input");
            inputEl.type = this.type;
            inputEl.value = this.value;
            inputEl.name = this.name;
            inputEl.required = this.required;
            inputEl.type = this.type;
            inputEl.value = this.value;
            return inputEl;
        }
    }
    toTranspile.HTMLInputBuilder = HTMLInputBuilder;
    HTMLInputBuilder["__class"] = "toTranspile.HTMLInputBuilder";
})(toTranspile || (toTranspile = {}));
