import { hot } from 'react-hot-loader/root'
import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router'
import { Route, Switch } from 'react-router-dom'

import { Layout } from 'antd'

import { UserAction } from '../action'
import { UrlConstant } from '../constant'

import { 
  PrivateRoute, OAuth2RedirectHandler,
} from '../component'

import {
  Home, MyHeader, MySider, Profile, AccountList
} from '../view'

import './App.sass'

const { Content } = Layout
class App extends React.Component {

  componentDidMount() {
    this.props.loadCurrentUser()
  }

  render() {
    return (
      <Layout>
        <MySider></MySider>
        <Layout>
          <MyHeader></MyHeader>
          <Content>
            <Switch>
              <Route exact path="/" component={Home}></Route>
              <PrivateRoute path="/profile" component={Profile}></PrivateRoute>
              <PrivateRoute path="/account" component={AccountList}></PrivateRoute>
              <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>
            </Switch>
          </Content>
        </Layout>
      </Layout>
    )
  }
}

const mapStateToProps = state => ({

})

const mapDispatchToProps = dispatch => ({
    loadCurrentUser: () => {
      if(localStorage.getItem(UrlConstant.ACCESS_TOKEN)) {
        dispatch(UserAction.getCurrentUserAsync());
      } 
    }
})

export default hot(withRouter(connect(mapStateToProps, mapDispatchToProps)(App)))
