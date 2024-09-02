import { useCallback, useEffect, useState,  } from "react";
import { Button, Col, Form, FormSelect, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import UserInput from "../../components/inputs/UserInput";
import { ROLES } from "../../Data.js";
import { passwordPattern } from "../../utils/validations.js";
import { userAction } from "../../store/user/user_slice.jsx";
import { registerUser } from "../../store/auth/auth_actions.jsx";



export default function SignUp() {
  const authentication = useSelector(state => state.auth)
  const user = useSelector ( state => state.user)
  const dispatch = useDispatch()
  const navigate = useNavigate()
  console.log('userState', user)
  
  const [isValidForm, setIsValidForm] = useState(false)

  useEffect(() => {
    validateForm(user);
  }, [user]);
 
  function validateForm(user) {
    const areFieldsFilled = Object.values(user).every((value) => value !== '');
    const isValid = areFieldsFilled && user.validPass && user.validRepPass;
    setIsValidForm(isValid);
  }

  const handleChange = useCallback((event) => {
    let target = event.target.name
    let value = event.target.value

    let updatedUser = {...user}

    if (target === "newPassword") {
      updatedUser = {
        ...updatedUser,
        validPass: passwordPattern.test(value),
        validRepPass: value === updatedUser.repeatedPassword,
        [target]: value,
      };
    } else if (target === "repeatedPassword") {
      updatedUser = {
        ...updatedUser,
        validRepPass: value === updatedUser.newPassword,
        [target]: value,
      };
    } else {
      updatedUser = { ...updatedUser, [target]: value };
    }

    console.log('user', updatedUser)

    dispatch(userAction.replaceUser(updatedUser))
  }, [user, dispatch])

  const register = (user, navigate) => {
    dispatch(registerUser(user, navigate))
  }

  return (
    <Row className="justify-content-center" style={{ marginTop: 75 }}>
      <Col md={4}>
        <Form>
          <UserInput label='First name' targetType='text' targetName='firstName' handleChange={handleChange}/>
          <UserInput label='Last name' targetType='text' targetName='lastName' handleChange={handleChange}/>
          <UserInput label='User name' targetType='text' targetName='userName' handleChange={handleChange}/>
          <UserInput label='eMail' targetType='email' targetName='eMail' handleChange={handleChange}/>
          <UserInput label='Password' targetName='newPassword' validPass={user.validPass} handleChange={handleChange}/>
          <UserInput label='Repeat Password' targetName='repeatedPassword' validRepPass={user.validRepPass} handleChange={handleChange}/>
          {authentication.role==='ROLE_ADMIN' && <Form.Group>
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
        <Button disabled={!isValidForm} style={{ marginTop: 10 }} onClick={() => register(user, navigate)} >
          Register
        </Button>
      </Col>
    </Row>
  );
}
