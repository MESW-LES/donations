import { useRouter } from "next/router";
import Dashboard from "../components/Dashboard";
import AppMenuBar from "../components/AppMenuBar";
import { SessionContext } from "../context/SessionContext";
import { useContext } from "react";

const titlesMap: Map<string, string[]> = new Map([
  ["donor", ["My Donations", "Ongoing Donations", "Profile"]],
  ["donne", ["Finished Donations", "Ongoing Donations", "Profile"]],
  ["admin", ["Categories", "Geographic Areas", "Donations"]],
]);

const imagesMap: Map<string, string[]> = new Map([
  ["donor", ["box_my_donations.png", "ongoing_donations.png", "profile.png"]],
  ["donne", ["tick.png", "ongoing_donations.png", "profile.png"]],
  ["admin", ["categories.png", "map.png", "donations.png"]],
]);

const onClickMenuMap: Map<string, string[]> = new Map([
  ["donor", ["/my-donations", "/ongoing-donations", "/profile"]],
  ["donne", ["/donations", "/ongoing-donations", "/profile"]],
  ["admin", ["/categories", "/geographic-ares", "/donations"]],
]);

function DonationsHome() {
  // gets the user role in session
  const context = useContext(SessionContext);
  const role: string = context.sessionUser.role;

  // creates the dashboard menu options
  const router = useRouter();
  // gets the titles for menu
  const titles: string[] | undefined = titlesMap.get(role);
  // gets the images for menu
  const images: string[] | undefined = imagesMap.get(role);
  // gets the click events for menu
  const urls = onClickMenuMap.get(role);
  const onClickMenu1 = () => {
    router.push(urls ? urls[0] : "/");
  };
  const onClickMenu2 = () => {
    router.push(urls ? urls[1] : "/");
  };
  const onClickMenu3 = () => {
    router.push(urls ? urls[2] : "/");
  };
  const onClickMenus: any = [onClickMenu1, onClickMenu2, onClickMenu3];
  return (
    <>
      <AppMenuBar />
      <Dashboard titles={titles} images={images} onClickMenus={onClickMenus} />
    </>
  );
}

export default DonationsHome;
