(function () {
  var $username,
    $password,
    $loginBtn;
  
  function init() {
    $username = $('#username');
    $password = $('#password');
    $loginBtn = $('#loginBtn');
    $loginBtn.click(login);
  }
  init();
  
  function login() {
    var user = {
      "username": $username.val(),
      "password": $password.val()
    };
    fetch('/login', {
      method: 'post',
      body: JSON.stringify(user),
      'credentials': 'include',
      headers: {
        'content-type': 'application/json'
      }
    }).then(function (response) {
        if (response.status === 409){
          alert('invalid credentials')
        }
        else {
          alert('login successful');
          navigateToProfile()
        }
    });
  }

  function navigateToProfile() {
    window.location.href = '/components/profile/profile.template.client.html';
  }
})();