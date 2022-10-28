import { Menubar } from "primereact/menubar";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useRouter } from "next/router";
import { useState } from "react";

function AppMenuBar() {
  const menuItems = [
    {
      label: "Home",
      icon: "pi pi-fw pi-home",
      command: () => goToPage("/"),
    },
    {
      label: "My Donations",
      icon: "pi pi-fw pi-box",
      command: () => goToPage("my-donations"),
    },
    {
      label: "Profile",
      icon: "pi pi-fw pi-user",
      command: () => goToPage("profile"),
    },
    {
      label: "Ongoing Donations",
      icon: "pi pi-fw pi-calendar-times",
      command: () => goToPage("ongoing-donations"),
    },
    {
      label: "Categories",
      icon: "pi pi-fw pi-tags",
      command: () => goToPage("categories"),
    },
    {
      label: "Administration",
      icon: "pi pi-fw pi-cog",
      command: () => goToPage("administration"),
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
            className="w-12 mr-6"
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
