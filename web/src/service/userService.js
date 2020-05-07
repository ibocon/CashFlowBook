import { UrlConstant } from '../constant'

export const userService = {
    getUsers
}

async function getUsers() {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    }

    try {
        const response = await fetch(UrlConstant.API_BASE_URL + "/users", requestOptions);
        return await response.json();
    }
    catch (error) {
        return console.log(error);
    }
}