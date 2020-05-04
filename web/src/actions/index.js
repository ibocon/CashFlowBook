export const GET_USERS = 'GET_USERS'

export const getUsers = (users) => ({
    type: 'GET_USERS',
    users
})

export function getUsersAsync(users) {
    return async dispatch => {
        return await fetch("http://localhost:8081/users")
        .then(
            response => response.json(),
            error => console.log(error)
        )
        .then(json => {
            dispatch(getUsers(json))
        })
    }
}