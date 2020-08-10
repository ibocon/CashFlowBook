
const MY_BASE_URL = 'http://localhost:8080'
const API_BASE_URL = 'http://localhost:8081'
const OAUTH2_REDIRECT_URI = MY_BASE_URL + '/oauth2/redirect'
const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI

const ACCESS_TOKEN = 'accessToken'

export const UrlConstant = {
    MY_BASE_URL,
    API_BASE_URL,
    GOOGLE_AUTH_URL,
    OAUTH2_REDIRECT_URI,
    ACCESS_TOKEN
}