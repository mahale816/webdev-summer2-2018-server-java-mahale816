function User(username, password, firstName, lastName, email, role, phoneNumber, dateofbirth) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
    this.phoneNumber = phoneNumber;
    this.dateofbirth = dateofbirth;

    this.setUsername = setUsername;
    this.getUsername = getUsername;
    this.setpassword = setpassword;
    this.getpassword = getpassword;
    this.setfirstName = setfirstName;
    this.getfirstName = getfirstName;
    this.setlastName = setlastName;
    this.getlastName = getlastName;
    this.setemail = setemail;
    this.getemail = getemail;
    this.setrole = setrole;
    this.getrole = getrole;
    this.setphoneNumber = setphoneNumber;
    this.getphoneNumber = getphoneNumber;
    this.setdateofbirth = setdateofbirth;
    this.getdateofbirth = getdateofbirth;

    function setUsername(username) {
        this.username = username;
    }

    function getUsername() {
        return this.username;
    }

    function setpassword(password) {
        this.password = password;
    }

    function getpassword() {
        return this.password;
    }

    function setfirstName(firstName) {
        this.firstName = firstName;
    }

    function getfirstName() {
        return this.firstName;
    }

    function setlastName(lastName) {
        this.lastName = lastName;
    }

    function getlastName() {
        return this.lastName;
    }

    function setemail(email) {
        this.email = email;
    }

    function getemail() {
        return this.email;
    }

    function setrole(role) {
        this.role = role;
    }

    function getrole() {
        return this.role;
    }

    function setphoneNumber(phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    function getphoneNumber() {
        return this.phoneNumber;
    }

    function setdateofbirth(dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    function getdateofbirth() {
        return this.dateofbirth;
    }
}
