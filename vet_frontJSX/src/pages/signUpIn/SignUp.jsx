import { useState,  } from "react";
import { Button, Col, Form, FormSelect, Row } from "react-bootstrap";
import { passwordPattern } from "../../utils/validations";
import Axios from "../../apis/Axios";
import { useLocation } from "react-router-dom";
import { ROLES } from "../../Data.js";
import UserInput from "../../components/inputs/UserInput";
import { useNavigate } from 'react-router-dom';


export default function SignUp() {
  const location = useLocation();
  const isAdmin = location.state?.isAdmin;
  const navigate = useNavigate()
  
  const [newUser, setNewUser] = useState({
    validPass: true,
    validRepPass: true,
    role: 'USER'
  });

  console.log(newUser)
  
  function handleChange(event) {
    let target = event.target.name;
    let value = event.target.value;
    if (target === "newPassword") {
      setNewUser((prevUser) => {
        let updatedUser = {
          ...prevUser,
          validPass: passwordPattern.test(value),
          validRepPass: value === prevUser.repeatedPassword,
          [target]: value,
        };
        return updatedUser;
      });
    } else if (target === "repeatedPassword") {
      setNewUser((prevUser) => {
        let updatedUser = {
          ...prevUser,
          validRepPass: value === prevUser.newPassword,
          [target]: value,
        };
        return updatedUser;
      });
    } else {
      setNewUser((prevUser) => {
        let updatedUser = { ...prevUser, [target]: value };
        return updatedUser;
      });
    }
  }

  function handleClick() {
    Axios.post("/users", newUser)
      .then((res) => {
        console.log(res);
        navigate('/' , {replace: true})
      })
      .catch((error) => {
        console.log(error);
        alert("Doslo je do greske");
      });
  }

  console.log(newUser.validPass)

  return (
    <Row className="justify-content-center" style={{ marginTop: 75 }}>
      <Col md={4}>
        <Form>
          {/* input fields for creating user */}
          <UserInput label='First name' targetType='text' targetName='firstName' handleChange={handleChange}/>
          <UserInput label='Last name' targetType='text' targetName='lastName' handleChange={handleChange}/>
          <UserInput label='User name' targetType='text' targetName='userName' handleChange={handleChange}/>
          <UserInput label='eMail' targetType='email' targetName='eMail' handleChange={handleChange}/>
          <UserInput label='Password' targetName='newPassword' validPass={newUser.validPass} handleChange={handleChange}/>
          <UserInput label='Password' targetName='repeatedPassword' validRepPass={newUser.validRepPass} handleChange={handleChange}/>
          {isAdmin && <Form.Group>
                <Form.Label>Role</Form.Label>
                <FormSelect name="role" onChange={handleChange}>
                    <option value={'USER'}>Chose a role</option>
                    {ROLES.map((role, index)=> {
                        return(
                            <option key={index} value={role}>{role}</option>
                        )
                    })
                }   
                </FormSelect>
            </Form.Group>}
        </Form>
        <Button style={{ marginTop: 10 }} onClick={handleClick}>
          Sign up
        </Button>
      </Col>
    </Row>
  );
}
