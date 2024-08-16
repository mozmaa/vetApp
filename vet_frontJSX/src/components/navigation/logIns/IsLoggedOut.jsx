import { Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";

export default function IsLoggedOut () {
    return (
        <>
      <Navbar expand bg="dark" variant="dark">
        <Navbar.Brand style={{ marginLeft: 10 }} as={Link} to="/">
          Logo
        </Navbar.Brand>
        <Nav className="ms-auto">
          <Nav.Link as={Link} to="/signIn">
            <div className="icon-wrapper">
            <i className="bi bi-person-up fs-2 " ></i>
            <div className="tooltip">Log in</div>
            </div>
          </Nav.Link>
          <Nav.Link as={Link} to="/signUp">
            <div className="icon-wrapper">
            <i className="bi bi-person-plus fs-2 "></i>
            <div className="tooltip">Register</div>
            </div>
          </Nav.Link>
        </Nav>
      </Navbar>
    </>
    )
}