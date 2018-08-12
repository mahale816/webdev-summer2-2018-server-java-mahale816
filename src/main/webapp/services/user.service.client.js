function UserServiceClient() {

    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.createUser = createUser;
    this.logoutUser = logoutUser;
    this.updateUser = updateUser;
    this.findUserById = findUserById;
    this.findUserByUsername = findUserByUsername;

    function findUserById(userId) {
        return fetch('/api/user/' + userId)
            .then(function (response) {
                return response.json();
            });
    }

    function updateUser(id, newUser) {
        return fetch("/api/user/" + id, {
            method: 'put',
            body: JSON.stringify(newUser),
            'credentials': 'include',
            headers: {
                'content-type': 'application/json'
            }
        });

    }

    function findUserByUsername(username) {
        return fetch("/api/"+username)
            .then(function (response) {
                return response.json();
            })
    }

    function deleteUser(id) {
        var url = "/api/user/" + id;

        return fetch(url, {
            method: 'delete'
        })
    }

    function createUser(user) {
        var url = "/api/user"
        return fetch(url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function logoutUser() {
            return fetch("/api/logout", {
                'credentials': 'include'
            });
    }

    function findAllUsers() {
        var url = "/api/user";
        return fetch(url)
            .then(function (response) {
                return response.json();
            });
    }

}