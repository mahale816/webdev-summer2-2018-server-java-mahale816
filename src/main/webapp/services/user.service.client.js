function UserServiceClient() {

    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.createUser = createUser;
    this.logoutUser = logoutUser;

    function deleteUser(id) {
        var url = "/api/user/" + id;

        return fetch(url, {
            method: 'delete'
        })
    }

    function createUser(user) {
        var url = "/api/user/" + id;
        return fetch(url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function logoutUser(user) {
        function createUser(user) {
            return fetch('api/logout', {
                method: 'post',
                body: JSON.stringify(user),
                headers: {
                    'content-type': 'application/json'
                }
            });
        }
    }



    function findAllUsers() {
        var url = "/api/user";
        return fetch(url)
            .then(function (response) {
                return response.json();
            });
    }

}