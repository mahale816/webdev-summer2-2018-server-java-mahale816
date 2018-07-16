(function () {

  var userServiceClient = new UserServiceClient();
  var currentUser = null;

  function init() {
        $('#createBtn').click(createUser);
        $('#updateBtn').click(updateUser);
        userServiceClient
        .findAllUsers()
        .then(renderUsers);
  }
  init();

  function updateUser() {
      var username = $("#username").val();
      var password = $("#password").val();
      var firstName = $('#firstName').val();
      var lastName = $('#lastName').val();
      var email = $('#email').val();
      var phoneNumber = $('#phoneNumber').val();
      var role = $('#role').val();
      var dateofbirth = $('#dateofbirth').val();

      var user = {
          username: username,
          password: password,
          firstName: firstName,
          lastname: lastName,
          email: email,
          phoneNumber: phoneNumber,
          role: role,
          dateofbirth: dateofbirth
      };
      userServiceClient
          .updateUser(currentUser.id, user)
          .then(function () {
              userServiceClient
                  .findAllUsers()
                  .then(renderUsers);
          });
  }
  
  function createUser() {
      console.log('createUser');
      var username = $('#username').val();
      var password = $('#password').val();
      var firstName = $('#firstName').val();
      var lastName = $('#lastName').val();
      var email = $('#email').val();
      var phoneNumber = $('#phoneNumber').val();
      var role = $('#role').val();
      var dateofbirth = $('#dateofbirth').val();

      var user = {
          username: username,
          password: password,
          firstName: firstName,
          lastname: lastName,
          email: email,
          phoneNumber: phoneNumber,
          role: role,
          dateofbirth: dateofbirth
      };
      
      userServiceClient
      .createUser(user)
      .then(function () {
          userServiceClient
              .findAllUsers()
              .then(renderUsers);
      });
  }

  function renderUsers(users) {
    console.log(users);

    var tbody = $('tbody');
    tbody.empty();
    for(var i=0; i<users.length; i++) {
      var user = users[i];

      var tr = $('<tr>');
      var td = $('<td>');
      td.append(user.username);
      tr.append(td);

      td = $('<td>');
      td.append('*******');
      tr.append(td);

      td = $('<td>');
      td.append(user.firstName);
      tr.append(td);

      td = $('<td>');
      td.append(user.lastname);
      tr.append(td);

      td = $('<td>');
      td.append(user.phoneNumber);
      tr.append(td);

      td = $('<td>');
      td.append(user.email);
      tr.append(td);

      td = $('<td>');
      td.append(user.role);
      tr.append(td);

      td = $('<td>');
      td.append(user.dateofbirth);
      tr.append(td);

    td = $('<td>');
    var editBtn = $('<button class="btn btn-primary">EDIT</button>');
    editBtn.click(renderUser);
    editBtn.attr('id', user.id);
    td.append(editBtn);
    // td.append(deleteBtn);
    tr.append(td);

      td = $('<td>');
      var deleteBtn = $('<button class="btn btn-danger">DELETE</button>');
      deleteBtn.click(deleteUser);
      deleteBtn.attr('id', user.id);
      td.append(deleteBtn);
      tr.append(td);

      tr.appendTo(tbody);
    }
  }

  function renderUser(event) {
      console.log(event);
      var $button = $(event.currentTarget);
      var id = $button.attr('id');

      userServiceClient
          .findUserById(id)
          .then(function (user) {
              $('#username').val(user.username);
              $('#password').val(user.password);
              $('#firstName').val(user.firstName);
              $('#lastName').val(user.lastname);
              $('#email').val(user.email);
              $('#phoneNumber').val(user.phoneNumber);
              $('#role').val(user.role);
              $('#dateofbirth').val(user.dateofbirth);
              currentUser = user;
          })

  }

  function deleteUser(event) {
    console.log(event);
    var $button = $(event.currentTarget);
    var id = $button.attr('id');

    userServiceClient
      .deleteUser(id)
      .then(function () {
        userServiceClient
          .findAllUsers()
          .then(renderUsers);
      });
  }
  
})();