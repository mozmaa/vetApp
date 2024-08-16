
import { jwtDecode } from "jwt-decode";
import "../navigation/navigation.css";

import IsLoggedOut from "./logIns/IsLoggedOut";
import IsLoggedUser from "./logIns/IsLoggedUser";
import IsLoggedAdmin from "./logIns/IsLoggedAdmin";


//helper vars for checking tokens and roles
let loggedIn = localStorage.getItem('jwt') ? jwtDecode(localStorage.getItem('jwt')) : null

const loginInfo = { 
    isAdmin: loggedIn?.role.authority === 'ROLE_ADMIN',
    isUser: loggedIn?.role.authority === 'ROLE_USER',
    name: loggedIn?.sub
}

export default function MainNavigation( ) {


  return <>
      {!loggedIn && <IsLoggedOut/>}
      {loginInfo.isUser && <IsLoggedUser/>}
      {loginInfo.isAdmin && <IsLoggedAdmin/>}
  </>;
}
