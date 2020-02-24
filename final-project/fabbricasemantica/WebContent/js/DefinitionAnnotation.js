/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di DefinitionAnnotation. Inizializza gli elementi e i loro attributi e aggiunge
     * gli event listener
     * @class
     * @extends toTranspile.BasicAnnotValidat
     */
    class DefinitionAnnotation extends toTranspile.BasicAnnotValidat {
        constructor() {
            super();
            if (this.wordContainer === undefined)
                this.wordContainer = null;
            if (this.hyperonymContainer === undefined)
                this.hyperonymContainer = null;
            if (this.wordAndHyperonymContainer === undefined)
                this.wordAndHyperonymContainer = null;
            if (this.definitionLabel === undefined)
                this.definitionLabel = null;
            if (this.definitionTextAreaField === undefined)
                this.definitionTextAreaField = null;
            this.form = toTranspile.Utilities.createPOSTForm("definitionAnnotation.jsp");
            this.wordContainer = document.createElement("span");
            this.wordContainer.className = "word-container";
            this.hyperonymContainer = document.createElement("span");
            this.hyperonymContainer.className = "hyperonym-container";
            this.wordAndHyperonymContainer = document.createElement("p");
            this.wordAndHyperonymContainer.className = "word-and-hyp-container";
            $(this.wordAndHyperonymContainer).append(this.wordContainer, this.hyperonymContainer);
            this.definitionLabel = toTranspile.Utilities.createLabel("Give a definition of the word using your first language");
            this.definitionTextAreaField = toTranspile.Utilities.createTextAreaField("definition");
            this.definitionTextAreaField.required = true;
            this.definitionTextAreaField.maxLength = 500;
            $.getJSON("nextExample.jsp", "task=DEFINITION_ANNOTATION", (result, a, ctx) => {
                let json = result;
                let word = (json["word"]);
                let hyperonym = (json["hypernym"]);
                this.wordContainer.textContent = word;
                this.hyperonymContainer.textContent = hyperonym;
                return null;
            });
            this.skipBtn.addEventListener("click", (event) => {
                this.form.reset();
                this.form.submit();
            });
        }
        static main(args) {
            new DefinitionAnnotation().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append(this.header, $(this.mainEl).append($(this.form).append(this.wordAndHyperonymContainer, this.definitionLabel, this.definitionTextAreaField, this.skipAndNextBtnsContainer)));
            return $("body");
        }
    }
    toTranspile.DefinitionAnnotation = DefinitionAnnotation;
    DefinitionAnnotation["__class"] = "toTranspile.DefinitionAnnotation";
    DefinitionAnnotation["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.DefinitionAnnotation.main(null);
