import { useState } from "react";
import { Form } from "react-bootstrap";
import PropTypes from "prop-types";

function UserInput({ label, targetName, targetType, handleChange, validPass, validRepPass }) {

    const [showPassword, setShowPassword] = useState({
        newPassword: false,
        repeatedPassword: false,
      });
    
      const togglePasswordVisibility = (pass) => {
        if (pass === "newPass") {
          setShowPassword((prev) => {
            let updatedShowPassword = {
              ...prev,
              newPassword: !showPassword.newPassword,
            };
            return updatedShowPassword;
          });
        } else if (pass === "repPass") {
          setShowPassword((prev) => {
            let updatedShowPassword = {
              ...prev,
              repeatedPassword: !showPassword.repeatedPassword,
            };
            return updatedShowPassword;
          });
        }
      };

    const password = (
    <Form.Group>
      <Form.Label>Password</Form.Label>
      <div className="position-relative">
        <Form.Control
          type={showPassword.newPassword ? "text" : "password"}
          name={targetName}
          onChange={handleChange}
        ></Form.Control>
        <i
          className={`bi ${
            showPassword.newPassword ? "bi-eye-slash" : "bi-eye"
          } eye-icon`}
          onClick={() => togglePasswordVisibility("newPass")}
        ></i>
      </div>
      {!validPass && (
        <p style={{ color: "red" }}>
          Password must be 12-20 characters long and include uppercase,
          lowercase, a number, and a special character.
        </p>
      )}
    </Form.Group>
  )
  
  const repPass = (
    
    <Form.Group>
    <Form.Label>Repeat password</Form.Label>
    <div className="position-relative">
      <Form.Control
        type={showPassword.repeatedPassword ? "text" : "password"}
        name="repeatedPassword"
        onChange={handleChange}
      ></Form.Control>
      <i
        className={`bi ${
          showPassword.repeatedPassword ? "bi-eye-slash" : "bi-eye"
        } eye-icon`}
        onClick={() => togglePasswordVisibility("repPass")}
      ></i>
    </div>
    {!validRepPass && (
      <p style={{ color: "red" }}>Passowrds don&apos;t match</p>
    )}
  </Form.Group>
  )

  if (targetName === "newPassword") {
    return password    
  } else if (targetName ==="repeatedPassword") {
    return repPass
  } else{
    return (
      <>
        <Form.Group>
          <Form.Label>{label}</Form.Label>
          <Form.Control
            type={targetType}
            name={targetName}
            onChange={handleChange}
          ></Form.Control>
        </Form.Group>
      </>
    );
  }
}

UserInput.propTypes = {
    label: PropTypes.string.isRequired,
    targetName: PropTypes.string.isRequired,
    targetType: PropTypes.string, 
    validPass: PropTypes.bool,
    validRepPass: PropTypes.bool,
    handleChange: PropTypes.func.isRequired
  };

  export default UserInput;
