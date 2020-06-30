import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router'

class Account extends React.Component {

    render() {
        return (
         <div>
            <p>{this.props.account.base}</p> 
            <p>{this.props.account.name}</p>
         </div>   
        )
    }
}

const mapStateToProps = state => ({
    
})
  
const mapDispatchToProps = dispatch => ({

})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Account))