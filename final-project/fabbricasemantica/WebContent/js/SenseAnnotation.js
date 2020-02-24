/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di SenseAnnotation. Inizializza gli elementi e i loro attributi e aggiunge gli
     * event listener
     * @class
     * @extends toTranspile.BasicAnnotValidat
     */
    class SenseAnnotation extends toTranspile.BasicAnnotValidat {
        constructor() {
            super();
            if (this.form === undefined)
                this.form = null;
            if (this.selectSentenceContainer === undefined)
                this.selectSentenceContainer = null;
            if (this.wordContainer === undefined)
                this.wordContainer = null;
            if (this.serverSentenceContainer === undefined)
                this.serverSentenceContainer = null;
            if (this.wordAndSentenceContainer === undefined)
                this.wordAndSentenceContainer = null;
            if (this.sensesBoxesContainer === undefined)
                this.sensesBoxesContainer = null;
            if (this.errMsgContainer === undefined)
                this.errMsgContainer = null;
            this.form = toTranspile.Utilities.createPOSTForm("senseAnnotation.jsp");
            this.form.addEventListener("click", (event) => {
                if ($(".checkboxes-container input:checked").length === 0) {
                    this.errMsgContainer.textContent = "At least one checkbox must be checked!";
                    event.preventDefault();
                }
            });
            this.selectSentenceContainer = document.createElement("span");
            this.wordContainer = document.createElement("span");
            this.wordContainer.className = "word-container";
            this.serverSentenceContainer = document.createElement("span");
            this.serverSentenceContainer.className = "sentence-container";
            this.wordAndSentenceContainer = document.createElement("p");
            this.wordAndSentenceContainer.className = "word-and-sentence-container";
            this.sensesBoxesContainer = document.createElement("div");
            this.sensesBoxesContainer.className = "checkboxes-container";
            $.getJSON("nextExample.jsp", "task=SENSE_ANNOTATION", (result, a, ctx) => {
                let json = result;
                let word = (json["word"]);
                let sentence = (json["description"]);
                let synsetsIds = (json["synsetsIds"]);
                let senses = (json["senses"]);
                this.wordContainer.textContent = word;
                this.serverSentenceContainer.textContent = "\"" + sentence + "\"";
                for (let i = 0; i < senses.length; ++i) {
                    {
                        let senseBox = toTranspile.Utilities.createCheckBox("sense", synsetsIds[i]);
                        let senseBoxLabel = toTranspile.Utilities.createLabel(senses[i]);
                        let boxContainer = document.createElement("div");
                        $(boxContainer).append(senseBox, senseBoxLabel);
                        $(this.sensesBoxesContainer).append(boxContainer);
                    }
                    ;
                }
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
            new SenseAnnotation().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append(this.header, $(this.mainEl).append($(this.form).append($(this.wordAndSentenceContainer).append($(this.selectSentenceContainer).append("Select the correct sense of the word ", this.wordContainer, " in the sentence"), this.serverSentenceContainer), this.sensesBoxesContainer, this.skipAndNextBtnsContainer, this.errMsgContainer)));
            return $("body");
        }
    }
    toTranspile.SenseAnnotation = SenseAnnotation;
    SenseAnnotation["__class"] = "toTranspile.SenseAnnotation";
    SenseAnnotation["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.SenseAnnotation.main(null);
