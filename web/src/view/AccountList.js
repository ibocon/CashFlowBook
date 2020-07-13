import React from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import { Button } from 'react-bootstrap';
import { AccountAction, ModalAction } from '../action';
import {AccountModal, Account} from '../view';
import { AccountService } from '../service';

class AccountList extends React.Component {

    componentDidMount() {
        this.props.getAccounts();
    }

    filterBaseAccount(base) {
        if(this.props == null || this.props.account == null) {
            return;
        }
        else {
            return this.props.account.list
            .filter(account => account.base === base)
            .map(account => <Account key={account.base + account.name} account={account}/> );
        }
    }

    render() {
        
        const assets = this.filterBaseAccount("Asset");
        const capitals = this.filterBaseAccount("Capital");
        const liabilities = this.filterBaseAccount("Liability");
        const revenues = this.filterBaseAccount("Revenue");
        const expenses = this.filterBaseAccount("Expense");

        return (
            <div>
                <p>Test2</p>
                <h1>계정과목</h1>
                <div>
                    <h2>자산</h2>
                    {assets}
                    <h2>비용</h2> 
                    {expenses}
                </div>
                <div>
                    <h2>자본</h2>
                    {capitals}
                    <h2>부채</h2>
                    {liabilities}
                    <h2>수익</h2>
                    {revenues}
                </div>
                <Button variant="primary" onClick={() => {
                    this.props.showModal(true);
                    this.props.RegisterAccountHandler((account) => 
                    {
                        this.props.createAccount(account);
                    });
                }}>계정 만들기</Button>

                <AccountModal   show={this.props.show} 
                                handleModal={this.props.showModal} 
                                handleAccount={this.props.createAccount} />
            </div>
        )
    }
}

const mapStateToProps = state => ({
    show: state.modal.show,
    account: state.account
})
  
const mapDispatchToProps = dispatch => ({
    getAccounts: async () => {
        const accounts = await AccountService.getAccounts();
        dispatch(AccountAction.getAccount(accounts));
    },
    showModal: (show) => {
        dispatch(ModalAction.showModal(show))
    },
    RegisterAccountHandler: (handler) => {
        dispatch(ModalAction.RegisterAccountHandler(handler))
    },
    createAccount: async (account) => {
        await AccountService.createAccount(account);
        dispatch(AccountAction.createAccount(account));
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(AccountList))