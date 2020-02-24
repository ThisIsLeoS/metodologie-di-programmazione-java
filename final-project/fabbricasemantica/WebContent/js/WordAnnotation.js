/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di WordnAnnotation. Inizializza gli elementi e i loro attributi e aggiunge
     * gli event listener
     * @class
     * @extends toTranspile.BasicAnnotValidat
     */
    class WordAnnotation extends toTranspile.BasicAnnotValidat {
        constructor() {
            super();
            if (this.definitionContainer === undefined)
                this.definitionContainer = null;
            if (this.wordLabel === undefined)
                this.wordLabel = null;
            if (this.wordTextField === undefined)
                this.wordTextField = null;
            this.form = toTranspile.Utilities.createPOSTForm("wordAnnotation.jsp");
            this.definitionContainer = document.createElement("p");
            this.definitionContainer.className = "definition-container";
            this.wordLabel = toTranspile.Utilities.createLabel("What\'s the word?");
            this.wordTextField = toTranspile.Utilities.createTextField("word");
            $.getJSON("nextExample.jsp", "task=WORD_ANNOTATION", (result, a, ctx) => {
                let json = result;
                let definition = (json["description"]);
                this.definitionContainer.textContent = definition;
                return null;
            });
            this.skipBtn.addEventListener("click", (event) => {
                this.form.reset();
                this.form.submit();
            });
        }
        static main(args) {
            new WordAnnotation().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append(this.header, $(this.mainEl).append($(this.form).append(this.definitionContainer, this.wordLabel, this.wordTextField, this.skipAndNextBtnsContainer)));
            return $("body");
        }
    }
    toTranspile.WordAnnotation = WordAnnotation;
    WordAnnotation["__class"] = "toTranspile.WordAnnotation";
    WordAnnotation["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.WordAnnotation.main(null);
