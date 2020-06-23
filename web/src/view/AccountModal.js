import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router'
import { Button, Modal, Form, ToggleButtonGroup, ToggleButton } from 'react-bootstrap'
import { AccountAction } from '../action'

class AccountModal extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            base: null,
            name: ""
        }
    }

    render() {

        if(this.props.account) {
            this.setState({
                base: this.props.account.base,
                name: this.props.account.name
            })
        }

        return (
            <Modal show={this.props.show} onHide={() => this.props.showModal(false)}>
                <Modal.Header>
                    <Modal.Title>계정</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form id="account-form" onSubmit={(event) => {
                            event.preventDefault();
                            console.log(this.props.handler);
                            this.props.handler({...this.state});
                        }}>
                        <Form.Group controlId="baseAccount">
                            <Form.Label>카테고리</Form.Label>                  
                            <ToggleButtonGroup  type="radio" name="base" defaultValue={this.state.base} 
                                onChange={(value) => {this.setState({base: value})}}
                            >
                                <ToggleButton variant="info" value="Asset"> Asset</ToggleButton>
                                <ToggleButton variant="info" value="Capital"> Capital</ToggleButton>
                                <ToggleButton variant="info" value="Liability"> Liability</ToggleButton>
                                <ToggleButton variant="info" value="Revenue"> Revenue</ToggleButton>
                                <ToggleButton variant="info" value="Expense"> Expense</ToggleButton>
                            </ToggleButtonGroup>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>이름</Form.Label>
                            <Form.Control type="text" value={this.state.name}
                                onChange={(event) => {this.setState({name: event.target.value})}}
                            ></Form.Control>
                        </Form.Group>
                        <Button variant="primary" type="submit">제출</Button>
                    </Form>
                </Modal.Body>
                {/* <Modal.Footer>
                    <Button variant="secondary" onClick={() => this.props.showModal(false)}>닫기</Button>
                </Modal.Footer> */}
            </Modal>
        )
    }
}

const mapStateToProps = state => ({
    show: state.account.modal.show,
    handler: state.account.modal.handler
})

const mapDispatchToProps = dispatch => ({
    showModal: (show) => {
        dispatch(AccountAction.showModal(show))
    }
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(AccountModal))