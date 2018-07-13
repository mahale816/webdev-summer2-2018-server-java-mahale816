function UserServiceClient() {

    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.createUser = createUser;
    this.logoutUser = logoutUser;
    this.updateUser = updateUser;
    this.findUserById = findUserById;

    function findUserById(userId) {
        return fetch('/api/user/' + userId)
            .then(function (response) {
                return response.json();
            });
    }

    function updateUser(id, newUser) {
        fetch("/api/user/" + id, {
            method: 'put',
            body: JSON.stringify(newUser),
            'credentials': 'include',
            headers: {
                'content-type': 'application/json'
            }
        });

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

    function logoutUser(user) {
            return fetch("api/logout", {
                method: 'post',
                body: JSON.stringify(user),
                'credentials': 'include',
                headers: {
                    'content-type': 'application/json'
                }
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