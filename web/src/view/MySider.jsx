import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from "react-router"
import { Link } from 'react-router-dom'

import { Layout, Menu } from 'antd'
import {
    PieChartOutlined,
    DesktopOutlined,
} from '@ant-design/icons'

import { isEmpty } from '../util'

const { Sider } = Layout

class _MySider extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            collapsed: false,
        }

        this.onCollapse = this.onCollapse.bind(this)
    }

    onCollapse(collapsed) {
        this.setState({ collapsed })
    }

    render() {
        const { user } = this.props;

        return (
            <Sider collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
                <div className="logo" />
                <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                    {
                        !isEmpty(user) &&
                        <Menu.Item key="1" icon={<PieChartOutlined />}>
                            <span>프로필</span>
                            <Link to="/profile" />
                        </Menu.Item>
                    }
                    {   
                        !isEmpty(user) &&
                        <Menu.Item key="2" icon={<DesktopOutlined />}>
                            <span>계정과목</span>
                            <Link to="/account" />
                        </Menu.Item>
                    }
                </Menu>
            </Sider>
        )
    }
}

const mapStateToProps = state => ({
    user: state.user
})

const mapDispatchToProps = dispatch => ({

})

export const MySider = withRouter(connect(mapStateToProps, mapDispatchToProps)(_MySider))