import { Menubar } from "primereact/menubar";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useRouter } from "next/router";
import { useContext, useEffect, useState } from "react";
import { SessionContext } from "../context/SessionContext";
import { Auth, getAuth } from "firebase/auth";
import { initFirebase } from "../auth/Firebase";

function AppMenuBar() {
  const context = useContext(SessionContext);

  // gets the session user
  useEffect(() => {
    // if it does not exist then gets the saved user in local storage and updates the context
    if (context.sessionUser) {
      const sessionUserStr: string | null = localStorage.getItem("user");
      if (sessionUserStr !== null) {
        context.setSessionUser(JSON.parse(sessionUserStr));
      } else {
        router.push("/");
      }
    }
  }, []);

  const role: string = context.sessionUser.role;
  const menuItems = [
    {
      label: "Finished Donations",
      icon: "pi pi-fw pi-check-circle",
      command: () => goToPage("/finished-donations"),
      visible: role === "donne",
    },
    {
      label: "My Donations",
      icon: "pi pi-fw pi-box",
      command: () => goToPage("/my-donations"),
      visible: role === "donor",
    },
    {
      label: "Ongoing Donations",
      icon: "pi pi-fw pi-calendar-times",
      command: () => goToPage("/ongoing-donations"),
      visible: role === "donor" || role === "donne",
    },
    {
      label: "Profile",
      icon: "pi pi-fw pi-user",
      command: () => goToPage("/profile"),
      visible: role === "donor" || role === "donne",
    },
    {
      label: "Categories",
      icon: "pi pi-fw pi-table",
      command: () => goToPage("/categories"),
      visible: role === "admin",
    },
    {
      label: "Geographic Areas",
      icon: "pi pi-fw pi-map",
      command: () => goToPage("/geographic-areas"),
      visible: role === "admin",
    },
    {
      label: "Donations",
      icon: "pi pi-fw pi-box",
      command: () => goToPage("/donations"),
      visible: role === "admin",
    },
  ];

  const router = useRouter();

  const [search, setSearch] = useState("");

  const goToPage = (page: string) => {
    router.push(page);
  };

  const goToDonations = () => {
    router.push(
      {
        pathname: "donations",
        query: { search: search },
      },
      "donations"
    );
  };

  // logouts the authenticated user
  const logout = () => {
    initFirebase();
    // removes and ends everything related to user session
    const auth: Auth = getAuth();
    auth.signOut();
    localStorage.removeItem("user");
    router.push("/");
  };

  return (
    <>
      <Menubar
        model={menuItems}
        start={
          <img
            src="/hand_heart_donate_icon.png"
            alt="image"
            className="w-2 mr-6 cursor-pointer"
            onClick={() => goToPage("/home")}
          />
        }
        end={
          <div className="flex space-x-3">
            <InputText
              onChange={(e) => setSearch(e.target.value)}
              value={search}
              placeholder="Search for a donation"
              type="text"
              className={`${role !== "donne" ? "hidden" : ""}`}
              style={{
                backgroundColor: "white",
                color: "black",
              }}
            />
            <Button
              label="Search"
              className={`${role !== "donne" ? "hidden" : ""}`}
              icon="pi pi-fw pi-search"
              onClick={() => goToDonations()}
            />
            <Button
              label="Logout"
              icon="pi pi-fw pi-arrow-circle-left"
              className="p-button-danger"
              onClick={() => logout()}
            />
          </div>
        }
      />
    </>
  );
}

export default AppMenuBar;
