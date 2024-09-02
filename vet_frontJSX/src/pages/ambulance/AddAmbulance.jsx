import { useState } from "react"
import { Button, Col, Form,  Row } from "react-bootstrap"
import { useDispatch } from "react-redux"
import { useNavigate } from "react-router-dom"
import { registerAmulance } from "../../store/ambulance/ambulance_actions"
// import FormCheckInput from "react-bootstrap/esm/FormCheckInput"


export default function AddAmbulance () {
    const [newAmbulance, setNewAmbulance] = useState({links: {}, addressDTO: {}, closed: false})
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const add = (body, navigate) => {
        dispatch(registerAmulance(body, navigate))
    }

    const handleAmbulanceInputChange = (event) => {
        const { name, value } = event.target;

        if(name === 'facebook' || name === 'instagram' || name === 'webSite'){
            setNewAmbulance((prev) => ({
                ...prev, links: {...prev.links, [name]: value}
            })) 
            return
        }
        setNewAmbulance((prev) => ({
            ...prev, [name]: value
        }))
        
    }

    const handleAddressInputChange = (event) => {
        const { name, value } = event.target;

        setNewAmbulance((prev) => ({
            ...prev, addressDTO: {...prev.addressDTO, [name]: value}
        }))
        
    }

    return (
        <Row className="justify-content-center" style={{ marginTop: 150 }}>
        <Col md={4}>
            <h1>Add new ambulance</h1>
            <Form.Group >
                <Form.Label>Ambulance name</Form.Label>
                <Form.Control type="text" name="name" onChange={(e) => handleAmbulanceInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Phone number</Form.Label>
                <Form.Control type="text" name="phoneNumber" onChange={(e) => handleAmbulanceInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Mobile phone number</Form.Label>
                <Form.Control type="text" name="mobilePhoneNumber" onChange={(e) => handleAmbulanceInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Facebook Link</Form.Label>
                <Form.Control type="text" name="facebook" onChange={(e) => handleAmbulanceInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Instagram link</Form.Label>
                <Form.Control type="text" name="instagram" onChange={(e) => handleAmbulanceInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Web site</Form.Label>
                <Form.Control type="text" name="webSite" onChange={(e) => handleAmbulanceInputChange(e)}></Form.Control>
            </Form.Group>
            <h2>Address</h2>
            <Form.Group>
                <Form.Label>Street name</Form.Label>
                <Form.Control type="text" name="streetName" onChange={(e) => handleAddressInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Building number</Form.Label>
                <Form.Control type="text" name="buildingNumber" onChange={(e) => handleAddressInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Appartment number</Form.Label>
                <Form.Control type="text" name="appartmentNumber" onChange={(e) => handleAddressInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Zip code</Form.Label>
                <Form.Control type="text" name="zipCode" onChange={(e) => handleAddressInputChange(e)}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>City</Form.Label>
                <Form.Control type="text" name="city" onChange={(e) => handleAddressInputChange(e)}></Form.Control>
            </Form.Group>
            <Button style={{ marginTop: 10 }} onClick={() => add(newAmbulance, navigate)}>Add</Button>
        </Col>
    </Row>
    )
}