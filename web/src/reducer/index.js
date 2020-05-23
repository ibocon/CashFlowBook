import { combineReducers } from 'redux'
import UserReducer from './UserReducer'
import AccountReducer from './AccountReducer'

export default combineReducers ({
    user: UserReducer,
    account: AccountReducer
})