import { Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";
import { logout } from "../../../services/auth";

export default function IsLoggedUser () {
    return (
        <>
      <Navbar expand bg="dark" variant="dark">
        <Navbar.Brand style={{ marginLeft: 10 }} as={Link} to="/">
          Logo
        </Navbar.Brand>
        <Nav className="ms-auto">
            <Nav.Link onClick={logout} style={{ cursor: 'pointer' }}>
            <div className="icon-wrapper">
            <i className="bi bi-person-x fs-2"></i>
            <div className="tooltip">Log out</div>
            </div>
          </Nav.Link>
        </Nav>
      </Navbar>
    </>
    )
}