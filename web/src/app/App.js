import { hot } from 'react-hot-loader/root'
import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router'
import { Route, Switch } from 'react-router-dom'

import { Layout, Breadcrumb } from 'antd'
import 'antd/dist/antd.css'

import { UserAction } from '../action'
import { userService } from '../service'

import { 
  PrivateRoute, OAuth2RedirectHandler,
} from '../component'

import {
  Home, MyHeader, MySider, ProfileView, AccountList, ErrorView
} from '../view'

import './App.sass'

const { Content, Footer } = Layout
class _App extends React.Component {

  componentDidMount() {
    this.props.loadCurrentUser();
  }

  render() {
    return (
      <Layout style={{ minHeight: '100vh' }}>
        <MySider></MySider>
        <Layout className="site-layout">
          <MyHeader></MyHeader>
          <Content style={{ margin: '0 16px'}}>
            <Breadcrumb style={{ margin: '16px 0' }}>
                <Breadcrumb.Item>Bread</Breadcrumb.Item>
                <Breadcrumb.Item>Crumb</Breadcrumb.Item>
            </Breadcrumb>
            <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
              <Switch>
                <Route exact path="/" component={Home} />
                <PrivateRoute path="/profile" component={ProfileView} />
                <PrivateRoute path="/account" component={AccountList} />
                <Route path="/oauth2/redirect" component={OAuth2RedirectHandler} />
                <Route path="/error" component={ErrorView} />
              </Switch>
            </div>
          </Content>
          <Footer style={{ textAlign: 'center' }}>Ledger ©2020 Created by Yegun Kim</Footer>
        </Layout>
      </Layout>
    )
  }
}

const mapStateToProps = state => ({

})

const mapDispatchToProps = dispatch => ({
    loadCurrentUser: async () => {
      const user = await userService.getCurrentUser();
      dispatch(UserAction.SetCurrentUser(user));
    }
})

export const App = hot(withRouter(connect(mapStateToProps, mapDispatchToProps)(_App)))
