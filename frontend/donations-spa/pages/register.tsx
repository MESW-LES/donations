import Router from "next/router";
import { Card } from "primereact/card";

const Register = () => {
  const backImage = `.back-image{
    background: url(https://www.owensboroparent.com/wp-content/uploads/2017/01/GiftofGiving.jpg);
  }`;
  const personHeader = (
    <img
      alt="Card"
      src="https://cdn-icons-png.flaticon.com/512/5526/5526465.png"
    />
  );
  const companyHeader = (
    <img
      alt="Card"
      src="https://cdn-icons-png.flaticon.com/512/2083/2083417.png"
    />
  );

  const registerPerson = () => {
    Router.push("/register-person");
  };

  const registerCompany = () => {
    Router.push("/register-company");
  };

  return (
    <div
      className="back-image flex justify-center pt-10"
      style={{
        height: "100vh",
        backgroundRepeat: "no-repeat",
        overflow: "hidden",
      }}
    >
      <Card className="bg-white w-8 h-20">
        <div className="grid grid-cols-3">
          <div className="w-2"></div>
          <div className="w-8 flex justify-center">
            <h2 className="text-black">Welcome to Donations, are you a...</h2>
          </div>
          <div className="w-2"></div>
        </div>
        <div className="grid grid-cols-2 flex justify-center gap-12 pt-10">
          <Card
            header={personHeader}
            className="w-5 bg-white cursor-pointer p-6"
            onClick={registerPerson}
          >
            <h2 className="text-black flex justify-center">Person</h2>
          </Card>
          <Card
            header={companyHeader}
            className="w-5 bg-white cursor-pointer p-6"
            onClick={registerCompany}
          >
            <h2 className="text-black flex justify-center">Company</h2>
          </Card>
        </div>
      </Card>
      <style>{backImage}</style>
    </div>
  );
};

export default Register;
