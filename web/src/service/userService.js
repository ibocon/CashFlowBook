import { UrlConstant } from '../constant'

// Module
export const userService = {
    getCurrentUser,
}

// Public
async function getCurrentUser() {
    if(!localStorage.getItem(UrlConstant.ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return await request({
        url: UrlConstant.API_BASE_URL + "/user/me",
        method: 'GET'
    });
}

// Private
async function request(options) {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })

    if(localStorage.getItem(UrlConstant.ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(UrlConstant.ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    try {
        const response = await fetch(options.url, options);
        const json = await response.json();
        if(response.ok){
            return json;
        } else {
            return Promise.reject(json);
        }
    }
    catch (error) {
        return console.log(error);
    }
}

