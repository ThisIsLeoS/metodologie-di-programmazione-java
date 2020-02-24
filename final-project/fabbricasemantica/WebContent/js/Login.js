/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di Login. Inizializza gli elementi e i loro attributi
     * @class
     */
    class Login {
        constructor() {
            if (this.__main === undefined)
                this.__main = null;
            if (this.form === undefined)
                this.form = null;
            if (this.emailLabel === undefined)
                this.emailLabel = null;
            if (this.emailField === undefined)
                this.emailField = null;
            if (this.pwdLabel === undefined)
                this.pwdLabel = null;
            if (this.pwdField === undefined)
                this.pwdField = null;
            if (this.loginButton === undefined)
                this.loginButton = null;
            if (this.signupBox === undefined)
                this.signupBox = null;
            if (this.signupLink === undefined)
                this.signupLink = null;
            this.__main = toTranspile.Utilities.createMain();
            this.form = toTranspile.Utilities.createPOSTForm("login.jsp");
            this.emailLabel = toTranspile.Utilities.createLabel("Email*");
            this.emailField = toTranspile.Utilities.createEmailField();
            this.pwdLabel = toTranspile.Utilities.createLabel("Password*");
            this.pwdField = toTranspile.Utilities.createPassField("pwd");
            this.loginButton = toTranspile.Utilities.createButton("submit", "Log in");
            this.loginButton.className = "btn btn-primary login-btn";
            this.signupBox = document.createElement("div");
            this.signupBox.className = "signup-box";
            this.signupBox.textContent = "Not a member yet? ";
            this.signupLink = document.createElement("a");
            this.signupLink.href = "signup.html";
            this.signupLink.textContent = "Sign up";
        }
        static main(args) {
            new Login().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append($(this.__main).append($(this.form).append(this.emailLabel, this.emailField, this.pwdLabel, this.pwdField, this.loginButton, $(this.signupBox).append(this.signupLink))));
            return $("body");
        }
    }
    toTranspile.Login = Login;
    Login["__class"] = "toTranspile.Login";
    Login["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.Login.main(null);
