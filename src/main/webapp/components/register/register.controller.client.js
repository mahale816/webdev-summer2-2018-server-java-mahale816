// IIFE
// Immediately Invoked Function Expression
(function () {

    var userServiceClient = new UserServiceClient();

    var registerBtn = jQuery('#registerBtn');
    var usernameFld = $('#username');
    var passwordFld = $('#password');
    var password2Fld = $('#password2');

    registerBtn.click(checkUser);

    // function findUserByUsername(usernameStr, passwordStr) {
    //     var user = {
    //         "username": usernameStr,
    //         "password": passwordStr
    //     };
    //     return fetch('/api/'+usernameStr ,{
    //         method: 'get',
    //         headers: {
    //             'content-type': 'application/json'
    //         }
    //     }).then();
    // }

    function checkUser() {
            var usernameStr = usernameFld.val();
            if (usernameStr === "" || usernameStr === null){
            	alert('All Fields are mandatory');
            }
            else {
            userServiceClient
                .findUserByUsername(usernameStr)
                .then(function (response){
                    // var user = response;response
                    // console.log(response);
                    // if(response.ok) {
                    if(response === null || !response) {
                        registerHandler();
                    } else {
                        alert('User Already Exists');
                        return;
                    }
                    //     alert('User Already Exists');
                    //     return;
                    // } else {
                    //     console.log("inside else");
                    //     registerHandler();
                    // }
                });
            }
    }

    function registerHandler() {
        var usernameStr = usernameFld.val();
        var passwordStr = passwordFld.val();
        var password2Str = password2Fld.val();

        if (usernameStr && passwordStr && password2Str) {
            if (passwordStr !== password2Str) {
                alert("Passwords don't Match");
                return;
            }
            var userObj = {
                username: usernameStr,
                password: passwordStr
            };

            var userObjStr = JSON.stringify(userObj);

            fetch('/register', {
                method: 'post',
                body: userObjStr,
                headers: {
                    'Content-Type': 'application/json'
                },
                'credentials': 'include'
            }).then(registrationSuccessful, registrationFailed)
        } else {
            alert("All Fields are mandatory");
        }
    }
    function registrationSuccessful() {
        window.location.href = '/components/profile/profile.template.client.html';
    }

    function registrationFailed() {
        alert('oops')
    }
})();
