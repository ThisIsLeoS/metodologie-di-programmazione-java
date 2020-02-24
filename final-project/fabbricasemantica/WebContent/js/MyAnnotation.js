/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di MyAnnotation. Inizializza gli elementi e i loro attributi e aggiunge gli event
     * listener
     * @class
     * @extends toTranspile.BasicAnnotValidat
     */
    class MyAnnotation extends toTranspile.BasicAnnotValidat {
        constructor() {
            super();
            if (this.wordsAndInputsContainer === undefined)
                this.wordsAndInputsContainer = null;
            this.form = toTranspile.Utilities.createPOSTForm("myAnnotation.jsp");
            this.wordsAndInputsContainer = document.createElement("div");
            this.wordsAndInputsContainer.className = "words-inputs-container";
            $.getJSON("nextExample.jsp", "task=MY_ANNOTATION", (result, a, ctx) => {
                let json = result;
                let words = (json["words"]);
                for (let index121 = 0; index121 < words.length; index121++) {
                    let word = words[index121];
                    {
                        let wordAndInputContainer = document.createElement("div");
                        wordAndInputContainer.className = "word-input-container";
                        let textField = toTranspile.Utilities.createTextField("word");
                        $(this.wordsAndInputsContainer).append($(wordAndInputContainer).append(word, textField));
                    }
                }
                return null;
            });
            this.skipBtn.addEventListener("click", (event) => {
                this.form.reset();
                this.form.submit();
            });
        }
        static main(args) {
            new MyAnnotation().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append(this.header, $(this.mainEl).append($(this.form).append("Translate the following words into your first language", this.wordsAndInputsContainer, this.skipAndNextBtnsContainer)));
            return $("body");
        }
    }
    toTranspile.MyAnnotation = MyAnnotation;
    MyAnnotation["__class"] = "toTranspile.MyAnnotation";
    MyAnnotation["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.MyAnnotation.main(null);
