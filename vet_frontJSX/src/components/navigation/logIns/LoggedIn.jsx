import { Nav, Navbar } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Fragment } from "react";

import NavIcons from "../../icons/NavIcons";
import logo from "../../../assets/vetAmbLogo.png.png";
import { logoutUser } from "../../../store/auth/auth_actions";

function LoggedIn() {
  const user = useSelector(state => state.auth)
  const { role, isLoggedIn } = user
  const dispatch = useDispatch()
  const navigate = useNavigate()

  const logout = () => {
    dispatch(logoutUser(navigate))
  }

  const visibleToAll = (
    <Fragment>
      <Navbar.Brand style={{ marginLeft: 10 }} as={Link} to="/">
        <div className="icon-wrapper">
          <img className="circle-img" src={logo}></img>
          <div className="tooltip-home">Home</div>
        </div>
      </Navbar.Brand>
      <Nav>
        <Nav.Link as={Link} to="/ambulances">
          <NavIcons
            wrapperClass="icon-wrapper"
            iconClass="bi bi-building fs-2"
            tooltipClass="tooltip"
            tooltipLabel="Ambulances"
          ></NavIcons>
        </Nav.Link>
      </Nav>
    </Fragment>
  );

  const logOut = (
    <Nav.Link onClick={logout} style={{ cursor: "pointer" }}>
      <NavIcons
        wrapperClass="icon-wrapper"
        iconClass="bi bi-person-x fs-2"
        tooltipClass="tooltip"
        tooltipLabel="Log out"
      ></NavIcons>
    </Nav.Link>
  );

  const signIn = (
    <Fragment>
      <Nav.Link as={Link} to="/signIn">
        <NavIcons
          wrapperClass="icon-wrapper"
          iconClass="bi bi-person-up fs-2"
          tooltipClass="tooltip"
          tooltipLabel="Log in"
        ></NavIcons>
      </Nav.Link>
    </Fragment>
  );

  const signUp = (
    <Fragment>
      <Nav.Link as={Link} to="/signUp">
        <NavIcons
          wrapperClass="icon-wrapper"
          iconClass="bi bi-person-plus fs-2"
          tooltipClass="tooltip"
          tooltipLabel="Register"
        ></NavIcons>
      </Nav.Link>
    </Fragment>
  );

  const signUpUserAsAdmin = (
    <Fragment>
      <Nav.Link as={Link} to="/signUp" >
        <NavIcons
          wrapperClass="icon-wrapper"
          iconClass="bi bi-person-plus fs-2"
          tooltipClass="tooltip"
          tooltipLabel="Register user"
        ></NavIcons>
      </Nav.Link>
    </Fragment>
  );

  return (
    <Fragment>
      <Navbar expand bg="dark" variant="dark" style={{zIndex:1100}}>
        {visibleToAll}
        <Nav className="ms-auto">
          {role === 'ROLE_ADMIN' && signUpUserAsAdmin}
          {!isLoggedIn && signIn}
          {!isLoggedIn && signUp}
          {(role === 'ROLE_ADMIN' || role === 'ROLE_USER') && logOut}
        </Nav>
      </Navbar>
    </Fragment>
  );
}

export default LoggedIn;
