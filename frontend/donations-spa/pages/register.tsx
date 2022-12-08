import Router from "next/router";
import { Card } from "primereact/card";

const Register = () => {
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
    <div className="flex justify-center pt-10">
      <Card className="bg-white w-8 h-30">
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
            className="w-5 bg-white cursor-pointer"
            onClick={registerPerson}
          >
            <h2 className="text-black flex justify-center">Person</h2>
          </Card>
          <Card
            header={companyHeader}
            className="w-5 bg-white cursor-pointer"
            onClick={registerCompany}
          >
            <h2 className="text-black flex justify-center">Company</h2>
          </Card>
        </div>
      </Card>
    </div>
  );
};

export default Register;
