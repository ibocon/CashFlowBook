import { UrlConstant } from '../constant'

export function isEmpty(object) {
    for(var key in object) {
        if(object.hasOwnProperty(key)) {
            return false;
        }
    }

    return true;
}

export async function request(options) {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })

    if(localStorage.getItem(UrlConstant.ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(UrlConstant.ACCESS_TOKEN))
    }
    else {
        return Promise.reject("No access token set.");
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

