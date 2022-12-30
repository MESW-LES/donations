import { Dropdown } from "primereact/dropdown";
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Donee } from "../types/Donee";
import Router from "next/router";
import doRegister from "../backend/RegisterDonee";
import {
  Auth,
  createUserWithEmailAndPassword,
  deleteUser,
  getAuth,
  UserCredential,
} from "firebase/auth";
import { initFirebase } from "../auth/Firebase";
import axios from "axios";

const RegisterCompany = () => {
  const [taxnumber, setTaxnumber] = useState("");
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [category, setcategory] = useState<any>();

  const categories = [
    { label: "Cat-A", value: "Cat-A" },
    { label: "Cateogry example 1", value: "2" },
  ];

  const onCategory = (event: any) => {
    console.log("Entrei Cate");
    let temp: string[] = [];
    temp.push(event.value);
    setcategory(temp);
  };
  // inits the firebase authentication
  initFirebase();
  const auth: Auth = getAuth();

  // creates the user in firebase auth
  const createUserFirebase = async () => {
    try {
      // creates the backend donor
      const formValues = {
        company: { taxnumber, name, description, phone, email },
        password,
        ["categoryCodes"]: category,
      };

      try {
        const { data } = await axios({
          url: "/api/formregistercompany",
          method: "POST",
          data: formValues,
        });

        if (data.code != 200) {
        } else {
          setName("");
          setTaxnumber("");
          setDescription("");
        }
      } catch (error) {
        if (error instanceof Error) {
        }
      }
      // creates the firebase user
      const user: UserCredential = await createUserWithEmailAndPassword(
        auth,
        email,
        password
      );

      // signs out the user
      await auth.signOut();
      toast.success("User registered with success!");
    } catch (error: any) {
      toast.error("An error occured while registering the user!");
    }
  };
  const backImage = `.back-image{
    background: url(https://www.owensboroparent.com/wp-content/uploads/2017/01/GiftofGiving.jpg);
  }`;

  // creates the user
  const createUser = async () => {
    let user: UserCredential | undefined = undefined;
    try {
      // creates the firebase user
      user = await createUserWithEmailAndPassword(auth, email, password);

      // creates the backend donee
      const donee: Donee = {
        company: {
          name: "Teste",
          description: "Teste",
          taxNumber: "123456789",
          phone: "911111111",
          email: email,
        },
        categoryCodes: ["CAT-A"],
      };
      // creates the user in donations app
      await doRegister(donee);

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
        <Card className="bg-white w-8 h-screen overflow-auto">
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
                <p className="w-4 text-black text-xl">Name</p>
                <InputText
                  className="w-8 bg-white"
                  style={{ color: "black" }}
                  value={name}
                  onChange={({ target }) => setName(target?.value)}
                />
              </div>
            </div>
            <div className="w-2"></div>
          </div>
          <div className="grid grid-cols-3 pt-10">
            <div className="w-2"></div>
            <div className="w-8">
              <div className="grid grid-cols-2">
                <p className="w-4 text-black text-xl">Description</p>
                <InputText
                  className="w-8 bg-white"
                  style={{ color: "black" }}
                  value={description}
                  onChange={({ target }) => setDescription(target?.value)}
                />
              </div>
            </div>
            <div className="w-2"></div>
          </div>
          <div className="grid grid-cols-3 pt-10">
            <div className="w-2"></div>
            <div className="w-8">
              <div className="grid grid-cols-2">
                <p className="w-4 text-black text-xl">Tax Number</p>
                <InputText
                  required
                  className="w-8 bg-white"
                  style={{ color: "black" }}
                  value={taxnumber}
                  onChange={({ target }) => setTaxnumber(target?.value)}
                />
              </div>
            </div>
            <div className="w-2"></div>
          </div>
          <div className="grid grid-cols-3 pt-10">
            <div className="w-2"></div>
            <div className="w-8">
              <div className="grid grid-cols-2">
                <p className="w-4 text-black text-xl">Phone</p>
                <InputText
                  className="w-8 bg-white"
                  style={{ color: "black" }}
                  value={phone}
                  onChange={({ target }) => setPhone(target?.value)}
                />
              </div>
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
                  onChange={({ target }) => setEmail(target?.value)}
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
                  onChange={({ target }) => setPassword(target?.value)}
                />
              </div>
            </div>
            <div className="w-2"></div>
          </div>
          <div className="grid grid-cols-3 pt-10">
            <div className="w-2"></div>
            <div className="w-8">
              <div className="grid grid-cols-2">
                <p className="w-4 text-black text-xl">Category</p>
                <Dropdown
                  options={categories}
                  optionLabel="label"
                  placeholder="Categories"
                  className="w-2/3"
                />
              </div>
            </div>
            <div className="w-2"></div>
          </div>
          <div className="grid grid-cols-3 pt-10">
            <div className="w-2"></div>
            <div className="w-8 flex justify-end ml-2">
              <Button
                className="p-button-info"
                label="Register"
                icon="pi pi-check"
                onClick={createUserFirebase}
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

export default RegisterCompany;
