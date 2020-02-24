/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di TranslationAnnotation. Inizializza gli elementi e i loro attributi e aggiunge
     * gli event listener
     * @class
     * @extends toTranspile.BasicAnnotValidat
     */
    class TranslationAnnotation extends toTranspile.BasicAnnotValidat {
        constructor() {
            super();
            if (this.wordContainer === undefined)
                this.wordContainer = null;
            if (this.definitionContainer === undefined)
                this.definitionContainer = null;
            if (this.wordAndDefinitionContainer === undefined)
                this.wordAndDefinitionContainer = null;
            if (this.translationLabel === undefined)
                this.translationLabel = null;
            if (this.translationTextField === undefined)
                this.translationTextField = null;
            this.form = toTranspile.Utilities.createPOSTForm("translationAnnotation.jsp");
            this.wordContainer = document.createElement("span");
            this.wordContainer.className = "word-container";
            this.definitionContainer = document.createElement("span");
            this.definitionContainer.className = "definition-container";
            this.wordAndDefinitionContainer = document.createElement("p");
            this.wordAndDefinitionContainer.className = "word-and-def-container";
            this.translationLabel = toTranspile.Utilities.createLabel("Translate the word into your first language");
            this.translationTextField = toTranspile.Utilities.createTextField("translation");
            $.getJSON("nextExample.jsp", "task=TRANSLATION_ANNOTATION", (result, a, ctx) => {
                let json = result;
                let word = (json["word"]);
                let definition = (json["description"]);
                this.wordContainer.textContent = word;
                this.definitionContainer.textContent = definition;
                return null;
            });
            this.skipBtn.addEventListener("click", (event) => {
                this.form.reset();
                this.form.submit();
            });
        }
        static main(args) {
            new TranslationAnnotation().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append(this.header, $(this.mainEl).append($(this.form).append($(this.wordAndDefinitionContainer).append(this.wordContainer, this.definitionContainer), this.translationLabel, this.translationTextField, this.skipAndNextBtnsContainer)));
            return $("body");
        }
    }
    toTranspile.TranslationAnnotation = TranslationAnnotation;
    TranslationAnnotation["__class"] = "toTranspile.TranslationAnnotation";
    TranslationAnnotation["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.TranslationAnnotation.main(null);
