import { Outlet } from "react-router-dom";
import { useSelector } from "react-redux";

import MainNavigation from "../components/navigation/MainNavigation";
import { Fragment } from "react";

export default function RootLayout() {
  const userRole = useSelector((state) => state.auth.role);

  return (
    <Fragment>
      <MainNavigation />
      <div
        style={
          userRole === "ROLE_ADMIN" ? { paddingLeft: 70 } : { paddingLeft: 0 }
        }
      >
        <Outlet />
      </div>
    </Fragment>
  );
}
