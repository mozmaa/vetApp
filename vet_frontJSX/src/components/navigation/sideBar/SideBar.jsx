
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import NavIcons from "../../icons/NavIcons";

export default function SideBar () {
    return (
        <div className="sidebar">
          <Nav className="flex-column">
            <Nav.Link as={Link} to="/addAmbulance">
              <NavIcons
                wrapperClass="icon-wrapper"
                iconClass="bi bi-building-add fs-2"
                tooltipClass="tooltip-home"
                tooltipLabel="Add ambulance"
              />
            </Nav.Link>
            {/* Add more icons/links as needed */}
          </Nav>
        </div>
      );
}