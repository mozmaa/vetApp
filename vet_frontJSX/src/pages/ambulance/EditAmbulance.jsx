
import { useEffect, useState } from "react"
import { Button, Col, Form, Row } from "react-bootstrap"
import { useDispatch, useSelector } from "react-redux"
import { useNavigate, useParams } from "react-router-dom"
import { fetchAmbulanceData, editAmbulance } from "../../store/ambulance/ambulance_actions"


export default function EditAmbulance () {
    const dispatch = useDispatch()
    const navigate = useNavigate()
    const ambulancesArray = useSelector(state => state.ambulances.ambulancesArray)
    const {id} = useParams()
    const ambulance = ambulancesArray.find((amb) => String(amb.id) === String(id))
    const [editedAmbulance, setEditedAmbulance] = useState(ambulance || {})

console.log(ambulancesArray, ambulance)

    useEffect(() => {
        if(!ambulance) {
            dispatch(fetchAmbulanceData({
                name: "",
                city: "",
                isClosed: false,
                pageNo: 0,
              }));
        } else {
            setEditedAmbulance(ambulance)
        }
    }, [dispatch ,ambulance])

    const edit = () => {
        dispatch(editAmbulance(editedAmbulance.id, editedAmbulance))
            .then(() => {
                navigate(`/ambulances`);
            })
    }

    const handleAmbulanceInputChange = (event) => {
        const { name, value } = event.target;

        if(name === 'facebook' || name === 'instagram' || name === 'webSite'){
            setEditedAmbulance((prev) => ({
                ...prev, links: {...prev.links, [name]: value}
            })) 
            return
        }
        setEditedAmbulance((prev) => ({
            ...prev, [name]: value
        }))
        
    }

    const handleAddressInputChange = (event) => {
        const { name, value } = event.target;

        setEditedAmbulance((prev) => ({
            ...prev, addressDTO: {...prev.addressDTO, [name]: value}
        }))
        
    }

    if (ambulancesArray.length === 0) {
        return <div>Loading...</div>;
    }

    return (
        <Row className="justify-content-center" style={{ marginTop: 150 }}>
        <Col md={4}>
            <h1>Edit ambulance</h1>
            <Form.Group >
                <Form.Label>Ambulance name</Form.Label>
                <Form.Control type="text" name="name" value={editedAmbulance.name || ''} onChange={handleAmbulanceInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Phone number</Form.Label>
                <Form.Control type="text" name="phoneNumber" value={editedAmbulance.phoneNumber || ''} onChange={handleAmbulanceInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Mobile phone number</Form.Label>
                <Form.Control type="text" name="mobilePhoneNumber" value={editedAmbulance.mobilePhoneNumber || ''} onChange={handleAmbulanceInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Facebook Link</Form.Label>
                <Form.Control type="text" name="facebook" value={editedAmbulance.links?.facebook || ''} onChange={handleAmbulanceInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Instagram link</Form.Label>
                <Form.Control type="text" name="instagram" value={editedAmbulance.links?.instagram || ''} onChange={handleAmbulanceInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Web site</Form.Label>
                <Form.Control type="text" name="webSite" value={editedAmbulance.links?.webSite || ''} onChange={handleAmbulanceInputChange}></Form.Control>
            </Form.Group>
            <h2>Address</h2>
            <Form.Group>
                <Form.Label>Street name</Form.Label>
                <Form.Control type="text" name="streetName" value={editedAmbulance.addressDTO?.streetName || ''} onChange={handleAddressInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Building number</Form.Label>
                <Form.Control type="text" name="buildingNumber" value={editedAmbulance.addressDTO?.buildingNumber || ''} onChange={handleAddressInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Appartment number</Form.Label>
                <Form.Control type="text" name="appartmentNumber" value={editedAmbulance.addressDTO?.appartmentNumber || ''} onChange={ handleAddressInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Zip code</Form.Label>
                <Form.Control type="text" name="zipCode" value={editedAmbulance.addressDTO?.zipCode || ''} onChange={handleAddressInputChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>City</Form.Label>
                <Form.Control type="text" name="city" value={editedAmbulance.addressDTO?.city || ''} onChange={handleAddressInputChange}></Form.Control>
            </Form.Group>
            <Button style={{ marginTop: 10 }} onClick={edit} >Edit</Button>
        </Col>
    </Row>
    )
}