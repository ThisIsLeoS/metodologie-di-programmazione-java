/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di Home. Inizializza gli elementi e i loro attributi
     * @class
     */
    class Home {
        constructor() {
            if (this.__main === undefined)
                this.__main = null;
            if (this.startBtn === undefined)
                this.startBtn = null;
            if (this.logoutLink === undefined)
                this.logoutLink = null;
            this.__main = toTranspile.Utilities.createMain();
            this.startBtn = toTranspile.Utilities.createButton("button", "Start");
            this.startBtn.className = "btn btn-primary btn-lg start-btn";
            this.startBtn.addEventListener("click", (event) => {
                window.location.href = toTranspile.Utilities.getRandomHTMLFilename("");
            });
            this.logoutLink = document.createElement("a");
            this.logoutLink.href = "logout.jsp";
            this.logoutLink.textContent = "Log out";
        }
        static main(args) {
            new Home().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append($(this.__main).append(this.startBtn, this.logoutLink));
            return $("body");
        }
    }
    toTranspile.Home = Home;
    Home["__class"] = "toTranspile.Home";
    Home["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.Home.main(null);
