import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router'
import { AccountAction } from '../action'
import { Button } from 'react-bootstrap'
import AccountModal from './AccountModal'

class Account extends React.Component {

    componentDidMount() {
        this.props.getAccounts();
    }

    render() {
        return (
            <div>
                <h1>계정과목</h1>
                <div>
                    <h2>자산</h2>
                    <h2>비용</h2> 
                </div>
                <div>
                    <h2>자본</h2>
                    <h2>부채</h2>
                    <h2>수익</h2>
                </div>
                <Button variant="primary" onClick={() => {
                    this.props.showModal(true);
                    this.props.handleAccount(AccountAction.createAccountAsync);
                }}>계정 만들기</Button>

                <AccountModal></AccountModal>
            </div>
        )
    }
}

const mapStateToProps = state => ({
    accounts: state.account.list,
})
  
const mapDispatchToProps = dispatch => ({
    getAccounts: () => {
        dispatch(AccountAction.getAccountsAsync())
    },
    showModal: (show) => {
        dispatch(AccountAction.showModal(show))
    },
    handleAccount: (handler) => {
        dispatch(AccountAction.handleAccount(handler))
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Account))