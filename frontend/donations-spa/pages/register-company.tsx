import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const RegisterCompany = () => {
  return (
    <>
      <div className="flex justify-center pt-10">
        <Card className="bg-white w-8 h-30">
          <div className="grid grid-cols-3">
            <div className="w-2"></div>
            <div className="w-8 flex justify-center">
              <h2 className="text-black">Register Company</h2>
            </div>
            <div className="w-2"></div>
          </div>
          <div className="grid grid-cols-3 pt-10">
            <div className="w-2"></div>
            <div className="w-8">
              <div className="grid grid-cols-2">
                <p className="w-4 text-black text-xl">Email</p>
                <InputText
                  className="w-8 bg-white"
                  style={{ color: "black" }}
                />
              </div>
            </div>
            <div className="w-2"></div>
          </div>
          <div className="grid grid-cols-3 pt-10">
            <div className="w-2"></div>
            <div className="w-8">
              <div className="grid grid-cols-2">
                <p className="w-4 text-black text-xl">Password</p>
                <InputText
                  type={"password"}
                  className="w-8 bg-white"
                  style={{ color: "black" }}
                />
              </div>
            </div>
            <div className="w-2"></div>
          </div>
          <div className="grid grid-cols-3 pt-10">
            <div className="w-2"></div>
            <div className="w-8 flex justify-end ml-2">
              <Button
                className="bg-white p-button-info"
                label="Register"
                icon="pi pi-check"
              />
            </div>
            <div className="w-2"></div>
          </div>
        </Card>
      </div>
    </>
  );
};

export default RegisterCompany;
