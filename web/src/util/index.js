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

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    try {
        console.log(typeof options);
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

