(function () {

  var userServiceClient = new UserServiceClient();

  var $username,
      $firstName,
      $lastName,
      $role,
      $dateofbirth,
      $email,
      $phoneNumber,
      $updateBtn,
      $logoutBtn;

  var currentUser = null;

  function init() {

    $username = $("#username");
    $firstName = $("#firstName");
    $lastName = $("#lastName");
    $role = $('#role');
    $email = $('#email');
    $phoneNumber = $('#phoneNumber');
    $dateofbirth = $('#dateofbirth');
    $updateBtn = $("#updateBtn");
    $logoutBtn = $("#logoutBtn");

    $updateBtn.click(updateUser);
    $logoutBtn.click(logoutUser);

    profile()
      .then(renderUser);
  }

  init();

  function logoutUser(){
      var user = currentUser;
      userServiceClient
          .logoutUser(user)
            .then(navigateToLogin);
  }

  function navigateToLogin() {
    window.location.href = "../login/login.template.client.html"
  }

  function updateUser() {
    var user = {
        firstName: $firstName.val(),
        lastname: $lastName.val(),
        role: $role.val(),
        email: $email.val(),
        phoneNumber: $phoneNumber.val(),
        dateofbirth: $dateofbirth.val()
    };

    userServiceClient
        .updateUser(currentUser.id, user);
  }

  function renderUser(user) {
    currentUser = user;
    $username.val(user.username);
    $firstName.val(user.firstName);
    $lastName.val(user.lastname);
    $email.val(user.email);
    $phoneNumber.val(user.phoneNumber);
    $role.val(user.role);
    $dateofbirth.val(user.dateofbirth);
  }

  function profile() {
    return fetch('/profile', {
      'credentials': 'include'
    })
    .then(function (response) {
      return response.json();
    });
  }
})();