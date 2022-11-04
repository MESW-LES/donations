import type { NextPage } from "next";
import HomeContent from "./HomeContent";
import AppMenuBar from "./AppMenuBar";

const Home: NextPage = () => {
  return (
    <>
      <AppMenuBar />
      <HomeContent />
    </>
  );
};

export default Home;
