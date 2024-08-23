import { useState} from "react"
import { Row, Col, Form, Button } from "react-bootstrap"
// import { login } from '../../services/auth.jsx'
import { useDispatch } from "react-redux"
import {authenticateUser} from '../../store/auth_actions.jsx'
import { useNavigate } from "react-router-dom"


export default function SignIn () {
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [user, setUser] = useState({})

    const login = () => {
        dispatch(authenticateUser(user , navigate))
    }

    function handleChange (e) {
        console.log('change' , e.target.value)
        setUser({...user, [e.target.name]: e.target.value})
    }

    return(
        <Row className="justify-content-center" style={{ marginTop: 75 }}>
            <Col md={4}>
            <Form>
                <Form.Group>
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" name='userName' onChange={handleChange}></Form.Control>
                </Form.Group>
                <Form.Group>
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" name="password" onChange={handleChange}></Form.Control>
                </Form.Group>
            </Form>
            <Button style={{marginTop:10}} onClick={()=>login(user)}>Sign in</Button>
            </Col>
        </Row>
    )
}