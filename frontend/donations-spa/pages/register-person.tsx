import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import { initFirebase } from "../auth/Firebase";
import {
  getAuth,
  createUserWithEmailAndPassword,
  UserCredential,
  Auth,
  sendEmailVerification,
  deleteUser,
} from "firebase/auth";
import { useRef, useState } from "react";
import { toast } from "react-toastify";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Router from "next/router";
import doRegister from "../backend/RegisterDonor";
import { Donor } from "../types/Donor";

const RegisterPerson = () => {
  const backImage = `.back-image{
    background: url(https://www.owensboroparent.com/wp-content/uploads/2017/01/GiftofGiving.jpg);
  }`;

  // email and password
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  // inits the firebase authentication
  initFirebase();
  const auth: Auth = getAuth();

  // creates the user
  const createUser = async () => {
    let user: UserCredential | undefined = undefined;
    try {
      // creates the firebase user
      user = await createUserWithEmailAndPassword(auth, email, password);

      // creates the backend donor
      const donor: Donor = {
        person: {
          firstName: "Teste",
          lastName: "Teste",
          nif: "123456789",
          address: "teste",
          email: email,
        },
      };
      // creates the user in donations app
      await doRegister(donor);

      // signs out the user
      await auth.signOut();
      toast.success("User registered with success!");
      setTimeout(() => {
        Router.push("/");
      }, 2000);
    } catch (error: any) {
      // deletes user from firebase if some error occurs in donatiosn backend
      if (user) {
        deleteUser(user.user);
      }
      toast.error("An error occured while registering the user!");
    }
  };

  return (
    <>
      <ToastContainer />
      <div
        className="back-image flex justify-center pt-10"
        style={{
          height: "100vh",
          backgroundRepeat: "no-repeat",
          overflow: "hidden",
        }}
      >
        <Card className="bg-white w-8 h-96">
          <div className="grid grid-cols-3">
            <div className="w-2"></div>
            <div className="w-8 flex justify-center">
              <h2 className="text-black">Register Person</h2>
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
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
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
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
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
                onClick={createUser}
              />
            </div>
            <div className="w-2"></div>
          </div>
        </Card>
        <style>{backImage}</style>
      </div>
    </>
  );
};

export default RegisterPerson;
