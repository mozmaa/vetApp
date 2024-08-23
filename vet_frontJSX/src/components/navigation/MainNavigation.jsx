import { useSelector } from "react-redux";
import "../navigation/navigation.css";

import LoggedIn from "./logIns/LoggedIn";
import SideBar from "./sideBar/SideBar";
import { Fragment } from "react";

function MainNavigation() {
  const isLoggedIn = useSelector((state) => state.auth.isLoggedIn);
  console.log(isLoggedIn);

  return (
    <Fragment>
      <LoggedIn />
      {isLoggedIn && <SideBar />}
    </Fragment>
  );
}

export default MainNavigation;
