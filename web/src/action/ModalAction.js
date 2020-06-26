import { ActionConstant } from '../constant'

export const ModalAction = {
    showModal(show) {
        return {type: ActionConstant.SHOW_MODAL, show}
    },
    RegisterAccountHandler(handler) {
        return {type: ActionConstant.REGISTER_ACCOUNT_HANDLER, handler}
    }
}
