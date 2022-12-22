import { Menubar } from "primereact/menubar";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useRouter } from "next/router";
import { useContext, useEffect, useState } from "react";
import { SessionContext } from "../context/SessionContext";

function AppMenuBar() {
  const context = useContext(SessionContext);

  // gets the session user
  useEffect(() => {
    // if it does not exist then gets the saved user in local storage and updates the context
    if (context.sessionUser) {
      const sessionUserStr: string | null = localStorage.getItem("user");
      context.setSessionUser(
        JSON.parse(sessionUserStr == null ? "" : sessionUserStr)
      );
    }
  }, []);

  const role: string = context.sessionUser.role;
  const menuItems = [
    {
      label: "Home",
      icon: "pi pi-fw pi-home",
      command: () => goToPage("/donations"),
    },
    {
      label: "My Donations",
      icon: "pi pi-fw pi-box",
      command: () => goToPage("/my-donations"),
      visible: role === "donor" || role === "donne",
    },
    {
      label: "Ongoing Donations",
      icon: "pi pi-fw pi-calendar-times",
      command: () => goToPage("/ongoing-donations"),
      visible: role === "donor" || role === "donne",
    },
    {
      label: "Categories",
      icon: "pi pi-fw pi-tags",
      command: () => goToPage("/categories"),
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

  return (
    <>
      <Menubar
        model={menuItems}
        start={
          <img
            src="/hand_heart_donate_icon.png"
            alt="image"
            className="w-2 mr-6"
          />
        }
        end={
          <div className="flex space-x-2">
            <InputText
              onChange={(e) => setSearch(e.target.value)}
              value={search}
              placeholder="Search for a donation"
              type="text"
              style={{
                backgroundColor: "white",
                color: "black",
              }}
            />
            <Button
              label="Search"
              icon="pi pi-fw pi-search"
              onClick={() => goToDonations()}
            />
          </div>
        }
      />
    </>
  );
}

export default AppMenuBar;
