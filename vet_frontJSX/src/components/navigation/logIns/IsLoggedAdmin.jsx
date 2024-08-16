import { Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";
import { logout } from "../../../services/auth";
import logo from "../../../assets/vetAmbLogo.png.png"


export default function IsLoggedAdmin () {
    return (
        <>
      <Navbar expand bg="dark" variant="dark">
        <Navbar.Brand style={{ marginLeft: 10 }} as={Link} to="/">
          <img className="circle-img" src={logo}></img>
        </Navbar.Brand>
        <Nav className="ms-auto">
          <Nav.Link as={Link} to="/signUp" state={{isAdmin: true}}>
            <div className="icon-wrapper">
            <i className="bi bi-person-plus fs-2 "></i>
            <div className="tooltip">Register user</div>
            </div>
          </Nav.Link>
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