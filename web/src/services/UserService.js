class UserService {
    getUsers() {
        // fetch("http://localhost:8081/users")
        // .then(res => res.json())
        // .then(res => {
        //     return res.users;
        // })

        return [{"id":1,"name":"Aliko"},{"id":2,"name":"Bill"},{"id":3,"name":"Yegun"}];
    }
}

export default UserService