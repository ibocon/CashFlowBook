import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router'

class _ErrorView extends React.Component {

    render() {
        return (
         <div>
             <h3>{this.state.error}</h3>
         </div>   
        )
    }
}

const mapStateToProps = state => ({
    
})
  
const mapDispatchToProps = dispatch => ({

})

export const ErrorView = withRouter(connect(mapStateToProps, mapDispatchToProps)(_ErrorView))