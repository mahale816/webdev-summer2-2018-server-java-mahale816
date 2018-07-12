(function () {

  var userServiceClient = new UserServiceClient();

  var $username, $firstName, $lastName, $role, $dateofbirth,
    $updateBtn, $logoutBtn;
  var currentUser = null;

  function init() {

    $username = $("#username");
    $firstName = $("#firstName");
    $lastName = $("#lastName");
    //$role = $('#role');
    //$dateofbirth = $('#dateofbirth');
    $updateBtn = $("#updateBtn");
    $logoutBtn = $("#logoutBtn");

    $updateBtn.click(updateUser);
    $logoutBtn.click(logoutUser);

    profile()
      .then(renderUser);
  }

  init();

  function logoutUser(user){
      user = currentUser;
      userServiceClient
          .logoutUser(user)
          .then(navigateToIndex);
  }

  function navigateToIndex() {
      window.location.href = "../login/login.template.client.html";
  }
  
  function updateUser() {
    var user = {
      firstName: $firstName.val(),
      lastName: $lastName.val()
    };

    fetch("/api/user/" + currentUser.id, {
      method: 'put',
      body: JSON.stringify(user),
      'credentials': 'include',
      headers: {
        'content-type': 'application/json'
      }
    });
  }

  function renderUser(user) {
    currentUser = user;
    $username.val(user.username);
    $firstName.val(user.firstName);
    $lastName.val(user.lastname);
    //$role.val(user.role);
    //$dateofbirth.val(user.dateobirth);
  }

  function profile() {
    return fetch('/profile', {
      'credentials': 'include'
    })
    .then(function (response) {
      return response.json();
    });
  }

  function findUserById(userId) {
    return fetch('/api/user/' + userId)
      .then(function (response) {
        return response.json();
      });
  }
})();