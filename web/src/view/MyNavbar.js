import React from 'react';
import { connect } from 'react-redux'
import { withRouter } from "react-router"
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import { NavLink } from 'react-router-dom'
import { UserAction } from '../action'
import { isEmpty } from '../util'
import { UrlConstant } from '../constant'

class MyNavbar extends React.Component {

    render() {
        const { user, logout } = this.props;

        return (    
            <Navbar collapseOnSelect expand="md" bg="dark" variant="dark">
                <Navbar.Brand href="/">Ledger</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="mr-auto">
                        {!isEmpty(user) && <Nav.Link as={NavLink} to="/profile">프로필</Nav.Link>}
                        {!isEmpty(user) && <Nav.Link as={NavLink} to="/account">계정과목</Nav.Link>}
                        {!isEmpty(user) && <Nav.Link as={NavLink} to="/dashboard">대시보드</Nav.Link>}
                    </Nav>
                    <Nav>
                        {isEmpty(user) && <Nav.Link href={UrlConstant.GOOGLE_AUTH_URL}>로그인</Nav.Link>}
                        {!isEmpty(user) && <Nav.Link onClick={logout}>로그아웃</Nav.Link>}
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        )
    }
}
const mapStateToProps = state => ({
    user: state.user
})

const mapDispatchToProps = dispatch => ({
    logout: e => {
        e.preventDefault();
        dispatch(UserAction.logout());
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(MyNavbar))