import {
  Auth,
  getAuth,
  signInWithEmailAndPassword,
  UserCredential,
} from "firebase/auth";
import Link from "next/link";
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import { useContext, useState } from "react";
import { initFirebase } from "../auth/Firebase";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Router from "next/router";
import { SessionContext } from "../context/SessionContext";
import { User } from "../types/User";
import doLogin from "../backend/Login";

const Login = () => {
  const backImage = `.back-image{
        background: url(https://www.owensboroparent.com/wp-content/uploads/2017/01/GiftofGiving.jpg);
    }`;

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const sessionContext = useContext(SessionContext);

  // inits the firebase authentication
  initFirebase();
  const auth: Auth = getAuth();

  const login = async () => {
    try {
      // starts session with the user
      const user: UserCredential = await signInWithEmailAndPassword(
        auth,
        email,
        password
      );

      // gets the user role
      const userRole: User = await doLogin(email);
      // updates the user context to the logged use
      const sessionUser: User = { user: user, role: userRole.role };
      sessionContext.setSessionUser(sessionUser);
      localStorage.setItem("user", JSON.stringify(sessionUser));

      Router.push("/home");
    } catch (e: any) {
      toast.error("An error occured while logging the user!");
    }
  };

  return (
    <>
      <ToastContainer />
      <div
        className="back-image"
        style={{
          height: "100vh",
          backgroundRepeat: "no-repeat",
          display: "flex",
          overflow: "hidden",
        }}
      >
        <div className="p-36">
          <Card className="bg-white w-96 h-auto">
            <div className="flex justify-center grid-cols-1">
              <h1 className="col-span-1 text-black">Login</h1>
            </div>
            <div className="flex justify-center grid-cols-1 pt-10">
              <span className="p-float-label col-span-1">
                <InputText
                  id="in"
                  onChange={(e) => {
                    setEmail(e.target.value);
                  }}
                  className="bg-white"
                  style={{ color: "black" }}
                />
                <label htmlFor="in" style={{ color: "black" }}>
                  Email
                </label>
              </span>
            </div>
            <div className="flex justify-center grid-cols-1 pt-10">
              <span className="p-float-label col-span-1">
                <InputText
                  id="in"
                  onChange={(e) => {
                    setPassword(e.target.value);
                  }}
                  className="bg-white"
                  style={{ color: "black" }}
                  type="password"
                />
                <label htmlFor="in" style={{ color: "black" }}>
                  Password
                </label>
              </span>
            </div>
            <div className="flex justify-center grid-cols-1 pt-10">
              <Button
                className="p-button-info"
                label="Login"
                icon="pi pi-sign-in"
                onClick={login}
              />
            </div>
            <div className="flex justify-center grid-cols-1 pt-10">
              <p className="text-black">
                If you don&apos;t have an account feel free to{" "}
                <Link href="/register">register</Link>.
              </p>
            </div>
          </Card>
        </div>
      </div>
      <style>{backImage}</style>
    </>
  );
};

export default Login;
