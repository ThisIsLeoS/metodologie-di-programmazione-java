/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di SenseValidation. Inizializza gli elementi e i loro attributi e aggiunge gli
     * event listener
     * @class
     * @extends toTranspile.BasicAnnotValidat
     */
    class SenseValidation extends toTranspile.BasicAnnotValidat {
        constructor() {
            super();
            if (this.form === undefined)
                this.form = null;
            if (this.wordContainer === undefined)
                this.wordContainer = null;
            if (this.selectSentenceContainer === undefined)
                this.selectSentenceContainer = null;
            if (this.serverSentenceContainer === undefined)
                this.serverSentenceContainer = null;
            if (this.isSentenceContainer === undefined)
                this.isSentenceContainer = null;
            if (this.senseContainer === undefined)
                this.senseContainer = null;
            if (this.sentencesContainer === undefined)
                this.sentencesContainer = null;
            if (this.yesRadio === undefined)
                this.yesRadio = null;
            if (this.yesRadioLabel === undefined)
                this.yesRadioLabel = null;
            if (this.noRadio === undefined)
                this.noRadio = null;
            if (this.noRadioLabel === undefined)
                this.noRadioLabel = null;
            if (this.radioBtnsContainer === undefined)
                this.radioBtnsContainer = null;
            if (this.errMsgContainer === undefined)
                this.errMsgContainer = null;
            this.form = toTranspile.Utilities.createPOSTForm("senseValidation.jsp");
            this.form.addEventListener("submit", (event) => {
                if ($("input[type=radio]:checked").length === 0) {
                    this.errMsgContainer.textContent = "Select \"yes\" or \"no\"";
                    event.preventDefault();
                }
            });
            this.wordContainer = document.createElement("span");
            this.wordContainer.className = "word-container";
            this.selectSentenceContainer = document.createElement("span");
            this.serverSentenceContainer = document.createElement("span");
            this.serverSentenceContainer.className = "sentence-container";
            this.isSentenceContainer = document.createElement("span");
            this.isSentenceContainer.className = "is-sentence-container";
            this.isSentenceContainer.textContent = "Is the following sense the correct one for the word?";
            this.senseContainer = document.createElement("span");
            this.senseContainer.className = "sense-container";
            this.sentencesContainer = document.createElement("p");
            this.sentencesContainer.className = "sentences-container";
            this.yesRadio = toTranspile.Utilities.createRadioBtn("radioBtn", "yes");
            this.yesRadioLabel = toTranspile.Utilities.createLabel("yes");
            this.noRadio = toTranspile.Utilities.createRadioBtn("radioBtn", "no");
            this.noRadio.className = "no-radio-btn";
            this.noRadioLabel = toTranspile.Utilities.createLabel("no");
            this.radioBtnsContainer = document.createElement("div");
            this.radioBtnsContainer.className = "radio-btns-container";
            $.getJSON("nextExample.jsp", "task=SENSE_VALIDATION", (result, a, ctx) => {
                let json = result;
                let word = (json["word"]);
                let sentence = (json["example"]);
                let sense = (json["sense"]);
                this.wordContainer.textContent = word;
                this.serverSentenceContainer.textContent = "\"" + sentence + "\"";
                this.senseContainer.textContent = "\"" + sense + "\"";
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
            new SenseValidation().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append(this.header, $(this.mainEl).append($(this.form).append($(this.sentencesContainer).append($(this.selectSentenceContainer).append("Given the word ", this.wordContainer, " in the sentence"), this.serverSentenceContainer, this.isSentenceContainer, this.senseContainer), $(this.radioBtnsContainer).append(this.yesRadio, this.yesRadioLabel, this.noRadio, this.noRadioLabel), this.skipAndNextBtnsContainer, this.errMsgContainer)));
            return $("body");
        }
    }
    toTranspile.SenseValidation = SenseValidation;
    SenseValidation["__class"] = "toTranspile.SenseValidation";
    SenseValidation["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.SenseValidation.main(null);
