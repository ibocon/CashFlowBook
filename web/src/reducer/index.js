import { combineReducers } from 'redux'
import { UserReducer } from './UserReducer'
import { AccountReducer } from './AccountReducer'
import { ModalReducer } from './ModalReducer'

export default combineReducers ({
    user: UserReducer,
    account: AccountReducer,
    modal: ModalReducer
})