import { ActionConstant } from '../constant'

const initialState = {
    list: []
};

const AccountReducer = (state = initialState, action) => {
    switch(action.type) {
        case ActionConstant.GET_ACCOUNTS:
            return Object.assign({}, state, {
                list: action.accounts
            });
        case ActionConstant.CREATE_ACCOUNT:
            return Object.assign({}, state);
        default:
            return state;
    }
}

export default AccountReducer