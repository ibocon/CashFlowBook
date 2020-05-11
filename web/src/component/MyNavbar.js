import React from 'react';
import { connect } from 'react-redux'
import { withRouter } from "react-router"
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import { NavLink } from 'react-router-dom'
import { userAction } from '../action'
import { isEmpty } from '../util'

class MyNavbar extends React.Component {

    render() {
        const { user, logout } = this.props;

        return (    
            <Navbar collapseOnSelect expand="md" bg="dark" variant="dark">
                <Navbar.Brand href="/">Ledger</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="mr-auto">
                        {!isEmpty(user) && <Nav.Link as={NavLink} to="/profile">Profile</Nav.Link>}
                        <Nav.Link as={NavLink} to="/dashboard">Dashboard</Nav.Link>
                    </Nav>
                    <Nav>
                        {isEmpty(user) && <Nav.Link as={NavLink} to="/signup">Sign Up</Nav.Link>}
                        {isEmpty(user) && <Nav.Link as={NavLink} to="/login">Login</Nav.Link>}
                        {!isEmpty(user) && <Nav.Link onClick={logout}>Logout</Nav.Link>}
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
        dispatch(userAction.logout());
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(MyNavbar))