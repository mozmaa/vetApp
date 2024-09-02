import { Button } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { deleteAmbulanceById } from "../../store/ambulance/ambulance_actions";

export default function AmbulanceRow() {
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const ambulances = useSelector((state) => state.ambulances);
  const { ambulancesArray } = ambulances;
  console.log(ambulances)

  const formatAddress = (address) => {
    const { streetName, buildingNumber, appartmentNumber, zipCode, city } =
      address;
    return `${streetName} ${buildingNumber}${
      appartmentNumber ? "/" + appartmentNumber : ""
    }, ${zipCode} ${city}`;
  };

  const deleteAmbulance = (id, navigate) => {
    dispatch(deleteAmbulanceById(id, navigate))
  }

  if (!Array.isArray(ambulancesArray)) {
    return null;
  }

  return (
    <tbody>
      {ambulancesArray.map((ambulance) => (
        <tr key={ambulance.id}>
          <td></td>
          <td><Link to={`/ambulances/${ambulance.id}/edit`}>{ambulance.name}</Link></td>
          <td>
            <ul>
              <li>{ambulance.mobilePhoneNumber}</li>
              <li>{ambulance.phoneNumber}</li>
              
            </ul>
          </td>
          <td>
            {Object.keys(ambulance.links).length !== 0 && <ul>
              <li>
                {ambulance.links.facebook && (
                  <div>
                    Facebook:{" "}
                    <a
                      href={ambulance.links.facebook}
                      target="_blank"
                      rel="noopener noreferrer"
                    > <br></br>
                      {ambulance.links.facebook}
                    </a>
                  </div>
                )}
              </li>
              <li>
                {ambulance.links.instagram && (
                  <div>
                    Instagram:{" "}
                    <a
                      href={ambulance.links.instagram}
                      target="_blank"
                      rel="noopener noreferrer"
                    ><br></br>
                      {ambulance.links.instagram}
                    </a>
                  </div>
                )}
              </li>
              <li>
                {ambulance.links.webSite && (
                  <div>
                    Web Site:{" "}
                    <a
                      href={ambulance.links.webSite}
                      target="_blank"
                      rel="noopener noreferrer"
                    ><br></br>
                      {ambulance.links.webSite}
                    </a>
                  </div>
                )}
              </li>
            </ul>}
          </td>
          <td>{formatAddress(ambulance.addressDTO)}</td>
          <td><Button onClick={() => deleteAmbulance(ambulance.id, navigate)}>Delete</Button></td>
        </tr>
      ))}
    </tbody>
  );
}
