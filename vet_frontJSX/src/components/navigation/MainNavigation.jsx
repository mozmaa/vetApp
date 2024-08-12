import { Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";
// import { logout } from "../../services/auth";

export default function MainNavigation() {
  return (
    <>
      <Navbar expand bg="dark" variant="dark">
        <Navbar.Brand style={{ marginLeft: 10 }} as={Link} to="/">
          Logo
        </Navbar.Brand>
        <Nav>
          <Nav.Link as={Link} to="/login">
            Sing in
          </Nav.Link>
          <Nav.Link as={Link} to="/signUp">
            Sing up
          </Nav.Link>
        </Nav>
      </Navbar>
    </>
  );
}
