import { useRouter } from "next/router";
import AppMenuBar from "./AppMenuBar";

function Donations() {
  const router = useRouter();
  return (
    <>
      <AppMenuBar />
      <h1>Donations with search term of: {router.query.search}</h1>
    </>
  );
}

export default Donations;
