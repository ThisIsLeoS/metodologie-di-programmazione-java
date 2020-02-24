/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di TranslationValidation. Inizializza gli elementi e i loro attributi e aggiunge
     * gli event listener
     * @class
     * @extends toTranspile.BasicAnnotValidat
     */
    class TranslationValidation extends toTranspile.BasicAnnotValidat {
        constructor() {
            super();
            if (this.wordContainer === undefined)
                this.wordContainer = null;
            if (this.selectSentenceContainer === undefined)
                this.selectSentenceContainer = null;
            if (this.transBoxesContainer === undefined)
                this.transBoxesContainer = null;
            if (this.errMsgContainer === undefined)
                this.errMsgContainer = null;
            this.form = toTranspile.Utilities.createPOSTForm("translationValidation.jsp");
            this.form.addEventListener("submit", (event) => {
                if ($(".checkboxes-container input:checked").length === 0) {
                    this.errMsgContainer.textContent = "At least one checkbox must be checked!";
                    event.preventDefault();
                }
                let noneBox = document.querySelector("[value=\"none of the above\"]");
                if (noneBox.checked && $(".checkboxes-container input:checked").length > 1) {
                    this.errMsgContainer.textContent = "You can\'t check \"none of the above\" and other boxes too";
                    event.preventDefault();
                }
            });
            this.wordContainer = document.createElement("span");
            this.wordContainer.className = "word-container";
            this.selectSentenceContainer = document.createElement("p");
            this.transBoxesContainer = document.createElement("div");
            this.transBoxesContainer.className = "checkboxes-container";
            $.getJSON("nextExample.jsp", "task=TRANSLATION_VALIDATION", (result, a, ctx) => {
                let json = result;
                let word = (json["word"]);
                let definition = (json["description"]);
                let translations = (json["translations"]);
                this.wordContainer.textContent = word;
                $(this.selectSentenceContainer).append(" (\"" + definition + "\")");
                for (let index123 = 0; index123 < translations.length; index123++) {
                    let translation = translations[index123];
                    {
                        let transBox = toTranspile.Utilities.createCheckBox("translation", translation);
                        let transBoxLabel = toTranspile.Utilities.createLabel(translation);
                        let boxContainer = document.createElement("div");
                        $(boxContainer).append(transBox, transBoxLabel);
                        $(this.transBoxesContainer).append(boxContainer);
                    }
                }
                let transBox = toTranspile.Utilities.createCheckBox("translation", "none of the above");
                let transBoxLabel = toTranspile.Utilities.createLabel("none of the above");
                let boxContainer = document.createElement("div");
                $(boxContainer).append(transBox, transBoxLabel);
                $(this.transBoxesContainer).append(boxContainer);
                return null;
            });
            this.skipBtn.addEventListener("click", (event) => {
                this.form.reset();
                this.form.submit();
            });
            this.errMsgContainer = document.createElement("div");
            this.errMsgContainer.className = "err-msg-container";
        }
        static main(args) {
            new TranslationValidation().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append(this.header, $(this.mainEl).append($(this.form).append($(this.selectSentenceContainer).append("Select the best translation of the word ", this.wordContainer), this.transBoxesContainer, this.skipAndNextBtnsContainer, this.errMsgContainer)));
            return $("body");
        }
    }
    toTranspile.TranslationValidation = TranslationValidation;
    TranslationValidation["__class"] = "toTranspile.TranslationValidation";
    TranslationValidation["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.TranslationValidation.main(null);
